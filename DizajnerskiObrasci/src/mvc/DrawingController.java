package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Drawing.DlgCircle;
import Drawing.DlgCircleUpdate;
import Drawing.DlgDonut;
import Drawing.DlgDonutUpdate;
import Drawing.DlgHexagonUpdate;
import Drawing.DlgLine;
import Drawing.DlgOption;
import Drawing.DlgPoint;
import Drawing.DlgRectangle;
import Drawing.DlgRectangleUpdate;
import adapter.HexAdapter;
import command.AddCircleCmd;
import command.AddDonutCmd;
import command.AddHexagonCmd;
import command.AddLineCmd;
import command.AddPointCmd;
import command.AddRectangleCmd;
import command.BringToBackCommand;
import command.BringToFrontCommand;

import command.Command;
import command.RemoveShapeCmd;
import command.SelectCommand;
import command.ToBackCommand;
import command.ToFrontCommand;
import command.UnselectCommand;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import drawingTool.DrawingTool;
import geometry1.Circle;
import geometry1.Donut;
import hexagon.Hexagon;
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
	private DrawingTool currentTool;
	
	 public void setCurrentTool(DrawingTool tool) {
	        this.currentTool = tool;
	    }
	
	/*ovde ce mi se nalaziti sve metode crtanje selektovanje 
	undo redo delete modify
	tofront to back i sve i za cuvanje i ucitavanje i readlog OVDE!!
	*/
	
	private Color outColor = Color.BLACK;
	private Color inColor = Color.WHITE;
	
	private DefaultListModel<String> actLog;
	private FileManager fileManager;

	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		super();
		this.model = model;
		this.frame = frame;
		this.actLog = frame.getDlmList();
	}
	
	public void mouseClicked(MouseEvent e) throws Exception{
		stateChecker(e);
		 if (currentTool != null) {
	            currentTool.mouseClicked(e);
	        }
		if(checkShapes(e) == false) {
			unselectAll();
		}
		frame.getView().repaint();
	}
	
	private PropertyChangeEvent pce;
	@Override 
	public void propertyChange(PropertyChangeEvent event) {
		this.pce = event;
		if((int) event.getNewValue() == 1 && event.getPropertyName() == "Selected Shapes" || model.getSelectedShapes().size() == 1) {
			frame.getTglBtnModify().setVisible(true); //ako postoji selektovan oblik
		} 
		else {
			frame.getTglBtnModify().setVisible(false);
		}
		if((int) event.getNewValue() == 1 && event.getPropertyName() == "Selected Shapes" || model.getSelectedShapes().size() > 0) {
			//prvi deo uslova mi proverava da li postoji neki trenutni event, novi i da li je on 1 odnosno da li je neki taster pritisnut ili ne
			frame.getTglBtnDelete().setVisible(true); //isto, proberava da li mi postoji makar  jedan selektovan shape
		} else {
			frame.getTglBtnDelete().setVisible(false);
		}
		if((int) event.getNewValue() == 0 && event.getPropertyName() == "Deleted Shapes") {
			frame.getTglBtnModify().setVisible(false);
			frame.getTglBtnDelete().setVisible(false);
		}
		if(event.getPropertyName() == "Undo Stack" && (int) event.getNewValue() > 0) {
			frame.getBtnUndo().setVisible(true); //dostupni kad je dostupna i funkcionalnost
		} else if ((int)event.getNewValue() == 0 && event.getPropertyName() == "Undo Stack Remove"){
			frame.getBtnUndo().setVisible(false);
		}

		if(event.getPropertyName() == "Redo Stack" && (int) event.getNewValue() > 0) {
			frame.getBtnRedo().setVisible(true);//dostupni kad je dostupna i funkcionalnost
		} else if ((int) event.getNewValue() == 0 && event.getPropertyName() == "Redo Stack Remove") {
			frame.getBtnRedo().setVisible(false);
		}
	}
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}

	
	
	
	private void drawPoint(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY(), getOutColor());
		AddPointCmd addPoint = new AddPointCmd(model, point);
		addPoint.execute();
		model.pushToUndoStack(addPoint);
		actLog.addElement("Added->" + point.toString());
		frame.getBtnRedo().setVisible(false);
		model.getRedoStack().removeAllElements();
	}
	
	private void drawLine(MouseEvent e) {
		if(model.getStartPoint() == null)
			model.setStartPoint(new Point(e.getX(), e.getY())) ;
		else
		{
			Line line = new Line(model.getStartPoint(), new Point(e.getX(), e.getY()), outColor);
			AddLineCmd addLine = new AddLineCmd(line, model);
			addLine.execute();
			model.pushToUndoStack(addLine);
			model.setStartPoint(null);
			actLog.addElement("Added->" + line.toString());
			frame.getBtnRedo().setVisible(false);
			model.getRedoStack().removeAllElements();
		}
		
	}
	
	private void drawCircle(MouseEvent e) {
		DlgCircle dlgCircle = new DlgCircle(e.getX(), e.getY(), frame.getInnerColorBtnBackgroundColor(), frame.getOuterColorBtnBackgroundColor());
		dlgCircle.setVisible(true);
		
		if(dlgCircle.isConfirmation()) {
			try {
				if(checkType(dlgCircle.getTxtRadius().getText())) {
					int radius = Integer.parseInt(dlgCircle.getTxtRadius().getText());
					Circle circle = new Circle(new Point(e.getX(), e.getY()), radius, outColor, inColor);
					AddCircleCmd addCircle = new AddCircleCmd(model,circle);
					addCircle.execute();
					model.pushToUndoStack(addCircle);
					actLog.addElement("Added->" + circle.toString());
					frame.getBtnRedo().setVisible(false);
					model.getRedoStack().removeAllElements();
				} else {
					JOptionPane.showMessageDialog(frame,
							"Illegal input type!",
							"Illegal radius error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex)
				{
					JOptionPane.showMessageDialog(frame,
							"Radius must be greater than 0!",
							"Illegal radius error",
							JOptionPane.ERROR_MESSAGE);
				}
			} 
		}
	
	
	private void drawRectangle(MouseEvent e) {
		DlgRectangle dlgRectangle = new DlgRectangle(e.getX(), e.getY(), frame.getInnerColorBtnBackgroundColor(), frame.getOuterColorBtnBackgroundColor());
		dlgRectangle.setVisible(true);

		if(dlgRectangle.isConfirmation()) {
			if(checkType(dlgRectangle.getTxtWidth().getText()) && checkType(dlgRectangle.getTxtHeight().getText())) {
				Rectangle rectangle = new Rectangle(new Point(e.getX(), e.getY()),Integer.parseInt(dlgRectangle.getTxtWidth().getText()),Integer.parseInt(dlgRectangle.getTxtHeight().getText()), outColor, inColor);
				AddRectangleCmd addRect = new AddRectangleCmd(model, rectangle);
				addRect.execute();
				model.pushToUndoStack(addRect);
				actLog.addElement("Added->" + rectangle.toString());
				frame.getBtnRedo().setVisible(false);
				model.getRedoStack().removeAllElements();
		} else {
			JOptionPane.showMessageDialog(frame,
					"Illegal input type!",
					"Illegal radius error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
	
	private void drawDonut(MouseEvent e) {
		DlgDonut dlgDonut = new DlgDonut(e.getX(), e.getY(), frame.getInnerColorBtnBackgroundColor(), frame.getOuterColorBtnBackgroundColor());
		dlgDonut.setVisible(true);

		if(dlgDonut.isConfirmation()) {
			try {
		
				if(checkType(dlgDonut.getOuterRadius().getText())) {
					if(dlgDonut.getOuterRadius().getText() != null && dlgDonut.getInnerRadius().getText() != null ) {
						Donut donut = new Donut(new Point(e.getX(), e.getY()), Integer.parseInt(dlgDonut.getOuterRadius().getText()), Integer.parseInt(dlgDonut.getInnerRadius().getText()), dlgDonut.getOuterColor(), dlgDonut.getInnerColor());
						AddDonutCmd addDonut = new AddDonutCmd(model,donut);
						addDonut.execute();
						model.pushToUndoStack(addDonut);
						actLog.addElement("Added->" + donut.toString());
						frame.getBtnRedo().setVisible(false);
						model.getRedoStack().removeAllElements();
					}
				} else {
					JOptionPane.showMessageDialog(frame,
							"Illegal input type!",
							"Illegal radius error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(frame,
						"Inner radius must be smaller than outer radius!",
						"Inappropriate radius error",
						JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			
		}
	}
	
	private void drawHexagon(MouseEvent e) throws NumberFormatException, Exception { 
		DlgCircle dlgHex = new DlgCircle(e.getX(), e.getY(), frame.getInnerColorBtnBackgroundColor(), frame.getOuterColorBtnBackgroundColor());
		dlgHex.setTitle("Add Hexagon");
		dlgHex.setVisible(true);

		if(dlgHex.isConfirmation()) {
			if(checkType(dlgHex.getTxtRadius().getText())) {
				Hexagon hexagon = new Hexagon(e.getX(),e.getY(),Integer.parseInt(dlgHex.getTxtRadius().getText()));
				hexagon.setBorderColor(outColor);
				hexagon.setAreaColor(inColor);
				HexAdapter adapter = new HexAdapter(hexagon);
				AddHexagonCmd addHex = new AddHexagonCmd(model,adapter);
				addHex.execute();
				model.pushToUndoStack(addHex);
				actLog.addElement("Added->" + adapter.toString());
				frame.getBtnRedo().setVisible(false);
				model.getRedoStack().removeAllElements();
			} else {
				JOptionPane.showMessageDialog(frame,
						"Illegal input type!",
						"Illegal radius error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	public void deleteObject() {
		DlgOption dlgOption = new DlgOption();
		dlgOption.setVisible(true);
		if(dlgOption.confirmation) {
			while(model.getSelectedShapes().size()>0) {
				for(int i = 0; i<model.getSelectedShapes().size(); i++) {
					Shape shape = model.getSelectedShapes().get(i);
					RemoveShapeCmd CDS = new RemoveShapeCmd(model, shape);
					CDS.execute();
					actLog.addElement("Deleted->" + shape.toString());
					model.getUndoStack().push(CDS);
						}	
					}
				
				}
				model.getRedoStack().removeAllElements();
				frame.repaint();
			}
	
		
		public void modifyShape() throws Exception {
			{
				if(model.getSelectedShapes().get(0) instanceof Point) {
					if(model.getSelectedShapes().get(0).isSelected()) {
						DlgPoint dlgPoint = new DlgPoint();
						Point oldState = (Point) model.getSelectedShapes().get(0);
						dlgPoint.getTxtX().setText(Integer.toString(oldState.getX()));
						dlgPoint.getTxtY().setText(Integer.toString(oldState.getY()));
						dlgPoint.setOuterColorBtnBackgroundColor(outColor);
						dlgPoint.setVisible(true);
						if(dlgPoint.isConfirmation()) {
							if(checkType(dlgPoint.getTxtX().getText()) && checkType(dlgPoint.getTxtY().getText())) {
								Point newState = new Point(Integer.parseInt(dlgPoint.getTxtX().getText()), Integer.parseInt(dlgPoint.getTxtY().getText()), dlgPoint.getOuterColorBtnBackgroundColor());
								actLog.addElement("Updated->" + oldState.toString() + "->" + newState.toString());
								UpdatePointCmd pointUpdate = new UpdatePointCmd(oldState , newState);
								pointUpdate.execute();
								model.pushToUndoStack(pointUpdate);
								model.getRedoStack().removeAllElements();
								frame.repaint();
							} else {
								JOptionPane.showMessageDialog(frame,
										"Illegal input type!",
										"Illegal radius error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				} else if (model.getSelectedShapes().get(0) instanceof Line) {

					if(model.getSelectedShapes().get(0).isSelected()) {
						DlgLine dlgLine = new DlgLine();
						Line oldLine = (Line) model.getSelectedShapes().get(0);
						dlgLine.getXStartPoint().setText((Integer.toString(oldLine.getStartPoint().getX())));
						dlgLine.getYStartPoint().setText((Integer.toString(oldLine.getStartPoint().getY())));
						dlgLine.getXEndPoint().setText((Integer.toString(oldLine.getEndPoint().getX())));
						dlgLine.getYEndPoint().setText((Integer.toString(oldLine.getEndPoint().getY())));
						dlgLine.setOuterColorBtnBackgroundColor(outColor);
						dlgLine.setVisible(true);
						if(dlgLine.isConfirmation()) {
							if(checkType(dlgLine.getXStartPoint().getText()) && checkType(dlgLine.getYStartPoint().getText()) && checkType(dlgLine.getXEndPoint().getText()) && checkType(dlgLine.getYEndPoint().getText())) {
								Line newLine = new Line(new Point(Integer.parseInt(dlgLine.getXStartPoint().getText()), Integer.parseInt(dlgLine.getYStartPoint().getText())), new Point(Integer.parseInt(dlgLine.getXEndPoint().getText()), Integer.parseInt(dlgLine.getYEndPoint().getText())), dlgLine.getOuterColorBtnBackgroundColor());
								UpdateLineCmd lineUpdate = new UpdateLineCmd(oldLine,newLine);
								actLog.addElement("Updated->" + oldLine.toString() + "->" + newLine.toString());
								lineUpdate.execute();
								model.pushToUndoStack(lineUpdate);
								model.getRedoStack().removeAllElements();
								frame.repaint();
							} else {
								JOptionPane.showMessageDialog(frame,
										"Illegal input type!",
										"Illegal radius error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				} else if (model.getSelectedShapes().get(0) instanceof Rectangle) {
					if(model.getSelectedShapes().get(0).isSelected()) {
						DlgRectangleUpdate dlgRectangleUpdate = new DlgRectangleUpdate();
						Rectangle oldRectangle = (Rectangle) model.getSelectedShapes().get(0);
						dlgRectangleUpdate.getTxtUpperLeftPointX().setText(Integer.toString(oldRectangle.getUpperLeftPoint().getX()));
						dlgRectangleUpdate.getTxtUpperLeftPointY().setText(Integer.toString(oldRectangle.getUpperLeftPoint().getY()));
						dlgRectangleUpdate.getTxtHeight().setText(Integer.toString(oldRectangle.getHeight()));
						dlgRectangleUpdate.getTxtWidth().setText(Integer.toString(oldRectangle.getWidth()));
						dlgRectangleUpdate.setOuterColorBtnBackgroundColor(outColor);
						dlgRectangleUpdate.setOuterColorBtnBackgroundColor(inColor);
						dlgRectangleUpdate.setVisible(true);
						if(dlgRectangleUpdate.isConfirmation()) {
							if(checkType(dlgRectangleUpdate.getTxtUpperLeftPointX().getText()) && checkType(dlgRectangleUpdate.getTxtUpperLeftPointY().getText()) && checkType(dlgRectangleUpdate.getTxtWidth().getText()) && checkType(dlgRectangleUpdate.getTxtHeight().getText())) {
								Rectangle newRectangle = new Rectangle(new Point(Integer.parseInt(dlgRectangleUpdate.getTxtUpperLeftPointX().getText()), Integer.parseInt(dlgRectangleUpdate.getTxtUpperLeftPointY().getText())), Integer.parseInt(dlgRectangleUpdate.getTxtWidth().getText()), Integer.parseInt(dlgRectangleUpdate.getTxtHeight().getText()),dlgRectangleUpdate.getOuterColorBtnBackgroundColor(), dlgRectangleUpdate.getInnerColorBtnBackgroundColor());
								UpdateRectangleCmd rectangleUpdate = new UpdateRectangleCmd(oldRectangle,newRectangle);
								actLog.addElement("Updated->" + oldRectangle.toString() + "->" + newRectangle.toString());
								rectangleUpdate.execute();
								model.pushToUndoStack(rectangleUpdate);
								model.getRedoStack().removeAllElements();
								frame.repaint();
							} else {
								JOptionPane.showMessageDialog(frame,
										"Illegal input type!",
										"Illegal radius error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
				else if (model.getSelectedShapes().get(0) instanceof Donut) {
					if(model.getSelectedShapes().get(0).isSelected()) {
						DlgDonutUpdate dlgDonutUpdate = new DlgDonutUpdate();
						Donut oldDonut = (Donut) model.getSelectedShapes().get(0);
						dlgDonutUpdate.getTxtX().setText(Integer.toString(oldDonut.getCenter().getX()));
						dlgDonutUpdate.getTxtY().setText(Integer.toString(oldDonut.getCenter().getY()));
						dlgDonutUpdate.getTxtInnerRadius().setText(Integer.toString(oldDonut.getInnerRadius()));
						dlgDonutUpdate.getTxtOuterRadius().setText(Integer.toString(oldDonut.getOuterRadius()));
						dlgDonutUpdate.setOuterColorBtnBackgroundColor(outColor);
						dlgDonutUpdate.setOuterColorBtnBackgroundColor(inColor);
						dlgDonutUpdate.setVisible(true);
						if(dlgDonutUpdate.isConfirm()) {
							if(checkType(dlgDonutUpdate.getTxtX().getText()) && checkType(dlgDonutUpdate.getTxtY().getText()) && checkType(dlgDonutUpdate.getTxtOuterRadius().getText()) && checkType(dlgDonutUpdate.getTxtInnerRadius().getText())) {
								Donut newDonut = new Donut(new Point(Integer.parseInt(dlgDonutUpdate.getTxtX().getText()), Integer.parseInt(dlgDonutUpdate.getTxtY().getText())), Integer.parseInt(dlgDonutUpdate.getTxtOuterRadius().getText()), Integer.parseInt(dlgDonutUpdate.getTxtInnerRadius().getText()), dlgDonutUpdate.getOuterColorBtnBackgroundColor(), dlgDonutUpdate.getInnerColorBtnBackgroundColor());
								UpdateDonutCmd donutUpdate = new UpdateDonutCmd(oldDonut, newDonut);
								actLog.addElement("Updated->" + oldDonut.toString() + "->" + newDonut.toString());
								donutUpdate.execute();
								model.pushToUndoStack(donutUpdate);
								model.getRedoStack().removeAllElements();
								frame.repaint();
							} else {
								JOptionPane.showMessageDialog(frame,
										"Illegal input type!",
										"Illegal radius error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
				else if (model.getSelectedShapes().get(0) instanceof Circle) { 
					if(model.getSelectedShapes().get(0).isSelected()) {
						DlgCircleUpdate dlgCircleUpdate = new DlgCircleUpdate();
						Circle oldCircle = (Circle) model.getSelectedShapes().get(0);
						dlgCircleUpdate.getTxtRadius().setText(Integer.toString(oldCircle.getRadius()));
						dlgCircleUpdate.getTxtCenterX().setText(Integer.toString(oldCircle.getCenter().getX()));
						dlgCircleUpdate.getTxtCenterY().setText(Integer.toString(oldCircle.getCenter().getY()));
						dlgCircleUpdate.setOuterColorBtnBackgroundColor(outColor);
						dlgCircleUpdate.setOuterColorBtnBackgroundColor(inColor);
						dlgCircleUpdate.setVisible(true);
						if(dlgCircleUpdate.isConfirmation()) {
							try {
								if(checkType(dlgCircleUpdate.getTxtCenterX().getText()) && checkType(dlgCircleUpdate.getTxtCenterY().getText()) && checkType(dlgCircleUpdate.getTxtRadius().getText())) {
									Circle newCircle = new Circle(new Point(Integer.parseInt(dlgCircleUpdate.getTxtCenterX().getText()), Integer.parseInt(dlgCircleUpdate.getTxtCenterY().getText())), Integer.parseInt(dlgCircleUpdate.getTxtRadius().getText()),dlgCircleUpdate.getOuterColorBtnBackgroundColor(), dlgCircleUpdate.getInnerColorBtnBackgroundColor());
									UpdateCircleCmd circleUpdate = new UpdateCircleCmd(oldCircle, newCircle);
									actLog.addElement("Updated->" + oldCircle.toString() + "->" + newCircle.toString());
									circleUpdate.execute();
									model.pushToUndoStack(circleUpdate);
									model.getRedoStack().removeAllElements();
									frame.repaint();
								} else {
									JOptionPane.showMessageDialog(frame,
											"Illegal input type!",
											"Illegal radius error",
											JOptionPane.ERROR_MESSAGE);
								}
							} catch (NumberFormatException e) {
								e.printStackTrace();
							}
						}
					}
				} else if (model.getSelectedShapes().get(0) instanceof HexAdapter) {
					if(((HexAdapter) model.getSelectedShapes().get(0)).getHexagon().isSelected()) {
						DlgHexagonUpdate dlgHexagonUpdate = new DlgHexagonUpdate();
						HexAdapter oldHexagon = (HexAdapter) model.getSelectedShapes().get(0);
						dlgHexagonUpdate.getTxtCenterX().setText(Integer.toString(oldHexagon.getHexagon().getX()));
						dlgHexagonUpdate.getTxtCenterY().setText(Integer.toString(oldHexagon.getHexagon().getY()));
						dlgHexagonUpdate.getTxtRadius().setText(Integer.toString(oldHexagon.getHexagon().getR()));
						dlgHexagonUpdate.setOuterColorBtnBackgroundColor(outColor);
						dlgHexagonUpdate.setOuterColorBtnBackgroundColor(inColor);
						dlgHexagonUpdate.setVisible(true);
						if(dlgHexagonUpdate.isConfirmation()) {
							try {
								if(checkType(dlgHexagonUpdate.getTxtCenterX().getText()) && checkType(dlgHexagonUpdate.getTxtCenterY().getText()) && checkType(dlgHexagonUpdate.getTxtRadius().getText())) {
									Hexagon newHex = new Hexagon(Integer.parseInt(dlgHexagonUpdate.getTxtCenterX().getText()), Integer.parseInt(dlgHexagonUpdate.getTxtCenterY().getText()), Integer.parseInt(dlgHexagonUpdate.getTxtRadius().getText()));
									newHex.setAreaColor(dlgHexagonUpdate.getInnerColorBtnBackgroundColor());
									newHex.setBorderColor(dlgHexagonUpdate.getOuterColorBtnBackgroundColor());
									HexAdapter adapter = new HexAdapter(newHex);
									UpdateHexagonCmd hexUpdate = new UpdateHexagonCmd(oldHexagon, adapter);
									actLog.addElement("Updated->" + oldHexagon.toString() + "->" + adapter.toString());
									hexUpdate.execute();
									model.pushToUndoStack(hexUpdate);
									model.getRedoStack().removeAllElements();
									frame.repaint();
								} else {
									JOptionPane.showMessageDialog(frame,
											"Illegal input type!",
											"Illegal radius error",
											JOptionPane.ERROR_MESSAGE);
								}
							} catch (NumberFormatException e) {
								//kada se pokuša konvertovati niz znakova (string) u numerički tip podataka
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		
	
	public void unselectAll() {
		for(int i = 0; i< model.getSelectedShapes().size(); i++) {
			Shape shape = model.getSelectedShapes().get(i);
			UnselectCommand unselect = new UnselectCommand(model, shape);
			unselect.execute();
			actLog.addElement("Unselected->" + shape.toString());
		}
		frame.getTglBtnModify().setVisible(false);
		frame.getTglBtnDelete().setVisible(false);
	}
	
	public void BringToFront() {
		if(model.getSelectedShapes().size() == 1) {
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			Shape shape = model.getShapes().get(index);
			BringToFrontCommand BringToFront = new BringToFrontCommand(model,shape);
			model.pushToUndoStack(BringToFront);
			model.getRedoStack().removeAllElements();
			actLog.addElement("Brought to front->" + shape.toString());
			BringToFront.execute();
		}
		frame.repaint();
	}

	public void BringToBack() {
		if(model.getSelectedShapes().size() == 1) {
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			Shape shape = model.getShapes().get(index);
			BringToBackCommand BringToBack = new BringToBackCommand(model, index, shape);
			model.pushToUndoStack(BringToBack);
			model.getRedoStack().removeAllElements();
			actLog.addElement("Brought to back->" + shape.toString());
			BringToBack.execute();
		}
		frame.repaint();
		
	}

	public void undo() {
		if(model.getUndoStack().size()>0) {
			Command command = model.getUndoStack().peek();
			model.pushToRedoStack(command);
			actLog.addElement("Undo->" + model.getUndoStack().peek().toString());
			model.removeFromUndoStack(); //maknem gai z stack-a jer sam ga vec prrbacila u redo sack tj pushovala
			frame.getView().repaint(); 
		}
	}

	public void redo() {
		if(model.getRedoStack().size()>0) {
			model.pushToUndoStack(model.getRedoStack().peek());
			actLog.addElement("Redo->" + model.getRedoStack().peek().toString());
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
			model.getRedoStack().removeAllElements();
			ToFront.execute();
			actLog.addElement( "Moved to front->" + shape.toString());
		} else {
			System.out.println("More than 2 shapes have been selected!");
		}
		frame.repaint();
	}
	
	public void toBack() {
		if(model.getSelectedShapes().size() == 1) {
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			Shape shape = model.getShapes().get(index);
			ToBackCommand ToBack = new ToBackCommand(model, index, shape);
			model.pushToUndoStack(ToBack);
			model.getRedoStack().removeAllElements();
			actLog.addElement("Moved to back->" + shape.toString());
			ToBack.execute();
		}
		frame.repaint();
	}
	
	public void deleteFromLog() {
		//služi za brisanje selektovanih oblika iz modela i njihovo belezenje u logu
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
	
	public void serialize() { //save
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
		}
		if (!model.getUndoStack().isEmpty() || model.getShapes().isEmpty()) chooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));
		if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			if (chooser.getFileFilter().getDescription().equals("Serialized draw")) fileManager = new FileManager(new SerializeFile(model));
			else if (chooser.getFileFilter().getDescription().equals("Commands log")) fileManager = new FileManager(new SerializeLog(frame, model, this));
			else fileManager = new FileManager(new SerializeDrawing(frame));
			fileManager.saveFile(chooser.getSelectedFile());
		}
		chooser.setVisible(false);
	}
	
	public void unserialize() { //open
		JFileChooser chooser = new JFileChooser();
		chooser.enableInputMethods(true);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
//		chooser.setFileFilter(new FileNameExtensionFilter("Serialized draw", "ser"));
		chooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));

		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			frame.getBtnUndo().setVisible(false);
			frame.getBtnRedo().setVisible(false);
			frame.getDlmList().clear();
			model.getShapes().clear();
			model.getUndoStack().clear();
			model.getRedoStack().clear();
			frame.getView().repaint();
			if (chooser.getFileFilter().getDescription().equals("Serialized draw")) {
				 fileManager = new FileManager(new SerializeFile(model));
			}
			else if (chooser.getFileFilter().getDescription().equals("Commands log")) fileManager = new FileManager(new SerializeLog(frame, model, this));
			fileManager.openFile(chooser.getSelectedFile());
		}	
		chooser.setVisible(false);
	}
	
	public void executeCommand(Command command) {
		//izvrsava sve komande i ovako se povezuje sa Command int
		command.execute();
		model.pushToUndoStack(command);
		frame.getView().repaint(); 	
		
	}
	public void selectShapeFromLog(Shape shape) { 
		//ima zadatak da odabere oblik iz loga i stavi ga u stanje "selektovanog" u okviru modela
		int index = model.getShapes().indexOf(shape);
		System.out.println("INDEX " + index);
		Shape selectedShape = model.getShapes().get(index);
		SelectCommand selectCmd = new SelectCommand(model, selectedShape);
		selectCmd.execute();
		model.getUndoStack().push(selectCmd); //omogućava korisniku da kasnije poništi selektovanje ako želi.
		frame.getView().repaint();
		
	}
	
	private void selectShape(MouseEvent e) {
		for (int i = 0; i < model.getShapes().size(); i++) {
			if(model.getShapes().get(i).contains(e.getX(), e.getY())) {
			
				Shape shape = model.getShapes().get(i);
				
				if(model.getShapes().get(i).isSelected()) {
					UnselectCommand unselect = new UnselectCommand(model, shape);
					unselect.execute();
					actLog.addElement("Unselected->" + shape.toString());					
					model.getUndoStack().push(unselect);
					model.getRedoStack().removeAllElements();
				}
				else {
					SelectCommand selectCmd = new SelectCommand(model, shape);
					selectCmd.execute();
					actLog.addElement("Selected->" +shape.toString());
					model.getUndoStack().push(selectCmd);
					model.getRedoStack().removeAllElements();
				}
			}
		}
		
		//proveri da li postoji selektova n oblik iz modela
		//ako ne postoji disableuj dugmad modify i delete
		
		if(!model.isAnyShapeSelected())
		{
			frame.getTglBtnModify().setVisible(false);
			frame.getTglBtnDelete().setVisible(false);
		}
	}

		
	private boolean checkType(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch(NumberFormatException e1) {
			return false;
		}
	}
	
	private boolean checkShapes(MouseEvent e) {
		for(int i = 0; i < model.getShapes().size(); i++)
		{
			if(model.getShapes().get(i).contains(e.getX(), e.getY())) {
				return true;
			}
		}
		return false;
	}
	
	
	public void stateChecker(MouseEvent e) throws Exception {
		if(frame.getState() == 1)
		{
			drawPoint(e);
		}
		else if (frame.getState() == 2)
		{
			drawLine(e);
		}
		else if (frame.getState() == 3)
		{
			drawCircle(e);
		}
		else if (frame.getState() == 4)
		{
			drawRectangle(e);
		}
		else if(frame.getState() == 5)
		{
			drawDonut(e);
		}
		else if(frame.getState() == 6)
		{
			drawHexagon(e);
		}
		else if(frame.getState() == 7)           
		{
			selectShape(e);
		}
		else if(frame.getState() == 8)
		{
			modifyShape();
		}
	}
	

	public Color getOutColor() {
		return outColor;
	}
	public void setOutColor(Color outColor) {
		this.outColor = outColor;
	}
	public Color getInColor() {
		return inColor;
	}
	public void setInColor(Color inColor) {
		this.inColor = inColor;
	}
}