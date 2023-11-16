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
	
		if(newCircle.getOuterColor() == Color.BLACK && originalState.getOuterColor() != Color.BLACK) {
			oldCircle.setOuterColor(originalState.getOuterColor());
		} else {
			oldCircle.setOuterColor(newCircle.getOuterColor());
		}
		
		if(newCircle.getInnerColor() == Color.WHITE && originalState.getInnerColor() != Color.WHITE) {
			oldCircle.setInnerColor(originalState.getInnerColor());
		} else {
			oldCircle.setInnerColor(newCircle.getInnerColor());
		}
	}

	@Override
	public void unexecute() {
		oldCircle.getCenter().setX(originalState.getCenter().getX());
		oldCircle.getCenter().setY(originalState.getCenter().getY());
		oldCircle.setRadius(originalState.getRadius());
		oldCircle.setOuterColor(originalState.getOuterColor());
		oldCircle.setInnerColor(originalState.getInnerColor());
	}

	@Override
	public String toString() {
		return "Updated->"+oldCircle.toString()+"->"+newCircle.toString();
	}
}
