package command;

import geometry1.Donut;
import mvc.DrawingModel;

public class AddDonutCmd implements Command {

	private DrawingModel model;
	private Donut donut;
	
	public AddDonutCmd(DrawingModel model, Donut donut) {
		this.model= model;
		this.donut = donut;
	}
	
	@Override
	public void execute() {
		model.add(donut);
	}

	@Override
	public void unexecute() {
		model.removeShape(donut);
	}
	
	@Override
	public String toString() {
		return donut.toString()+" added";
	}
}
