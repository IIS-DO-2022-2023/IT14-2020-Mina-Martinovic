package command;

import geometry1.Line;
import mvc.DrawingModel;

public class AddLineCmd implements Command{

	private Line line;
	private DrawingModel model;
	
	public AddLineCmd(Line line, DrawingModel model) {
		this.line = line;
		this.model = model;
	}

	@Override
	public void execute() {
		model.add(line);
	}

	@Override
	public void unexecute() {
		model.removeShape(line);

	}
	
	@Override
	public String toString() {
		return "Added->"+line.toString();
	}
}
