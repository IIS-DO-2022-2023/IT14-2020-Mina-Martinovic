package mvc;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Drawing.DlgCircle;
import Drawing.DlgDonut;
import Drawing.DlgLine;
import Drawing.DlgPoint;
import Drawing.DlgRectangle;
import command.AddDonutCmd;
import command.BringToBackCommand;
import command.BringToFrontCommand;

import command.Command;
import command.RemoveShapeCmd;
import command.SelectCommand;
import command.ToBackCommand;
import command.ToFrontCommand;
import command.UnselectCommand;
import command.UpdatePointCmd;
import geometry1.Circle;
import geometry1.Donut;
import geometry1.Line;
import geometry1.Point;
import geometry1.Rectangle;
import geometry1.Shape;

import strategy.FileManager;
import strategy.SerializeDrawing;
import strategy.SerializeFile;
import strategy.SerializeLog;

public class DrawingController implements PropertyChangeListener{

	private DrawingModel model;
	private DrawingFrame frame;
	//nije potrebno referencirati model i frame nadji zasto
	
	/*ovde ce mi se nalaziti sve metode crtanje selektovanje 
	undo redo delete modify
	tofront to back i sve i za cuvanje i ucitavanje i readlog OVDE!!
	*/
	
	private boolean isFirstClick = true;
	private int line_x = 0;
	private int line_y = 0;
	private DefaultListModel<String> actLog;
	private FileManager fileManager;
	//private PropertyChangeSupport propertyChangeSupport;

//	public DrawingController() {
	//	propertyChangeSupport = new PropertyChangeSupport(this);
	//}
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		this.actLog = frame.getDlmList();
	}
	
	private PropertyChangeEvent pce;
	@Override 
	public void propertyChange(PropertyChangeEvent evt) {
		this.pce = evt;
		if((int) evt.getNewValue() == 1 && evt.getPropertyName() == "Selected Shapes" || model.getSelectedShapes().size() == 1) {
			frame.getbtnModifikacija().setVisible(true);
		} 
		else {
			frame.getbtnModifikacija().setVisible(false);
		}
		if((int) evt.getNewValue() == 1 && evt.getPropertyName() == "Selected Shapes" || model.getSelectedShapes().size() > 0) {
			frame.getbtnBrisanje().setVisible(true);
		} else {
			frame.getbtnBrisanje().setVisible(false);
		}
		if((int) evt.getNewValue() == 0 && evt.getPropertyName() == "Deleted Shapes") {
			frame.getbtnModifikacija().setVisible(false);
			frame.getbtnBrisanje().setVisible(false);
		}
		if(evt.getPropertyName() == "Undo Stack" && (int) evt.getNewValue() > 0) {
			frame.getBtnUndo().setVisible(true);
		} else if ((int)evt.getNewValue() == 0 && evt.getPropertyName() == "Undo Stack Remove"){
			frame.getBtnUndo().setVisible(false);
		}

		if(evt.getPropertyName() == "Redo Stack" && (int) evt.getNewValue() > 0) {
			frame.getBtnRedo().setVisible(true);
		} else if ((int) evt.getNewValue() == 0 && evt.getPropertyName() == "Redo Stack Remove") {
			frame.getBtnRedo().setVisible(false);
		}
	}
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}
	
	public void mouseClicked(MouseEvent e) {
		/*
		stateChecker(e);
		if(checkShapes(e) == false) {
			unselectAll();
		}
		*/
		frame.getView().repaint();
	}

	public void mouseReleased(MouseEvent e) {
	
		//obrisi selekciju
		for(int i = DrawingModel.shapes.size()-1; i>=0; i--) {
			DrawingModel.shapes.get(i).setSelected(false);
		}
		
		// provera selekcije
		System.out.println(DrawingModel.shapes);
		for (int i=DrawingModel.shapes.size()-1; i>=0; i--) {
			
			if ((DrawingModel.shapes.get(i)).contains(e.getX(), e.getY())) {		// ukoliko je tacka klika u objektu
				/*Shape clickedShape = DrawingModel.shapes.get(i);
		        if (DrawingModel.selectedShapes.contains(clickedShape)) {
		        	DrawingModel.selectedShapes.remove(clickedShape);
		        } else {
		        	DrawingModel.selectedShapes.add(clickedShape);
		        }
		        */

				if(model.getShapes().get(i).isSelected())
				{
					return;
				}
				Shape shape = model.getShapes().get(i);
				SelectCommand selectCmd = new SelectCommand(model, shape);
				selectCmd.execute();
				actLog.addElement("Selected->" + shape.toString());
				model.getUndoStack().push(selectCmd);
			}
		}
	
		
		public void selectShapeFromLog(Shape shape) {
			int index = model.getShapes().indexOf(shape);
			Shape selectedShape = model.getShapes().get(index);
			SelectCommand CmdSelect = new SelectCommand(model, selectedShape);
			CmdSelect.execute();
			model.getUndoStack().push(CmdSelect);
			frame.getView().repaint();
		}

		public void unselectAll() {
			for(int i = 0; i< model.getSelectedShapes().size(); i++) {
				Shape shape = model.getSelectedShapes().get(i);
				UnselectCommand unselect = new UnselectCommand(model, shape);
				unselect.execute();
				actLog.addElement("Unselected->" + shape.toString());
			}
			frame.getbtnModifikacija().setVisible(false);
			frame.getbtnBrisanje().setVisible(false);
		}
		
		// ukoliko smo kliknuli van postojeceg objekta, onda crtamo
		

		switch(DrawingModel.drawingObject) {
		case "Point" : 
			Point p1 = new Point(e.getX(),e.getY());
			p1.setColor(DrawingModel.color);
			DrawingModel.shapes.add(p1);
		break;
		case "Circle" : 
			try {
				DlgCircle dialog = new DlgCircle(e.getX(), e.getY(), 0);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		break;
		case "Rectangle" :
			try {
				DlgRectangle dialog = new DlgRectangle(e.getX(), e.getY(), 0, 0);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		break;
		case "Donut" : 
			try {
				DlgDonut dialog = new DlgDonut(e.getX(), e.getY(), 0, 0);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		break;
		case "Hexagon" :
			try {
				
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
				Line l1 = new Line(new Point(line_x, line_y), new Point(e.getX(), e.getY()));
				l1.setColor(DrawingModel.color);
				DrawingModel.shapes.add(l1);
				isFirstClick = true;
			}
		break;
		default: 
			System.out.println("Izabrali ste oblik koji nije na listi!");
		}
	}
	
	public void deleteObject(ActionEvent e) {
		for (int i=DrawingModel.shapes.size()-1; i>=0; i--) {
			if(DrawingModel.shapes.get(i).isSelected() == true) {
				int reply = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete Vas objekat?");
				if(reply == JOptionPane.YES_OPTION) {
					Shape shape = model.getSelectedShapes().get(i);
					RemoveShapeCmd CDS = new RemoveShapeCmd(model, shape);
					CDS.execute();
					actLog.addElement("Deleted->" + shape.toString());
					model.getUndoStack().push(CDS);
					DrawingModel.shapes.remove(i);
				}
			}
		}
		frame.repaint();
	}
	
	public void modifyObject(ActionEvent e) {
		// prolazimo kroz sve promenljive kako bi proverili koja je selektovana
		for (int i=DrawingModel.shapes.size()-1; i>=0; i--) {
			if(DrawingModel.shapes.get(i).isSelected()) {
				// kreiramo dijalog
				if (DrawingModel.shapes.get(i) instanceof Point) {
					try {
						DlgPoint dialog = new DlgPoint();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				
				/*
				if (DrawingModel.shapes.get(i) instanceof Point) {
				DlgPoint dlgPoint = new DlgPoint();
				Point oldState = (Point) model.getSelectedShapes().get(0);
				dlgPoint.getX().setText(Integer.toString(oldState.getX()));
				dlgPoint.getY().setText(Integer.toString(oldState.getY()));
				dlgPoint.setVisible(true);
				if(dlgPoint.isConfirm()) {
					if(checkType(dlgPoint.getX().getText()) && checkType(dlgPoint.getY().getText())) {
						Point newState = new Point(Integer.parseInt(dlgPoint.getX().getText()), Integer.parseInt(dlgPoint.getY().getText()), dlgPoint.getColor());
						actLog.addElement("Updated->" + oldState.toString() + "->" + newState.toString());
						UpdatePointCmd CmdPointUpdate = new UpdatePointCmd(oldState , newState);
						UpdatePointCmd.execute();
						model.pushToUndoStack(CmdPointUpdate);
						frame.repaint();
					}
					*/
				}
				else if(DrawingModel.shapes.get(i) instanceof Donut) {							
					try {
						Donut d1 = (Donut) DrawingModel.shapes.get(i);
						DlgDonut dialog = new DlgDonut(
								d1.getCenter().getX(),
								d1.getCenter().getY(),
								d1.getRadius(),
								d1.getInnerRadius());
								//d1.getColor()
		
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				else if(DrawingModel.shapes.get(i) instanceof Circle) {

					try {
						Circle c1 = (Circle) DrawingModel.shapes.get(i);
						DlgCircle dialog = new DlgCircle(
								c1.getCenter().getX(),
								c1.getCenter().getY(),
								c1.getRadius());
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				else if(DrawingModel.shapes.get(i) instanceof Rectangle) {
					try {
						Rectangle r1 = (Rectangle) DrawingModel.shapes.get(i);
						DlgRectangle dialog = new DlgRectangle(
								r1.getUpperLeftPoint().getX(),
								r1.getUpperLeftPoint().getY(),
								r1.getWidth(),
								r1.getHeight());
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				else if(DrawingModel.shapes.get(i) instanceof Line) {
					try {
						DlgLine dialog = new DlgLine();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				
				break;
			}
		}

	}

	
	public void BringToFront(ActionEvent e) {
		if(model.getSelectedShapes().size() == 1) {
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			Shape shape = model.getShapes().get(index);
			BringToFrontCommand BringToFront = new BringToFrontCommand(model,shape);
			model.pushToUndoStack(BringToFront);
			actLog.addElement(shape.toString()+"brought to front");
			BringToFront.execute();
		}
		frame.repaint();
	}

	public void BringToBack(ActionEvent e) {
		if(model.getSelectedShapes().size() == 1) {
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			Shape shape = model.getShapes().get(index);
			BringToBackCommand BringToBack = new BringToBackCommand(model, index, shape);
			model.pushToUndoStack(BringToBack);
			actLog.addElement(shape.toString()+"brought to back");
			BringToBack.execute();
		}
		frame.repaint();
		
	}


	public void undo() {
		if(model.getUndoStack().size()>0) {
			Command command = model.getUndoStack().peek();
			model.pushToRedoStack(command);
			actLog.addElement("Undo is done to" + model.getUndoStack().peek().toString());
			model.removeFromUndoStack(); //maknem gai z stack-a jer sam ga vec prrbacila u redo sack tj pushovala
			frame.getView().repaint(); 
		}
	}

	public void redo() {
		if(model.getRedoStack().size()>0) {
			model.pushToUndoStack(model.getRedoStack().peek());
			actLog.addElement("Redo is done to" + model.getRedoStack().peek().toString());
			model.removeFromRedoStack();
			frame.getView().repaint();
		}	
	}
	
	public void toFront() {
		if(model.getSelectedShapes().size() == 1) {
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			Shape shape = model.getShapes().get(index);
			ToFrontCommand ToFront = new ToFrontCommand(model, index , shape);
			model.pushToUndoStack(ToFront);
			actLog.addElement( shape.toString()+"moved to front");
			ToFront.execute();
		} 
		frame.repaint();
	}
	
	public void toBack() {
		if(model.getSelectedShapes().size() == 1) {
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			Shape shape = model.getShapes().get(index);
			ToBackCommand ToBack = new ToBackCommand(model, index, shape);
			model.pushToUndoStack(ToBack);
			actLog.addElement(shape.toString()+"moved to back");
			ToBack.execute();
		}
		frame.repaint();
	}
	
	public void deleteFromLog() {
		while(model.getSelectedShapes().size()>0) {
			for(int i = 0; i<model.getSelectedShapes().size(); i++) {
				Shape shape = model.getSelectedShapes().get(i);
				RemoveShapeCmd RMShape = new RemoveShapeCmd(model, shape);
				RMShape.execute();
				actLog.addElement("Deleted->" + shape.toString());
				model.getUndoStack().push(RMShape);
		}
			frame.getView().repaint();
		}	
	}
	
	public void serialize() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
		chooser.enableInputMethods(false);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setDialogTitle("Save");
		chooser.setAcceptAllFileFilterUsed(false);

		if (!model.getShapes().isEmpty()) {
			chooser.setFileFilter(new FileNameExtensionFilter("Serialized draw", "ser"));
//			chooser.setFileFilter(new FileNameExtensionFilter("Image", "img"));
		}
		if (!model.getUndoStack().isEmpty() || model.getShapes().isEmpty()) chooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));
		if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			if (chooser.getFileFilter().getDescription().equals("Serialized draw")) fileManager = new FileManager(new SerializeFile(model));
			else if (chooser.getFileFilter().getDescription().equals("Commands log")) fileManager = new FileManager(new SerializeLog(frame, model, this));
			else /*if (chooser.getFileFilter().getDescription().equals("Image"))*/ fileManager = new FileManager(new SerializeDrawing(frame));
			fileManager.saveFile(chooser.getSelectedFile());
		}
		chooser.setVisible(false);
	}
	
	public void unserialize() {
		JFileChooser chooser = new JFileChooser();
		chooser.enableInputMethods(true);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
		chooser.setFileFilter(new FileNameExtensionFilter("Serialized draw", "ser"));
		chooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));

		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			frame.getBtnUndo().setVisible(false);
			frame.getBtnRedo().setVisible(false);
			frame.getDlmBoje().clear();
			model.getShapes().clear();
			model.getUndoStack().clear();
			model.getRedoStack().clear();
			frame.getView().repaint();
			if (chooser.getFileFilter().getDescription().equals("Serialized draw")) {
				FileManager fileManager = new FileManager(new SerializeFile(model));
			}
			else if (chooser.getFileFilter().getDescription().equals("Commands log")) fileManager = new FileManager(new SerializeLog(frame, model, this));
			fileManager.openFile(chooser.getSelectedFile());
		}	
		chooser.setVisible(false);
	}
	/*
	public void save() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
	    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
		chooser.enableInputMethods(false);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setDialogTitle("Save");
		chooser.setAcceptAllFileFilterUsed(false);
		if (!model.getAll().isEmpty()) {
			chooser.setFileFilter(new FileNameExtensionFilter("Serialized draw", "ser"));
			chooser.setFileFilter(new FileNameExtensionFilter("Picture", "jpeg"));
		}
		if (!commands.isEmpty()) chooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));
		if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			if (chooser.getFileFilter().getDescription().equals("Serialized draw")) manager = new ManagerFile(new SerializableFile(model));
			else if (chooser.getFileFilter().getDescription().equals("Commands log")) manager = new ManagerFile(new LogFile(frame, model, this));
			else manager = new ManagerFile(new SaveDraw(frame));
			manager.save(chooser.getSelectedFile());
		}
		chooser.setVisible(false);
	}
	
	public void open() {
		JFileChooser chooser = new JFileChooser();
		chooser.enableInputMethods(true);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
		chooser.setFileFilter(new FileNameExtensionFilter("Serialized draw", "ser"));
		chooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			model.removeAll();
			log.removeAllElements();
			undoCommands.clear();
			commands.clear();
			frame.getView().repaint();
			if (chooser.getFileFilter().getDescription().equals("Serialized draw")) {
				manager = new ManagerFile(new SerializableFile(model));
				propertyChangeSupport.firePropertyChange("serialized draw opened", false, true);
			}
			else if (chooser.getFileFilter().getDescription().equals("Commands log")) manager = new ManagerFile(new LogFile(frame, model, this));
			manager.open(chooser.getSelectedFile());
		}	
		chooser.setVisible(false);
	}
	*/
	public void executeCommand(Command command) {
		command.execute();
		model.pushToUndoStack(command);
		frame.getView().repaint(); 	
		
	}
	public void selectShapeFromLog(Shape selectedShape) {
		// TODO Auto-generated method stub
		
	}
	

}