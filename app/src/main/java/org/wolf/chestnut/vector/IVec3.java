package org.wolf.chestnut.vector;

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

    public static int dot(IVec3 a, IVec3 b) {
        return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ();
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public int getZ() {return z;}
}