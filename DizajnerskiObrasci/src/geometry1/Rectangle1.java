package geometry1;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle1 extends Shape1 {

	private Point1 upperLeftPoint;
	private int width;
	private int height;
	protected Color color;
	protected Color edgecolor;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setEdgeColor(Color edgecolor) {
		this.edgecolor = edgecolor;
	}

	public Rectangle1() {
	}

	public Rectangle1(Point1 upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
	}

	public Rectangle1(Point1 upperLeftPoint, int width, int height, boolean selected) {
		this(upperLeftPoint, width, height);

		setSelected(selected);
		// menja se prilikom dodavanja Shape
		// this.selected = selected;
	}

	public int area() {
		return this.width * this.height;
	}

	public int circumference() {
		return 2 * this.width + 2 * this.height;
	}

	public boolean equals(Object obj) {

		if (obj instanceof Rectangle1) {

			Rectangle1 pomocna = (Rectangle1) obj;
			if (this.upperLeftPoint.equals(pomocna.upperLeftPoint) && this.width == pomocna.width
					&& this.height == pomocna.height)
				return true;
			else
				return false;
		} else
			return false;

	}

	public boolean contains(int x, int y) {
		if (x >= upperLeftPoint.getX() && x <= upperLeftPoint.getX() + width && y >= upperLeftPoint.getY()
				&& y <= upperLeftPoint.getY() + height)
			return true;
		return false;
	}

	public boolean contains(Point1 p) {
		if (p.getX() >= upperLeftPoint.getX() && p.getX() <= upperLeftPoint.getX() + width
				&& p.getY() >= upperLeftPoint.getY() && p.getY() <= upperLeftPoint.getY() + height)
			return true;
		return false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(edgecolor);
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		g.setColor(color);
		g.fillRect(upperLeftPoint.getX() +1, upperLeftPoint.getY() +1, width -1, height-1);
		if(selected) {
			g.setColor(Color.blue);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width  - 2, upperLeftPoint.getY() + height - 2, 4, 4);
		}
	}

	@Override
	public void moveTo(int x, int y) {
		upperLeftPoint.moveTo(x, y);

	}

	@Override
	public void moveBy(int x, int y) {
		upperLeftPoint.moveBy(x, y);

	}

	@Override
	public int compareTo(Object o) {

		if(o instanceof Rectangle1) {
			return this.area()-((Rectangle1)o).area();
		}
		return 0;
	}

	public Point1 getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point1 upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String toString() {
		return upperLeftPoint.getX() + " " + upperLeftPoint.getY() + " " + width + " "+ height;
	}

}



