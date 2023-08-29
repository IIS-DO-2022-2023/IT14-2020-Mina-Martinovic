package command;

import geometry1.Rectangle;
import mvc.DrawingModel;

public class RemoveRectangleCmd implements Command{

	private DrawingModel model;
	private Rectangle rectangle;

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
		return rectangle.toString()+"removed";
	}
	
}
