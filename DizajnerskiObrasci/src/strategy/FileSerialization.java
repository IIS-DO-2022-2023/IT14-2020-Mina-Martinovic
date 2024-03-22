package strategy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import geometry1.Shape;
import mvc.DrawingModel;

public class FileSerialization implements IFileStrategy{

	private DrawingModel model;
	private String filePath;
	
	public FileSerialization(DrawingModel model, String filePath)
	{
		this.model = model;
		this.filePath = filePath;
	}
		
	@Override
	public void save() {
		
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(model.getShapes());
			objectOutputStream.close();
			fileOutputStream.close();
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Saving was unsuccessfull!");
		}
	}

	@Override
	public void open() {
		
		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			
			ArrayList<Shape> shapes = (ArrayList<Shape>) objectInputStream.readObject();
			
			for(Shape shape : shapes)
			{
				model.getShapes().add(shape);
				if(shape.isSelected())
				{
					model.getSelectedShapes().add(shape);
				}
			}
			
			objectInputStream.close();
			fileInputStream.close();
			
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Opening of a file was unsuccessfull!");
		}
		
		
	}

}
