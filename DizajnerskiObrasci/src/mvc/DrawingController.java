package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Drawing.DlgCircle;
import Drawing.DlgDonut;
import Drawing.DlgDonutUpdate;
import Drawing.DlgLine;
import Drawing.DlgPoint;
import Drawing.DlgRectangle;
import Drawing.DlgRectangleUpdate;
import adapter.HexagonAdapter;
import command.AddShape;
import command.RemoveShape;
import geometry1.Circle;
import geometry1.Donut;
import geometry1.Line;
import geometry1.Point;
import geometry1.Rectangle;
import geometry1.Shape;

public class DrawingController {

	
	private DrawingFrame frame;
	private DrawingModel model;	
	
	private int line_x;
	private int line_y;
	
	private boolean isFirstClick = true;
	
	private Shape selectedShape;
	
	public DrawingController (DrawingFrame frame, DrawingModel model)
	{
		this.frame = frame;
		this.model = model;				
	}
	
	public void selectShape(MouseEvent e)
	{
		boolean containsShape = false;
		
		for(int i = model.getShapes().size() -1; i >= 0; i--)
		{
			Shape shape = model.getShapes().get(i);
			
			if(shape.contains(e.getX(), e.getY()) )
			{
				containsShape = true;
				if(shape.isSelected() == false)
				{
					shape.setSelected(true);
					model.getSelectedShapes().add(shape);
					break;
				}
				else 
				{
					shape.setSelected(false);
					model.getSelectedShapes().remove(shape);
					break;
				}				
			}
			
		}
		if(containsShape == false)
		{
			for(Shape shape : model.getShapes())
			{
				if(shape.isSelected())
				{
					shape.setSelected(false);
					model.getSelectedShapes().remove(shape);
				}
				
			}
		}
		System.out.println(model.getSelectedShapes().size());
		frame.repaint();
	}
	
	public void drawingShape(MouseEvent e)
	{
		switch(frame.getDrawingObject()) {
		case "Point" : 
			Point point = new Point(e.getX(),e.getY(), frame.getOuterColor());
			AddShape addShapePoint = new AddShape(point, model);
			addShapePoint.execute();
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
					AddShape addShapeCircle = new AddShape(circle, model);
					addShapeCircle.execute();
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
					AddShape addShapeRectangle = new AddShape(rectangle, model);
					addShapeRectangle.execute();
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
					AddShape addShapeDonut = new AddShape(donut, model);
					addShapeDonut.execute();
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
				Line line = new Line(new Point(line_x, line_y), new Point(e.getX(), e.getY()), frame.getOuterColor());
				AddShape addShapeLine = new AddShape(line, model);
				addShapeLine.execute();
				isFirstClick = true;
			}
		break;
		case "Hexagon" :
			try {
				DlgCircle dialog = new DlgCircle(e.getX(), e.getY(), frame.getInnerColor(), frame.getOuterColor());
				dialog.setTitle("Add Hexagon");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				if(dialog.isConfirmation())
				{
					int radius = Integer.parseInt(dialog.getTxtRadius().getText());
					HexagonAdapter hex = new HexagonAdapter(new Point(e.getX(), e.getY()), radius, dialog.getOuterColor(), dialog.getInnerColor());
					AddShape addShapeHexagon = new AddShape(hex, model);
					addShapeHexagon.execute();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		break;
		default: 
			System.out.println("You have chosen an object that is not on the list!");
		}
		frame.repaint();
	}
	
	public void deleteSelectedShapes()
	{
		int reply = JOptionPane.showConfirmDialog(null, "Are you sure that you want to remove this shape?");
		if(reply == JOptionPane.YES_OPTION) 
		{
			RemoveShape removeShape = new RemoveShape(model);
			removeShape.execute();
			
			model.getSelectedShapes().clear();
			frame.repaint();
		}
		
	}
	
	public void modifySelectedShape()
	{
		selectedShape = model.getSelectedShapes().get(0);
		
		if(selectedShape instanceof Point)
		{	
			try {
				Point point = (Point)selectedShape;
				DlgPoint dialog = new DlgPoint(point.getX(), point.getY(), point.getOuterColor());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				if(dialog.isConfirmation())
				{
					int x = Integer.parseInt(dialog.getTxtX().getText());
					int y = Integer.parseInt(dialog.getTxtY().getText());
					Point newPoint = new Point(x, y, dialog.getOuterColor());
					
					int index = model.getShapes().indexOf(selectedShape);
					model.getShapes().set(index, newPoint);
					frame.repaint();
				};
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		else if (selectedShape instanceof Line)
		{
			try {
				Line line = (Line)selectedShape;
				DlgLine dialog = new DlgLine(line.getStartPoint(), line.getEndPoint(), line.getOuterColor());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
				if(dialog.isConfirmation())
				{
					Point startPoint = new Point(Integer.parseInt(dialog.getXStartPoint().getText()), Integer.parseInt(dialog.getYStartPoint().getText()));
					Point endPoint = new Point(Integer.parseInt(dialog.getXEndPoint().getText()), Integer.parseInt(dialog.getYEndPoint().getText()));
					
					Line newLine = new Line(startPoint, endPoint, dialog.getOuterColor());
					
					int index = model.getShapes().indexOf(selectedShape);
					model.getShapes().set(index, newLine);
					frame.repaint();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		
		}
		else if (selectedShape instanceof Donut)
		{
			try {
				Donut donut = (Donut)selectedShape;
				DlgDonutUpdate dialog = new DlgDonutUpdate(
						donut.getCenter(),
						donut.getInnerRadius(),
						donut.getOuterRadius(),
						donut.getInnerColor(),
						donut.getOuterColor());

				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
				if(dialog.isConfirmation())
				{
					Point center = new Point(Integer.parseInt(dialog.getTxtX().getText()), Integer.parseInt(dialog.getTxtY().getText()));
				
					Donut newDonut = new Donut (center, Integer.parseInt(dialog.getTxtOuterRadius().getText()), Integer.parseInt(dialog.getTxtInnerRadius().getText()), 
							dialog.getOuterColor(),	dialog.getInnerColor(), true);
					
					int index = model.getShapes().indexOf(selectedShape);
					model.getShapes().set(index, newDonut);
					frame.repaint();
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		else if (selectedShape instanceof Rectangle) 
		{
			try {
				Rectangle rectangle = (Rectangle)selectedShape;
				DlgRectangleUpdate dialog = new DlgRectangleUpdate(
						rectangle.getUpperLeftPoint(),
						rectangle.getWidth(),
						rectangle.getHeight(),
						rectangle.getOuterColor(),
						rectangle.getInnerColor());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				

				if(dialog.isConfirmation())
				{
					Point upperLeftPoint = new Point(Integer.parseInt(dialog.getTxtUpperLeftPointX().getText()), Integer.parseInt(dialog.getTxtUpperLeftPointY().getText()));
				
					Rectangle newRectangle = new Rectangle (upperLeftPoint, Integer.parseInt(dialog.getTxtWidth().getText()), Integer.parseInt(dialog.getTxtHeight().getText()), 
							dialog.getOuterColor(),	dialog.getInnerColor());
					
					int index = model.getShapes().indexOf(selectedShape);
					model.getShapes().set(index, newRectangle);
					frame.repaint();
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}	
		else if(selectedShape instanceof Circle)
		{
			
		}
		else if (selectedShape instanceof HexagonAdapter)
		{
			
		}
	}
	
}
