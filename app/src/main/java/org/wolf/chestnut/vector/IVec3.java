package org.wolf.chestnut.vector;

import java.lang.Math;

public class IVec3 {
    private int x, y, z;

    public IVec3(int _x, int _y, int _z) {
        x = _x;
        y = _y;
        z = _z;
    }

    public String toString() {
        return x + ", " + y + ", " + z;
    }

    public IVec3 negate() {
        return new IVec3(-x, -y, -z);
    }

    public static IVec3 add(IVec3 a, IVec3 b) {
        return new IVec3(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
    }

    public static IVec3 sub(IVec3 a, IVec3 b) {
        return new IVec3(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
    }

    public static IVec3 mul(IVec3 a, IVec3 b) {
        return new IVec3(a.getX() * b.getX(), a.getY() * b.getY(), a.getZ() * b.getZ());
    }

    public static IVec3 div(IVec3 a, IVec3 b) {
        return new IVec3(a.getX() / b.getX(), a.getY() / b.getY(), a.getZ() / b.getZ());
    }

    public IVec3 add(int value) {
        return new IVec3(x + value, y + value, z + value);
    }

    public IVec3 sub(int value) {
        return new IVec3(x - value, y - value, z - value);
    }

    public IVec3 mul(int value) {
        return new IVec3(x * value, y * value, z * value);
    }

    public IVec3 div(int value) {
        return new IVec3(
            (int)Math.floor((float)x / (float)value), 
            (int)Math.floor((float)y / (float)value), 
            (int)Math.floor((float)z / (float)value)
        );
    }

    public IVec2 toIVec2() {
        return new IVec2(x, y);
    }

    public IVec4 toIVec4() {
        return new IVec4(x, y, z, 1);
    }

    public Vec2 toVec2() {
        return new Vec2((float)x, (float)y);
    }

    public Vec3 toVec3() {
        return new Vec3((float)x, (float)y, (float)z);
    }

    public Vec4 toVec4() {
        return new Vec4((float)x, (float)y, (float)z, 1f);
    }

    public static boolean equals(IVec3 a, IVec3 b) {
        return a.getX() == b.getX() && a.getY() == b.getY() && a.getZ() == b.getZ();
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public int getZ() {return z;}
}