package command;

import geometry1.Rectangle;

public class UpdateRectangleCmd implements Command{

	private Rectangle oldRect;
	private Rectangle newRect;
	private Rectangle originalState;
	
	public UpdateRectangleCmd(Rectangle oldRect, Rectangle newRect) {
		this.oldRect = oldRect;
		this.newRect = newRect;
	}

	@Override
	public void execute() {
		originalState.getUpperLeftPoint().setX(oldRect.getUpperLeftPoint().getX());
		originalState.getUpperLeftPoint().setY(oldRect.getUpperLeftPoint().getY());
		originalState.setHeight(oldRect.getHeight());
		originalState.setWidth(oldRect.getWidth());	
		
		oldRect.getUpperLeftPoint().setX(newRect.getUpperLeftPoint().getX());
		oldRect.getUpperLeftPoint().setY(newRect.getUpperLeftPoint().getY());
		oldRect.setHeight(newRect.getHeight());
		oldRect.setWidth(newRect.getWidth());	
	}

	@Override
	public void unexecute() {
		oldRect.getUpperLeftPoint().setX(originalState.getUpperLeftPoint().getX());
		oldRect.getUpperLeftPoint().setY(originalState.getUpperLeftPoint().getY());
		oldRect.setHeight(originalState.getHeight());
		oldRect.setWidth(originalState.getWidth());

	}
	
	@Override
	public String toString() {
		return "Updated->" + oldRect.toString() + "to" + newRect.toString();
	}
}
