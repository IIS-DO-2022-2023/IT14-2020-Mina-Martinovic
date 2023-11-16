package command;

import java.awt.Color;

import geometry1.Donut;

public class UpdateDonutCmd implements Command{

	private Donut oldDonut;
	private Donut newDonut;
	private Donut originalState;

	public UpdateDonutCmd(Donut oldDonut, Donut newDonut) {
		this.oldDonut = oldDonut;
		this.newDonut = newDonut;
	}
	
	@Override
	public void execute() {
		originalState = oldDonut.clone();
		
		//oldDonut.getCenter().setX(newDonut.getCenter().getX());
		//oldDonut.getCenter().setY(newDonut.getCenter().getY());
		oldDonut.setInnerRadius(newDonut.getInnerRadius());
		oldDonut.setOuterRadius(newDonut.getOuterRadius());
		oldDonut.setCenter(newDonut.getCenter().clone());
		
		if(newDonut.getOuterColor() == Color.BLACK && originalState.getOuterColor() != Color.BLACK) {
			oldDonut.setOuterColor(originalState.getOuterColor());
		} else {
			oldDonut.setOuterColor(newDonut.getOuterColor());
		}

		if(newDonut.getInnerColor() == Color.WHITE && originalState.getInnerColor() != Color.WHITE) {
			oldDonut.setInnerColor(originalState.getInnerColor());
		} else {
			oldDonut.setInnerColor(newDonut.getInnerColor());
		}

	}
	@Override
	public void unexecute() {
		oldDonut.setOuterRadius(originalState.getOuterRadius());
		oldDonut.setInnerRadius(originalState.getInnerRadius());
		oldDonut.setCenter(originalState.getCenter());	
		oldDonut.setOuterColor(originalState.getOuterColor());
		oldDonut.setInnerColor(originalState.getInnerColor());	
	}
	
	@Override
	public String toString() {
		return "Updated->" + oldDonut.toString() +"to"+ newDonut.toString();
	}
}
	

