package mvc;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

public class DrawingView extends JPanel{
	private DrawingModel model = new DrawingModel(null);

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public void paint(Graphics g) {
		Iterator<geometry1.Shape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}
	//tjt Branka ima samo ovo
}