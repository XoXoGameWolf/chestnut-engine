package org.wolf.chestnut.matrix;

import org.wolf.chestnut.vector.Vec2;
import org.wolf.chestnut.vector.Vec3;

import java.lang.Math;

public class Mat3 {
    private Vec3 x, y, z;

    public Mat3(Vec3 _x, Vec3 _y, Vec3 _z) {
        x = _x;
        y = _y;
        z = _z;
    }

    public static Mat3 identity() {
        return new Mat3(
            new Vec3(1f, 0f, 0f),
            new Vec3(0f, 1f, 0f),
            new Vec3(0f, 0f, 1f)
        );
    }

    public static Mat3 translate(Vec2 value) {
        return new Mat3(
            new Vec3(1f, 0f, value.getX()),
            new Vec3(0f, 1f, value.getY()),
            new Vec3(0f, 0f, 1f)
        );
    }

    public static Mat3 rotate(Vec2 value) {
        return new Mat3(
            new Vec3((float) Math.cos(Math.toRadians(value.getY())), 0f, 0f),
            new Vec3(0f, (float) Math.cos(Math.toRadians(value.getX())), 0f),
            new Vec3(0f, 0f, 1f)
        );
    }

    public static Mat3 scale(Vec2 value) {
        return new Mat3(
            new Vec3(value.getX(), 0f, 0f),
            new Vec3(0f, value.getY(), 0f),
            new Vec3(0f, 0f, 1f)
        );
    }

    public static Mat3 multiply(Mat3 a, Mat3 b) {
        Vec3 a0 = a.getX();
        Vec3 a1 = a.getY();
        Vec3 a2 = a.getZ();
        Vec3 b0 = new Vec3(b.getX().getX(), b.getX().getY(), b.getX().getZ());
        Vec3 b1 = new Vec3(b.getY().getX(), b.getY().getY(), b.getY().getZ());
        Vec3 b2 = new Vec3(b.getZ().getX(), b.getZ().getY(), b.getZ().getZ());

        return new Mat3(
            new Vec3(a0.getX() * b0.getX() + a1.getX() * b1.getX() + a2.getX() * b2.getX(), a0.getY() * b0.getX() + a1.getY() * b1.getX() + a2.getY() * b2.getX(), a0.getZ() * b0.getX() + a1.getZ() * b1.getX() + a2.getZ() * b2.getX()),
            new Vec3(a0.getX() * b0.getY() + a1.getX() * b1.getY() + a2.getX() * b2.getY(), a0.getY() * b0.getY() + a1.getY() * b1.getY() + a2.getY() * b2.getY(), a0.getZ() * b0.getY() + a1.getZ() * b1.getY() + a2.getZ() * b2.getY()),
            new Vec3(a0.getX() * b0.getZ() + a1.getX() * b1.getZ() + a2.getX() * b2.getZ(), a0.getY() * b0.getZ() + a1.getY() * b1.getZ() + a2.getY() * b2.getZ(), a0.getZ() * b0.getZ() + a1.getZ() * b1.getZ() + a2.getZ() * b2.getZ())
        );
    }

    public Vec3 getX() {return x;}
    public Vec3 getY() {return y;}
    public Vec3 getZ() {return z;}
}