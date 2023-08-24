package command;

import geometry1.Circle;
import mvc.DrawingModel;

public class RemoveCircleCmd implements Command{

	private DrawingModel model;
	private Circle circle;

	public RemoveCircleCmd(DrawingModel model, Circle circle) {
		this.model = model;
		this.circle = circle;
	}

	@Override
	public void execute() {
		model.remove(circle);
	}

	@Override
	public void unexecute() {
		model.add(circle);	
	}
	
	@Override
	public String toString() {
		return circle.toString() +"removed.";
	}
}
