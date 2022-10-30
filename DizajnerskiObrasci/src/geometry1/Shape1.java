package geometry1;

import java.awt.Graphics;

public abstract class Shape1 implements Moveable1, Comparable{
	
	//private boolean selected;//mozemo definisati i kao protected pa ne bismo morali da menjamo konstruktore u ostalim klasama
	protected boolean selected;
	
	public Shape1 () {
	}
	
	public Shape1 (boolean selected) {
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

