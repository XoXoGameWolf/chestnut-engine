package org.wolf.chestnut.world;

import org.wolf.chestnut.vector.Vec3;
import org.wolf.chestnut.graphics.Mesh;
import org.wolf.chestnut.graphics.Shader;
import org.wolf.chestnut.graphics.Texture;

import java.util.ArrayList;

public class Object {
    private Mesh mesh;
    private Shader shader;
    private Vec3 position, rotation, scale;

    private Texture texture0 = null;
    private Texture texture1 = null;
    private Texture texture2 = null;
    private Texture texture3 = null;

    public Object(Mesh _mesh, Shader _shader, Vec3 _position, Vec3 _rotation, Vec3 _scale) {
        mesh = _mesh;
        shader = _shader;
        position = _position;
        rotation = _rotation;
        scale = _scale;
    }

    public Texture getTexture0() {
        return texture0;
    }

    public Texture getTexture1() {
        return texture1;
    }

    public Texture getTexture2() {
        return texture2;
    }

    public Texture getTexture3() {
        return texture3;
    }

    public void setTexture0(Texture texture) {
        texture0 = texture;
    }

    public void setTexture1(Texture texture) {
        texture1 = texture;
    }

    public void setTexture2(Texture texture) {
        texture2 = texture;
    }

    public void setTexture3(Texture texture) {
        texture3 = texture;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public Shader getShader() {
        return shader;
    }

    public Vec3 getPosition() {
        return position;
    }

    public Vec3 getRotation() {
        return rotation;
    }

    public Vec3 getScale() {
        return scale;
    }

    public void setMesh(Mesh _mesh) {
        mesh = _mesh;
    }

    public void setPosition(Vec3 _position) {
        position = _position;
    }

    public void setRotation(Vec3 _rotation) {
        rotation = _rotation;
    } 

    public void setScale(Vec3 _scale) {
        scale = _scale;
    }
}