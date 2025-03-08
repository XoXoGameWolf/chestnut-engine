package org.wolf.chestnut.comp;

import org.wolf.chestnut.world.Object;
import org.wolf.chestnut.graphics.Shader;
import org.wolf.chestnut.matrix.Mat4;

import java.util.ArrayList;

public class ObjectHandler {
    private Renderer renderer;
    private Camera camera;
    private ArrayList<Object> objects;

    public ObjectHandler(Renderer _renderer, Camera _camera) {
        renderer = _renderer;
        camera = _camera;
        objects = new ArrayList<Object>();
    }

    public void update() {
        for(int i = 0; i < objects.size(); i++) {
            Object object = objects.get(i);

            object.getShader().setUniform(renderer, "projection", camera.getProjection());
            object.getShader().setUniform(renderer, "view", camera.getView());
            object.getShader().setUniform(renderer, "model", Mat4.multiply(Mat4.multiply(
                Mat4.scale(object.getScale()), 
                Mat4.rotate(object.getRotation())), 
                Mat4.translate(object.getPosition())
            ));

            if(object.getTexture0() != null) {
                object.getShader().setUniform(renderer, "texture0", object.getTexture0(), 0);
            }
            if(object.getTexture1() != null) {
                object.getShader().setUniform(renderer, "texture1", object.getTexture1(), 1);
            }
            if(object.getTexture2() != null) {
                object.getShader().setUniform(renderer, "texture2", object.getTexture2(), 2);
            }
            if(object.getTexture3() != null) {
                object.getShader().setUniform(renderer, "texture3", object.getTexture3(), 3);
            }

            objects.get(i).getMesh().render(renderer, object.getShader());
        }
    }

    public void addObject(Object object) {
        objects.add(object);
    }

    public void removeObject(Object object) {
        objects.remove(object);
    }
}