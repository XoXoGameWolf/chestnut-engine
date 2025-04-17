package org.wolf.chestnut.comp;

import org.wolf.chestnut.world.VoxelGrid;
import org.wolf.chestnut.graphics.Mesh;
import org.wolf.chestnut.graphics.Buffer;
import org.wolf.chestnut.vector.Vec3;
import org.wolf.chestnut.vector.Vec4;
import org.wolf.chestnut.vector.IVec3;

import java.util.ArrayList;

public class VoxelHandler {
    Renderer renderer;

    public VoxelHandler(Renderer _renderer) {
        renderer = _renderer;
    }

    public Mesh generate(VoxelGrid grid) {
        ArrayList<Vec3> vertices = new ArrayList<Vec3>();
        ArrayList<Vec4> colors = new ArrayList<Vec4>();
        ArrayList<Vec3> normals = new ArrayList<Vec3>();
        ArrayList<Integer> indices = new ArrayList<Integer>();

        for(int x = 0; x < grid.getSize().getX(); x++) {
            for(int y = 0; y < grid.getSize().getY(); y++) {
                for(int z = 0; z < grid.getSize().getZ(); z++) {
                    Vec4 color = grid.getVoxel(new IVec3(x, y, z));

                    if(color.getW() > grid.getVoxel(new IVec3(x - 1, y, z)).getW()) {
                        vertices.add(new Vec3((float)x, (float)y,     (float)z    ));
                        vertices.add(new Vec3((float)x, (float)y,     (float)z + 1));
                        vertices.add(new Vec3((float)x, (float)y + 1, (float)z    ));
                        vertices.add(new Vec3((float)x, (float)y + 1, (float)z + 1));
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        normals.add(new Vec3(-1f, 0f, 0f));
                        normals.add(new Vec3(-1f, 0f, 0f));
                        normals.add(new Vec3(-1f, 0f, 0f));
                        normals.add(new Vec3(-1f, 0f, 0f));
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 1);
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 2);
                    }

                    if(color.getW() > grid.getVoxel(new IVec3(x + 1, y, z)).getW()) {
                        vertices.add(new Vec3((float)x + 1, (float)y,     (float)z    ));
                        vertices.add(new Vec3((float)x + 1, (float)y,     (float)z + 1));
                        vertices.add(new Vec3((float)x + 1, (float)y + 1, (float)z    ));
                        vertices.add(new Vec3((float)x + 1, (float)y + 1, (float)z + 1));
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        normals.add(new Vec3(1f, 0f, 0f));
                        normals.add(new Vec3(1f, 0f, 0f));
                        normals.add(new Vec3(1f, 0f, 0f));
                        normals.add(new Vec3(1f, 0f, 0f));
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 1);
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 2);
                    }

