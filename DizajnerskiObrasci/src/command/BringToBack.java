package command;

import geometry1.Shape;
import mvc.DrawingModel;

public class BringToBack implements ICommand{

	private DrawingModel model;
	private Shape selectedShape;
	private int selectedShapeIndex;
	
	public BringToBack(int selectedShapeIndex, Shape selectedShape, DrawingModel model)
	{
		this.selectedShapeIndex = selectedShapeIndex;
		this.selectedShape = selectedShape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		
		model.getShapes().remove(selectedShapeIndex);
		
		model.getShapes().add(0, selectedShape);
	}

	@Override
	public void unexecute() {
		
		model.getShapes().remove(selectedShape);
		
		model.getShapes().add(selectedShapeIndex, selectedShape);
	}

}
