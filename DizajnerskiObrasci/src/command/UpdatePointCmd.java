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
		originalState.setX(oldPoint.getX());
		originalState.setY(oldPoint.getY());
		originalState.setColor(oldPoint.getColor());
		
		oldPoint.setX(newPoint.getX());
		oldPoint.setY(newPoint.getY());
		oldPoint.setColor(newPoint.getColor());
		
		if(newPoint.getOutlineColor() == Color.BLACK && originalState.getOutlineColor() != Color.BLACK) {
			oldPoint.setOutlineColor(originalState.getOutlineColor());
		} else {
			oldPoint.setOutlineColor(newPoint.getOutlineColor());
		}
	}
 
	@Override
	public void unexecute() {
		oldPoint.setX(originalState.getX());
		oldPoint.setY(originalState.getY());   	
		oldPoint.setOutlineColor(originalState.getOutlineColor());
	}
	
	@Override
	public String toString() {
		return "Updated->"+oldPoint.toString() + "to" + newPoint.toString();
	}
}
