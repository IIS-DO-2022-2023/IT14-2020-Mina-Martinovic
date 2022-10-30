package geometry1;

import java.awt.Color;
import java.awt.Graphics;

public class Donut1 extends Circle1{
	
	protected int innerRadius;
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
	

	public Donut1() {
		
	}
	
	public Donut1(Point1 center, int radius, int innerRadius) {
		//this.setCenter(center); --center je definisan kao private
		//this.center=center;--center je definisan kao protected
		super(center, radius);
		this.innerRadius=innerRadius;
	}
	
	public Donut1(Point1 center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}
	
	public double area() {
		return super.area()-innerRadius*innerRadius*Math.PI;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut1) {
			Donut1 pomocni = (Donut1) obj;
			if (this.center.equals(pomocni.center) &&
					this.getRadius() == pomocni.getRadius() && innerRadius == pomocni.innerRadius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean contains (int x, int y) {
		return center.distance(x, y) >= innerRadius && super.contains(x,y);
	}
	
	public boolean contains (Point1 p) {
		return center.distance(p.getX(), p.getY()) >= innerRadius && super.contains(p.getX(), p.getY());
	}


@Override
public void draw(Graphics g) {
	g.setColor(edgecolor);
	g.drawOval(center.getX() - getRadius(), center.getY() - getRadius(), getRadius()*2, getRadius()*2);
	g.setColor(color);
	g.fillOval(center.getX() - getRadius(), center.getY() - getRadius(), getRadius()*2, getRadius()*2);
	
	g.setColor(edgecolor);
	g.drawOval(center.getX() - innerRadius/2, center.getY() - innerRadius/2, innerRadius, innerRadius);
	g.setColor(Color.WHITE);
	g.fillOval(center.getX() - innerRadius/2, center.getY() - innerRadius/2, innerRadius, innerRadius);
	if(selected) {
		g.setColor(Color.BLUE);
		g.drawRect(center.getX() - 2, center.getY() - 2, 4, 4);
		g.drawRect(center.getX() - getRadius() - 2, center.getY() - 2, 4, 4);
		g.drawRect(center.getX() + getRadius() - 2, center.getY() - 2, 4, 4);
		g.drawRect(center.getX() - 2, center.getY() - getRadius()- 2, 4, 4);
		g.drawRect(center.getX() - 2, center.getY() + getRadius() - 2, 4, 4);
		
	}

}
	@Override
	public int compareTo(Object o) {

		if(o instanceof Donut1) {
			return (int)(this.area()-((Donut1)o).area());
		}
		return 0;
	}

	public int getInnerRadius() {
		return innerRadius;
	}
	protected Color getInnerColor() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public String toString() {
		// Center=(x,y), radius= radius, innerRadius=innerRadius
		return super.toString() + " inner radius=" + innerRadius;
	}

}
