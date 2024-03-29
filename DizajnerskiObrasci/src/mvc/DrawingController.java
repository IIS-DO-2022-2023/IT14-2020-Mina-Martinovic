package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import strategy.FileLog;
import strategy.FileManager;
import strategy.FileSerialization;

public class DrawingController {

	
	private DrawingFrame frame;
	private DrawingModel model;	
	
	private int line_x;
	private int line_y;
	
	private boolean isFirstClick = true;
	
	private Shape selectedShape;
	
	private PropertyChangeSupport propertyChangeSupport;
	
	private ArrayList<String> executeLoggerList = new ArrayList<String>();
	
	private int currentLoggerCommand = 0;
	
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
					frame.getDlm().addElement("Selected " + shape.toString());

					break;
				}
				else 
				{
					shape.setSelected(false);
					model.getSelectedShapes().remove(shape);
					frame.getDlm().addElement("Unselected " + shape.toString());

					break;
				}				
			}
			
		}
		if(containsShape == false)
		{
			if(model.getSelectedShapes().size() > 0)
			{
				frame.getDlm().addElement("All selected shapes unselected ");
			}
			
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
			
			frame.getDlm().addElement("Added " + point.toString());
			
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
					
					frame.getDlm().addElement("Added " + circle.toString());

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
					
					frame.getDlm().addElement("Added " + rectangle.toString());

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
					
					frame.getDlm().addElement("Added " + donut.toString());

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
				
				frame.getDlm().addElement("Added " + line.toString());
				
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
					
					frame.getDlm().addElement("Added " + hex.toString());

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
			
			frame.getDlm().addElement("All selected shapes deleted ");
			
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
					
					frame.getDlm().addElement("Modified to " + newPoint.toString());
									
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
					
					frame.getDlm().addElement("Modified to " + newLine.toString());

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
					
					frame.getDlm().addElement("Modified to " + newDonut.toString());

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
							dialog.getOuterColor(),	dialog.getInnerColor(), true);
					
					UpdateRectangleCmd updateRectangle = new UpdateRectangleCmd(rectangle, newRectangle);
					updateRectangle.execute();
					model.getUndoList().add(updateRectangle);
					model.getRedoList().clear();
					propertyChangeSupport.firePropertyChange("Undo", false, true);
					propertyChangeSupport.firePropertyChange("Redo", true, false);

					frame.getDlm().addElement("Modified to " + newRectangle.toString());
					
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
					
					frame.getDlm().addElement("Modified to " + newCircle.toString());

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
					
					frame.getDlm().addElement("Modified to " + newHexagon.toString());

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
		
		frame.getDlm().addElement("To front " + selectedShape.toString());
		
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
		
		frame.getDlm().addElement("To back " + selectedShape.toString());
		
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
		
		frame.getDlm().addElement("Brought to front " + selectedShape.toString());
		
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
		
		frame.getDlm().addElement("Brought to back " + selectedShape.toString());
		
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
		
		frame.getDlm().addElement("Undo " + undoCommand.toString().substring(8, undoCommand.toString().indexOf("@")) + " command");
		
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
		
		frame.getDlm().addElement("Redo " + redoCommand.toString().substring(8, redoCommand.toString().indexOf("@")) + " command");

		frame.repaint();
	}
	
	
	public void saveDrawing()
	{
		if(model.getShapes().isEmpty())
		{
			JOptionPane.showMessageDialog(frame, "You can't save an empty drawing!");
			return;
		}
		
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home")+ "/Desktop");
		int reply = fileChooser.showSaveDialog(frame);
		
		if(reply == JFileChooser.APPROVE_OPTION)
		{
			FileSerialization fileSerialization = new FileSerialization(model, fileChooser.getSelectedFile().getAbsolutePath());
			FileManager fileManager = new FileManager(fileSerialization);
			fileManager.save();
		}
	}	
	
	
	public void saveLog()
	{
		if(frame.getDlm().isEmpty())
		{
			JOptionPane.showMessageDialog(frame, "You can't save an empty log!");
			return;
		}
		
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home")+ "/Desktop");
		int reply = fileChooser.showSaveDialog(frame);
		
		if(reply == JFileChooser.APPROVE_OPTION)
		{
			System.out.println(fileChooser.getSelectedFile().getAbsolutePath().concat(".txt"));
			ArrayList<String> dlm = Collections.list(frame.getDlm().elements());
			System.out.println(dlm.size());
			
			FileLog fileLog = new FileLog(dlm, fileChooser.getSelectedFile().getAbsolutePath().concat(".txt"));
			FileManager fileManager = new FileManager(fileLog);
			fileManager.save();
		}
	}
	
	
	public void open()
	{
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home")+ "/Desktop");
		int reply = fileChooser.showOpenDialog(frame);
		
		if(reply == JFileChooser.APPROVE_OPTION)
		{
			if(fileChooser.getSelectedFile().getAbsolutePath().endsWith(".txt") == false)
			{
				FileSerialization fileSerialization = new FileSerialization(model, fileChooser.getSelectedFile().getAbsolutePath());
				FileManager fileManager = new FileManager(fileSerialization);
				fileManager.open();
				frame.repaint();
				System.out.println(model.getSelectedShapes().size());
			}			
			else 
			{
				executeLoggerList = new ArrayList<String>();
				currentLoggerCommand = 0;
				
				FileLog fileLog = new FileLog(executeLoggerList, fileChooser.getSelectedFile().getAbsolutePath());
				FileManager fileManager = new FileManager(fileLog);
				fileManager.open();
				frame.repaint();
				
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
		}		
	}
	
	public void executeLog()
	{
		if(executeLoggerList.size() > 0 && executeLoggerList.size() > currentLoggerCommand)
		{
			String s = executeLoggerList.get(currentLoggerCommand);
			System.out.println(s);
			String[] commandParts = s.split("[ ,]");
			String command = commandParts[0];
			
			if(command.equals("Added"))
			{
				executeCommandAdded(commandParts);
			}
			else if(command.equals("Selected"))
			{	
				executeCommandSelected(commandParts);
			}
			else if(command.equals("Unselected"))
			{
				executeCommandUnselected(commandParts);
			}
			else if(command.equals("All") && commandParts[3].equals("unselected"))
			{
				executeCommandUnselectedAll();
			}
			
			
			frame.repaint();

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
			
			currentLoggerCommand++;
		}
		else
		{
			JOptionPane.showMessageDialog(frame, "You must load a text file first!");
		}
	}
	
	public void executeCommandAdded(String[] commandParts)
	{
		String shapeName = commandParts[1];
		
		if(shapeName.contains("Point"))
		{
			int x = Integer.parseInt(commandParts[3]);
			int y = Integer.parseInt(commandParts[6]);
			Color outerColor = new Color(Integer.parseInt(commandParts[10]));
			
			Point point = new Point(x, y, outerColor);
			AddShape addShapePoint = new AddShape(point, model);
			addShapePoint.execute();
			
			frame.getDlm().addElement("Added " + point.toString());
		}
		else if (shapeName.contains("Line"))
		{
			int startX = Integer.parseInt(commandParts[5]);
			int startY = Integer.parseInt(commandParts[10]);
			int endX = Integer.parseInt(commandParts[15]);
			int endY = Integer.parseInt(commandParts[20]);
			Color outerColor = new Color(Integer.parseInt(commandParts[24]));
			
			Line line = new Line(new Point(startX, startY), new Point(endX, endY), outerColor);
			AddShape addShapeLine = new AddShape(line, model);
			addShapeLine.execute();
			
			frame.getDlm().addElement("Added " + line.toString());
					
		}
		else if (shapeName.contains("Circle"))
		{
			int radius = Integer.parseInt(commandParts[3]);
			int x = Integer.parseInt(commandParts[6]);
			int y = Integer.parseInt(commandParts[9]);
			
			Color outerColor = new Color(Integer.parseInt(commandParts[13]));
			Color innerColor = new Color(Integer.parseInt(commandParts[17]));
			
			Circle circle;
			try {
				circle = new Circle(new Point(x, y), radius, outerColor, innerColor);
				AddShape addShapeCircle = new AddShape(circle, model);
				addShapeCircle.execute();
				
				frame.getDlm().addElement("Added " + circle.toString());

			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, "Invalid circle data!");
			}
		}
		else if(shapeName.contains("Donut"))
		{
			int radius = Integer.parseInt(commandParts[3]);
			int innerRadius = Integer.parseInt(commandParts[7]);
			int x = Integer.parseInt(commandParts[10]);
			int y = Integer.parseInt(commandParts[13]);
			
			Color outerColor = new Color(Integer.parseInt(commandParts[17]));
			Color innerColor = new Color(Integer.parseInt(commandParts[21]));

			Donut donut = new Donut(new Point(x, y), radius, innerRadius, outerColor, innerColor);
			AddShape addShapeDonut = new AddShape(donut, model);
			addShapeDonut.execute();
			
			frame.getDlm().addElement("Added " + donut.toString());

		}
		else if(shapeName.contains("Rectangle"))
		{
			int x = Integer.parseInt(commandParts[3]);
			int y = Integer.parseInt(commandParts[6]);
			int height = Integer.parseInt(commandParts[9]);
			int width = Integer.parseInt(commandParts[12]);
			
			Color outerColor = new Color(Integer.parseInt(commandParts[16]));
			Color innerColor = new Color(Integer.parseInt(commandParts[20]));

			Rectangle rectangle = new Rectangle(new Point(x, y), width, height, outerColor, innerColor);
			AddShape addShapeRectangle = new AddShape(rectangle, model);
			addShapeRectangle.execute();
			
			frame.getDlm().addElement("Added " + rectangle.toString());

		}
		else if(shapeName.contains("Hexagon"))
		{
			int radius = Integer.parseInt(commandParts[3]);
			int x = Integer.parseInt(commandParts[6]);
			int y = Integer.parseInt(commandParts[9]);
			
			Color outerColor = new Color(Integer.parseInt(commandParts[13]));
			Color innerColor = new Color(Integer.parseInt(commandParts[17]));
			
			HexagonAdapter hexagon = new HexagonAdapter(new Point(x, y), radius, outerColor, innerColor);
			AddShape addShapeHexagon = new AddShape(hexagon, model);
			addShapeHexagon.execute();
			
			frame.getDlm().addElement("Added " + hexagon.toString());

		}
		else
		{
			JOptionPane.showMessageDialog(frame, "There's no such object in the list!");
		}
	}
	
	public void executeCommandSelected(String[] commandParts)
	{
		String shapeName = commandParts[1];

		if(shapeName.contains("Point"))
		{
			int x = Integer.parseInt(commandParts[3]);
			int y = Integer.parseInt(commandParts[6]);
			Color outerColor = new Color(Integer.parseInt(commandParts[10]));
			
			Point point = new Point(x, y, outerColor);
			
			int index = model.getShapes().indexOf(point);
			Shape executedSelectedShape = model.getShapes().get(index);
			executedSelectedShape.setSelected(true);
			model.getSelectedShapes().add(executedSelectedShape);
			
			frame.getDlm().addElement("Selected " + point.toString());
		}
		else if(shapeName.contains("Line"))
		{
			int startX = Integer.parseInt(commandParts[5]);
			int startY = Integer.parseInt(commandParts[10]);
			int endX = Integer.parseInt(commandParts[15]);
			int endY = Integer.parseInt(commandParts[20]);
			Color outerColor = new Color(Integer.parseInt(commandParts[24]));
			
			Line line = new Line(new Point(startX, startY), new Point(endX, endY), outerColor);
			
			int index = model.getShapes().indexOf(line);
			Shape executedSelectedShape = model.getShapes().get(index);
			executedSelectedShape.setSelected(true);
			model.getSelectedShapes().add(executedSelectedShape);
			
			frame.getDlm().addElement("Selected " + line.toString());
		}
		else if(shapeName.contains("Circle"))
		{
			int radius = Integer.parseInt(commandParts[3]);
			int x = Integer.parseInt(commandParts[6]);
			int y = Integer.parseInt(commandParts[9]);
			
			Color outerColor = new Color(Integer.parseInt(commandParts[13]));
			Color innerColor = new Color(Integer.parseInt(commandParts[17]));
			
			Circle circle;
			try {
				circle = new Circle(new Point(x, y), radius, outerColor, innerColor);
				
				int index = model.getShapes().indexOf(circle);
				Shape executedSelectedShape = model.getShapes().get(index);
				executedSelectedShape.setSelected(true);
				model.getSelectedShapes().add(executedSelectedShape);
				
				frame.getDlm().addElement("Selected " + circle.toString());

			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, "Invalid circle data!");
			}
		}
		else if(shapeName.contains("Donut"))
		{
			int radius = Integer.parseInt(commandParts[3]);
			int innerRadius = Integer.parseInt(commandParts[7]);
			int x = Integer.parseInt(commandParts[10]);
			int y = Integer.parseInt(commandParts[13]);
			
			Color outerColor = new Color(Integer.parseInt(commandParts[17]));
			Color innerColor = new Color(Integer.parseInt(commandParts[21]));

			Donut donut = new Donut(new Point(x, y), radius, innerRadius, outerColor, innerColor);

			int index = model.getShapes().indexOf(donut);
			Shape executedSelectedShape = model.getShapes().get(index);
			executedSelectedShape.setSelected(true);
			model.getSelectedShapes().add(executedSelectedShape);
			
			frame.getDlm().addElement("Selected " + donut.toString());
		}
		else if(shapeName.contains("Rectangle"))
		{
			int x = Integer.parseInt(commandParts[3]);
			int y = Integer.parseInt(commandParts[6]);
			int height = Integer.parseInt(commandParts[9]);
			int width = Integer.parseInt(commandParts[12]);
			
			Color outerColor = new Color(Integer.parseInt(commandParts[16]));
			Color innerColor = new Color(Integer.parseInt(commandParts[20]));

			Rectangle rectangle = new Rectangle(new Point(x, y), width, height, outerColor, innerColor);

			int index = model.getShapes().indexOf(rectangle);
			Shape executedSelectedShape = model.getShapes().get(index);
			executedSelectedShape.setSelected(true);
			model.getSelectedShapes().add(executedSelectedShape);
			
			frame.getDlm().addElement("Selected " + rectangle.toString());
		}
		else if(shapeName.contains("Hexagon"))
		{
			int radius = Integer.parseInt(commandParts[3]);
			int x = Integer.parseInt(commandParts[6]);
			int y = Integer.parseInt(commandParts[9]);
			
			Color outerColor = new Color(Integer.parseInt(commandParts[13]));
			Color innerColor = new Color(Integer.parseInt(commandParts[17]));
			
			HexagonAdapter hexagon = new HexagonAdapter(new Point(x, y), radius, outerColor, innerColor);

			int index = model.getShapes().indexOf(hexagon);
			Shape executedSelectedShape = model.getShapes().get(index);
			executedSelectedShape.setSelected(true);
			model.getSelectedShapes().add(executedSelectedShape);
			
			
			frame.getDlm().addElement("Selected " + hexagon.toString());

		}
		else
		{
			JOptionPane.showMessageDialog(frame, "There's no such object in the list!");
		}
	}
	
	public void executeCommandUnselected(String[] commandParts)
	{
		String shapeName = commandParts[1];
		
		if(shapeName.contains("Point"))
		{
			int x = Integer.parseInt(commandParts[3]);
			int y = Integer.parseInt(commandParts[6]);
			Color outerColor = new Color(Integer.parseInt(commandParts[10]));
			
			Point point = new Point(x, y, outerColor);				
			
			int index = model.getShapes().indexOf(point);
			Shape executedUnselectedShape = model.getShapes().get(index);
			executedUnselectedShape.setSelected(false);
			model.getSelectedShapes().remove(executedUnselectedShape);
			
			frame.getDlm().addElement("Unselected " + point.toString());
		}
		else if(shapeName.contains("Line"))
		{
			int startX = Integer.parseInt(commandParts[5]);
			int startY = Integer.parseInt(commandParts[10]);
			int endX = Integer.parseInt(commandParts[15]);
			int endY = Integer.parseInt(commandParts[20]);
			Color outerColor = new Color(Integer.parseInt(commandParts[24]));
			
			Line line = new Line(new Point(startX, startY), new Point(endX, endY), outerColor);
			
			int index = model.getShapes().indexOf(line);
			Shape executedUnselectedShape = model.getShapes().get(index);
			executedUnselectedShape.setSelected(false);
			model.getSelectedShapes().remove(executedUnselectedShape);
			
			frame.getDlm().addElement("Unselected " + line.toString());
		}
		else if(shapeName.contains("Circle"))
		{
			int radius = Integer.parseInt(commandParts[3]);
			int x = Integer.parseInt(commandParts[6]);
			int y = Integer.parseInt(commandParts[9]);
			
			Color outerColor = new Color(Integer.parseInt(commandParts[13]));
			Color innerColor = new Color(Integer.parseInt(commandParts[17]));
			
			Circle circle;
			try {
				circle = new Circle(new Point(x, y), radius, outerColor, innerColor);
				
				int index = model.getShapes().indexOf(circle);
				Shape executedUnselectedShape = model.getShapes().get(index);
				executedUnselectedShape.setSelected(false);
				model.getSelectedShapes().remove(executedUnselectedShape);
				
				frame.getDlm().addElement("Unselected " + circle.toString());

			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, "Invalid circle data!");
			}
		}
		else if(shapeName.contains("Donut"))
		{
			int radius = Integer.parseInt(commandParts[3]);
			int innerRadius = Integer.parseInt(commandParts[7]);
			int x = Integer.parseInt(commandParts[10]);
			int y = Integer.parseInt(commandParts[13]);
			
			Color outerColor = new Color(Integer.parseInt(commandParts[17]));
			Color innerColor = new Color(Integer.parseInt(commandParts[21]));

			Donut donut = new Donut(new Point(x, y), radius, innerRadius, outerColor, innerColor);

			int index = model.getShapes().indexOf(donut);
			Shape executedUnselectedShape = model.getShapes().get(index);
			executedUnselectedShape.setSelected(false);
			model.getSelectedShapes().remove(executedUnselectedShape);
			
			frame.getDlm().addElement("Unselected " + donut.toString());
		}
		else if(shapeName.contains("Rectangle"))
		{
			int x = Integer.parseInt(commandParts[3]);
			int y = Integer.parseInt(commandParts[6]);
			int height = Integer.parseInt(commandParts[9]);
			int width = Integer.parseInt(commandParts[12]);
			
			Color outerColor = new Color(Integer.parseInt(commandParts[16]));
			Color innerColor = new Color(Integer.parseInt(commandParts[20]));

			Rectangle rectangle = new Rectangle(new Point(x, y), width, height, outerColor, innerColor);

			int index = model.getShapes().indexOf(rectangle);
			Shape executedUnselectedShape = model.getShapes().get(index);
			executedUnselectedShape.setSelected(false);
			model.getSelectedShapes().remove(executedUnselectedShape);
			
			frame.getDlm().addElement("Unselected " + rectangle.toString());
		}
		else if(shapeName.contains("Hexagon"))
		{
			int radius = Integer.parseInt(commandParts[3]);
			int x = Integer.parseInt(commandParts[6]);
			int y = Integer.parseInt(commandParts[9]);
			
			Color outerColor = new Color(Integer.parseInt(commandParts[13]));
			Color innerColor = new Color(Integer.parseInt(commandParts[17]));
			
			HexagonAdapter hexagon = new HexagonAdapter(new Point(x, y), radius, outerColor, innerColor);

			int index = model.getShapes().indexOf(hexagon);
			Shape executedUnselectedShape = model.getShapes().get(index);
			executedUnselectedShape.setSelected(false);
			model.getSelectedShapes().remove(executedUnselectedShape);
			
			frame.getDlm().addElement("Unselected " + hexagon.toString());
		}
		else
		{
			JOptionPane.showMessageDialog(frame, "There's no such object in the list!");
		}
	}
	
	public void executeCommandUnselectedAll()
	{
		for(Shape shape : model.getSelectedShapes())
		{
			shape.setSelected(false);
		}
		
		frame.getDlm().addElement("All selected shapes unselected ");
		model.getSelectedShapes().clear();
		
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
