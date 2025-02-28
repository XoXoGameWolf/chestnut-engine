package org.wolf.chestnut.matrix;

import org.wolf.chestnut.vector.Vec3;
import org.wolf.chestnut.vector.Vec4;

import java.lang.Math;

public class Mat4 {
    private Vec4 x, y, z, w;

    public Mat4(Vec4 _x, Vec4 _y, Vec4 _z, Vec4 _w) {
        x = _x;
        y = _y;
        z = _z;
        w = _w;
    }

    public static Mat4 identity() {
        return new Mat4(
            new Vec4(1f, 0f, 0f, 0f),
            new Vec4(0f, 1f, 0f, 0f),
            new Vec4(0f, 0f, 1f, 0f),
            new Vec4(0f, 0f, 0f, 1f)
        );
    }

    public static Mat4 translate(Vec3 value) {
        return new Mat4(
            new Vec4(1f, 0f, 0f, value.getX()),
            new Vec4(0f, 1f, 0f, value.getY()),
            new Vec4(0f, 0f, 1f, value.getZ()),
            new Vec4(0f, 0f, 0f, 1f)
        );
    }

    public static Mat4 rotate(Vec3 value) {
        float cos_x = (float)Math.cos(Math.toRadians(value.getX()));
        float cos_y = (float)Math.cos(Math.toRadians(value.getY()));
        float cos_z = (float)Math.cos(Math.toRadians(value.getZ()));
        float sin_x = (float)Math.sin(Math.toRadians(value.getX()));
        float sin_y = (float)Math.sin(Math.toRadians(value.getY()));
        float sin_z = (float)Math.sin(Math.toRadians(value.getZ()));

        return Mat4.multiply(Mat4.multiply(new Mat4(
            new Vec4(cos_z, -sin_z, 0f, 0f),
            new Vec4(sin_z, cos_z, 0f, 0f),
            new Vec4(0f, 0f, 1f, 0f),
            new Vec4(0f, 0f, 0f, 1f)
        ), new Mat4(
            new Vec4(cos_y, 0f, sin_y, 0f),
            new Vec4(0f, 1f, 0f, 0f),
            new Vec4(-sin_y, 0f, cos_y, 0f),
            new Vec4(0f, 0f, 0f, 1f)
        )), new Mat4(
            new Vec4(1f, 0f, 0f, 0f),
            new Vec4(0f, cos_x, -sin_x, 0f),
            new Vec4(0f, sin_x, cos_x, 0f),
            new Vec4(0f, 0f, 0f, 1f)
        ));
    }

    public static Mat4 scale(Vec3 value) {
        return new Mat4(
            new Vec4(value.getX(), 0f, 0f, 0f),
            new Vec4(0f, value.getY(), 0f, 0f),
            new Vec4(0f, 0f, value.getZ(), 0f),
            new Vec4(0f, 0f, 0f, 1f)
        );
    }

    public static Mat4 multiply(Mat4 b, Mat4 a) {
        Vec4 a0 = a.getX();
        Vec4 a1 = a.getY();
        Vec4 a2 = a.getZ();
        Vec4 a3 = a.getW();
        Vec4 b0 = new Vec4(b.getX().getX(), b.getY().getX(), b.getZ().getX(), b.getW().getX());
        Vec4 b1 = new Vec4(b.getX().getY(), b.getY().getY(), b.getZ().getY(), b.getW().getY());
        Vec4 b2 = new Vec4(b.getX().getZ(), b.getY().getZ(), b.getZ().getZ(), b.getW().getZ());
        Vec4 b3 = new Vec4(b.getX().getW(), b.getY().getW(), b.getZ().getW(), b.getW().getW());

        return new Mat4(
            new Vec4(Vec4.dot(a0, b0), Vec4.dot(a0, b1), Vec4.dot(a0, b2), Vec4.dot(a0, b3)),
            new Vec4(Vec4.dot(a1, b0), Vec4.dot(a1, b1), Vec4.dot(a1, b2), Vec4.dot(a1, b3)),
            new Vec4(Vec4.dot(a2, b0), Vec4.dot(a2, b1), Vec4.dot(a2, b2), Vec4.dot(a2, b3)),
            new Vec4(Vec4.dot(a3, b0), Vec4.dot(a3, b1), Vec4.dot(a3, b2), Vec4.dot(a3, b3))
        );
    }

    public static Vec4 multiply(Mat4 mat, Vec4 vec) {
        return new Vec4(
            vec.getX() * mat.getX().getX() + vec.getY() * mat.getX().getY() + vec.getZ() * mat.getX().getZ() + vec.getW() * mat.getX().getW(),
            vec.getX() * mat.getY().getX() + vec.getY() * mat.getY().getY() + vec.getZ() * mat.getY().getZ() + vec.getW() * mat.getY().getW(),
            vec.getX() * mat.getZ().getX() + vec.getY() * mat.getZ().getY() + vec.getZ() * mat.getZ().getZ() + vec.getW() * mat.getZ().getW(),
            vec.getX() * mat.getW().getX() + vec.getY() * mat.getW().getY() + vec.getZ() * mat.getW().getZ() + vec.getW() * mat.getW().getW()
        );
    }

    public static Mat4 perspective(float fov, float aspect, float near, float far) {
        return new Mat4(
            new Vec4(1f / (aspect * (float) Math.tan(Math.toRadians(fov) / 2f)), 0f, 0f, 0f),
            new Vec4(0f, 1f / (float) Math.tan(Math.toRadians(fov) / 2f), 0f, 0f),
            new Vec4(0f, 0f, -(far + near) / (far - near), -(2f * far * near) / (far - near)),
            new Vec4(0f, 0f, -1f, 0f)
        );
    }

    public String toString() {
        return x + ", \n" + y + ", \n" + z + ", \n" + w;
    }

    public Vec4 getX() {return x;}
    public Vec4 getY() {return y;}
    public Vec4 getZ() {return z;}
    public Vec4 getW() {return w;}
}