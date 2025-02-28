package org.wolf.chestnut.world;

import org.wolf.chestnut.vector.Vec3;
import org.wolf.chestnut.graphics.Mesh;

public class Object {
    private Mesh mesh;
    private Vec3 position, rotation, scale;

    public Object(Mesh _mesh, Vec3 _position, Vec3 _rotation, Vec3 _scale) {
        mesh = _mesh;
        position = _position;
        rotation = _rotation;
        scale = _scale;
    }

    public Mesh getMesh() {
        return mesh;
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