package org.wolf.chestnut.comp;

import org.wolf.chestnut.vector.Vec3;
import org.wolf.chestnut.vector.Vec4;
import org.wolf.chestnut.matrix.Mat4;

import java.lang.Math;

public class Camera {
    private Window window;
    private Vec3 position, rotation;

    private Mat4 projection;
    private float fov;

    public Camera(Window _window) {
        window = _window;

        position = new Vec3(0f, 0f, 0f);
        rotation = new Vec3(0f, 0f, 0f);

        fov = 60f;

        createProjection();
    }

    private void createProjection() {
        projection = Mat4.perspective(
            fov,
            (float)window.getResolution().getX() / (float)window.getResolution().getY(),
            0.01f,
            1000f
        );
    }

    public void setFOV(float _fov) {
        fov = _fov;
        createProjection();
    }

    public float getFOV() {
        return fov;
    }

    public Mat4 getProjection() {
        return projection;
    }

    public Mat4 getView() {
        return Mat4.multiply(
            Mat4.translate(position.negate()),
            Mat4.rotate(rotation.negate())
        );
    }

    public void setPosition(Vec3 _position) {
        position = _position;
    }

    public void setRotation(Vec3 _rotation) {
        rotation = _rotation;
    }

    public Vec3 getPosition() {
        return position;
    }

    public Vec3 getRotation() {
        return rotation;
    }

    public Vec3 getRight() {
        return new Vec3(1f, 0f, 0f).rotate(rotation);
    }

    public Vec3 getUp() {
        return new Vec3(0f, 1f, 0f).rotate(rotation);
    }

    public Vec3 getForward() {
        return new Vec3(0f, 0f, 1f).rotate(rotation);
    }
}