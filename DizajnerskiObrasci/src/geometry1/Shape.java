package geometry1;

import java.awt.Graphics;

public abstract class Shape implements Moveable, Comparable{
	
	//private boolean selected;//mozemo definisati i kao protected pa ne bismo morali da menjamo konstruktore u ostalim klasama
	protected boolean selected;
	protected ShapeManager shapeManager; // Dodajte referencu na ShapeManager

	
	public Shape () {
	}
	
	public Shape (boolean selected) {
		this.selected=selected;
	}
	
	public void setShapeManager(ShapeManager shapeManager) {
        this.shapeManager = shapeManager;
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

