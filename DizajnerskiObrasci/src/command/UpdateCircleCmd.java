package command;

import java.awt.Color;

import geometry1.Circle;

public class UpdateCircleCmd implements Command{

	private Circle oldCircle;
	private Circle newCircle;
	
	private Circle originalState = new Circle();
	
	public UpdateCircleCmd(Circle oldCircle, Circle newCircle) {
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
	}

	@Override
	public void execute() {
		/*
		originalState.getCenter().setX(oldCircle.getCenter().getX());
		originalState.getCenter().setY(oldCircle.getCenter().getY());
		originalState.setRadius(oldCircle.getRadius());
		*/
		originalState = oldCircle.clone();
		
		oldCircle.getCenter().setX(newCircle.getCenter().getX());
		oldCircle.getCenter().setY(newCircle.getCenter().getY());
		oldCircle.setRadius(newCircle.getRadius());
	
		if(newCircle.getOutlineColor() == Color.BLACK && originalState.getOutlineColor() != Color.BLACK) {
			oldCircle.setOutlineColor(originalState.getOutlineColor());
		} else {
			oldCircle.setOutlineColor(newCircle.getOutlineColor());
		}
		
		if(newCircle.getFillColor() == Color.WHITE && originalState.getFillColor() != Color.WHITE) {
			oldCircle.setFillColor(originalState.getFillColor());
		} else {
			oldCircle.setFillColor(newCircle.getFillColor());
		}
	}

	@Override
	public void unexecute() {
		oldCircle.getCenter().setX(originalState.getCenter().getX());
		oldCircle.getCenter().setY(originalState.getCenter().getY());
		oldCircle.setRadius(originalState.getRadius());
		oldCircle.setOutlineColor(originalState.getOutlineColor());
		oldCircle.setFillColor(originalState.getFillColor());
	}

	@Override
	public String toString() {
		return "Updated->"+oldCircle.toString()+"to"+newCircle.toString();
	}
}
