package strategy;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import mvc.DrawingFrame;

public class SerializeDrawing implements OptionChooser {
	//omogućava korisniku da sačuva trenutni crtež kao sliku na računar u JPEG formatu.
	
	private DrawingFrame frame;

	public SerializeDrawing(DrawingFrame frame) {
		this.frame = frame;
	}

	@Override
	public void saveFile(File file) {
		BufferedImage imageBuffer = null; //cuva mi sliku a postavlj ase inicijalno na null jer mi prvo ne sadrzi nijednu sliku
		try {
			imageBuffer = new Robot().createScreenCapture(frame.getView().getBounds());
			//prvo se koristi Robot klasa da bi se kreirala slika trenutnog sadržaja frame.getView()
			frame.getView().paint(imageBuffer.createGraphics()); //crtanje slike crteza i tako cuvam tren stanje crteza
			ImageIO.write(imageBuffer,"jpeg", new File(file + ".jpeg")); // zapisivanje slike u JPEG formatu u odabranu datoteku
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void openFile(File file) {
		// TODO Auto-generated method stub

	}
}
