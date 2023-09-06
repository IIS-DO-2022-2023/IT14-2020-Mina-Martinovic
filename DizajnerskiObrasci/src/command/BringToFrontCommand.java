package command;

import geometry1.Shape;
import mvc.DrawingModel;

public class BringToFrontCommand implements Command {
	
	private DrawingModel model;
    private int prevIndex;
    private Shape shape;
 

    public BringToFrontCommand(DrawingModel model,  Shape shape) {
		this.model = model;
		this.shape = shape;
	}
    
    @Override
	public void execute() {
    	prevIndex=model.getIndexOfShape(shape);
		model.getShapes().remove(shape);
		model.getShapes().add(shape);
	}
    

	@Override
	public void unexecute() {
		model.getShapes().remove(shape);
		model.getShapes().add(prevIndex, shape);
	}
	
	@Override
	public String toString() {
		return shape.toString() +"has been brought to front";
	}
}
