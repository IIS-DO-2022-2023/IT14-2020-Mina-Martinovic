package command;

import geometry1.Donut;
import mvc.DrawingModel;

public class RemoveDonutCmd implements Command{

	private DrawingModel model;
	private Donut donut;
	
	public RemoveDonutCmd (DrawingModel model, Donut donut) {
		this.model = model;
		this.donut = donut;
	}
	
	@Override
	public void execute() {
		model.removeShape(donut);	
	}
	@Override
	public void unexecute() {
		model.add(donut);	
	}
	
	@Override
	public String toString() {
		return donut.toString() +"removed.";
	}
}
