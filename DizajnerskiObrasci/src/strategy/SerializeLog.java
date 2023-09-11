package strategy;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;

import Drawing.DlgLog;
import adapter.HexAdapter;
import command.AddCircleCmd;
import command.AddDonutCmd;
import command.AddHexagonCmd;
import command.AddLineCmd;
import command.AddPointCmd;
import command.AddRectangleCmd;
import command.BringToBackCommand;
import command.BringToFrontCommand;
import command.ToBackCommand;
import command.ToFrontCommand;
import command.UnselectCommand;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import geometry1.Circle;
import geometry1.Donut;
import geometry1.Line;
import geometry1.Point;
import geometry1.Rectangle;
import geometry1.Shape;
import hexagon.Hexagon;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class SerializeLog implements OptionChooser{
	// čuvanje i čitanje logova akcija iz aplikacije 

	private BufferedReader reader;
	private BufferedWriter writer;
	
	private DrawingFrame frame;
	private DrawingModel model;
	private DrawingController controller;
	
	private DlgLog dlgLog;
	
	private Point latestPoint;
	private Line latestLine;
	private Circle latestCircle;
	
	public SerializeLog(DrawingFrame frame, DrawingModel model, DrawingController controller) {
		this.frame = frame;
		this.model = model;
		this.controller = controller; //omogucava klasi pristup tren stanju 
	}

	@Override
	public void saveFile(File file) {
		try {
			writer = new BufferedWriter(new FileWriter(file + ".log")); //pisanje u datoteku
			DefaultListModel<String> list = frame.getDlmList(); //prolazi kroz svaku liniju
			for (int i = 0; i < frame.getDlmList().size(); i++) {
				writer.write(list.getElementAt(i));
				writer.newLine();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}//Svaka linija se zapisuje u datoteku, nakon završetka upisa zatvaraju se tokovi
	}

	@Override
	public void openFile(File file) {
		try {
			reader = new BufferedReader(new FileReader(file));
			dlgLog = new DlgLog(); //prikazuje mi log u prozoru
			dlgLog.setFileLog(this);//postavlja mu s e ref na tren instancu klase
			dlgLog.addCommand(reader.readLine()); //dodaje se pocetna komanda loga
			dlgLog.setVisible(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
	
	public void readLogLine(String command) { // obrađuje liniju loga 
		//Na osnovu komande u liniji, razdvaja se linija na komandu i
		//parametre, a zatim se izvršava odgovarajuća akcija.
		try {
			String[] commands = command.split("->");
			switch(commands[0]) {
				case "Undo":
					controller.undo();
					break;
				case "Redo":
					controller.redo();
					break;
				case "Added":
					Shape shape = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
					if(commands[1].split(":")[0].equals("Donut")) {
						controller.executeCommand(new AddDonutCmd(model,(Donut)shape));				
					}
					else if(commands[1].split(":")[0].equals("Circle")) {
						latestCircle = (Circle) shape;
						controller.executeCommand(new AddCircleCmd(model, latestCircle));
					}
					else if(commands[1].split(":")[0].equals("Hexagon")) {
						controller.executeCommand(new AddHexagonCmd(model,(HexAdapter)shape));
					}
					else if(commands[1].split(":")[0].equals("Line")) {
						latestLine = (Line) shape;
						controller.executeCommand(new AddLineCmd(latestLine, model));
					}
					else if(commands[1].split(":")[0].equals("Point")) {
						latestPoint = (Point) shape;
						controller.executeCommand(new AddPointCmd(model, latestPoint));
					}
					else if(commands[1].split(":")[0].equals("Rectangle")) {
						controller.executeCommand(new AddRectangleCmd(model, (Rectangle)shape));
					}
					frame.getDlmList().addElement("Added->" + shape.toString());
					break;
				case "Updated":
					Shape oldShape = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
					int index = model.getIndexOfShape(oldShape);
					if (oldShape instanceof Point) {
						Point newPoint = parsePoint(commands[2].split(":")[1]);
						controller.executeCommand(new UpdatePointCmd((Point) model.getByIndex(index), newPoint));
						frame.getDlmList().addElement("Updated->" + oldShape.toString() + "->" + newPoint.toString());
					}
					else if (oldShape instanceof Line) {
						Line newLine = parseLine(commands[2].split(":")[1]);
						controller.executeCommand(new UpdateLineCmd((Line) model.getByIndex(index), newLine));
						frame.getDlmList().addElement("Updated->" + oldShape.toString() + "->" + newLine.toString());
					}
					else if (oldShape instanceof Rectangle) {
						Rectangle newRectangle = parseRectangle(commands[2].split(":")[1]);
						controller.executeCommand(new UpdateRectangleCmd((Rectangle) model.getByIndex(index), newRectangle));
						frame.getDlmList().addElement("Updated->" + oldShape.toString() + "->" + newRectangle.toString());
					}
					else if (oldShape instanceof Donut) {
						Donut newDonut = parseDonut(commands[2].split(":")[1]);
						controller.executeCommand(new UpdateDonutCmd((Donut) model.getByIndex(index), newDonut));
						frame.getDlmList().addElement("Updated->" + oldShape.toString() + "->" + newDonut.toString());
					}
					else if (oldShape instanceof Circle) {
						Circle newCircle = parseCircle(commands[2].split(":")[1]);
						controller.executeCommand(new UpdateCircleCmd((Circle) model.getByIndex(index), newCircle));
						frame.getDlmList().addElement("Updated->" + oldShape.toString() + "->" + newCircle.toString());
					}
					else if (oldShape instanceof HexAdapter) {
						HexAdapter newHexagon = parseHexagon(commands[2].split(":")[1]);
						controller.executeCommand(new UpdateHexagonCmd((HexAdapter) model.getByIndex(index), newHexagon));
						frame.getDlmList().addElement("Updated->" + oldShape.toString() + "->" + newHexagon.toString());
					}
					break;
				case "Deleted":
					controller.deleteFromLog(); 
					break;
				case "Selected":
					Shape selectedShape = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
					controller.selectShapeFromLog(selectedShape);
					frame.getDlmList().addElement("Selected->" + selectedShape.toString());
					break;
				case "Moved to front":
					Shape shapeMovedToFront = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
					controller.executeCommand(new ToFrontCommand(model,model.getShapes().indexOf(shapeMovedToFront), shapeMovedToFront));
					frame.getDlmList().addElement("Moved to front->" + shapeMovedToFront.toString());
					break;
				case "Moved to back": 
					Shape shapeMovedToBack = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
					controller.executeCommand(new ToBackCommand(model, model.getShapes().indexOf(shapeMovedToBack),shapeMovedToBack));
					frame.getDlmList().addElement("Moved to back->" + shapeMovedToBack.toString());
					break;
				case "Brought to front":
					Shape shapeBroughtToFront = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
					controller.executeCommand(new BringToFrontCommand(model, shapeBroughtToFront));
					frame.getDlmList().addElement("Brought to front->" + shapeBroughtToFront.toString());
					break;
				case "Brought to back":
					Shape shapeBroughtToBack = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
					controller.executeCommand(new BringToBackCommand(model,model.getShapes().indexOf(shapeBroughtToBack), shapeBroughtToBack));
					frame.getDlmList().addElement("Brought to back->" + shapeBroughtToBack.toString());
					break;
				case "Unselected":
					Shape unselectedShape = parseShape(commands[1].split(":")[0], commands[1].split(":")[1]);
					controller.executeCommand(new UnselectCommand(model, unselectedShape));
					frame.getDlmList().addElement("Unselected->" + unselectedShape.toString());
					break;
			}
			String line = reader.readLine();
			if (line != null) dlgLog.addCommand(line);
			else {
				dlgLog.closeDialog();
				return;
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	//služe za analizu i konverziju tekstualnih opisa oblika iz linije loga u odgovarajuće objekte
	//npr point mi razdvaja tekstualni opis x, y i boju
	private Shape parseShape(String shape, String shapeParameters) throws Exception {
		if (shape.equals("Point")) return parsePoint(shapeParameters);
		else if (shape.equals("Hexagon")) return parseHexagon(shapeParameters);
		else if (shape.equals("Line")) return parseLine(shapeParameters);
		else if (shape.equals("Circle")) return parseCircle(shapeParameters);
		else if (shape.equals("Rectangle")) return parseRectangle(shapeParameters);
		else if (shape.equals("Donut")) return parseDonut(shapeParameters);
		else return parseDonut(shapeParameters);
	}
	
	private Point parsePoint(String string) {
		String [] pointParts = string.split(";"); 		
		String s = pointParts[2].split("=")[1].substring(1, pointParts[2].split("=")[1].length() - 1);
		String [] colors = s.split(",");
		return new Point(Integer.parseInt(pointParts[0].split("=")[1]), Integer.parseInt(pointParts[1].split("=")[1]), new Color(Integer.parseInt(colors[0].split("-")[1]), Integer.parseInt(colors[1].split("-")[1]), Integer.parseInt(colors[2].split("-")[1])));
	}
	
	private Line parseLine(String string) {
		String [] lineParts = string.split(";"); 	
		int xStart = Integer.parseInt(lineParts[0].split("=")[1]);
		int yStart = Integer.parseInt(lineParts[1].split("=")[1]);
		int xEnd = Integer.parseInt(lineParts[2].split("=")[1]);
		int yEnd = Integer.parseInt(lineParts[3].split("=")[1]);
		String s = lineParts[4].split("=")[1].substring(1, lineParts[4].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		Point startPoint = new Point(xStart, yStart);
		Point endPoint = new Point(xEnd, yEnd);
		Color lineColor = new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1]));
		return new Line(startPoint, endPoint, lineColor);
	}

	private Circle parseCircle(String string) throws Exception{
		String [] circleParts = string.split(";"); 	
		int radius = Integer.parseInt(circleParts[0].split("=")[1]);
		int x = Integer.parseInt(circleParts[1].split("=")[1]);
		int y = Integer.parseInt(circleParts[2].split("=")[1]);
		String s = circleParts[3].split("=")[1].substring(1, circleParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = circleParts[4].split("=")[1].substring(1, circleParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		return new Circle(new Point(x, y), radius, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}
	
	private HexAdapter parseHexagon(String string) {
		String [] hexagonParts = string.split(";"); 	
		int radius = Integer.parseInt(hexagonParts[0].split("=")[1]);
		int x = Integer.parseInt(hexagonParts[1].split("=")[1]);
		int y = Integer.parseInt(hexagonParts[2].split("=")[1]);
		String s = hexagonParts[3].split("=")[1].substring(1, hexagonParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = hexagonParts[4].split("=")[1].substring(1, hexagonParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		Hexagon h = new Hexagon(x, y, radius);
		h.setBorderColor(new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])));
		h.setAreaColor(new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
		return new HexAdapter(h);
	}

	private Donut parseDonut(String string) throws NumberFormatException {
		String [] donutParts = string.split(";"); 	
		int radius = Integer.parseInt(donutParts[0].split("=")[1]);
		int x = Integer.parseInt(donutParts[1].split("=")[1]);
		int y = Integer.parseInt(donutParts[2].split("=")[1]);
		String s = donutParts[3].split("=")[1].substring(1, donutParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = donutParts[4].split("=")[1].substring(1, donutParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		int innerRadius = Integer.parseInt(donutParts[5].split("=")[1]);
		return new Donut(new Point(x, y), radius, innerRadius, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}

	private Rectangle parseRectangle(String string) {
		String [] rectangleParts = string.split(";"); 	
		int x = Integer.parseInt(rectangleParts[0].split("=")[1]);
		int y = Integer.parseInt(rectangleParts[1].split("=")[1]);
		int height = Integer.parseInt(rectangleParts[2].split("=")[1]);
		int width = Integer.parseInt(rectangleParts[3].split("=")[1]);
		String s = rectangleParts[4].split("=")[1].substring(1, rectangleParts[4].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = rectangleParts[5].split("=")[1].substring(1, rectangleParts[5].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		return new Rectangle(new Point(x, y), width, height, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}
}
