package command;

import hexagon.Hexagon;
import adapter.HexAdapter;

public class UpdateHexagonCmd implements Command{

	private HexAdapter oldHex;
	private HexAdapter newHex;
	private HexAdapter originalState= new HexAdapter(new Hexagon(0, 0, 0)); //x, y, r
	
	public UpdateHexagonCmd(HexAdapter oldHex, HexAdapter newHex) {
		this.oldHex = oldHex;
		this.newHex = newHex;
	}

	@Override
	public void execute() {
		originalState.getHexagon().setX(oldHex.getHexagon().getX());
		originalState.getHexagon().setY(oldHex.getHexagon().getY());
		originalState.getHexagon().setR(oldHex.getHexagon().getR());
		
		oldHex.getHexagon().setX(newHex.getHexagon().getX());
		oldHex.getHexagon().setY(newHex.getHexagon().getY());
		oldHex.getHexagon().setR(newHex.getHexagon().getR());
		
	}

	@Override
	public void unexecute() {
		oldHex.getHexagon().setX(originalState.getHexagon().getX());
		oldHex.getHexagon().setY(originalState.getHexagon().getY());
		oldHex.getHexagon().setR(originalState.getHexagon().getR());
		
	}

	@Override
	public String toString() {
		return "Updated->"+oldHex.toString()+"to"+newHex.toString();
	}	
}
