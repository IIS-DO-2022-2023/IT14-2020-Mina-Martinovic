package command;

import geometry1.Point;

public class UpdatePointCmd implements ICommand{

	private Point oldState;
	private Point newState;
	private Point original;
	
	public UpdatePointCmd(Point oldPoint, Point newPoint)
	{
		this.oldState = oldPoint;
		this.newState = newPoint;
	}
	
	@Override
	public void execute() {
	
		original = oldState.clone();
		
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setOuterColor(newState.getOuterColor());
	}

	@Override
	public void unexecute() {
		
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setOuterColor(original.getOuterColor());
	}

}
