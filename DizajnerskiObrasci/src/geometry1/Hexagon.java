package geometry1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.Serializable;

public class Hexagon implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private boolean selected;
	private int x;
	private int y;
	private int radius;
	
	private Color borderColor;
	private Color areaColor;
	
	public Hexagon() {
		
	}
	
	public Hexagon(int x, int y, int radius) throws Exception {
		if(radius > 0)
		{
			this.x = x;
			this.y = y;
			setRadius(radius);
		}
	}
	
	public Hexagon (int x, int y, int radius, Color outlineColor, Color fillColor) throws Exception {
		if(radius > 0)
		{
			this.x = x;
			this.y = y;
			setRadius(radius);
			borderColor = outlineColor;
			areaColor = fillColor;
		}
	}
	
	public void paint (Graphics g) {
		int[] xs = new int [6]; // pravim sestougao
		int[] ys = new int [6];
		Polygon polygon = new Polygon(); // definise mi mnogougao sa proizvoljnim tackama	
		
		for (int i= 0; i < 6; i++) {
			xs[i] = (int)(this.x + this.radius * Math.cos((i * 2) * Math.PI / 6.0D)); //racunam koord x i y, uglovi u sestouglu su mi jednaki
			ys[i] = (int)(this.y + this.radius * Math.sin((i * 2) * Math.PI / 6.0D)); //this.x i this.y centar sestougla
		}
		if (this.areaColor != null) {
			g.setColor(this.areaColor);
			g.fillPolygon(polygon);
		}
		if(this.borderColor != null) {
			g.setColor(borderColor);
		} else {
			g.setColor(Color.BLACK); //ako ne postoji
		}
		g.drawPolygon(polygon); //crtam ivicu sestougla
		if (this.selected) {
			g.setColor(Color.BLUE);
			for(int i = 0; i < 6; i++) {
				g.drawRect(xs[i] - 2, ys[i] - 2, 5, 5); //crtam kvadratic 5x5 piksela oko svake tacke sestougla kako bi oznacili polozaj
			}
		}
	}
	
	public boolean doesContain(int x, int y) {
		Polygon polygon = new Polygon();
		int i = 0;
		
		while (i < 6)
		{
			polygon.addPoint((int)(this.x + this.radius * Math.cos((i*2)* Math.PI / 6.0D)), (int)(this.y + this.radius * Math.sin((i * 2) * Math.PI / 6.0D)));
			i++;
		}
		return polygon.contains(x, y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getAreaColor() {
		return areaColor;
	}

	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
	}
	
}
