package command;

import geometry1.Circle;
import geometry1.Donut;

public class UpdateDonutCmd implements ICommand{

	private Donut oldState;
	private Donut newState;
	private Donut original;
	
	public UpdateDonutCmd(Donut oldDonut, Donut newDonut)
	{
		this.oldState = oldDonut;
		this.newState = newDonut;
	}
	
	@Override
	public void execute() {
		
		original = oldState.clone();
		
		oldState.setCenter(newState.getCenter());
		oldState.setOuterRadius(newState.getOuterRadius());
		oldState.setInnerRadius(newState.getInnerRadius());
		oldState.setOuterColor(newState.getOuterColor());
		oldState.setInnerColor(newState.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		
		oldState.setCenter(original.getCenter());
		oldState.setOuterRadius(original.getOuterRadius());
		oldState.setInnerRadius(original.getInnerRadius());
		oldState.setOuterColor(original.getOuterColor());
		oldState.setInnerColor(original.getInnerColor());
		
	}
	
}
