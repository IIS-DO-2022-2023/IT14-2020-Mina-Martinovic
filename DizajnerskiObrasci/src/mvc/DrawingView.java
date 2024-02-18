package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry1.Shape;

public class DrawingView extends JPanel{

		
	private DrawingModel model = new DrawingModel();
	
		@Override
		public void paint(Graphics g) {
			super.paintComponent(g);

			Iterator<Shape> it=model.getShapes().iterator();
	
			while(it.hasNext()) {
				it.next().draw(g);
			}						
	}

		public void setModel(DrawingModel model) {
			this.model = model;
		}
}
