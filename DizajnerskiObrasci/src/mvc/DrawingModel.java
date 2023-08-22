package mvc;

import java.awt.Color;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import geometry1.Shape;
import strategy.DrawingStorageStrategy;

public class DrawingModel {
	
	public static  ArrayList<Shape> shapes=new ArrayList<Shape>();	
	
	public static ArrayList<Shape> selectedShapes = new ArrayList<>();
	

	public static String drawingObject = "Point" ;
	public static Color color = new Color(255, 255, 255);

	
	public List<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public void setSelectedShapes(ArrayList<Shape> selectedShapes) {
		this.selectedShapes = selectedShapes;
	}

	public void add(Shape s) {
		shapes.add(s);
	}

	public void remove(Shape s) {
		shapes.remove(s);
	}

	public List<Shape> getShapes() {
		return shapes;
	}
	
	/*public Point get (int index) {
		return shapes.get(index);
	}
*/
	  @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        for (Shape shape : shapes) {
	            sb.append(shape.toString()).append("\n");
	        }
	        return sb.toString();
	    }
	  /*Ova metoda prolazi kroz sve oblike (shapes) u crtežu i koristi njihove toString() metode kako bi ih dodala u StringBuilder. Svaki oblik se dodaje na novu liniju (\n).

Na kraju, StringBuilder koji sadrži sve oblike se pretvara u jedan veliki string koristeći toString() metodu StringBuilder klase, i taj string se vraća kao rezultat.

Ovo je korisno jer omogućava da dobijete tekstualni prikaz celog crteža. Na primer, ako imate crtež sa nekoliko oblika (krugova, pravougaonika itd.), pozivanje toString() na tom crtežu će rezultirati stringom koji sadrži informacije o svim oblicima.

Ovaj oblik implementacije toString() metode je čest u Javi i koristi se za olakšavanje debugiranja i pregleda objekata u čitljivom obliku.
*/
	  private DrawingStorageStrategy storageStrategy;

	    public DrawingModel(DrawingStorageStrategy storageStrategy) {
	        this.storageStrategy = storageStrategy;
	    }

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
	
}