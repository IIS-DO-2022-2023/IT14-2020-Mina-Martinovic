package mvc;

import java.awt.Graphics;

public class Line extends Shape{

	protected Point startPoint;
	protected Point endPoint;


	public Line() {
	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getStartPoint() {
		return this.startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	public String toString() {
		return startPoint+"-- >"+endPoint;
	}

}
