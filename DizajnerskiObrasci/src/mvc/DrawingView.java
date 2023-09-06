package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

public class DrawingView extends JPanel{
	
	private DrawingModel model = new DrawingModel();

	public DrawingView() {

	}

	public DrawingModel getModel() {
		return model;
	}
	
	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(Color.white);
		this.setSize(2000, 1800);

		Iterator<geometry1.Shape> it = model.getShapes().iterator();
		
		while(it.hasNext()) {
			it.next().draw(g);
	
		}
	}
	
}