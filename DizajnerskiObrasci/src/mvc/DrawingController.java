package mvc;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Drawing.DlgCircle;
import Drawing.DlgDonut;
import Drawing.DlgLine;
import Drawing.DlgPoint;
import Drawing.DlgRectangle;

import geometry1.Circle;
import geometry1.Donut;
import geometry1.Line;
import geometry1.Point;
import geometry1.Rectangle;


public class DrawingController {

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

	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	//public void mouseClicked(MouseEvent e) {
		//Point p = new Point(e.getX(), e.getY(), Color.RED);
		//model.add(p);
		//AddPointCmd addPointCmd = new AddPointCmd(model, p);
		//addPointCmd.execute();
		
		//SremBanatBacka sremBanatBacka = new SremBanatBacka();
		
		/*Point srem = new Point(20, 20, Color.BLUE);
		Point banat = new Point(30, 20, Color.BLUE);
		Point backa = new Point(25, 12, Color.BLUE);
	*/	
		//sremBanatBacka.add(srem);
		//sremBanatBacka.add(banat);
		//sremBanatBacka.add(backa);
		
	//	model.add(sremBanatBacka);
		
	//	frame.repaint();
	//}
	
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}
	
	public void mouseClicked(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {
		
		//obrisi selekciju
		for(int i = DrawingModel.shapes.size()-1; i>=0; i--) {
			DrawingModel.shapes.get(i).setSelected(false);
		}
		// provera selekcije
		System.out.println(DrawingModel.shapes);
		for (int i=DrawingModel.shapes.size()-1; i>=0; i--) {
			if ((DrawingModel.shapes.get(i)).contains(e.getX(), e.getY())) {		// ukoliko je tacka klika u objektu
				if(DrawingModel.shapes.get(i).isSelected() == true)
					DrawingModel.shapes.get(i).setSelected(false);
				else 
					DrawingModel.shapes.get(i).setSelected(true);
				return;															// ukoliko smo selektovali objekat, ne iscrtavamo vise nista pa izlazimo iz funkcije
			}
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
					DrawingModel.shapes.remove(i);
				}
			}
		}
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

	
	public void bringToFront(ActionEvent e) {
		
	}

	public void btnSendToBack(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}