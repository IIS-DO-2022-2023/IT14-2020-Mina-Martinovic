package mvc;

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

	public Point1(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(x - 2, y, x + 2, y);
		g.drawLine(x, y - 2, x, y + 2);
		g.setColor(Color.BLUE);
		
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

