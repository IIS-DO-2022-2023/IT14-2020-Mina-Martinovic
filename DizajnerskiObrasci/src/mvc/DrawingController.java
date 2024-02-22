package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;

import Drawing.DlgCircle;
import Drawing.DlgDonut;
import Drawing.DlgRectangle;
import geometry1.Circle;
import geometry1.Donut;
import geometry1.Line;
import geometry1.Point;
import geometry1.Rectangle;

public class DrawingController {

	
	private DrawingFrame frame;
	private DrawingModel model;	
	
	private int line_x;
	private int line_y;
	
	private boolean isFirstClick = true;
	
	public DrawingController (DrawingFrame frame, DrawingModel model)
	{
		this.frame = frame;
		this.model = model;				
	}
	
	public void drawingShape(MouseEvent e)
	{
		switch(frame.getDrawingObject()) {
		case "Point" : 
			Point p1 = new Point(e.getX(),e.getY(), frame.getOuterColor());
			model.getShapes().add(p1);
		break;
		case "Circle" : 
			try {
				DlgCircle dialog = new DlgCircle(e.getX(), e.getY(), frame.getInnerColor(), frame.getOuterColor());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				if(dialog.isConfirmation())
				{
					int radius = Integer.parseInt(dialog.getTxtRadius().getText());
					Circle circle = new Circle(new Point(e.getX(), e.getY()), radius, dialog.getOuterColor(), dialog.getInnerColor());
					model.getShapes().add(circle);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		break;
		case "Rectangle" :
			try {
				DlgRectangle dialog = new DlgRectangle(e.getX(), e.getY(),  frame.getInnerColor(), frame.getOuterColor());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
				if(dialog.isConfirmation())
				{
					int height = Integer.parseInt(dialog.getTxtHeight().getText());
					int width = Integer.parseInt(dialog.getTxtWidth().getText());
					Rectangle rectangle = new Rectangle(new Point(e.getX(), e.getY()), width, height, dialog.getOuterColor(), dialog.getInnerColor());
					model.getShapes().add(rectangle);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		break;
		case "Donut" : 
			try {
				DlgDonut dialog = new DlgDonut(e.getX(), e.getY(),  frame.getInnerColor(), frame.getOuterColor());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
				if(dialog.isConfirmation())
				{
					int innerRadius = Integer.parseInt(dialog.getInnerRadius().getText());
					int outerRadius = Integer.parseInt(dialog.getOuterRadius().getText());
					Donut donut = new Donut(new Point(e.getX(), e.getY()), outerRadius, innerRadius, dialog.getOuterColor(), dialog.getInnerColor());
					model.getShapes().add(donut);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		break;
		case "Line" : 
			if (isFirstClick) {
				line_x = e.getX();
				line_y = e.getY();
				isFirstClick = false;
			}
			else {
				Line l1 = new Line(new Point(line_x, line_y), new Point(e.getX(), e.getY()), Color.black);
				model.getShapes().add(l1);
				isFirstClick = true;
			}
		break;
		default: 
			System.out.println("You have chosen an object that is not on the list!");
		}
		frame.repaint();
	}
	
}
