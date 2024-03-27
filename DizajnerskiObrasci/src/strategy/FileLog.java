package strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import mvc.DrawingFrame;

public class FileLog implements IFileStrategy{

	private ArrayList<String> panelLoggerList;
	private String filePath;
	
	public FileLog(ArrayList<String> panelLoggerList, String filePath)
	{
		this.panelLoggerList = panelLoggerList;
		this.filePath = filePath;	
	}
	
	@Override
	public void save() {
		
		try 
		{
			File file = new File(filePath);

			PrintWriter printWriter = new PrintWriter(file);
			for(String text : panelLoggerList)
			{
				printWriter.println(text);
			}
			
			printWriter.close();
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Saving was unsuccessfull!");
		}
	}

	@Override
	public void open() {
		
		try
		{
			File file = new File(filePath);
			
			FileReader fileReader = new FileReader(file);

			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String textLine;
			
			while ((textLine = bufferedReader.readLine()) != null)
			{
				panelLoggerList.add(textLine);
			}
			
			bufferedReader.close();			
			fileReader.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Opening of a file was unsuccessfull!");
		}
		
	}

}
