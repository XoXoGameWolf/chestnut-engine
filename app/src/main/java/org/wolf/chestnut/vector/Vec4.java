package org.wolf.chestnut.vector;

import org.wolf.chestnut.vector.Vec2;
import org.wolf.chestnut.vector.Vec3;

import java.lang.Math;
import java.util.Random;

public class Vec4 {
    private float x, y, z, w;

    public Vec4(float _x, float _y, float _z, float _w) {
        x = _x;
        y = _y;
        z = _z;
        w = _w;
    }

    public String toString() {
        return x + ", " + y + ", " + z + ", " + w;
    }

    public Vec4 negate() {
        return new Vec4(-x, -y, -z, -w);
    }

    public static Vec4 add(Vec4 a, Vec4 b) {
        return new Vec4(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ(), a.getW() + b.getW());
    }

    public static Vec4 sub(Vec4 a, Vec4 b) {
        return new Vec4(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ(), a.getW() - b.getW());
    }

    public static Vec4 mul(Vec4 a, Vec4 b) {
        return new Vec4(a.getX() * b.getX(), a.getY() * b.getY(), a.getZ() * b.getZ(), a.getW() * b.getW());
    }

    public static Vec4 div(Vec4 a, Vec4 b) {
        return new Vec4(a.getX() / b.getX(), a.getY() / b.getY(), a.getZ() / b.getZ(), a.getW() / b.getW());
    }

    public Vec4 add(float value) {
        return new Vec4(x + value, y + value, z + value, w + value);
    }

    public Vec4 sub(float value) {
        return new Vec4(x - value, y - value, z - value, w - value);
    }

    public Vec4 mul(float value) {
        return new Vec4(x * value, y * value, z * value, w * value);
    }

    public Vec4 div(float value) {
        return new Vec4(x / value, y / value, z / value, w / value);
    }

    public static float dot(Vec4 a, Vec4 b) {
        return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ() + a.getW() * b.getW();
    }

    public Vec4 vary(float amount) {
        Random random = new Random();
        float rn = random.nextFloat() * (2f * amount) - amount;
        return new Vec4(x + rn, y + rn, z + rn, w);
    }

    public Vec4 reciprocal() {
        return new Vec4(1f / x, 1f / y, 1f / z, 1f / w);
    }

    public Vec2 toVec2() {
        return new Vec2(x, y);
    }

    public Vec3 toVec3() {
        return new Vec3(x, y, z);
    }
    
    public float getX() {return x;}
    public float getY() {return y;}
    public float getZ() {return z;}
    public float getW() {return w;}
}