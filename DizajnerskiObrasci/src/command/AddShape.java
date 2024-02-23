package command;

import geometry1.Shape;
import mvc.DrawingModel;

public class AddShape implements ICommand{

	private Shape shape;
	private DrawingModel model;
	
	public AddShape(Shape shape, DrawingModel model)
	{
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		
		model.getShapes().add(shape);
		
	}

	@Override
	public void unexecute() {
		
		model.getShapes().remove(shape);
	}

	
}