                    if(color.getW() > grid.getVoxel(new IVec3(x, y - 1, z)).getW()) {
                        vertices.add(new Vec3((float)x,     (float)y, (float)z    ));
                        vertices.add(new Vec3((float)x + 1, (float)y, (float)z    ));
                        vertices.add(new Vec3((float)x,     (float)y, (float)z + 1));
                        vertices.add(new Vec3((float)x + 1, (float)y, (float)z + 1));
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        normals.add(new Vec3(0f, -1f, 0f));
                        normals.add(new Vec3(0f, -1f, 0f));
                        normals.add(new Vec3(0f, -1f, 0f));
                        normals.add(new Vec3(0f, -1f, 0f));
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 1);
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 2);
                    }

                    if(color.getW() > grid.getVoxel(new IVec3(x, y + 1, z)).getW()) {
                        vertices.add(new Vec3((float)x,     (float)y + 1, (float)z    ));
                        vertices.add(new Vec3((float)x + 1, (float)y + 1, (float)z    ));
                        vertices.add(new Vec3((float)x,     (float)y + 1, (float)z + 1));
                        vertices.add(new Vec3((float)x + 1, (float)y + 1, (float)z + 1));
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        normals.add(new Vec3(0f, 1f, 0f));
                        normals.add(new Vec3(0f, 1f, 0f));
                        normals.add(new Vec3(0f, 1f, 0f));
                        normals.add(new Vec3(0f, 1f, 0f));
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 1);
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 2);
                    }

                    if(color.getW() > grid.getVoxel(new IVec3(x, y, z - 1)).getW()) {
                        vertices.add(new Vec3((float)x,     (float)y,     (float)z));
                        vertices.add(new Vec3((float)x + 1, (float)y,     (float)z));
                        vertices.add(new Vec3((float)x,     (float)y + 1, (float)z));
                        vertices.add(new Vec3((float)x + 1, (float)y + 1, (float)z));
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        normals.add(new Vec3(0f, 0f, -1f));
                        normals.add(new Vec3(0f, 0f, -1f));
                        normals.add(new Vec3(0f, 0f, -1f));
                        normals.add(new Vec3(0f, 0f, -1f));
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 1);
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 2);
                    }
                    
                    if(color.getW() > grid.getVoxel(new IVec3(x, y, z + 1)).getW()) {
                        vertices.add(new Vec3((float)x,     (float)y,     (float)z + 1));
                        vertices.add(new Vec3((float)x + 1, (float)y,     (float)z + 1));
                        vertices.add(new Vec3((float)x,     (float)y + 1, (float)z + 1));
                        vertices.add(new Vec3((float)x + 1, (float)y + 1, (float)z + 1));
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        normals.add(new Vec3(0f, 0f, 1f));
                        normals.add(new Vec3(0f, 0f, 1f));
                        normals.add(new Vec3(0f, 0f, 1f));
                        normals.add(new Vec3(0f, 0f, 1f));
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 1);
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 2);
                    }
                }
            }
        }

        Vec3[] _vertices = new Vec3[vertices.size()];
        Vec4[] _colors = new Vec4[colors.size()];
        Vec3[] _normals = new Vec3[normals.size()];
        int[] _indices = new int[indices.size()];

        for(int i = 0; i < vertices.size(); i++) {
            _vertices[i] = vertices.get(i);
        }
        for(int i = 0; i < colors.size(); i++) {
            _colors[i] = colors.get(i);
        }
        for(int i = 0; i < normals.size(); i++) {
            _normals[i] = normals.get(i);
        }
        for(int i = 0; i < indices.size(); i++) {
            _indices[i] = indices.get(i);
        }

        return new Mesh(renderer, new Buffer[] {
            new Buffer(renderer, _vertices), 
            new Buffer(renderer, _colors),
            new Buffer(renderer, _normals)}, 
            new Buffer(renderer, _indices)
        );
    }

    public Mesh generateEdgeless(VoxelGrid grid) {
        ArrayList<Vec3> vertices = new ArrayList<Vec3>();
        ArrayList<Vec4> colors = new ArrayList<Vec4>();
        ArrayList<Vec3> normals = new ArrayList<Vec3>();
        ArrayList<Integer> indices = new ArrayList<Integer>();

        for(int x = 1; x < grid.getSize().getX() - 1; x++) {
            for(int y = 1; y < grid.getSize().getY() - 1; y++) {
                for(int z = 1; z < grid.getSize().getZ() - 1; z++) {
                    Vec4 color = grid.getVoxel(new IVec3(x, y, z));

                    if(color.getW() > grid.getVoxel(new IVec3(x - 1, y, z)).getW()) {
                        vertices.add(new Vec3((float)x, (float)y,     (float)z    ));
                        vertices.add(new Vec3((float)x, (float)y,     (float)z + 1));
                        vertices.add(new Vec3((float)x, (float)y + 1, (float)z    ));
                        vertices.add(new Vec3((float)x, (float)y + 1, (float)z + 1));
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        normals.add(new Vec3(-1f, 0f, 0f));
                        normals.add(new Vec3(-1f, 0f, 0f));
                        normals.add(new Vec3(-1f, 0f, 0f));
                        normals.add(new Vec3(-1f, 0f, 0f));
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 1);
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 2);
                    }

                    if(color.getW() > grid.getVoxel(new IVec3(x + 1, y, z)).getW()) {
                        vertices.add(new Vec3((float)x + 1, (float)y,     (float)z    ));
                        vertices.add(new Vec3((float)x + 1, (float)y,     (float)z + 1));
                        vertices.add(new Vec3((float)x + 1, (float)y + 1, (float)z    ));
                        vertices.add(new Vec3((float)x + 1, (float)y + 1, (float)z + 1));
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        normals.add(new Vec3(1f, 0f, 0f));
                        normals.add(new Vec3(1f, 0f, 0f));
                        normals.add(new Vec3(1f, 0f, 0f));
                        normals.add(new Vec3(1f, 0f, 0f));
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 1);
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 2);
                    }

                    if(color.getW() > grid.getVoxel(new IVec3(x, y - 1, z)).getW()) {
                        vertices.add(new Vec3((float)x,     (float)y, (float)z    ));
                        vertices.add(new Vec3((float)x + 1, (float)y, (float)z    ));
                        vertices.add(new Vec3((float)x,     (float)y, (float)z + 1));
                        vertices.add(new Vec3((float)x + 1, (float)y, (float)z + 1));
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        normals.add(new Vec3(0f, -1f, 0f));
                        normals.add(new Vec3(0f, -1f, 0f));
                        normals.add(new Vec3(0f, -1f, 0f));
                        normals.add(new Vec3(0f, -1f, 0f));
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 1);
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 2);
                    }

                    if(color.getW() > grid.getVoxel(new IVec3(x, y + 1, z)).getW()) {
                        vertices.add(new Vec3((float)x,     (float)y + 1, (float)z    ));
                        vertices.add(new Vec3((float)x + 1, (float)y + 1, (float)z    ));
                        vertices.add(new Vec3((float)x,     (float)y + 1, (float)z + 1));
                        vertices.add(new Vec3((float)x + 1, (float)y + 1, (float)z + 1));
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        normals.add(new Vec3(0f, 1f, 0f));
                        normals.add(new Vec3(0f, 1f, 0f));
                        normals.add(new Vec3(0f, 1f, 0f));
                        normals.add(new Vec3(0f, 1f, 0f));
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 1);
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 2);
                    }

                    if(color.getW() > grid.getVoxel(new IVec3(x, y, z - 1)).getW()) {
                        vertices.add(new Vec3((float)x,     (float)y,     (float)z));
                        vertices.add(new Vec3((float)x + 1, (float)y,     (float)z));
                        vertices.add(new Vec3((float)x,     (float)y + 1, (float)z));
                        vertices.add(new Vec3((float)x + 1, (float)y + 1, (float)z));
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        normals.add(new Vec3(0f, 0f, -1f));
                        normals.add(new Vec3(0f, 0f, -1f));
                        normals.add(new Vec3(0f, 0f, -1f));
                        normals.add(new Vec3(0f, 0f, -1f));
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 1);
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 2);
                    }
                    
                    if(color.getW() > grid.getVoxel(new IVec3(x, y, z + 1)).getW()) {
                        vertices.add(new Vec3((float)x,     (float)y,     (float)z + 1));
                        vertices.add(new Vec3((float)x + 1, (float)y,     (float)z + 1));
                        vertices.add(new Vec3((float)x,     (float)y + 1, (float)z + 1));
                        vertices.add(new Vec3((float)x + 1, (float)y + 1, (float)z + 1));
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        colors.add(color);
                        normals.add(new Vec3(0f, 0f, 1f));
                        normals.add(new Vec3(0f, 0f, 1f));
                        normals.add(new Vec3(0f, 0f, 1f));
                        normals.add(new Vec3(0f, 0f, 1f));
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 1);
                        indices.add(vertices.size() - 4 + 0);
                        indices.add(vertices.size() - 4 + 3);
                        indices.add(vertices.size() - 4 + 2);
                    }
                }
            }
        }

        Vec3[] _vertices = new Vec3[vertices.size()];
        Vec4[] _colors = new Vec4[colors.size()];
        Vec3[] _normals = new Vec3[normals.size()];
        int[] _indices = new int[indices.size()];

        for(int i = 0; i < vertices.size(); i++) {
            _vertices[i] = vertices.get(i);
        }
        for(int i = 0; i < colors.size(); i++) {
            _colors[i] = colors.get(i);
        }
        for(int i = 0; i < normals.size(); i++) {
            _normals[i] = normals.get(i);
        }
        for(int i = 0; i < indices.size(); i++) {
            _indices[i] = indices.get(i);
        }

        return new Mesh(renderer, new Buffer[] {
            new Buffer(renderer, _vertices), 
            new Buffer(renderer, _colors),
            new Buffer(renderer, _normals)}, 
            new Buffer(renderer, _indices)
        );
    }
}