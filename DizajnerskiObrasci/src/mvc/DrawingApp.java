package mvc;

public class DrawingApp {

	public static void main(String[] args) {
		
		DrawingFrame frame = new DrawingFrame();
		DrawingModel model = new DrawingModel();
		
		frame.getView().setModel(model);

	}

}
