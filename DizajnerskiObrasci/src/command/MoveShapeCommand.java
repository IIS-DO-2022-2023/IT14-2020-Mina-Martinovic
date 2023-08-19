package command;

import java.awt.Shape;

import geometry1.Point;

class MoveShapeCommand implements Command {
	private Point point;
    private Shape shape;
    private int newX;
    private int newY;
    private int prevX;
    private int prevY;

    public MoveShapeCommand(Shape shape, int newX, int newY, Point point) {
        this.shape = shape;
        this.newX = newX;
        this.newY = newY;
        this.point = point;
    }

    @Override
    public void execute() {
    	prevX = point.getX();
        prevY = point.getY();
        //preY = shaep.getY -?> isto i za x !!! proveri
         ((Point) shape).moveTo(newX, newY);
    }

    @Override
    public void unexecute() {
        ((Point) shape).moveTo(prevX, prevY);
    }
}



// Slično implementirajte i ostale konkretne Command klase za ostale akcije
