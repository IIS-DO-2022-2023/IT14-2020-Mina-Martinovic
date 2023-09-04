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
import Drawing.DlgCircleUpdate;
import Drawing.DlgDonut;
import Drawing.DlgDonutUpdate;
import Drawing.DlgHexagon;
import Drawing.DlgHexagonUpdate;
import Drawing.DlgLine;
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
	//nije potrebno referencirati model i frame nadji zasto
	
	/*ovde ce mi se nalaziti sve metode crtanje selektovanje 
	undo redo delete modify
	tofront to back i sve i za cuvanje i ucitavanje i readlog OVDE!!
	*/
	
	private Color outColor = Color.BLACK;
	private Color inColor = Color.WHITE;

	private boolean isFirstClick = true;
	private int line_x = 0;
	private int line_y = 0;
	
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
			frame.getTglBtnDelete().setVisible(true); //isto
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
		DlgCircle dlgCircle = new DlgCircle();
		dlgCircle.setVisible(true);
		
		if(dlgCircle.isConfirmation()) {
			
				if(checkType(dlgCircle.getRadius().getText())) {
					int radius = Integer.parseInt(dlgCircle.getRadius().getText());
					Circle circle = null;
					try {
						circle = new Circle(new Point(e.getX(), e.getY()), radius, outColor, inColor);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
			} 
		}
	
	
	private void drawRectangle(MouseEvent e) {
		DlgRectangle dlgRectangle = new DlgRectangle();
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
		DlgDonut dlgDonut = new DlgDonut();
		dlgDonut.setVisible(true);

		if(dlgDonut.isConfirmation()) {
		
				if(checkType(dlgDonut.getOuterRadius().getText())) {
					if(dlgDonut.getOuterRadius().getText() != null && dlgDonut.getInnerRadius().getText() != null ) {
						Donut donut = new Donut(new Point(e.getX(), e.getY()), Integer.parseInt(dlgDonut.getOuterRadius().getText()), Integer.parseInt(dlgDonut.getInnerRadius().getText()), outColor, inColor);
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
			
		}
	}
	
	private void drawHexagon(MouseEvent e) { 
		DlgCircle dlgHex = new DlgCircle();
		dlgHex.setTitle("Add Hexagon");
		dlgHex.setVisible(true);

		if(dlgHex.isConfirmation()) {
			if(checkType(dlgHex.getRadius().getText())) {
				Hexagon hexagon = new Hexagon(e.getX(),e.getY(),Integer.parseInt(dlgHex.getRadius().getText()));
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
	
	
	
	/*
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
	
		/*
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
			DlgHexagon dialog = new DlgHexagon(e.getX(), e.getY(), 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
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
	*/

	public void deleteObject() {
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
	
		
		public void modifyShape() throws Exception {
			{
				if(model.getSelectedShapes().get(0) instanceof Point) {
					if(model.getSelectedShapes().get(0).isSelected()) {
						DlgPoint dlgPoint = new DlgPoint();
						Point oldState = (Point) model.getSelectedShapes().get(0);
						dlgPoint.getXKoordinata().setText(Integer.toString(oldState.getX()));
						dlgPoint.getYKoordinata().setText(Integer.toString(oldState.getY()));
						dlgPoint.setVisible(true);
						if(dlgPoint.isConfirmation()) {
							if(checkType(dlgPoint.getXKoordinata().getText()) && checkType(dlgPoint.getYKoordinata().getText())) {
								Point newState = new Point(Integer.parseInt(dlgPoint.getXKoordinata().getText()), Integer.parseInt(dlgPoint.getYKoordinata().getText()), dlgPoint.getColor());
								actLog.addElement("Updated->" + oldState.toString() + "->" + newState.toString());
								UpdatePointCmd pointUpdate = new UpdatePointCmd(oldState , newState);
								pointUpdate.execute();
								model.pushToUndoStack(pointUpdate);
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
						dlgLine.setVisible(true);
						if(dlgLine.isConfirmation()) {
							if(checkType(dlgLine.getXStartPoint().getText()) && checkType(dlgLine.getYStartPoint().getText()) && checkType(dlgLine.getXEndPoint().getText()) && checkType(dlgLine.getYEndPoint().getText())) {
								Line newLine = new Line(new Point(Integer.parseInt(dlgLine.getXStartPoint().getText()), Integer.parseInt(dlgLine.getYStartPoint().getText())), new Point(Integer.parseInt(dlgLine.getXEndPoint().getText()), Integer.parseInt(dlgLine.getYEndPoint().getText())), dlgLine.getColor());
								UpdateLineCmd lineUpdate = new UpdateLineCmd(oldLine,newLine);
								actLog.addElement("Updated->" + oldLine.toString() + "->" + newLine.toString());
								lineUpdate.execute();
								model.pushToUndoStack(lineUpdate);
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
						dlgRectangleUpdate.setVisible(true);
						if(dlgRectangleUpdate.isConfirmation()) {
							if(checkType(dlgRectangleUpdate.getTxtUpperLeftPointX().getText()) && checkType(dlgRectangleUpdate.getTxtUpperLeftPointY().getText()) && checkType(dlgRectangleUpdate.getTxtWidth().getText()) && checkType(dlgRectangleUpdate.getTxtHeight().getText())) {
								Rectangle newRectangle = new Rectangle(new Point(Integer.parseInt(dlgRectangleUpdate.getTxtUpperLeftPointX().getText()), Integer.parseInt(dlgRectangleUpdate.getTxtUpperLeftPointY().getText())), Integer.parseInt(dlgRectangleUpdate.getTxtWidth().getText()), Integer.parseInt(dlgRectangleUpdate.getTxtHeight().getText()),dlgRectangleUpdate.getOutlineColor(), dlgRectangleUpdate.getFillColor());
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
						dlgDonutUpdate.setVisible(true);
						if(dlgDonutUpdate.isConfirm()) {
							if(checkType(dlgDonutUpdate.getTxtX().getText()) && checkType(dlgDonutUpdate.getTxtY().getText()) && checkType(dlgDonutUpdate.getTxtOuterRadius().getText()) && checkType(dlgDonutUpdate.getTxtInnerRadius().getText())) {
								Donut newDonut = new Donut(new Point(Integer.parseInt(dlgDonutUpdate.getTxtX().getText()), Integer.parseInt(dlgDonutUpdate.getTxtY().getText())), Integer.parseInt(dlgDonutUpdate.getTxtOuterRadius().getText()), Integer.parseInt(dlgDonutUpdate.getTxtInnerRadius().getText()), dlgDonutUpdate.getBorderColor(), dlgDonutUpdate.getFillColor());
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
						dlgCircleUpdate.setVisible(true);
						if(dlgCircleUpdate.isConfirmation()) {
							try {
								if(checkType(dlgCircleUpdate.getTxtCenterX().getText()) && checkType(dlgCircleUpdate.getTxtCenterY().getText()) && checkType(dlgCircleUpdate.getTxtRadius().getText())) {
									Circle newCircle = new Circle(new Point(Integer.parseInt(dlgCircleUpdate.getTxtCenterX().getText()), Integer.parseInt(dlgCircleUpdate.getTxtCenterY().getText())), Integer.parseInt(dlgCircleUpdate.getTxtRadius().getText()),dlgCircleUpdate.getOutlineColor(), dlgCircleUpdate.getFillColor());
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
						dlgHexagonUpdate.getTxtR().setText(Integer.toString(oldHexagon.getHexagon().getR()));
						dlgHexagonUpdate.setVisible(true);
						if(dlgHexagonUpdate.isConfirmation()) {
							try {
								if(checkType(dlgHexagonUpdate.getTxtCenterX().getText()) && checkType(dlgHexagonUpdate.getTxtCenterY().getText()) && checkType(dlgHexagonUpdate.getTxtR().getText())) {
									Hexagon hex = new Hexagon(Integer.parseInt(dlgHexagonUpdate.getTxtCenterX().getText()), Integer.parseInt(dlgHexagonUpdate.getTxtCenterY().getText()), Integer.parseInt(dlgHexagonUpdate.getTxtR().getText()));
									hex.setAreaColor(dlgHexagonUpdate.getFillColor());
									hex.setBorderColor(dlgHexagonUpdate.getOutlineColor());
									HexAdapter adapter = new HexAdapter(hex);
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
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		
		
		
		/*
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
				else if(DrawingModel.shapes.get(i) instanceof HexAdapter) {
					try {
						DlgHexagon dialog = new DlgHexagon();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				
				break;
			}
		}
*/
			

	
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
			actLog.addElement(shape.toString()+"brought to front");
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
			else if (chooser.getFileFilter().getDescription().equals("Image"))
				fileManager = new FileManager(new SerializeDrawing(frame));
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
			//frame.getDlmBoje().clear();
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
	public void selectShapeFromLog(Shape shape) {
		int index = model.getShapes().indexOf(shape);
		Shape selectedShape = model.getShapes().get(index);
		SelectCommand selectCmd = new SelectCommand(model, selectedShape);
		selectCmd.execute();
		model.getUndoStack().push(selectCmd);
		frame.getView().repaint();
		
	}
	
	private void selectShape(MouseEvent e) {
		for (int i = 0; i < model.getShapes().size(); i++) {
			if(model.getShapes().get(i).contains(e.getX(), e.getY())) {
			
				if(model.getShapes().get(i).isSelected()) {
					return;
				}
				Shape shape = model.getShapes().get(i);
				SelectCommand selectCmd = new SelectCommand(model, shape);
				selectCmd.execute();
				actLog.addElement("Selected shapes ->" +shape.toString());
				model.getUndoStack().push(selectCmd);
			}
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