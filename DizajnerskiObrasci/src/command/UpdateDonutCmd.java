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
		
		if(newDonut.getOutlineColor() == Color.BLACK && originalState.getOutlineColor() != Color.BLACK) {
			oldDonut.setOutlineColor(originalState.getOutlineColor());
		} else {
			oldDonut.setOutlineColor(newDonut.getOutlineColor());
		}

		if(newDonut.getFillColor() == Color.WHITE && originalState.getFillColor() != Color.WHITE) {
			oldDonut.setFillColor(originalState.getFillColor());
		} else {
			oldDonut.setFillColor(newDonut.getFillColor());
		}

	}
	@Override
	public void unexecute() {
		oldDonut.setOuterRadius(originalState.getOuterRadius());
		oldDonut.setInnerRadius(originalState.getInnerRadius());
		oldDonut.setCenter(originalState.getCenter());	
		oldDonut.setOutlineColor(originalState.getOutlineColor());
		oldDonut.setFillColor(originalState.getFillColor());	
	}
	
	@Override
	public String toString() {
		return "Updated->" + oldDonut.toString() +"to"+ newDonut.toString();
	}
}
	

