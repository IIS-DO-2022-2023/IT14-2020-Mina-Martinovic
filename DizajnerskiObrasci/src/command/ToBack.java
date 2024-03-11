package command;

import geometry1.Shape;
import mvc.DrawingModel;

public class ToBack implements ICommand{

	private DrawingModel model;
	private Shape selectedShape;
	private int selectedShapeIndex;
	
	private Shape previousShape;
	
	public ToBack(int selectedShapeIndex, Shape selectedShape, DrawingModel model)
	{
		this.selectedShapeIndex = selectedShapeIndex;
		this.selectedShape = selectedShape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		
		previousShape = model.getShapes().get(selectedShapeIndex-1);
		
		 model.getShapes().set(selectedShapeIndex, previousShape);
		 
		 model.getShapes().set(selectedShapeIndex-1, selectedShape);
	}

	@Override
	public void unexecute() {	
		
		model.getShapes().set(selectedShapeIndex, selectedShape);
		 
		model.getShapes().set(selectedShapeIndex-1, previousShape);
	}

}
