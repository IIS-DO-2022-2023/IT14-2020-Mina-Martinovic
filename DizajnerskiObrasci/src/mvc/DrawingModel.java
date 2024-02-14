package mvc;

import java.util.ArrayList;
import java.util.List;

import geometry1.Shape;

public class DrawingModel {

	private ArrayList<Shape> shapes;
	private ArrayList<Shape> selectedShapes;
	
	public DrawingModel()	
	{
		shapes = new ArrayList<Shape>();
		selectedShapes = new ArrayList<Shape>();
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
	
	
}
