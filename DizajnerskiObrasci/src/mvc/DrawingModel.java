package mvc;

import java.awt.Color;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import command.Command;
import geometry1.Circle;
import geometry1.Point;
import geometry1.Shape;
import strategy.DrawingStorageStrategy;

public class DrawingModel {
	
	public static  ArrayList<Shape> shapes=new ArrayList<Shape>();	
	
	public static ArrayList<Shape> selectedShapes = new ArrayList<>();
	
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();


	public static String drawingObject = "Point" ;
	public static Color color = new Color(255, 255, 255);
	private Point startPoint;
	private PropertyChangeSupport propertyChangeSupport; //zaposmatranje objekata->Observer obrazac

	public DrawingModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
	}

	
	/*public Point get (int index) {
		return shapes.get(index);
	}
*/
	/*
	  @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        for (Shape shape : shapes) {
	            sb.append(shape.toString()).append("\n");
	        }
	        return sb.toString();
	    }
	    */
	  /*Ova metoda prolazi kroz sve oblike (shapes) u crtežu i koristi njihove toString() metode kako bi ih dodala u StringBuilder. Svaki oblik se dodaje na novu liniju (\n).

Na kraju, StringBuilder koji sadrži sve oblike se pretvara u jedan veliki string koristeći toString() metodu StringBuilder klase, i taj string se vraća kao rezultat.

Ovo je korisno jer omogućava da dobijete tekstualni prikaz celog crteža. Na primer, ako imate crtež sa nekoliko oblika (krugova, pravougaonika itd.), pozivanje toString() na tom crtežu će rezultirati stringom koji sadrži informacije o svim oblicima.

Ovaj oblik implementacije toString() metode je čest u Javi i koristi se za olakšavanje debugiranja i pregleda objekata u čitljivom obliku.
*/
	  private DrawingStorageStrategy storageStrategy;

	  /*
	    public DrawingModel(DrawingStorageStrategy storageStrategy) {
	        this.storageStrategy = storageStrategy;
	    }
	    */

	    public void saveDrawing(String filePath) {
	        DrawingStorageStrategy.saveDrawing(this, filePath); // Poziv strategije za čuvanje crteža
	    }

		/*public void addTransparentCircleWithHole() {
			 Ellipse2D outerCircle = new Ellipse2D.Double(50, 50, 100, 100);
		     Ellipse2D innerCircle = new Ellipse2D.Double(70, 70, 60, 60);
		     // Ova klasa ima konstruktor sa četiri parametra: 
		     //(double x, double y, double width, double height). 
		     //Ovi parametri se odnose na koordinate gornjeg levog ugla elipse (x i y),
		     //širinu elipse (width) i visinu elipse (height)

		     Area area = new Area(outerCircle);
		     area.subtract(new Area(innerCircle));

		     Shape Shape = null; //izmeni!!!!!!!
			shapes.add(Shape);
			ArrayList<Color>colors = new ArrayList<>();
			 colors.add(new Color(0, 0, 0, 0)); // Transparentna boja
			
		}
*/
		
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

		/*
		public void remove(Shape s) {
			shapes.remove(s);
		}
		*/
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
				this.undoStack.pop().unexecute();
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
			propertyChangeSupport.firePropertyChange("Selected Shapes", selectedShapesSizeBefore, selectedShapes.size());
		}
		
		public void removeSelectedShape(Shape toBeRemoved) {
			int index = shapes.indexOf(toBeRemoved);
			shapes.get(index).setSelected(false);
			selectedShapes.remove(toBeRemoved);
		}
		
	
}