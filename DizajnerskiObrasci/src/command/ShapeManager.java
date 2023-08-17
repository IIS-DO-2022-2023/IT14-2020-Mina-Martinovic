package command;

import java.util.ArrayList;
import java.util.List;

class Shape {
    private int x;
    private int y;

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveTo(int newX, int newY) {
        x = newX;
        y = newY;
    }
}

class ShapeManager {
    private List<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    public void bringToFront(java.awt.Shape shape) {
        shapes.remove(shape);
        shapes.add((Shape) shape);
    }

    public void sendToBack(java.awt.Shape shape) {
        shapes.remove(shape);
        shapes.add(0, (Shape) shape);
    }

    // Dodajte ostale metode za manipulaciju oblicima

    public List<Shape> getShapes() {
        return shapes;
    }
}
