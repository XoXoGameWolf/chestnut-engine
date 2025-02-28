package org.wolf.chestnut.comp;

import org.wolf.chestnut.world.Object;
import org.wolf.chestnut.graphics.Shader;
import org.wolf.chestnut.matrix.Mat4;

import java.util.ArrayList;

public class ObjectHandler {
    private Renderer renderer;
    private Camera camera;
    private Shader shader;
    private ArrayList<Object> objects;

    public ObjectHandler(Renderer _renderer, Camera _camera) {
        renderer = _renderer;
        camera = _camera;
        objects = new ArrayList<Object>();
    }

    public void update() {
        if(shader == null) {
            return;
        }
        shader.setUniform(renderer, "projection", camera.getProjection());
        shader.setUniform(renderer, "view", camera.getView());

        for(int i = 0; i < objects.size(); i++) {
            Object object = objects.get(i);

            shader.setUniform(renderer, "model", Mat4.multiply(Mat4.multiply(
                Mat4.scale(object.getScale()), 
                Mat4.rotate(object.getRotation())), 
                Mat4.translate(object.getPosition())
            ));

            objects.get(i).getMesh().render(renderer, shader);
        }
    }

    public void setShader(Shader _shader) {
        shader = _shader;
    }

    public void addObject(Object object) {
        objects.add(object);
    }

    public void removeObject(Object object) {
        objects.remove(object);
    }
}