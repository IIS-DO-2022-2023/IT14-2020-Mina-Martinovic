package geometry1;

import java.awt.Color;
import java.awt.Graphics;

public class Line1 extends Shape1{

	protected Point1 startPoint;
	protected Point1 endPoint;
	protected Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Line1() {
	}

	public Line1(Point1 startPoint, Point1 endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line1(Point1 startPoint, Point1 endPoint, boolean selected) {
		this(startPoint, endPoint);// prva naredba u bloku (telu konstruktora)
		
		setSelected(selected);
		//menja se prilikom dodavanja Shape
		//this.selected = selected;
	}

	public double length() {
		return this.startPoint.distance(this.endPoint.getX(), this.endPoint.getY());
	}
	
	public boolean equals(Object obj) {

		if (obj instanceof Line1) {

			Line1 pomocna = (Line1) obj;
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
	
	

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		
		if(selected) {
			g.setColor(Color.BLUE);
			g.drawRect(startPoint.getX()-2, startPoint.getY()-2, 4, 4);
			g.drawRect(endPoint.getX()-2, endPoint.getY()-2, 4, 4);
		}
	}
	
	

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
		
		if(o instanceof Line1) {
			return (int)(this.length()-((Line1)o).length());
		}
		return 0;
	}

	public void setStartPoint(Point1 startPoint) {
		this.startPoint = startPoint;
	}

	public Point1 getStartPoint() {
		return this.startPoint;
	}

	public Point1 getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point1 endPoint) {
		this.endPoint = endPoint;
	}
	
	public String toString() {
		return startPoint+"-- >"+endPoint;
	}

}
