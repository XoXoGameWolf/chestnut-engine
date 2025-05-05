package org.wolf.chestnut;

import org.wolf.chestnut.comp.Window;
import org.wolf.chestnut.comp.Renderer;
import org.wolf.chestnut.comp.Camera;
import org.wolf.chestnut.comp.ObjectHandler;
import org.wolf.chestnut.comp.VoxelHandler;
import org.wolf.chestnut.util.Logger;
import org.wolf.chestnut.vector.IVec2;

public abstract class Chestnut {
    public Window window;
    public Renderer renderer;
    public Camera camera;
    public ObjectHandler objectHandler;
    public VoxelHandler voxelHandler;

    public boolean running;

    public Chestnut(String title, IVec2 resolution) {
        window = new Window(title, resolution);
        renderer = new Renderer();
        camera = new Camera(window);
        objectHandler = new ObjectHandler(renderer, camera);
        voxelHandler = new VoxelHandler(renderer);

        running = true;

        start();

        while(running) {
            renderer.update();
            update();
            objectHandler.update();
            window.update();

            if(window.isOpen() == false) {
                running = false;
            }
        }
        
        renderer.destroy();
        window.destroy();
    }

    public abstract void start();
    public abstract void update();
}