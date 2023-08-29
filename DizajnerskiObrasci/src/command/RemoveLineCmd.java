package command;

import geometry1.Line;
import mvc.DrawingModel;

public class RemoveLineCmd implements Command{

	private Line line;
	private DrawingModel model;
	
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
		return line.toString() +"removed.";
	}
}
