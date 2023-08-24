package command;

import geometry1.Shape;
import geometry1.ShapeManager;
import mvc.DrawingModel;

class BringToFrontCommand implements Command {
	
	private DrawingModel model;
   // private ShapeManager shapeManager;
    private int prevIndex;
    private Shape shape;
   // private boolean wasSentToBack = false;

    /*
    public BringToFrontCommand(ShapeManager shapeManager, int shapeIndex) {
        this.shapeManager = shapeManager;
        this.shapeIndex = shapeIndex;
    }
    */

    public BringToFrontCommand(DrawingModel model,  Shape shape) {
		this.model = model;
		this.shape = shape;
	}
    
    /*
    @Override
    public void execute() {
        if (wasSentToBack) {
            shapeManager.sendToBack(shapeIndex);
            wasSentToBack = false;
        } else {
            shapeManager.bringToFront(shapeIndex);
        }
    }

    @Override
    public void unexecute() {
        shapeManager.sendToBack(shapeIndex);
        wasSentToBack = true;
    }
    */
    
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
