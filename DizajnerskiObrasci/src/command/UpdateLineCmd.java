package command;

import geometry1.Line;

public class UpdateLineCmd implements ICommand{

	private Line oldState;
	private Line newState;
	private Line original;
	
	public UpdateLineCmd(Line oldLine, Line newLine)
	{
		this.oldState = oldLine;
		this.newState = newLine;
	}
	
	@Override
	public void execute() {
		original= oldState.clone();
		
		oldState.setStartPoint(newState.getStartPoint());
		oldState.setEndPoint(newState.getEndPoint());
		oldState.setOuterColor(newState.getOuterColor());
		
	}

	@Override
	public void unexecute() {
		oldState.setStartPoint(original.getStartPoint());
		oldState.setEndPoint(original.getEndPoint());
		oldState.setOuterColor(original.getOuterColor());
		
	}

}
