package command;

import java.util.HashMap;
import java.util.Map;

import geometry1.Shape;
import mvc.DrawingModel;

public class RemoveShape implements ICommand{

	
	private DrawingModel model;
	private Map<Integer, Shape> shapesToDelete = new HashMap<Integer, Shape>();
	
	public RemoveShape(DrawingModel model)
	{
		this.model = model;
	}
	
	@Override
	public void execute() {
	
		for(int i = model.getSelectedShapes().size() -1; i >=0; i--)
		{
			Shape shape = model.getSelectedShapes().get(i);
			
			int index = model.getShapes().indexOf(shape);
			shapesToDelete.put(index, shape);
			model.getShapes().remove(index);
		}
		
	}

	@Override
	public void unexecute() {
	
		for(Map.Entry<Integer, Shape> shape : shapesToDelete.entrySet())
		{
			model.getShapes().add(shape.getKey(), shape.getValue());
		}
		shapesToDelete.clear();
	}

	
}
