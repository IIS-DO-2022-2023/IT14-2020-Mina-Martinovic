package geometry1;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Comparable, Serializable, Cloneable {
	
	//private boolean selected;//mozemo definisati i kao protected pa ne bismo morali da menjamo konstruktore u ostalim klasama
	protected boolean selected;
	private Color outlineColor;
	//protected ShapeManager shapeManager; // Dodajte referencu na ShapeManager
	
	public Shape () {
	}
	
	public Color getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}

	public Shape (boolean selected) {
		this.selected=selected;
	}
	
	/*
	public void setShapeManager(ShapeManager shapeManager) {
        this.shapeManager = shapeManager;
    }
    */
	
	public abstract boolean contains (int x, int y);
	public abstract void draw(Graphics g);

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	

}

