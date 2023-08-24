package command;

import geometry1.Shape;
import mvc.DrawingModel;

public class RemoveShapeCommand implements Command{

	private DrawingModel model;
	private Shape shape;
	
	public RemoveShapeCommand(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		model.remove(shape);
		model.getSelectedShapes().remove(shape);
	}

	@Override
	public void unexecute() {
		model.getShapes().add(shape);
	}
	
	@Override
	public String toString() {
		return shape.toString()+"deleted";
	}
}
