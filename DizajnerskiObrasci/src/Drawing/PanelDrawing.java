package Drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import geometry1.Shape;

public class PanelDrawing extends JPanel{

	public static ArrayList<Shape> shapes=new ArrayList<Shape>();	

	public static String drawingObject = "Point";
	public static Color color = new Color(255, 255, 255);


	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.white);
		this.setSize(2000, 1800);

		Iterator<Shape> it=shapes.iterator();

		this.repaint();

		while(it.hasNext()) {
			it.next().draw(g);
			this.repaint();
		}
	}
}
