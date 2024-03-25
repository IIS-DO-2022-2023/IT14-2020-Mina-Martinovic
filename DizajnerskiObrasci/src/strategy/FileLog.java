package strategy;

import java.util.ArrayList;

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
		
	}

	@Override
	public void open() {
		
		
	}

}
