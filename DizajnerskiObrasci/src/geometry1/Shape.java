package geometry1;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Comparable, Serializable {
	
	//private boolean selected;//mozemo definisati i kao protected pa ne bismo morali da menjamo konstruktore u ostalim klasama
	protected boolean selected;
	private Color outerColor;
	//protected ShapeManager shapeManager; // Dodajte referencu na ShapeManager
	
	public Shape () {
	}
	
	public Color getOuterColor() {
		return outerColor;
	}

	public void setOuterColor(Color outerColor) {
		this.outerColor = outerColor;
	}

	public Shape (boolean selected) {
		this.selected=selected;
	}
	
	public abstract boolean contains (int x, int y);
	public abstract void draw(Graphics g);

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	

}

