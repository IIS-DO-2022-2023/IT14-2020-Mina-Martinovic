package geometry1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle{
	
	private static final long serialVersionUID = 1L;
	protected int innerRadius;
	protected int outerRadius;

	private boolean confirmation;

	protected Color innerColor;
	protected Color outerColor;	

	public Donut() {
		
	}
	
	public Donut(Point center, int outerRadius, int innerRadius) throws Exception{
		super(center,outerRadius);
		if(innerRadius < outerRadius)
		{
			this.innerRadius = innerRadius;
			this.outerRadius = outerRadius;
		}
	}
	
	public Donut(Point center, int outerRadius, int innerRadius, boolean selected) throws Exception{
		this(center, outerRadius, innerRadius);
		setSelected(selected);
	}
	
	public Donut(Point center, int outerRadius, int innerRadius, Color outerColor , Color innerColor) {
		super(center,outerRadius);
			this.innerRadius = innerRadius;
			this.outerRadius = outerRadius;
			this.outerColor  = outerColor ;
			this.innerColor = innerColor;
	}
	
	public double area() {
		return super.area()-innerRadius*innerRadius*Math.PI;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocni = (Donut) obj;
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
	
	public Donut clone() {
    	try {
			return new Donut(center.clone(), outerRadius, innerRadius, getOuterColor(), getInnerColor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
	
	public Color getOuterColor() {
		return outerColor ;
	}

	public void setOuterColor(Color outerColor) {
		this.outerColor = outerColor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public boolean contains (int x, int y) {
		return center.distance(x, y) >= innerRadius && super.contains(x,y);
	}
	
	public boolean contains (Point p) {
		return center.distance(p.getX(), p.getY()) >= innerRadius && super.contains(p.getX(), p.getY());
	}


@Override
public void draw(Graphics g) {

	Graphics2D g2D=(Graphics2D)g;
	Shape outer=new Ellipse2D.Double(center.getX() - outerRadius, center.getY() - outerRadius, 2 * outerRadius, 2 * outerRadius);
	Shape inner=new Ellipse2D.Double(this.getCenter().getX() - this.getInnerRadius(), this.getCenter().getY() - this.getInnerRadius(), this.getInnerRadius()*2, this.getInnerRadius()*2);
	Area circle=new Area(outer);
	circle.subtract(new Area(inner));
	g2D.setColor(getInnerColor());
	g2D.fill(circle);
	g2D.setColor(getInnerColor());
	g2D.draw(circle);
	
	 
    // Ova klasa ima konstruktor sa četiri parametra: 
    //(double x, double y, double width, double height). 
    //Ovi parametri se odnose na koordinate gornjeg levog ugla elipse (x i y),
    //širinu elipse (width) i visinu elipse (height)

	
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

		if(o instanceof Donut) {
			return (int)(this.area()-((Donut)o).area());
		}
		return 0;
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public int getOuterRadius() {
		return outerRadius;
	}

	public void setOuterRadius(int outerRadius) {
		this.outerRadius = outerRadius;
	}
	
	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}
	
	public String toString() {
		// Center=(x,y), radius= radius, innerRadius=innerRadius
		return "Donut: radius=" + outerRadius + "; x=" + center.getX() + "; y=" + center.getY() + "; edge color=" + getOuterColor().toString().substring(14).replace('=', '-') + "; area color=" + getInnerColor().toString().substring(14).replace('=', '-') + "; inner radius=" + innerRadius;
	}

}
