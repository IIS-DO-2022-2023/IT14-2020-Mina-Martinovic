package command;

import geometry1.Line;
import mvc.DrawingModel;
import geometry1.Shape;

public class RemoveLineCmd implements Command{

	private Line line;
	private DrawingModel model;
	private Shape shape;
	
	public RemoveLineCmd(Line line, DrawingModel model) {
		this.line = line;
		this.model = model;
	}

	@Override
	public void execute() {
		model.removeShape(line);

	}

	@Override
	public void unexecute() {
		model.add(line);

	}
	
	@Override
	public String toString() {
		return "Removed->" + shape.toString();
	}
}
