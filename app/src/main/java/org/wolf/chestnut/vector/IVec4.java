package org.wolf.chestnut.vector;

import java.lang.Math;

public class IVec4 {
    private int x, y, z, w;

    public IVec4(int _x, int _y, int _z, int _w) {
        x = _x;
        y = _y;
        z = _z;
        w = _w;
    }

    public String toString() {
        return x + ", " + y + ", " + z + ", " + w;
    }

    public IVec4 negate() {
        return new IVec4(-x, -y, -z, -w);
    }

    public static IVec4 add(IVec4 a, IVec4 b) {
        return new IVec4(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ(), a.getW() + b.getW());
    }

    public static IVec4 sub(IVec4 a, IVec4 b) {
        return new IVec4(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ(), a.getW() - b.getW());
    }

    public static IVec4 mul(IVec4 a, IVec4 b) {
        return new IVec4(a.getX() * b.getX(), a.getY() * b.getY(), a.getZ() * b.getZ(), a.getW() * b.getW());
    }

    public static IVec4 div(IVec4 a, IVec4 b) {
        return new IVec4(
            (int)Math.floor((float)a.getX() / (float)b.getX()), 
            (int)Math.floor((float)a.getY() / (float)b.getY()), 
            (int)Math.floor((float)a.getZ() / (float)b.getZ()), 
            (int)Math.floor((float)a.getW() / (float)b.getW())
        );
    }

    public IVec4 add(int value) {
        return new IVec4(x + value, y + value, z + value, w + value);
    }

    public IVec4 sub(int value) {
        return new IVec4(x - value, y - value, z - value, w - value);
    }

    public IVec4 mul(int value) {
        return new IVec4(x * value, y * value, z * value, w * value);
    }

    public IVec4 div(int value) {
        return new IVec4(x / value, y / value, z / value, w / value);
    }

    public IVec2 toIVec2() {
        return new IVec2(x, y);
    }

    public IVec3 toIVec3() {
        return new IVec3(x, y, z);
    }

    public Vec2 toVec2() {
        return new Vec2((float)x, (float)y);
    }

    public Vec3 toVec3() {
        return new Vec3((float)x, (float)y, (float)z);
    }

    public Vec4 toVec4() {
        return new Vec4((float)x, (float)y, (float)z, (float)w);
    }

    public static boolean equals(IVec4 a, IVec4 b) {
        return a.getX() == b.getX() && a.getY() == b.getY() && a.getZ() == b.getZ() && a.getW() == b.getW();
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public int getZ() {return z;}
    public int getW() {return w;}
}