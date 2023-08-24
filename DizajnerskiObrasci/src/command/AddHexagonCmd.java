package command;


import adapter.HexAdapter;
import geometry1.Shape;
import mvc.DrawingModel;

public class AddHexagonCmd implements Command{
	
	private DrawingModel model;
	private Hexagon hexagon;

	public AddHexagonCmd(DrawingModel model, HexAdapter hexagon) {
		this.model = model;
		this.hexagon = hexagon;
	}

	@Override
	public void execute() {
		model.add(hexagon);
	}

	@Override
	public void unexecute() {
		model.remove(hexagon);
	}
	
	@Override
	public String toString() {
		return hexagon.toString()+ "added";
	}
}
