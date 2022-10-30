package Drawing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;

import geometry1.Circle1;
import geometry1.Donut1;
import geometry1.Line1;
import geometry1.Point1;
import geometry1.Rectangle1;

public class PanelListener implements MouseListener {
	
	private boolean isFirstClick = true;
	private int line_x = 0;
	private int line_y = 0;

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {

		
		//obrisi selekciju
		for(int i = PanelDrawing.shapes.size()-1; i>=0; i--) {
			PanelDrawing.shapes.get(i).setSelected(false);
		}
		// provera selekcije
		System.out.println(PanelDrawing.shapes);
		for (int i=PanelDrawing.shapes.size()-1; i>=0; i--) {
			if ((PanelDrawing.shapes.get(i)).contains(e.getX(), e.getY())) {		// ukoliko je tacka klika u objektu
				if(PanelDrawing.shapes.get(i).isSelected() == true)
					PanelDrawing.shapes.get(i).setSelected(false);
				else 
					PanelDrawing.shapes.get(i).setSelected(true);
				return;															// ukoliko smo selektovali objekat, ne iscrtavamo vise nista pa izlazimo iz funkcije
			}
		}

		// ukoliko smo kliknuli van postojeceg objekta, onda crtamo
		
		

		switch(PanelDrawing.drawingObject) {
		case "Point" : 
			Point1 p1 = new Point1(e.getX(),e.getY());
			p1.setColor(PanelDrawing.color);
			PanelDrawing.shapes.add(p1);
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
				Line1 l1 = new Line1(new Point1(line_x, line_y), new Point1(e.getX(), e.getY()));
				l1.setColor(PanelDrawing.color);
				PanelDrawing.shapes.add(l1);
				isFirstClick = true;
			}
		break;
		default: 
			System.out.println("Izabrali ste oblik koji nije na listi!");
		}

	} 

}