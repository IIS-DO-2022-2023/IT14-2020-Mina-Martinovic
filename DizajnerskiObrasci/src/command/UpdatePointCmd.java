package command;

import java.awt.Color;

import geometry1.Point;

public class UpdatePointCmd implements Command{

	private Point oldPoint;
	private Point newPoint;
	private Point originalState = new Point();
	
	public UpdatePointCmd( Point oldState, Point newState) {
		this.oldPoint = oldState;
		this.newPoint = newState;
	}

	public void execute() {
		/*
		originalState.setX(oldPoint.getX());
		originalState.setY(oldPoint.getY());
		originalState.setColor(oldPoint.getColor());
		*/
		originalState = oldPoint.clone();
		
		oldPoint.setX(newPoint.getX());
		oldPoint.setY(newPoint.getY());
		
		if(newPoint.getOuterColor() == Color.BLACK && originalState.getOuterColor() != Color.BLACK) {
			oldPoint.setOuterColor(originalState.getOuterColor());
		} else {
			oldPoint.setOuterColor(newPoint.getOuterColor());
		}
	}
 
	@Override
	public void unexecute() {
		oldPoint.setX(originalState.getX());
		oldPoint.setY(originalState.getY());   	
		oldPoint.setOuterColor(originalState.getOuterColor());
	}
	
	@Override
	public String toString() {
		return "Updated->"+oldPoint.toString() + "to" + newPoint.toString();
	}
}
