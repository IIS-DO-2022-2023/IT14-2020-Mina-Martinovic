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
	System.out.println("provera brisanja");
		System.out.println(model.getSelectedShapes().size());
		
		for(int i = model.getSelectedShapes().size() -1; i >=0; i--)
		{
			System.out.println("provera selekcije: " + model.getSelectedShapes().size());
			Shape shape = model.getSelectedShapes().get(i);
			
			int index = model.getShapes().indexOf(shape);
			System.out.println("provera indeksa: " +index);
			shapesToDelete.put(index, shape);
			//model.getSelectedShapes().remove(shape);
			//model.getShapes().remove(index);
		}

		for(Map.Entry<Integer, Shape> shape : shapesToDelete.entrySet())
		{
			System.out.println("INDEKSI " +shape.getKey());
			model.getShapes().remove(shape.getValue());
		}
		
		model.getSelectedShapes().clear();
		System.out.println(model.getShapes().size());
		System.out.println(shapesToDelete.size());
		System.out.println(model.getSelectedShapes().size());
	}

	@Override
	public void unexecute() {
	
		for(Map.Entry<Integer, Shape> shape : shapesToDelete.entrySet())
		{
			model.getShapes().add(shape.getKey(), shape.getValue());
			model.getSelectedShapes().add(shape.getValue());
		}
		shapesToDelete.clear();
	}

	
}
