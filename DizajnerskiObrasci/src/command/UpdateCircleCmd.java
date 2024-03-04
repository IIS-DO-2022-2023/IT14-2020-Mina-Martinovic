package command;

import geometry1.Circle;
import geometry1.Line;

public class UpdateCircleCmd implements ICommand{

	private Circle oldState;
	private Circle newState;
	private Circle original;
	
	public UpdateCircleCmd(Circle oldCircle, Circle newCircle)
	{
		this.oldState = oldCircle;
		this.newState = newCircle;
	}
	
	@Override
	public void execute() {
		
		original = oldState.clone();
		
		oldState.setCenter(newState.getCenter());
		oldState.setRadius(newState.getRadius());
		oldState.setOuterColor(newState.getOuterColor());
		oldState.setInnerColor(newState.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		
		oldState.setCenter(original.getCenter());
		oldState.setRadius(original.getRadius());
		oldState.setOuterColor(original.getOuterColor());
		oldState.setInnerColor(original.getInnerColor());
		
	}

	
}
