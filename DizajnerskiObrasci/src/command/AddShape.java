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
		
		if(shape.isSelected())
		{
			model.getSelectedShapes().add(shape);
		}
		
	}

	@Override
	public void unexecute() {
		
		if(shape.isSelected())
		{
			model.getSelectedShapes().remove(shape);
		}
		model.getShapes().remove(shape);
	}

	
}
