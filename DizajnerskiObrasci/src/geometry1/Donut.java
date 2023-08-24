package geometry1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import mvc.DrawingModel;

public class Donut extends Circle{
	
	protected int innerRadius;
	protected int outerRadius;

	protected Color color;
	protected Color edgecolor;
	private DrawingModel model = new DrawingModel(null);
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setEdgeColor(Color edgecolor) {
		this.edgecolor = edgecolor;
	}
	

	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		//this.setCenter(center); --center je definisan kao private
		//this.center=center;--center je definisan kao protected
		super(center, radius);
		this.innerRadius=innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
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
	
	public boolean contains (int x, int y) {
		return center.distance(x, y) >= innerRadius && super.contains(x,y);
	}
	
	public boolean contains (Point p) {
		return center.distance(p.getX(), p.getY()) >= innerRadius && super.contains(p.getX(), p.getY());
	}


@Override
public void draw(Graphics g) {
	//g.setColor(edgecolor);
	//g.drawOval(center.getX() - getRadius(), center.getY() - getRadius(), getRadius()*2, getRadius()*2);
	//g.setColor(color);
	//g.fillOval(center.getX() - getRadius(), center.getY() - getRadius(), getRadius()*2, getRadius()*2);
	
	Ellipse2D outerCircle = new Ellipse2D.Double();
	g.drawOval(center.getX() - getRadius(), center.getY() - getRadius(), getRadius()*2, getRadius()*2);
	
	// fill Ellipse2D.Double
	//GradientPaint redtowhite = new GradientPaint(0,0,color.RED,100, 0,color.WHITE);
	//g2.setPaint(redtowhite);
	//g2.fill (new Ellipse2D.Double(0, 0, 100, 50));
	 ArrayList<Color>colors = new ArrayList<>();
	 colors.add(new Color(0, 0, 0, 0)); 
	//g.fillOval(new Ellipse2D.Double(0, 0, 100, 50));
	//g.setColor(edgecolor);
	//g.drawOval(center.getX() - innerRadius/2, center.getY() - innerRadius/2, innerRadius, innerRadius);
	//g.setColor(Color.WHITE);
	//g.fillOval(center.getX() - innerRadius/2, center.getY() - innerRadius/2, innerRadius, innerRadius);
	
	//Ellipse2D outerCircle = new Ellipse2D.Double(center.getX() - getRadius(), center.getY() - getRadius(), getRadius()*2, getRadius()*2);
    Ellipse2D innerCircle = new Ellipse2D.Double(center.getX() - innerRadius/2, center.getY() - innerRadius/2, innerRadius, innerRadius);
    // Ova klasa ima konstruktor sa četiri parametra: 
    //(double x, double y, double width, double height). 
    //Ovi parametri se odnose na koordinate gornjeg levog ugla elipse (x i y),
    //širinu elipse (width) i visinu elipse (height)

    Area area = new Area(outerCircle);
    area.subtract(new Area(innerCircle));
    g.fillOval(center.getX() - getRadius(), center.getY() - getRadius(), getRadius()*2, getRadius()*2);

    Shape Shape = null; //izmeni!!!!!!!
	 model.add(Shape);
	// ArrayList<Color>colors = new ArrayList<>();
	 //colors.add(new Color(0, 0, 0, 0)); // Transparentna boja
	
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
	protected Color getInnerColor() {
		// TODO Auto-generated method stub
		return null;
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
	
	public String toString() {
		// Center=(x,y), radius= radius, innerRadius=innerRadius
		return super.toString() + " inner radius=" + innerRadius;
	}

}
