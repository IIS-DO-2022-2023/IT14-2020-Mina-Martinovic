package command;

import geometry1.Shape;
import mvc.DrawingModel;

public class ToFront implements ICommand{
	
	private DrawingModel model;
	private int selectedShapeIndex;
	private Shape selectedShape;
	private Shape nextShape;

	public ToFront(int selectedShapeIndex, Shape selectedShape, DrawingModel model)
	{
		this.model = model;
		this.selectedShapeIndex = selectedShapeIndex;
		this.selectedShape = selectedShape;

	}
	
	@Override
	public void execute() {
		
		nextShape = model.getShapes().get(selectedShapeIndex+1);
		
		model.getShapes().set(selectedShapeIndex, nextShape);
	
		model.getShapes().set(selectedShapeIndex+1, selectedShape);
			
	}

	@Override
	public void unexecute() {
		
		model.getShapes().set(selectedShapeIndex, selectedShape);
		model.getShapes().set(selectedShapeIndex+1, nextShape);
		
	}

}
