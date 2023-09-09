package command;

import hexagon.Hexagon;

import java.awt.Color;

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
		/*
		originalState.getHexagon().setX(oldHex.getHexagon().getX());
		originalState.getHexagon().setY(oldHex.getHexagon().getY());
		originalState.getHexagon().setR(oldHex.getHexagon().getR());
		*/
		originalState = oldHex.clone();
		
		oldHex.getHexagon().setX(newHex.getHexagon().getX());
		oldHex.getHexagon().setY(newHex.getHexagon().getY());
		oldHex.getHexagon().setR(newHex.getHexagon().getR());
		
		if(newHex.getHexagon().getBorderColor() == Color.BLACK && originalState.getHexagon().getBorderColor() != Color.BLACK) {
			oldHex.getHexagon().setBorderColor(originalState.getHexagon().getBorderColor());
		} else {
			oldHex.getHexagon().setBorderColor(newHex.getHexagon().getBorderColor());
		}
		
		if(newHex.getHexagon().getAreaColor()== Color.WHITE && originalState.getHexagon().getAreaColor()!= Color.WHITE) {
			oldHex.getHexagon().setAreaColor(originalState.getHexagon().getAreaColor());
		} else {
			oldHex.getHexagon().setAreaColor(newHex.getHexagon().getAreaColor());
		}
		
	}

	@Override
	public void unexecute() {
		oldHex.getHexagon().setX(originalState.getHexagon().getX());
		oldHex.getHexagon().setY(originalState.getHexagon().getY());
		oldHex.getHexagon().setR(originalState.getHexagon().getR());
		oldHex.getHexagon().setBorderColor(originalState.getHexagon().getBorderColor());
		oldHex.getHexagon().setAreaColor(originalState.getHexagon().getAreaColor());
		
	}

	@Override
	public String toString() {
		return "Updated->"+oldHex.toString()+"to"+newHex.toString();
	}	
}
