package command;

import adapter.HexAdapter;
import mvc.DrawingModel;

public class RemoveHexagonCmd implements Command{

	private DrawingModel model;
	private HexAdapter hexagon;
	
	public RemoveHexagonCmd(DrawingModel model, HexAdapter hexagon) {
		this.model = model;
		this.hexagon = hexagon;
	}

	@Override
	public void execute() {
		model.removeShape(hexagon);
	}

	@Override
	public void unexecute() {
		model.add(hexagon);	
	}
	
	@Override
	public String toString() {
		return hexagon.toString() +"removed.";
	}
}
