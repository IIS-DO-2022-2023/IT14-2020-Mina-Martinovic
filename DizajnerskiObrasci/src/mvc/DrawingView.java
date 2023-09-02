package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry1.Shape;

public class DrawingView extends JPanel{
	
	private DrawingModel model = new DrawingModel();
	//public static ArrayList<Shape> shapes=new ArrayList<Shape>();	

	//public static String drawingObject = "Point";
	//public static Color color = new Color(255, 255, 255);

	public DrawingView() {

	}

	
	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(Color.white);
		this.setSize(2000, 1800);

		Iterator<geometry1.Shape> it = model.getShapes().iterator();
	
		//this.repaint();
		
		while(it.hasNext()) {
			it.next().draw(g);
			
		//	this.repaint();
		}
	}
	
}