package geometry1;

import java.awt.Color;
import java.awt.Graphics;

public class Point1 extends Shape1{
	private int x;
	private int y;
	protected Color color;



	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Point1() {
	}

	public Point1(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point1(int x, int y, boolean selected) {
		// this.x=x;
		// this.y=y;
		this(x, y);

		setSelected(selected);
		// menja se prilikom dodavanja Shape
		// this.selected = selected;
	}

	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}

	public boolean equals(Object obj) {

		if (obj instanceof Point1) {

			Point1 pomocna = (Point1) obj;
			if (this.x == pomocna.x && this.y == pomocna.y)
				return true;
			else
				return false;
		} else
			return false;

	}

	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 2;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(x - 2, y, x + 2, y);
		g.drawLine(x, y - 2, x, y + 2);
		g.setColor(Color.RED);
		
		if(selected) {
			g.setColor(Color.BLUE);
			g.drawRect(x-2, y-2, 4, 4);
		}
	}

	@Override
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void moveBy(int x, int y) {
		this.x = this.x + x;
		this.y += y;
	}

	
	@Override
	public int compareTo(Object o) {
		
		if(o instanceof Point1) {
			return (int)(this.distance(0,0)-((Point1)o).distance(0,0));
		}
		return 0;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return this.x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return this.y;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

}

