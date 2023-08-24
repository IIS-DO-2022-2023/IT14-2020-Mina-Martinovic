package command;

import geometry1.Shape;
import mvc.DrawingModel;

public class UnselectCommand implements Command {
	
	private DrawingModel model;
	private Shape selectedShape;
	
	public UnselectCommand(DrawingModel model, Shape selectedShape) {
		this.model = model;
		this.selectedShape = selectedShape;
	}

	@Override
	public void execute() {
		model.getShapes().get(model.getShapes().indexOf(selectedShape)).setSelected(false);
		model.getSelectedShapes().remove(selectedShape);
	}

	@Override
	public void unexecute() {
		model.getShapes().get(model.getShapes().indexOf(selectedShape)).setSelected(true);
		model.getSelectedShapes().add(selectedShape);
	}
}
