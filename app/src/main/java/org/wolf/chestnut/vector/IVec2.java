package org.wolf.chestnut.vector;

import java.lang.Math;
import java.util.Random;

public class IVec2 {
    private int x, y;

    public IVec2(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public String toString() {
        return x + ", " + y;
    }

    public IVec2 negate() {
        return new IVec2(-x, -y);
    }

    public static IVec2 add(IVec2 a, IVec2 b) {
        return new IVec2(a.getX() + b.getX(), a.getY() + b.getY());
    }

    public static IVec2 sub(IVec2 a, IVec2 b) {
        return new IVec2(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public static IVec2 mul(IVec2 a, IVec2 b) {
        return new IVec2(a.getX() * b.getX(), a.getY() * b.getY());
    }

    public static IVec2 div(IVec2 a, IVec2 b) {
        return new IVec2(a.getX() / b.getX(), a.getY() / b.getY());
    }

    public IVec2 add(int value) {
        return new IVec2(x + value, y + value);
    }

    public IVec2 sub(int value) {
        return new IVec2(x - value, y - value);
    }

    public IVec2 mul(int value) {
        return new IVec2(x * value, y * value);
    }

    public IVec2 div(int value) {
        return new IVec2(x / value, y / value);
    }

    public IVec3 toIVec3() {
        return new IVec3(x, y, 0);
    }

    public IVec4 toIVec4() {
        return new IVec4(x, y, 0, 1);
    }

    public Vec2 toVec2() {
        return new Vec2((float)x, (float)y);
    }

    public Vec3 toVec3() {
        return new Vec3((float)x, (float)y, 0f);
    }

    public Vec4 toVec4() {
        return new Vec4((float)x, (float)y, 0f, 1f);
    }

    public int getX() {return x;}
    public int getY() {return y;}
}