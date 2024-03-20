package strategy;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

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
		
	}

}
