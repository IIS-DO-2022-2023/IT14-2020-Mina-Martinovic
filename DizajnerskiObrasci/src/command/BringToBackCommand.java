package command;

import geometry1.Shape;
import mvc.DrawingModel;

public class BringToBackCommand implements Command {

	private DrawingModel model;
	private int shapeIndex;
	private Shape shape;
	
	public BringToBackCommand (DrawingModel model, int shapeIndex, Shape shape) {
		this.model= model;
		this.shapeIndex = shapeIndex;
		this.shape = shape;
	}
	
	@Override
	public void execute() {
		if(shapeIndex != 0)
		{
			model.getShapes().remove(shape);
			model.getShapes().add(0, shape);
		}
	}
	
	@Override
	public void unexecute() {
		model.getShapes().remove(shape);
		model.getShapes().add(shapeIndex, shape);
	}
	
	@Override
	public String toString() {
		return shape.toString() +"has been brought to back-";
	}
}
