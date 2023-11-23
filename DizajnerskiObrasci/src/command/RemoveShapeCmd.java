package command;

import geometry1.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command{

	private DrawingModel model;
	private Shape shape;
	
	public RemoveShapeCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		model.removeShape(shape);
		model.getSelectedShapes().remove(shape);
	}

	@Override
	public void unexecute() {
		model.getShapes().add(shape);
		model.addSelectedShape(shape);
	}
	
	@Override
	public String toString() {
		return "Removed->" + shape.toString();
	}
}
