package org.wolf.chestnut.vector;

public class IVec2 {
    private int x, y;

    public IVec2(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public String toString() {
        return x + ", " + y;
    }

    public int getX() {return x;}
    public int getY() {return y;}
}