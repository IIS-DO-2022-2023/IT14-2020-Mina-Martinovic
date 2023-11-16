package command;

import geometry1.Rectangle;
import geometry1.Shape;
import mvc.DrawingModel;

public class RemoveRectangleCmd implements Command{

	private DrawingModel model;
	private Rectangle rectangle;
	private Shape shape;

	public RemoveRectangleCmd(DrawingModel model, Rectangle rectangle) {
		this.model = model;
		this.rectangle = rectangle;
	}

	@Override
	public void execute() {
		model.removeShape(rectangle);
	}
	
	@Override
	public void unexecute() {
		model.add(rectangle);	
	}

	@Override
	public String toString() {
		return "Removed->" + shape.toString();
	}
	
}
