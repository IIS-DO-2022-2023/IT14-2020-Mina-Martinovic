package command;

import java.awt.Color;

import geometry1.Circle;
import geometry1.Point;

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
		originalState.getCenter().setX(oldCircle.getCenter().getX());
		originalState.getCenter().setY(oldCircle.getCenter().getY());
		originalState.setRadius(oldCircle.getRadius());
		
		oldCircle.getCenter().setX(newCircle.getCenter().getX());
		oldCircle.getCenter().setY(newCircle.getCenter().getY());
		oldCircle.setRadius(newCircle.getRadius());
	
	}

	@Override
	public void unexecute() {
		oldCircle.getCenter().setX(originalState.getCenter().getX());
		oldCircle.getCenter().setY(originalState.getCenter().getY());
		oldCircle.setRadius(originalState.getRadius());
	}

	@Override
	public String toString() {
		return "Updated->"+oldCircle.toString()+"to"+newCircle.toString();
	}
}
