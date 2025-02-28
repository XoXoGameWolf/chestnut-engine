package org.wolf.chestnut.vector;

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
        return new IVec4(a.getX() / b.getX(), a.getY() / b.getY(), a.getZ() / b.getZ(), a.getW() / b.getW());
    }

    public static int dot(IVec4 a, IVec4 b) {
        return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ() + a.getW() * b.getW();
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public int getZ() {return z;}
    public int getW() {return w;}
}