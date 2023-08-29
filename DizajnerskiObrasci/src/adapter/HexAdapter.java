package adapter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import geometry1.SurfaceShape;
import geometry1.Hexagon;

public class HexAdapter extends SurfaceShape{

	private static final long serialVersionUID = 1L; //za serijaklizaciju
	private Hexagon hexagon;
	
	public HexAdapter() {
			
		}
		
	public HexAdapter(Hexagon hexagon) {
		super();
		this.hexagon=hexagon;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HexAdapter) {
			Hexagon hex = ((HexAdapter) obj).hexagon;
			return hexagon.getX() == hex.getX() && hexagon.getY() == hex.getY() && hexagon.getR() == hex.getR();
		}
		return false;
	}
	
	public void draw(Graphics g) {
		hexagon.paint(g); //izmeni i proveri da li je ta metoda u hex
	}
	
	public HexAdapter clone() {
		Hexagon h;
		h = new Hexagon(hexagon.getX(), hexagon.getY(), hexagon.getR());
		h.setBorderColor(hexagon.getBorderColor());
		h.setAreaColor(hexagon.getAreaColor());
		return new HexAdapter(h);
	}


	public int compareTo(Object o) {
		return 0;
	}

	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}
	
	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "Hexagon: radius=" + hexagon.getR() + "; x=" + hexagon.getX() + "; y=" + hexagon.getY() + "; edge color=" + getColor().toString().substring(14).replace('=', '-') + "; area color=" + getInteriorColor().toString().substring(14).replace('=', '-');
	}
	
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
		super.setOutlineColor(color);
	}
	
	
	public Color getInteriorColor() {
		return hexagon.getAreaColor();
	}
	
	
	public void setInteriorColor(Color color) {
		hexagon.setAreaColor(color);
		super.setFillColor(color);
	}
	
	public void setSelected(boolean selected) {
		this.hexagon.setSelected(selected);
	}
	
	public int getR() {
		return hexagon.getR();
	}
	
	public void setR(int r) throws Exception {
		hexagon.setR(r);
	}
	
	public int getX() {
		return hexagon.getX();
	}
	
	public int getY() {
		return hexagon.getY();
	}
	
	public void setX(int x) {
		hexagon.setX(x);
	}
	
	public void setY(int y) {
		hexagon.setY(y);
	}
}
