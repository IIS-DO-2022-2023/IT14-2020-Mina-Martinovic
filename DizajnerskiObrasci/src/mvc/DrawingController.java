package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Drawing.DlgCircle;
import Drawing.DlgCircleUpdate;
import Drawing.DlgDonut;
import Drawing.DlgDonutUpdate;
import Drawing.DlgHexagonUpdate;
import Drawing.DlgLineUpdate;
import Drawing.DlgPointUpdate;
import Drawing.DlgRectangle;
import Drawing.DlgRectangleUpdate;
import adapter.HexagonAdapter;
import command.AddShape;
import command.BringToBack;
import command.BringToFront;
import command.ICommand;
import command.RemoveShape;
import command.ToBack;
import command.ToFront;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
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
	
	private PropertyChangeSupport propertyChangeSupport;
	
	public DrawingController (DrawingFrame frame, DrawingModel model)
	{
		this.frame = frame;
		this.model = model;				
		propertyChangeSupport = new PropertyChangeSupport(this);
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
		if(model.getSelectedShapes().size() > 1)
		{
			propertyChangeSupport.firePropertyChange("Delete", false, true);
			propertyChangeSupport.firePropertyChange("Modify", true, false);
		}
		else if(model.getSelectedShapes().size() == 1)
		{
			propertyChangeSupport.firePropertyChange("Delete", false, true);
			propertyChangeSupport.firePropertyChange("Modify", false, true);
		}
		else if(model.getSelectedShapes().size() == 0)
		{
			propertyChangeSupport.firePropertyChange("Delete", true, false);
			propertyChangeSupport.firePropertyChange("Modify", true, false);
		}
		
		frame.repaint();
	}
	
	public void drawingShape(MouseEvent e)
	{
		switch(frame.getDrawingObject()) {
		case "Point" : 
			Point point = new Point(e.getX(),e.getY(), frame.getOuterColor());
			AddShape addShapePoint = new AddShape(point, model);
			addShapePoint.execute();
			model.getUndoList().add(addShapePoint);
			model.getRedoList().clear();
			propertyChangeSupport.firePropertyChange("Undo", false, true);
			propertyChangeSupport.firePropertyChange("Redo", true, false);
			
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
					model.getUndoList().add(addShapeCircle);
					model.getRedoList().clear();
					propertyChangeSupport.firePropertyChange("Undo", false, true);
					propertyChangeSupport.firePropertyChange("Redo", true, false);

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
					model.getUndoList().add(addShapeRectangle);
					model.getRedoList().clear();
					propertyChangeSupport.firePropertyChange("Undo", false, true);
					propertyChangeSupport.firePropertyChange("Redo", true, false);

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
					model.getUndoList().add(addShapeDonut);
					model.getRedoList().clear();
					propertyChangeSupport.firePropertyChange("Undo", false, true);
					propertyChangeSupport.firePropertyChange("Redo", true, false);

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
				model.getUndoList().add(addShapeLine);
				model.getRedoList().clear();
				propertyChangeSupport.firePropertyChange("Undo", false, true);
				propertyChangeSupport.firePropertyChange("Redo", true, false);

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
					model.getUndoList().add(addShapeHexagon);
					model.getRedoList().clear();
					propertyChangeSupport.firePropertyChange("Undo", false, true);
					propertyChangeSupport.firePropertyChange("Redo", true, false);

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
			model.getUndoList().add(removeShape);
			model.getRedoList().clear();
			propertyChangeSupport.firePropertyChange("Undo", false, true);
			propertyChangeSupport.firePropertyChange("Redo", true, false);

			propertyChangeSupport.firePropertyChange("Delete", true, false);
			propertyChangeSupport.firePropertyChange("Modify", true, false);
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
				DlgPointUpdate dialog = new DlgPointUpdate(point.getX(), point.getY(), point.getOuterColor());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				if(dialog.isConfirmation())
				{
					int x = Integer.parseInt(dialog.getTxtX().getText());
					int y = Integer.parseInt(dialog.getTxtY().getText());
					Point newPoint = new Point(x, y, dialog.getOuterColor(), true);
					
					UpdatePointCmd updatePoint = new UpdatePointCmd(point, newPoint);
					updatePoint.execute();			
					model.getUndoList().add(updatePoint);
					model.getRedoList().clear();
					propertyChangeSupport.firePropertyChange("Undo", false, true);
					propertyChangeSupport.firePropertyChange("Redo", true, false);
									
					frame.repaint();
				};
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Invalid input!");
			}
		}
		else if (selectedShape instanceof Line)
		{
			try {
				Line line = (Line)selectedShape;
				DlgLineUpdate dialog = new DlgLineUpdate(line.getStartPoint(), line.getEndPoint(), line.getOuterColor());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
				if(dialog.isConfirmation())
				{
					Point startPoint = new Point(Integer.parseInt(dialog.getXStartPoint().getText()), Integer.parseInt(dialog.getYStartPoint().getText()));
					Point endPoint = new Point(Integer.parseInt(dialog.getXEndPoint().getText()), Integer.parseInt(dialog.getYEndPoint().getText()));
					
					Line newLine = new Line(startPoint, endPoint, dialog.getOuterColor(), true);
					
					UpdateLineCmd updateLine = new UpdateLineCmd(line, newLine);
					updateLine.execute();
					model.getUndoList().add(updateLine);
					model.getRedoList().clear();
					propertyChangeSupport.firePropertyChange("Undo", false, true);
					propertyChangeSupport.firePropertyChange("Redo", true, false);

					frame.repaint();
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Invalid input!");
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
					
					UpdateDonutCmd updateDonut = new UpdateDonutCmd(donut, newDonut);
					updateDonut.execute();
					model.getUndoList().add(updateDonut);
					model.getRedoList().clear();
					propertyChangeSupport.firePropertyChange("Undo", false, true);
					propertyChangeSupport.firePropertyChange("Redo", true, false);

					frame.repaint();
				}
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Invalid input!");
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
						rectangle.getInnerColor(),
						rectangle.getOuterColor());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				

				if(dialog.isConfirmation())
				{
					Point upperLeftPoint = new Point(Integer.parseInt(dialog.getTxtUpperLeftPointX().getText()), Integer.parseInt(dialog.getTxtUpperLeftPointY().getText()));
				
					Rectangle newRectangle = new Rectangle (upperLeftPoint, Integer.parseInt(dialog.getTxtWidth().getText()), Integer.parseInt(dialog.getTxtHeight().getText()), 
							dialog.getInnerColor(),	dialog.getOuterColor(), true);
					
					UpdateRectangleCmd updateRectangle = new UpdateRectangleCmd(rectangle, newRectangle);
					updateRectangle.execute();
					model.getUndoList().add(updateRectangle);
					model.getRedoList().clear();
					propertyChangeSupport.firePropertyChange("Undo", false, true);
					propertyChangeSupport.firePropertyChange("Redo", true, false);

					frame.repaint();
				}
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Invalid input!");
			}
		}	
		else if(selectedShape instanceof Circle)
		{
			try {
				Circle circle = (Circle)selectedShape;
				DlgCircleUpdate dialog = new DlgCircleUpdate(
						circle.getCenter(),
						circle.getRadius(),
						circle.getInnerColor(),
						circle.getOuterColor());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
				if(dialog.isConfirmation())
				{
					Point center = new Point(Integer.parseInt(dialog.getTxtCenterX().getText()), Integer.parseInt(dialog.getTxtCenterY().getText()));
				
					Circle newCircle = new Circle (center, Integer.parseInt(dialog.getTxtRadius().getText()), 
							dialog.getOuterColor(),	dialog.getInnerColor(), true);
					
					UpdateCircleCmd updateCircle = new UpdateCircleCmd(circle, newCircle);
					updateCircle.execute();
					model.getUndoList().add(updateCircle);
					model.getRedoList().clear();
					propertyChangeSupport.firePropertyChange("Undo", false, true);
					propertyChangeSupport.firePropertyChange("Redo", true, false);

					frame.repaint();
				}
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Invalid input!");
			}
		}
		else if (selectedShape instanceof HexagonAdapter)
		{
			try {
				HexagonAdapter hexagon = (HexagonAdapter)selectedShape;
				DlgHexagonUpdate dialog = new DlgHexagonUpdate(
						hexagon.getCenter(),
						hexagon.getRadius(),
						hexagon.getInnerColor(),
						hexagon.getOuterColor());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
				if(dialog.isConfirmation())
				{
					Point center = new Point(Integer.parseInt(dialog.getTxtCenterX().getText()), Integer.parseInt(dialog.getTxtCenterY().getText()));
				
					HexagonAdapter newHexagon = new HexagonAdapter (center, Integer.parseInt(dialog.getTxtRadius().getText()), 
							dialog.getOuterColor(),	dialog.getInnerColor(), true);
					
					UpdateHexagonCmd updateHexagon = new UpdateHexagonCmd(hexagon, newHexagon);
					updateHexagon.execute();
					model.getUndoList().add(updateHexagon);
					model.getRedoList().clear();
					propertyChangeSupport.firePropertyChange("Undo", false, true);
					propertyChangeSupport.firePropertyChange("Redo", true, false);

					frame.repaint();
				}
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Invalid input!");
			}
		}
	}
	
	public void toFrontSelectedShape() 
	{
		
		if(model.getSelectedShapes().isEmpty() || model.getSelectedShapes().size() > 1)
		{
			JOptionPane.showMessageDialog(frame, "You have to select only one shape if you want to move it to front!");
			return;
		}
		
		selectedShape = model.getSelectedShapes().get(0);
		
		int selectedShapeIndex = model.getShapes().indexOf(selectedShape);
		
		if(selectedShapeIndex == model.getShapes().size()-1)
		{
			JOptionPane.showMessageDialog(frame, "Your shape is already at the top!");
			return;
		}
		
		ToFront toFront = new ToFront(selectedShapeIndex, selectedShape, model);
		toFront.execute();
		model.getUndoList().add(toFront);
		model.getRedoList().clear();
		propertyChangeSupport.firePropertyChange("Undo", false, true);
		propertyChangeSupport.firePropertyChange("Redo", true, false);
		
		frame.repaint();
		
		
	}
	
	public void toBackSelectedShape() 
	{
		if(model.getSelectedShapes().isEmpty() || model.getSelectedShapes().size() > 1)
		{
			JOptionPane.showMessageDialog(frame, "You have to select only one shape if you want to move it to back!");
			return;
		}
		
		selectedShape = model.getSelectedShapes().get(0);
		
		int selectedShapeIndex = model.getShapes().indexOf(selectedShape);
		
		if(selectedShapeIndex == 0)
		{
			JOptionPane.showMessageDialog(frame, "Your shape is already at the lowest point!");
			return;
		}

		ToBack toBack = new ToBack(selectedShapeIndex, selectedShape, model);
		toBack.execute();
		model.getUndoList().add(toBack);
		model.getRedoList().clear();
		propertyChangeSupport.firePropertyChange("Undo", false, true);
		propertyChangeSupport.firePropertyChange("Redo", true, false);
		
		frame.repaint();
		
	}
	
	public void bringToFront()
	{
		if(model.getSelectedShapes().isEmpty() || model.getSelectedShapes().size() > 1)
		{
			JOptionPane.showMessageDialog(frame, "You have to select only one shape if you want to move it to front!");
			return;
		}
		
		selectedShape = model.getSelectedShapes().get(0);
		
		int selectedShapeIndex = model.getShapes().indexOf(selectedShape);
		
		if(selectedShapeIndex == model.getShapes().size()-1)
		{
			JOptionPane.showMessageDialog(frame, "Your shape is already at the top!");
			return;
		}
		
		BringToFront bringToFront = new BringToFront(selectedShapeIndex, selectedShape, model);
		bringToFront.execute();
		model.getUndoList().add(bringToFront);
		model.getRedoList().clear();
		propertyChangeSupport.firePropertyChange("Undo", false, true);
		propertyChangeSupport.firePropertyChange("Redo", true, false);
		
		frame.repaint();

	}
	
	public void bringToBack() 
	{

		if(model.getSelectedShapes().isEmpty() || model.getSelectedShapes().size() > 1)
		{
			JOptionPane.showMessageDialog(frame, "You have to select only one shape if you want to move it to back!");
			return;
		}
		
		selectedShape = model.getSelectedShapes().get(0);
		
		int selectedShapeIndex = model.getShapes().indexOf(selectedShape);
		
		if(selectedShapeIndex == 0)
		{
			JOptionPane.showMessageDialog(frame, "Your shape is already at the lowest point!");
			return;
		}
		
		BringToBack bringToBack= new BringToBack(selectedShapeIndex, selectedShape, model);
		bringToBack.execute();
		model.getUndoList().add(bringToBack);
		model.getRedoList().clear();
		propertyChangeSupport.firePropertyChange("Undo", false, true);
		propertyChangeSupport.firePropertyChange("Redo", true, false);
		
		frame.repaint();
	}

	public void undo()
	{
		if(model.getUndoList().isEmpty())
		{
			JOptionPane.showMessageDialog(frame, "Undo list is empty! You have nothing to undo.");
			return;
		}
		
		int lastIndex = model.getUndoList().size()-1;
		
		ICommand undoCommand = model.getUndoList().get(lastIndex);
		undoCommand.unexecute();
		model.getRedoList().add(undoCommand);
		model.getUndoList().remove(undoCommand);
		
		if(model.getUndoList().size() > 0 && model.getRedoList().size() > 0)
		{
			propertyChangeSupport.firePropertyChange("Undo", false, true);
			propertyChangeSupport.firePropertyChange("Redo", false, true);
		}
		else if (model.getUndoList().size() > 0)
		{
			propertyChangeSupport.firePropertyChange("Undo", false, true);
			propertyChangeSupport.firePropertyChange("Redo", true, false);
		}
		else if (model.getRedoList().size() > 0)
		{
			propertyChangeSupport.firePropertyChange("Undo", true, false);
			propertyChangeSupport.firePropertyChange("Redo", false, true);
		}
		else
		{
			propertyChangeSupport.firePropertyChange("Undo", true, false);
			propertyChangeSupport.firePropertyChange("Redo", true, false);
		}
		
				
		if(model.getSelectedShapes().size() == 0)
		{
			propertyChangeSupport.firePropertyChange("Modify", true, false);
			propertyChangeSupport.firePropertyChange("Delete", true, false);
		}
		else if(model.getSelectedShapes().size() == 1)
		{
			propertyChangeSupport.firePropertyChange("Modify", false, true);
			propertyChangeSupport.firePropertyChange("Delete", false, true);
		}
		else if(model.getSelectedShapes().size() > 1)
		{
			propertyChangeSupport.firePropertyChange("Modify", true, false);
			propertyChangeSupport.firePropertyChange("Delete", false, true);
		}
		
		frame.repaint();
	}
	
	public void redo()
	{
		if(model.getRedoList().isEmpty())
		{
			JOptionPane.showMessageDialog(frame, "Redo list is empty! You have nothing to redo.");
			return;
		}
		
		int lastIndex = model.getRedoList().size()-1;
		
		ICommand redoCommand = model.getRedoList().get(lastIndex);
		redoCommand.execute();
		model.getUndoList().add(redoCommand);
		model.getRedoList().remove(redoCommand);
		
		if(model.getUndoList().size() > 0 && model.getRedoList().size() > 0)
		{
			propertyChangeSupport.firePropertyChange("Undo", false, true);
			propertyChangeSupport.firePropertyChange("Redo", false, true);
		}
		else if (model.getUndoList().size() > 0)
		{
			propertyChangeSupport.firePropertyChange("Undo", false, true);
			propertyChangeSupport.firePropertyChange("Redo", true, false);
		}
		else if (model.getRedoList().size() > 0)
		{
			propertyChangeSupport.firePropertyChange("Undo", true, false);
			propertyChangeSupport.firePropertyChange("Redo", false, true);
		}
		else
		{
			propertyChangeSupport.firePropertyChange("Undo", true, false);
			propertyChangeSupport.firePropertyChange("Redo", true, false);
		}
		
				
		if(model.getSelectedShapes().size() == 0)
		{
			propertyChangeSupport.firePropertyChange("Modify", true, false);
			propertyChangeSupport.firePropertyChange("Delete", true, false);
		}
		else if(model.getSelectedShapes().size() == 1)
		{
			propertyChangeSupport.firePropertyChange("Modify", false, true);
			propertyChangeSupport.firePropertyChange("Delete", false, true);
		}
		else if(model.getSelectedShapes().size() > 1)
		{
			propertyChangeSupport.firePropertyChange("Modify", true, false);
			propertyChangeSupport.firePropertyChange("Delete", false, true);
		}
		
		frame.repaint();
	}
	
	public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener)
	{
		propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener)
	{
		propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
	}
	
	
}
