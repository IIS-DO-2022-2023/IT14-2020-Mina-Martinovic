package command;
import java.awt.Color;

import geometry1.Line;

public class UpdateLineCmd implements Command{

	private Line oldLine;
	private Line newLine;
	private Line originalState = new Line();

	public UpdateLineCmd(Line oldLine, Line newLine) {
		this.oldLine = oldLine;
		this.newLine = newLine;
	}

	@Override
	public void execute() {
		/*
		originalState.getStartPoint().setX(oldLine.getStartPoint().getX());
		originalState.getStartPoint().setY(oldLine.getStartPoint().getY());
		originalState.getEndPoint().setX(oldLine.getEndPoint().getX());
		originalState.getEndPoint().setY(oldLine.getEndPoint().getY());
		*/
		originalState = oldLine.clone();

		oldLine.getStartPoint().setX(newLine.getStartPoint().getX());
		oldLine.getStartPoint().setY(newLine.getStartPoint().getY());
		oldLine.getEndPoint().setX(newLine.getEndPoint().getX());
		oldLine.getEndPoint().setY(newLine.getEndPoint().getY());
		oldLine.setColor(newLine.getColor());

	}

	@Override
	public void unexecute() {
		oldLine.getStartPoint().setX(originalState.getStartPoint().getX());
		oldLine.getStartPoint().setY(originalState.getStartPoint().getY());
		oldLine.getEndPoint().setX(originalState.getEndPoint().getX());
		oldLine.getEndPoint().setY(originalState.getEndPoint().getY());
		oldLine.setOutlineColor(originalState.getOutlineColor());

	}
	
	@Override
	public String toString() {
		return "Updated->"+oldLine.toString()+"to"+newLine.toString();
	}
}
