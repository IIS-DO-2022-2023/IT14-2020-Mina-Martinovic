package geometry1;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends ThreeDShape{
	

	private static final long serialVersionUID = 1L;
	protected Point center;
	private int radius;
	private boolean confirmation;


	public Circle() {
	}
	
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Circle(Point center, int r, Color outerColor, Color innerColor) throws Exception {
		this.center = center;
		this.radius = r;
		setInnerColor(innerColor);
		setOuterColor(outerColor);
	}
	public Circle(Point center, int radius, boolean selected) throws Exception{
		this(center, radius);
		setSelected(selected);
	}
	
	public Circle(Point center, int r, Color outerColor, Color innerColor, boolean selected)
	{
		this.center = center;
		this.radius = r;
		setInnerColor(innerColor);
		setOuterColor(outerColor);
		setSelected(selected);
	}

	public double area() {
		return this.radius * this.radius * Math.PI;
	}

	public double circumference() {
		return 2 * this.radius * Math.PI;
	}

	public boolean equals(Object obj) {
		if(obj instanceof Circle) {
			Circle pomocna=(Circle) obj;
			if(this.center.equals(pomocna.center) && this.radius==(pomocna.radius))

				return true;
			else
				return false;
		}
		else
			return false;
	}

	public boolean contains (int x, int y) {
		return center.distance(x, y) <= radius;
	}

	public boolean contains(Point p) {
		return center.distance(p.getX(), p.getY())<= radius;
	}
	
	public Circle clone() {
    	try {
			return new Circle(center.clone(), radius, getOuterColor(), getInnerColor());
		} catch (Exception e) {
			
			return null;
		}
    }
	
	public void draw(Graphics g) {
		g.setColor(getOuterColor());
		g.drawOval(center.getX()-radius, center.getY()-radius, radius*2, radius*2);
		g.setColor(getInnerColor());
		g.fillOval(center.getX()- radius , center.getY() - radius , radius*2 , radius*2 ); 

		if(selected) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getX()-2	, center.getY() - 2, 4, 4);
			g.drawRect(center.getX()-radius -2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX()+ radius -2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX()-2	, center.getY() - radius - 2, 4, 4);
			g.drawRect(center.getX()-2	, center.getY() + radius - 2, 4, 4);
		}
	}

	/*
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		center.moveTo(x,y);
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		center.moveBy(x,y);
	}

	@Override
	public int compareTo(Object o) {

		if(o instanceof Circle) {
			return (int)(this.area()-((Circle)o).area());
		}
		return 0;
	}
	*/

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}
	public void setRadius (int radius){
		this.radius = radius;
	}

	public String toString() {
		return "Circle: radius=" + radius + "; x=" + center.getX() + "; y=" + center.getY() + "; edge color=" + getOuterColor().toString().substring(14).replace('=', '-') + "; area color=" + getInnerColor().toString().substring(14).replace('=', '-'); 
		//splitujem partove circla tipa radius koord i to a crtica misplituje boju znam da se radi o boji	}

	}
	
	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}
	

}