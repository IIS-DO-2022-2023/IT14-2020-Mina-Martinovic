package command;

import java.awt.Color;

import geometry1.Point;
import geometry1.Rectangle;

public class UpdateRectangleCmd implements Command{

	private Rectangle oldRect;
	private Rectangle newRect;
	private Rectangle originalState = new Rectangle(new Point(), 1, 1);
	
	public UpdateRectangleCmd(Rectangle oldRect, Rectangle newRect) {
		this.oldRect = oldRect;
		this.newRect = newRect;
	}

	@Override
	public void execute() {
		/*
		originalState.getUpperLeftPoint().setX(oldRect.getUpperLeftPoint().getX());
		originalState.getUpperLeftPoint().setY(oldRect.getUpperLeftPoint().getY());
		originalState.setHeight(oldRect.getHeight());
		originalState.setWidth(oldRect.getWidth());	
		*/
		originalState = oldRect.clone();
		
		oldRect.getUpperLeftPoint().setX(newRect.getUpperLeftPoint().getX());
		oldRect.getUpperLeftPoint().setY(newRect.getUpperLeftPoint().getY());
		//oldRect.setHeight(newRect.getHeight());
		//oldRect.setWidth(newRect.getWidth());	
		
		if(newRect.getOuterColor() == Color.BLACK && originalState.getOuterColor() != Color.BLACK) {
			oldRect.setOuterColor(originalState.getOuterColor());
		} else {
			oldRect.setOuterColor(newRect.getOuterColor());
		} //Ako je boja ivice newRect crna, a boja ivice originalState nije crna, tada će postaviti boju ivice
		//oldRect na boju ivice originalState. U suprotnom, ako boja ivice newRect nije crna ili 
		//ako je boja ivice originalState crna, postaviće boju ivice oldRect na boju ivice newRect.
		
		if(newRect.getInnerColor() == Color.WHITE && originalState.getInnerColor() != Color.WHITE) {
			oldRect.setInnerColor(originalState.getInnerColor());
		} else {
			oldRect.setInnerColor(newRect.getInnerColor());
		}
	}

	@Override
	public void unexecute() {
		oldRect.getUpperLeftPoint().setX(originalState.getUpperLeftPoint().getX());
		oldRect.getUpperLeftPoint().setY(originalState.getUpperLeftPoint().getY());
		oldRect.setHeight(originalState.getHeight());
		oldRect.setWidth(originalState.getWidth());
		oldRect.setOuterColor(originalState.getOuterColor());
		oldRect.setInnerColor(originalState.getInnerColor());

	}
	
	@Override
	public String toString() {
		return "Updated->" + oldRect.toString() + "to" + newRect.toString();
	}
}
