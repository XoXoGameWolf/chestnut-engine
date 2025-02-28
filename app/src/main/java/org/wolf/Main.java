package org.wolf;

import org.wolf.chestnut.Chestnut;
import org.wolf.chestnut.vector.Vec2;
import org.wolf.chestnut.vector.IVec3;
import org.wolf.chestnut.vector.IVec2;
import org.wolf.chestnut.vector.Vec3;
import org.wolf.chestnut.vector.Vec4;
import org.wolf.chestnut.matrix.Mat4;
import org.wolf.chestnut.graphics.Buffer;
import org.wolf.chestnut.graphics.Mesh;
import org.wolf.chestnut.graphics.Shader;
import org.wolf.chestnut.world.Object;
import org.wolf.chestnut.world.VoxelGrid;
import org.wolf.chestnut.util.IO;
import org.wolf.chestnut.util.Keys;

import java.lang.Math;
import java.util.Random;

class App extends Chestnut {
    Shader shader;
    VoxelGrid grid;
    Object chunk;

    public App() {super("App", new IVec2(1280, 760));}

    public void start() {
        shader = new Shader(renderer, IO.read("shaders/terrain/main.vert"), IO.read("shaders/terrain/main.frag"));

        Random random = new Random();
        grid = new VoxelGrid(new IVec3(100, 110, 100));

        for(int x = 0; x < 100; x++) {
            for(int z = 0; z < 100; z++) {
                for(int i = 0; i < 100; i++) {
                    grid.setVoxel(new IVec3(x, i, z), new Vec3(0.5f, 0.4f, 0.3f).vary().toVec4());
                }
                if(random.nextBoolean()) {
                    grid.setVoxel(new IVec3(x, 100, z), new Vec3(0f, 0.8f, 0f).vary().toVec4());
                } else {
                    grid.setVoxel(new IVec3(x, 100, z), new Vec3(0.5f, 0.4f, 0.3f).vary().toVec4());
                }
                grid.setVoxel(new IVec3(x, 101, z), new Vec3(0f, 0.8f, 0f).vary().toVec4());
                if(random.nextBoolean()) {
                    grid.setVoxel(new IVec3(x, 102, z), new Vec3(0f, 0.8f, 0f).vary().toVec4());
                }
            }
        }

        chunk = new Object(
            voxelHandler.generate(grid), 
            new Vec3(0f, 0f, 0f), 
            new Vec3(0f, 0f, 0f), 
            new Vec3(0.025f, 0.025f, 0.025f)
        );

        objectHandler.setShader(shader);
        objectHandler.addObject(chunk);

        camera.setPosition(new Vec3(0f, 0f, -1f));
        renderer.setColor(new Vec4(0.3f, 0.6f, 1f, 1f));
    }

    public void update() {
        if(window.getKeyState(Keys.W)) {
            camera.setPosition(Vec3.add(camera.getPosition(), camera.getForward().mul(0.02f)));
        }
        if(window.getKeyState(Keys.A)) {
            camera.setPosition(Vec3.sub(camera.getPosition(), camera.getRight().mul(0.02f)));
        }
        if(window.getKeyState(Keys.S)) {
            camera.setPosition(Vec3.sub(camera.getPosition(), camera.getForward().mul(0.02f)));
        }
        if(window.getKeyState(Keys.D)) {
            camera.setPosition(Vec3.add(camera.getPosition(), camera.getRight().mul(0.02f)));
        }
        if(window.getKeyState(Keys.E)) {
            camera.setPosition(Vec3.add(camera.getPosition(), camera.getUp().mul(0.02f)));
        }
        if(window.getKeyState(Keys.Q)) {
            camera.setPosition(Vec3.sub(camera.getPosition(), camera.getUp().mul(0.02f)));
        }

        if(window.getKeyDown(Keys.ESCAPE)) {
            window.setMouseGrab(!window.getMouseGrab());
        }

        if(window.getMouseGrab()) {
            camera.setRotation(Vec3.add(camera.getRotation(), new Vec3(
                (float)(window.getCursorRel().getY()) * 25f, 
                (float)(window.getCursorRel().getX()) * 25f, 
                0f
            )));
        }

        if(window.getMouseButtonDown(Keys.LEFT_MOUSE)) {
            IVec3 voxel = grid.raycast(
                camera.getPosition(), 
                camera.getForward(), 
                new Vec3(0f, 0f, 0f),
                new Vec3(0f, 0f, 0f),
                new Vec3(0.025f, 0.025f, 0.025f)
            );

            if(voxel.getX() != -1) {
                grid.setVoxel(voxel, new Vec4(0f, 0f, 0f, 0f));
                chunk.setMesh(voxelHandler.generate(grid));
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        App app = new App();
    }
}