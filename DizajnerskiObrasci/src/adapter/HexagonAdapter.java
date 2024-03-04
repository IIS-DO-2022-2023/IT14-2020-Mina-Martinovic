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
	
	public HexagonAdapter(Point center, int r, Color outerColor, Color innerColor, boolean selected ) {
			
			hexagon = new Hexagon(center.getX(), center.getY(), r);
			setInnerColor(innerColor);
			setOuterColor(outerColor);
			setSelected(selected);
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
	
	public HexagonAdapter clone()
	{
		return new HexagonAdapter(getCenter().clone(), getRadius(), getOuterColor(), getInnerColor());
	}

	@Override
	public void draw(Graphics g) {
		
		hexagon.paint(g);		
	}
	
	@Override
	public String toString() {
		return "HexagonAdapter: radius=" + getRadius() + "; x=" + getCenter().getX() + "; y=" + getCenter().getY() + "; edge color=" + getOuterColor().toString().substring(14).replace('=', '-') + "; area color=" + getInnerColor().toString().substring(14).replace('=', '-'); 
	}

	public Color getInnerColor()
	{
		return hexagon.getAreaColor();
	}
	
	public void setInnerColor(Color color)
	{
		hexagon.setAreaColor(color);
	}
	
	public Color getOuterColor()
	{
		return hexagon.getBorderColor();
	}
	
	public void setOuterColor(Color color)
	{
		hexagon.setBorderColor(color);
	}
	
	public boolean isSelected()
	{
		return hexagon.isSelected();
	}
	
	public void setSelected(boolean selected)
	{
		hexagon.setSelected(selected);
	}
	
	public Point getCenter()
	{
		Point center = new Point(hexagon.getX(), hexagon.getY());
		return center;
	}
	
	public void setCenter(Point center)
	{
		hexagon.setX(center.getX());
		hexagon.setY(center.getY());
	}
	
	public int getRadius()
	{
		int radius = hexagon.getR();
		return radius;
	}
	
	public void setRadius(int radius)
	{
		hexagon.setR(radius);
	}
	
	
}
