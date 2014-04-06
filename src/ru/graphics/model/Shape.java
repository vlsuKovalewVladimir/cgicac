package ru.graphics.model;

public abstract class Shape {

    private final double x;
    private final double y;
    private final double c;
    private final double r;

    public Shape(double x, double y, double c, double r) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.r = r;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getC() {
        return c;
    }
    public double getR() {
        return r;
    }
}
