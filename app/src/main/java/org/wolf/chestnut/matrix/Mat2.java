package org.wolf.chestnut.matrix;

import org.wolf.chestnut.vector.Vec2;

public class Mat2 {
    private Vec2 x, y;

    public Mat2(Vec2 _x, Vec2 _y) {
        x = _x;
        y = _y;
    }

    public static Mat2 identity() {
        return new Mat2(
            new Vec2(1f, 0f),
            new Vec2(0f, 1f)
        );
    }

    public static Mat2 translate(float value) {
        return new Mat2(
            new Vec2(1f, value),
            new Vec2(0f, 1f)
        );
    }

    public static Mat2 rotate(float value) {
        return new Mat2(
            new Vec2(1f, 0f),
            new Vec2(0f, 1f)
        );
    }

    public static Mat2 scale(float value) {
        return new Mat2(
            new Vec2(value, 0f),
            new Vec2(0f, 1f)
        );
    }

    public static Mat2 multiply(Mat2 a, Mat2 b) {
        Vec2 a0 = a.getX();
        Vec2 a1 = a.getY();
        Vec2 b0 = new Vec2(b.getX().getX(), b.getX().getY());
        Vec2 b1 = new Vec2(b.getY().getX(), b.getY().getY());

        return new Mat2(
            new Vec2(a0.getX() * b0.getX() + a1.getX() * b1.getX(), a0.getY() * b0.getX() + a1.getY() * b1.getX()),
            new Vec2(a0.getX() * b0.getY() + a1.getX() * b1.getY(), a0.getY() * b0.getY() + a1.getY() * b1.getY())
        );
    }

    public Vec2 getX() {return x;}
    public Vec2 getY() {return y;}
}