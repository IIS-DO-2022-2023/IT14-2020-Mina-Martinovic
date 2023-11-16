package command;

import java.util.Collections;

import geometry1.Shape;
import mvc.DrawingModel;

public class ToBackCommand implements Command {

	private DrawingModel model;
	private int shapeIndex;
	private Shape shape;
	
	public ToBackCommand(DrawingModel model, int shapeIndex, Shape shape) {
		this.model = model;
		this.shapeIndex = shapeIndex;
		this.shape = shape;
	}

	@Override
	public void execute() {
		if(shapeIndex!=0) { 
			Collections.swap(model.getShapes(), shapeIndex-1, shapeIndex);//pozicija po pozicija, jedan za drugim
		}	
	}

	@Override
	public void unexecute() {
		if(shapeIndex!=0) {
			Collections.swap(model.getShapes(), shapeIndex, shapeIndex-1);
		}
	}
	
	@Override
	public String toString() {
		return "Moved to back->" + shape.toString();
	}
}
