package mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import command.Command;
import geometry1.Point;
import geometry1.Shape;

public class DrawingModel {
	
	public static ArrayList<Shape> shapes=new ArrayList<Shape>();	
	
	public static ArrayList<Shape> selectedShapes = new ArrayList<>();
	
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();

	//public static String drawingObject = "Point" ;
	//public static Color color = new Color(255, 255, 255);
	
	private Point startPoint;
	private PropertyChangeSupport propertyChangeSupport; //zaposmatranje objekata->Observer obrazac

	
	public DrawingModel() {
		propertyChangeSupport = new PropertyChangeSupport(this); //inicijalizacija propertyChangeSupport objekta
		//prati svojstva trenutnog drawing model objekta pomocu ovog this
	}
	
	public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
	}

		
		public Stack<Command> getUndoStack() {
			return undoStack;
		}

		public void setUndoStack(Stack<Command> undoStack) {
			this.undoStack = undoStack;
		}

		public Stack<Command> getRedoStack() {
			return redoStack;
		}

		public void setRedoStack(Stack<Command> redoStack) {
			this.redoStack = redoStack;
		}
		
		public Point getStartPoint() {
			return startPoint;
		}

		public void setStartPoint(Point startPoint) {
			this.startPoint = startPoint;
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
		
		public Shape getByIndex(int index) {
			return shapes.get(index);
		}

		public ArrayList<Shape> getAllShapes() {
			return (ArrayList<Shape>) shapes;
		}
		
		public void addMultipleShapes(ArrayList<Shape> shapes) {
			this.shapes.addAll(shapes);
		}
		

		public void add(Shape toBeAdded) {
			shapes.add(toBeAdded);
		}

		public void removeShape(Shape toBeRemoved) {
			int selectedShapesSizeBefore = selectedShapes.size();
			if(shapes.remove(toBeRemoved) == false) {
				System.out.println("Shape does not exist in list of shapes!");
			}
			
			selectedShapes.remove(toBeRemoved);
			propertyChangeSupport.firePropertyChange("Deleted Shapes", selectedShapesSizeBefore, selectedShapes.size());
		}

	
		public void pushToUndoStack(Command toBePushed) {
			int undoStackSizeBefore = undoStack.size();
			this.undoStack.push(toBePushed);
			propertyChangeSupport.firePropertyChange("Undo Stack", undoStackSizeBefore, undoStack.size());
		}
		
		public void removeFromUndoStack() {
			int undoStackSizeBefore = undoStack.size();
			if(undoStack.peek()!=null) {
				this.undoStack.pop().unexecute(); // uklanja i vraća poslednji element sa steka za "undo".  Nakon što se akcija povuče iz steka, poziva se metoda unexecute() na toj akciji. 
				// metoda treba da ima logiku za poništavanje efekata akcije. Na primer, ako je akcija bila dodavanje oblika na crtež, unexecute() bi trebalo da obriše taj oblik.
			}
			propertyChangeSupport.firePropertyChange("Undo Stack Remove", undoStackSizeBefore, undoStack.size());
		}
		
		public void pushToRedoStack(Command toBePushed) {
			int redoStackSizeBefore = redoStack.size();
			this.redoStack.push(toBePushed);
			propertyChangeSupport.firePropertyChange("Redo Stack", redoStackSizeBefore, redoStack.size());
		}
		
		public void removeFromRedoStack() {
			int redoStackSizeBefore = redoStack.size();
			if(redoStack.peek()!=null) {
				this.redoStack.pop().execute();
			}
			propertyChangeSupport.firePropertyChange("Redo Stack Remove", redoStackSizeBefore, redoStack.size());
		}
		
		public int getIndexOfShape(Shape s) {
			int listSize = shapes.size() - 1;
			
			for (int i = 0; i <= listSize; i++) {
				if (shapes.get(i).equals(s)) {
					
					return i;
				}
			}
			return -1;
		}

		public void addSelectedShape(Shape selectedShape) {
			int selectedShapesSizeBefore = selectedShapes.size();
			selectedShapes.add(selectedShape);
			System.out.println(shapes.get(0).isSelected());
			propertyChangeSupport.firePropertyChange("Selected Shapes", selectedShapesSizeBefore, selectedShapes.size()); //generiše događaj koji obaveštava sve registrovane slušaoce (listener-e) o promeni u svojstvima modela
		}
		
		public void removeSelectedShape(Shape toBeRemoved) {
			int index = shapes.indexOf(toBeRemoved);
			shapes.get(index).setSelected(false);
			selectedShapes.remove(toBeRemoved);
		}
		
	
}