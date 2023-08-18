package mvc;

import java.util.ArrayList;
import java.util.List;

import strategy.DrawingStorageStrategy;

public class DrawingModel {
	private List<Shape> shapes = new ArrayList<>();
	
	public void add(Shape s) {
		shapes.add(s);
	}
	
	public void remove(Shape s) {
		shapes.remove(s);
	}

	public List<Shape> getShapes() {
		return shapes;
	}

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

}