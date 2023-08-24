package command;

import java.util.Collections;

import geometry1.Shape;
import mvc.DrawingModel;

public class ToFrontCommand implements Command{

	private DrawingModel model;
	private int shapeIndex;
	private Shape shape;
	
	public ToFrontCommand(DrawingModel model, int shapeIndex, Shape shape) {
		this.model = model;
		this.shapeIndex = shapeIndex;
		this.shape = shape;
	}

	@Override
	public void execute() {
		if(shapeIndex!=model.getShapes().size()-1) {
			Collections.swap(model.getShapes(), shapeIndex+1, shapeIndex);
		}	
	}

	@Override
	public void unexecute() {
		if(shapeIndex!=model.getShapes().size()-1) {
			Collections.swap(model.getShapes(), shapeIndex, shapeIndex+1);
		}
	}
	
	@Override
	public String toString() {
		return shape.toString()+"moved to front";
	}
}
