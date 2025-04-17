package org.wolf.chestnut.world;

import org.wolf.chestnut.vector.Vec3;
import org.wolf.chestnut.vector.Vec4;
import org.wolf.chestnut.vector.IVec3;

import org.wolf.chestnut.graphics.Shader;

import org.wolf.chestnut.comp.VoxelHandler;
import org.wolf.chestnut.comp.ObjectHandler;

import org.wolf.chestnut.util.HitResult;

import java.util.ArrayList;

public class InfiniteVoxelGrid {
    public class Chunk {
        private VoxelGrid grid;
        private Object object = null;
        private IVec3 position;
        private Shader shader;
        private VoxelHandler voxelHandler;

        public Chunk(IVec3 _position, Shader _shader, VoxelHandler _voxelHandler, Generator generator) {
            position = _position;
            shader = _shader;
            voxelHandler = _voxelHandler;

            if(generator != null) {
                Vec4[] voxels = new Vec4[34 * 34 * 34];
                for(int x = 0; x < 34; x++) {
                    for(int y = 0; y < 34; y++) {
                        for(int z = 0; z < 34; z++) {
                            voxels[x + 34 * y + 34 * 34 * z] = generator.generate(IVec3.add(new IVec3(x, y, z).sub(1), position.mul(32)));
                        }
                    }
                }

                grid = new VoxelGrid(new IVec3(34, 34, 34), voxels);
            } else {
                grid = new VoxelGrid(new IVec3(34, 34, 34));
            }

            object = new Object(
                voxelHandler.generateEdgeless(grid),
                shader,
                position.toVec3().mul(3.2f).sub(0.1f),
                new Vec3(0f, 0f, 0f),
                new Vec3(0.1f, 0.1f, 0.1f)
            );
        }

        public Vec4 getVoxel(IVec3 _position) {
            return grid.getVoxel(_position.add(1));
        }

        public void setVoxel(IVec3 _position, Vec4 color) {
            grid.setVoxel(_position.add(1), color);
            object.setMesh(voxelHandler.generateEdgeless(grid));
        }

        public VoxelGrid getGrid() {
            return grid;
        }

        public Object getObject() {
            return object;
        }

        public IVec3 getPosition() {
            return position;
        }
    }

    public abstract class Generator {
        public abstract Vec4 generate(IVec3 position);
    }

    private ArrayList<Chunk> chunks;
    private ArrayList<Chunk> rendered;

    private ObjectHandler objectHandler;
    private VoxelHandler voxelHandler;
    private Shader shader;

    private int renderDistance;
    private Generator generator;

    private int generationsAvailable;

    public InfiniteVoxelGrid(ObjectHandler _objectHandler, VoxelHandler _voxelHandler, Shader _shader, int _renderDistance) {
        chunks = new ArrayList<Chunk>();
        rendered = new ArrayList<Chunk>();

        objectHandler = _objectHandler;
        voxelHandler = _voxelHandler;
        shader = _shader;

        renderDistance = _renderDistance;
        generator = null;
    }

    public void setGenerator(Generator _generator) {
        generator = _generator;
    }

    public void update(Vec3 cameraPosition) {
        generationsAvailable = 2;

        for(int i = 0; i < rendered.size(); i++) {
            objectHandler.removeObject(rendered.get(i).getObject());
        }

        rendered = new ArrayList<Chunk>();

        IVec3 position = cameraPosition.div(3.2f).toIVec3();

        for(int x = -renderDistance; x < renderDistance; x++) {
            for(int y = -renderDistance; y < renderDistance; y++) {
                for(int z = -renderDistance; z < renderDistance; z++) {
                    if(new IVec3(x, y, z).toVec3().length() <= renderDistance) {
                        Chunk chunk = getChunk(IVec3.add(new IVec3(x, y, z), position));

                        if(chunk != null) {
                            rendered.add(chunk);
                            objectHandler.addObject(chunk.getObject());
                        }
                    }
                }
            }
        }
    }

    public HitResult raycast(Vec3 cameraPosition, Vec3 cameraForward) {
        HitResult best = new HitResult(false, -1f, new IVec3(-1, -1, -1));
        Chunk bestChunk = null;

        for(int i = 0; i < rendered.size(); i++) {
            HitResult result = rendered.get(i).getGrid().raycast(
                cameraPosition,
                cameraForward,
                rendered.get(i).getObject().getPosition(),
                rendered.get(i).getObject().getRotation(),
                rendered.get(i).getObject().getScale()
            );

            if(result.getHit() && result.getDistance() < best.getDistance() || best.getHit() == false) {
                best = result;
                bestChunk = rendered.get(i);
            }
        }

        return new HitResult(
            best.getHit(),
            best.getDistance(),
            IVec3.add(best.getPosition().sub(1), best.getHit() ? bestChunk.getPosition().mul(32) : new IVec3(0, 0, 0))
        );
    }

    private Chunk getChunk(IVec3 position) {
        for(int i = 0; i < chunks.size(); i++) {
            if(IVec3.equals(chunks.get(i).getPosition(), position)) {
                return chunks.get(i);
            }
        }

        if(generationsAvailable == 0) {
            return null;
        }
        generationsAvailable--;

        Chunk chunk = new Chunk(position, shader, voxelHandler, generator);

        chunks.add(chunk);
        return chunk;
    }

    public Vec4 getVoxel(IVec3 position) {
        Chunk chunk = getChunk(position.div(32));
        if(chunk != null) return chunk.getVoxel(IVec3.sub(position, position.div(32).mul(32)));
        return new Vec4(0f, 0f, 0f, 0f);
    }

    public void setVoxel(IVec3 position, Vec4 color) {
        IVec3 pos = IVec3.sub(position, position.div(32).mul(32));

        Chunk chunk = getChunk(position.div(32));
        if(chunk != null) {
            chunk.setVoxel(pos, color);
            if(pos.getX() == 0) {
                chunk = getChunk(IVec3.add(position.div(32), new IVec3(-1, 0, 0)));
                chunk.setVoxel(new IVec3(32, pos.getY(), pos.getZ()), color);
            }
            if(pos.getX() == 31) {
                chunk = getChunk(IVec3.add(position.div(32), new IVec3(1, 0, 0)));
                chunk.setVoxel(new IVec3(-1, pos.getY(), pos.getZ()), color);
            }
            if(pos.getY() == 0) {
                chunk = getChunk(IVec3.add(position.div(32), new IVec3(0, -1, 0)));
                chunk.setVoxel(new IVec3(pos.getX(), 32, pos.getZ()), color);
            }
            if(pos.getY() == 31) {
                chunk = getChunk(IVec3.add(position.div(32), new IVec3(0, 1, 0)));
                chunk.setVoxel(new IVec3(pos.getX(), -1, pos.getZ()), color);
            }
            if(pos.getZ() == 0) {
                chunk = getChunk(IVec3.add(position.div(32), new IVec3(0, 0, -1)));
                chunk.setVoxel(new IVec3(pos.getX(), pos.getY(), 32), color);
            }
            if(pos.getZ() == 31) {
                chunk = getChunk(IVec3.add(position.div(32), new IVec3(0, 0, 1)));
                chunk.setVoxel(new IVec3(pos.getX(), pos.getY(), -1), color);
            }
        }
    }
}