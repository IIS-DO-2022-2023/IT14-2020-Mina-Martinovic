package command;

import java.awt.Shape;

class MoveShapeCommand implements Command {
    private Shape shape;
    private int newX;
    private int newY;
    private int prevX;
    private int prevY;

    public MoveShapeCommand(Shape shape, int newX, int newY) {
        this.shape = shape;
        this.newX = newX;
        this.newY = newY;
    }

    @Override
    public void execute() {
   //     prevX = shape.getX();
    //    prevY = shape.getY();
   //     shape.moveTo(newX, newY);
    }

    @Override
    public void unexecute() {
     //   shape.moveTo(prevX, prevY);
    }
}

class BringToFrontCommand implements Command {
    private Shape shape;
    private ShapeManager shapeManager;

    public BringToFrontCommand(Shape shape, ShapeManager shapeManager) {
        this.shape = shape;
        this.shapeManager = shapeManager;
    }

    @Override
    public void execute() {
        shapeManager.bringToFront(shape);
    }

    @Override
    public void unexecute() {
        shapeManager.sendToBack(shape);
    }
}

// Slično implementirajte i ostale konkretne Command klase za ostale akcije
