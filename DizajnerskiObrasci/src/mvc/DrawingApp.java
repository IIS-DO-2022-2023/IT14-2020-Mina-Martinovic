package mvc;

public class DrawingApp {

	public static void main(String[] args) {
		
		DrawingFrame frame = new DrawingFrame();
		DrawingModel model = new DrawingModel();
		
		frame.getView().setModel(model);
		
		frame.setVisible(true);
		
		DrawingController controller = new DrawingController(frame, model);
		
		frame.setController(controller);
		
		controller.addPropertyChangeListener(frame);

	}
}
