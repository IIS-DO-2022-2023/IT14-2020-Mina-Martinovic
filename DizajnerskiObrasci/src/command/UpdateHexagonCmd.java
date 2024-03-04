package command;

import adapter.HexagonAdapter;

public class UpdateHexagonCmd implements ICommand{

	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter original;
	
	public UpdateHexagonCmd(HexagonAdapter oldHexagon, HexagonAdapter newHexagon)
	{
		this.oldState = oldHexagon;
		this.newState = newHexagon;
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
