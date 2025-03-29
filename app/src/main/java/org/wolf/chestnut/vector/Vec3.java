package org.wolf.chestnut.vector;

import java.lang.Math;
import java.util.Random;

public class Vec3 {
    private float x, y, z;

    public Vec3(float _x, float _y, float _z) {
        x = _x;
        y = _y;
        z = _z;
    }

    public String toString() {
        return x + ", " + y + ", " + z;
    }

    public Vec3 negate() {
        return new Vec3(-x, -y, -z);
    }

    public static Vec3 add(Vec3 a, Vec3 b) {
        return new Vec3(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
    }

    public static Vec3 sub(Vec3 a, Vec3 b) {
        return new Vec3(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
    }

    public static Vec3 mul(Vec3 a, Vec3 b) {
        return new Vec3(a.getX() * b.getX(), a.getY() * b.getY(), a.getZ() * b.getZ());
    }

    public static Vec3 div(Vec3 a, Vec3 b) {
        return new Vec3(a.getX() / b.getX(), a.getY() / b.getY(), a.getZ() / b.getZ());
    }

    public Vec3 add(float value) {
        return new Vec3(x + value, y + value, z + value);
    }

    public Vec3 sub(float value) {
        return new Vec3(x - value, y - value, z - value);
    }

    public Vec3 mul(float value) {
        return new Vec3(x * value, y * value, z * value);
    }

    public Vec3 div(float value) {
        return new Vec3(x / value, y / value, z / value);
    }

    public static float dot(Vec3 a, Vec3 b) {
        return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ();
    }

    public Vec3 vary(float amount) {
        Random random = new Random();
        float rn = random.nextFloat() * (amount * 2f) - amount;
        return new Vec3(x + rn, y + rn, z + rn);
    }

    public Vec3 rotate(Vec3 rotation) {
        float cos_x = (float)Math.cos(Math.toRadians(rotation.getX()));
        float cos_y = (float)Math.cos(Math.toRadians(rotation.getY()));
        float cos_z = (float)Math.cos(Math.toRadians(rotation.getZ()));
        float sin_x = (float)Math.sin(Math.toRadians(rotation.getX()));
        float sin_y = (float)Math.sin(Math.toRadians(rotation.getY()));
        float sin_z = (float)Math.sin(Math.toRadians(rotation.getZ()));

        Vec3 value = new Vec3(x, y, z);
        value = new Vec3(value.getX(), cos_x * value.getY() - sin_x * value.getZ(), cos_x * value.getZ() + sin_x * value.getY());
        value = new Vec3(value.getX() * cos_y + value.getZ() * sin_y, value.getY(), value.getZ() * cos_y - value.getX() * sin_y);
        value = new Vec3(value.getX() * cos_z - value.getY() * sin_z, value.getY() * cos_z + value.getY() * sin_z, value.getZ());

        return value;
    }

    public Vec3 reciprocal() {
        return new Vec3(1f / x, 1f / y, 1f / z);
    }

    public Vec2 toVec2() {
        return new Vec2(x, y);
    }

    public Vec4 toVec4() {
        return new Vec4(x, y, z, 1f);
    }

    public IVec2 toIVec2() {
        return new IVec2((int)Math.floor(x), (int)Math.floor(y));
    }

    public IVec3 toIVec3() {
        return new IVec3((int)Math.floor(x), (int)Math.floor(y), (int)Math.floor(z));
    }

    public IVec4 toIVec4() {
        return new IVec4((int)Math.floor(x), (int)Math.floor(y), (int)Math.floor(z), 1);
    }

    public float length() {
        return (float)Math.sqrt(x * x + y * y + z * z);
    }

    public Vec3 normalize() {
        return div(length());
    }

    public static boolean equals(Vec3 a, Vec3 b) {
        return a.getX() == b.getX() && a.getY() == b.getY() && a.getZ() == b.getZ();
    }

    public float getX() {return x;}
    public float getY() {return y;}
    public float getZ() {return z;}
}