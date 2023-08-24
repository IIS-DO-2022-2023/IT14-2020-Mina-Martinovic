package command;

import geometry1.Shape;
import mvc.DrawingModel;

public class SelectCommand implements Command {

	private DrawingModel model;
	private Shape selectedShape;
	
	public SelectCommand(DrawingModel model, Shape selectedShape) {
		this.model = model;
		this.selectedShape = selectedShape;
	}

	@Override
	public void execute() {
		selectedShape.setSelected(true);
		model.addSelectedShape(selectedShape);
	}

	@Override
	public void unexecute() {
		selectedShape.setSelected(false);
		model.getSelectedShapes().remove(selectedShape);
	}

	@Override
	public String toString() {
		return selectedShape.toString()+"selected";
	}
}
