package geometry1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.Serializable;

public class Hexagon implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean selected;
	private int x;
	private int y;
	private int r;
	private Color borderColor;
	private Color areaColor; 
	
	public Hexagon() {
		
	}
	
	public Hexagon(int x, int y, int r) { //samo inicijalizacija hex uz pomoc koord centra i radiusa
		if(r>0) {
			this.x = x;
			this.y = y;
			setR(r);
		}
	}
	
	public Hexagon(int x, int y, int r, Color outlineColor, Color fillColor) { // mogucnost inicijalizacije hex i sa bojama
		if(r>0) {
			this.x = x;
			this.y = y;
			setR(r);
			borderColor = outlineColor;
			areaColor = fillColor;
		}
	}

	public void paint(Graphics g) {
		int[] xs = new int[6];
		int[] ys = new int[6];
		Polygon plg = new Polygon();
		int i;
		for (i = 0; i < 6; i++) {
			xs[i] = (int)(this.x + this.r * Math.cos((i * 2) * Math.PI / 6.0D));
			ys[i] = (int)(this.y + this.r * Math.sin((i * 2) * Math.PI / 6.0D));
			plg.addPoint(xs[i], ys[i]);
		} 
		if(this.areaColor!=null) {
			g.setColor(this.areaColor);
			g.fillPolygon(plg);
		}
		if(this.borderColor != null) {
			g.setColor(borderColor);
		} else {
			g.setColor(Color.BLACK);
		}
		g.drawPolygon(plg);
		if (this.selected) {
			g.setColor(Color.BLUE);
			for (i = 0; i < 6; i++)
				g.drawRect(xs[i] - 2, ys[i] - 2, 5, 5); 
		} 
	}
	
	public boolean doesContain(int x, int y) {
		Polygon plg = new Polygon();
		int i = 0;
		while (i < 6) {
			plg.addPoint((int)(this.x + this.r * Math.cos((i * 2) * Math.PI / 6.0D)), (int)(this.y + this.r * Math.sin((i * 2) * Math.PI / 6.0D)));
			i++;
		} 
		return plg.contains(x, y);
	}
		
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
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

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
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
