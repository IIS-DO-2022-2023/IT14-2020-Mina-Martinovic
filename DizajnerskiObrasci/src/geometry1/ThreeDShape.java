package geometry1;

import java.awt.Color;

public abstract class ThreeDShape extends Shape{

	private static final long serialVersionUID = 1L;
	private Color innerColor;
	
	public Color getInnerColor() {
		return innerColor;
	}
 
	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	
}
