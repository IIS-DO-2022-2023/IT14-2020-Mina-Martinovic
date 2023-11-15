package command;

import geometry1.Circle;
import mvc.DrawingModel;

public class AddCircleCmd implements Command {

	private DrawingModel model;
	private Circle circle;
	
	public AddCircleCmd(DrawingModel model, Circle circle) {
		this.model = model; //azuriranje liste oblika i ostalo
		this.circle = circle;
	}

	@Override
	public void execute() {
		model.add(circle);
	}

	@Override
	public void unexecute() {
		model.removeShape(circle);
	}
	
	@Override
	public String toString() {
		return circle.toString() +"added.";
	}
}
