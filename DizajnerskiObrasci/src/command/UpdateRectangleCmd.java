package command;

import geometry1.Rectangle;

public class UpdateRectangleCmd implements ICommand{
	
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle original;
	
	public UpdateRectangleCmd(Rectangle oldRectangle, Rectangle newRectangle)
	{
		this.oldState = oldRectangle;
		this.newState = newRectangle;
	}
	
	@Override
	public void execute() {
		
		original = oldState.clone();
		
		oldState.setUpperLeftPoint(newState.getUpperLeftPoint());
		oldState.setHeight(newState.getHeight());
		oldState.setWidth(newState.getWidth());
		oldState.setOuterColor(newState.getOuterColor());
		oldState.setInnerColor(newState.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		
		oldState.setUpperLeftPoint(original.getUpperLeftPoint());
		oldState.setHeight(original.getHeight());
		oldState.setWidth(original.getWidth());
		oldState.setOuterColor(original.getOuterColor());
		oldState.setInnerColor(original.getInnerColor());
		
	}
}
