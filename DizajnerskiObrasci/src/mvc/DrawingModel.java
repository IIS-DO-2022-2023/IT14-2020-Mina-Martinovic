package mvc;

import java.util.ArrayList;
import java.util.List;

import command.ICommand;
import geometry1.Shape;

public class DrawingModel{

	private ArrayList<Shape> shapes;
	private ArrayList<Shape> selectedShapes;
	
	private ArrayList<ICommand> undoList;
	private ArrayList<ICommand> redoList;
	
	public DrawingModel()	
	{
		shapes = new ArrayList<Shape>();
		selectedShapes = new ArrayList<Shape>();
		undoList = new ArrayList<ICommand>();
		redoList = new ArrayList<ICommand>();
	}	
	
	public List<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public List<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public void setSelectedShapes(ArrayList<Shape> selectedShapes) {
		this.selectedShapes = selectedShapes;
	}

	public ArrayList<ICommand> getUndoList() {
		return undoList;
	}

	public void setUndoList(ArrayList<ICommand> undoList) {
		this.undoList = undoList;
	}

	public ArrayList<ICommand> getRedoList() {
		return redoList;
	}

	public void setRedoList(ArrayList<ICommand> redoList) {
		this.redoList = redoList;
	}
	
	
}
