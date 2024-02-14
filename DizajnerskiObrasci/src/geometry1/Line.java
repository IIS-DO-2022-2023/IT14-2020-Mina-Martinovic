package geometry1;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape{

	
	private static final long serialVersionUID = 1L;
	protected Point startPoint;
	protected Point endPoint;

	public Line() {
	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		
		setSelected(selected);	
	}

	public Line(Point startPoint, Point endPoint, Color outerColor) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		setOuterColor(outerColor);
	}

	public double length() {
		return this.startPoint.distance(this.endPoint.getX(), this.endPoint.getY());
	}
	
	public boolean equals(Object obj) {

		if (obj instanceof Line) {

			Line pomocna = (Line) obj;
			if (startPoint.equals(pomocna.startPoint) && endPoint.equals(pomocna.endPoint))
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public boolean contains(int x, int y) {
		return (this.startPoint.distance(x, y)+this.endPoint.distance(x, y)) - length() <= 2;
	}
	
	
	public Line clone() {
		return new Line(startPoint.clone(), endPoint.clone(), getOuterColor());
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getOuterColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		
		if(selected) {
			g.setColor(Color.BLUE);
			g.drawRect(startPoint.getX()-2, startPoint.getY()-2, 4, 4);
			g.drawRect(endPoint.getX()-2, endPoint.getY()-2, 4, 4);
		}
	}

/*
	@Override
	public void moveTo(int x, int y) {
		//nije moguce implementirati
		
	}

	@Override
	public void moveBy(int x, int y) {
		startPoint.moveBy(x, y);
		endPoint.moveBy(x, y);
	}
	
	@Override
	public int compareTo(Object o) {
		
		if(o instanceof Line) {
			return (int)(this.length()-((Line)o).length());
		}
		return 0;
	}
	*/

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
		 return "Line: start point x=" + startPoint.getX() + "; start point y=" + startPoint.getY() + "; end point x=" + endPoint.getX() + "; end point y=" + endPoint.getY() + "; color=" + getOuterColor().toString().substring(14).replace('=', '-');	}

}
