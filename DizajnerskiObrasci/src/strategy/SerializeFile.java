package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import geometry1.Shape;
import mvc.DrawingModel;

public class SerializeFile implements OptionChooser {

	private FileOutputStream fileOutputStream; 
	private FileInputStream fileInputStream;
	private DrawingModel model;


	public SerializeFile(DrawingModel model) {
		this.model = model;
	}

	@Override
	public void saveFile(File file) {
		try {
			fileOutputStream = new FileOutputStream(file + ".ser"); //da bi se otvorio izlazni tok za datoteku sa ekstenzijom .ser
			ObjectOutputStream out = new ObjectOutputStream(fileOutputStream); //serijalizovanje -> ( proces pretvaranja objekata u binarni format) liste svih oblika
			out.writeObject(model.getAllShapes());
			out.close();
			fileOutputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked") // potiskuje upozorenja kompajlera. U ovom slučaju, potiskuje upozorenje koje bi se pojavilo kada se kastuje objekat iz Object u ArrayList<Shape>
	@Override
	public void openFile(File file) {
		try {
			fileInputStream = new FileInputStream(file); //otvara ulazni tok ka datoteci
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream); //kako bi pročitao binarne podatke iz datoteke i konvertovao ih u listu oblika
	        model.addMultipleShapes((ArrayList<Shape>) objectInputStream.readObject()); //dodaje se u DrawigModel
	        objectInputStream.close();
	        fileInputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
