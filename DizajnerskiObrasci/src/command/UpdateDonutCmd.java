package command;

import geometry1.Donut;
import mvc.DrawingModel;

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
		originalState.getCenter().setX(oldDonut.getCenter().getX());
		originalState.getCenter().setY(oldDonut.getCenter().getY());
		originalState.setInnerRadius(oldDonut.getInnerRadius());
		originalState.setOuterRadius(oldDonut.getOuterRadius());
		
		oldDonut.getCenter().setX(newDonut.getCenter().getX());
		oldDonut.getCenter().setY(newDonut.getCenter().getY());
		oldDonut.setInnerRadius(newDonut.getInnerRadius());
		oldDonut.setOuterRadius(newDonut.getOuterRadius());

	}
	@Override
	public void unexecute() {
		oldDonut.setOuterRadius(originalState.getOuterRadius());
		oldDonut.setInnerRadius(originalState.getInnerRadius());
		oldDonut.setCenter(originalState.getCenter());	
	}
	
	@Override
	public String toString() {
		return "Updated->" + oldDonut.toString() +"to"+ newDonut.toString();
	}
}
	

