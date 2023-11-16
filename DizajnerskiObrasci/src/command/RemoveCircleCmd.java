package command;

import geometry1.Circle;
import geometry1.Shape;
import mvc.DrawingModel;

public class RemoveCircleCmd implements Command{

	private DrawingModel model;
	private Circle circle;
	private Shape shape;

	public RemoveCircleCmd(DrawingModel model, Circle circle) {
		this.model = model;
		this.circle = circle;
	}

	@Override
	public void execute() {
		model.removeShape(circle);
	}

	@Override
	public void unexecute() {
		model.add(circle);	
	}
	
	@Override
	public String toString() {
		return "Removed->" + shape.toString();
	}
}
