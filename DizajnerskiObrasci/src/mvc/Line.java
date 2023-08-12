package mvc;

import java.awt.Graphics;

public class Line1 extends Shape1{

	protected Point1 startPoint;
	protected Point1 endPoint;


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

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
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
