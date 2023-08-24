package command;

import geometry1.Circle;
import mvc.DrawingModel;

public class AddCircleCmd implements Command {

	private DrawingModel model;
	private Circle circle;
	
	public AddCircleCmd(DrawingModel model, Circle circle) {
		this.model = model;
		this.circle = circle;
	}

	@Override
	public void execute() {
		model.addShape(circle);
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
