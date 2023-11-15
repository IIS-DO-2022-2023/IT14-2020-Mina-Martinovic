package command;

import geometry1.Rectangle;
import mvc.DrawingModel;

public class AddRectangleCmd implements Command{

	private DrawingModel model;
	private Rectangle rectangle;

	public AddRectangleCmd(DrawingModel model, Rectangle rectangle) {
		this.model = model;
		this.rectangle = rectangle;
	}
	
	@Override
	public void execute() {
		model.add(rectangle);
	}

	@Override
	public void unexecute() {
		model.removeShape(rectangle);
	}
	
	@Override
	public String toString() {
		return "Added->"+rectangle.toString();
	}
}
