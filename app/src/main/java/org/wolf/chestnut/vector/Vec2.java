package org.wolf.chestnut.vector;

import org.wolf.chestnut.vector.Vec3;
import org.wolf.chestnut.vector.Vec4;

import java.lang.Math;
import java.util.Random;

public class Vec2 {
    private float x, y;

    public Vec2(float _x, float _y) {
        x = _x;
        y = _y;
    }

    public String toString() {
        return x + ", " + y;
    }

    public Vec2 negate() {
        return new Vec2(-x, -y);
    }

    public static Vec2 add(Vec2 a, Vec2 b) {
        return new Vec2(a.getX() + b.getX(), a.getY() + b.getY());
    }

    public static Vec2 sub(Vec2 a, Vec2 b) {
        return new Vec2(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public static Vec2 mul(Vec2 a, Vec2 b) {
        return new Vec2(a.getX() * b.getX(), a.getY() * b.getY());
    }

    public static Vec2 div(Vec2 a, Vec2 b) {
        return new Vec2(a.getX() / b.getX(), a.getY() / b.getY());
    }

    public Vec2 add(float value) {
        return new Vec2(x + value, y + value);
    }

    public Vec2 sub(float value) {
        return new Vec2(x - value, y - value);
    }

    public Vec2 mul(float value) {
        return new Vec2(x * value, y * value);
    }

    public Vec2 div(float value) {
        return new Vec2(x / value, y / value);
    }

    public static float dot(Vec2 a, Vec2 b) {
        return a.getX() * b.getX() + a.getY() * b.getY();
    }

    public Vec2 vary(float amount) {
        Random random = new Random();
        float rn = random.nextFloat() * (amount * 2f) - amount;
        return new Vec2(x + amount, y + amount);
    }

    public Vec2 rotate(float rotation) {
        float cos = (float)Math.cos(Math.toRadians(rotation));
        float sin = (float)Math.sin(Math.toRadians(rotation));

        return new Vec2(x * cos - y * sin, y * cos + x * sin);
    }

    public Vec2 reciprocal() {
        return new Vec2(1f / x, 1f / y);
    }

    public Vec3 toVec3() {
        return new Vec3(x, y, 0f);
    }

    public Vec4 toVec4() {
        return new Vec4(x, y, 0f, 1f);
    }

    public IVec2 toIVec2() {
        return new IVec2((int)Math.floor(x), (int)Math.floor(y));
    }

    public IVec3 toIVec3() {
        return new IVec3((int)Math.floor(x), (int)Math.floor(y), 0);
    }

    public IVec4 toIVec4() {
        return new IVec4((int)Math.floor(x), (int)Math.floor(y), 0, 1);
    }

    public float length() {
        return (float)Math.sqrt(x * x + y * y);
    }

    public Vec2 normalize() {
        return div(length());
    }

    public static boolean equals(Vec2 a, Vec2 b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }

    public float getX() {return x;}
    public float getY() {return y;}
}