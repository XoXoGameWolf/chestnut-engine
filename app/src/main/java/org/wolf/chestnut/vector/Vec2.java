package org.wolf.chestnut.vector;

import org.wolf.chestnut.vector.Vec3;
import org.wolf.chestnut.vector.Vec4;

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

    public static float dot(Vec2 a, Vec2 b) {
        return a.getX() * b.getX() + a.getY() * b.getY();
    }

    public Vec3 toVec3() {
        return new Vec3(x, y, 0f);
    }

    public Vec4 toVec4() {
        return new Vec4(x, y, 0f, 1f);
    }

    public IVec3 toIVec3() {
        return new IVec3((int)x, (int)y, 0);
    }

    public IVec4 toIVec4() {
        return new IVec4((int)x, (int)y, 0, 1);
    }

    public float getX() {return x;}
    public float getY() {return y;}
}