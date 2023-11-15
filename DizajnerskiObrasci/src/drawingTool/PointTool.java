package drawingTool;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;

import command.AddPointCmd;
import geometry1.Point;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class PointTool implements DrawingTool{

	private DrawingModel model;
	
	private DefaultListModel<String> actLog;
	private DrawingFrame frame;
	private Color outColor;
	
	public Color getOutColor() {
		return outColor;
	}

	public void setOutColor(Color outColor) {
		this.outColor = outColor;
	}

	public PointTool(DrawingModel model) {
		this.model = model;
	}
	
	public void mouseClicked(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY(), getOutColor());
		AddPointCmd addPoint = new AddPointCmd(model, point);
		addPoint.execute();
		model.pushToUndoStack(addPoint);
		actLog.addElement("Added->" + point.toString());
	    frame.getBtnRedo().setVisible(false);
	    model.getRedoStack().removeAllElements();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
