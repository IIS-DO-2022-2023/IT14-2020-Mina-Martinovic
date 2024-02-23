package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry1.Circle;
import geometry1.Point;
import geometry1.ThreeDShape;
import hexagon.Hexagon;

public class HexagonAdapter extends ThreeDShape{

	private Hexagon hexagon;
	
	public HexagonAdapter(Point center, int r, Color outerColor, Color innerColor ) {
		
		hexagon = new Hexagon(center.getX(), center.getY(), r);
		setInnerColor(innerColor);
		setOuterColor(outerColor);
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof HexagonAdapter) {
			HexagonAdapter pomocna=(HexagonAdapter) obj;
			if(this.getCenter().equals(pomocna.getCenter()) && this.getRadius()==(pomocna.getRadius()))

				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	@Override
	public boolean contains(int x, int y) {
		
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		
		hexagon.paint(g);		
	}
	
	@Override
	public String toString() {
		return "HexagonAdapter: radius=" + getRadius() + "; x=" + getCenter().getX() + "; y=" + getCenter().getY() + "; edge color=" + getOuterColor().toString().substring(14).replace('=', '-') + "; area color=" + getInnerColor().toString().substring(14).replace('=', '-'); 
	}

	public void setInnerColor(Color color)
	{
		hexagon.setAreaColor(color);
	}
	
	public void setOuterColor(Color color)
	{
		hexagon.setBorderColor(color);
	}
	
	public Point getCenter()
	{
		Point center = new Point(hexagon.getX(), hexagon.getY());
		return center;
	}
	
	public int getRadius()
	{
		int radius = hexagon.getR();
		return radius;
	}
}
