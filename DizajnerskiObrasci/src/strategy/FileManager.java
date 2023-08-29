package strategy;

import java.io.File;

public class FileManager implements OptionChooser{

	private OptionChooser optionChooser;

	public FileManager(OptionChooser optionChooser) { //setujem kroz konstruktor sta god prosledim
		this.optionChooser = optionChooser;
	}

	@Override
	public void saveFile(File file) {
		optionChooser.saveFile(file);
	}

	@Override
	public void openFile(File file) {
		optionChooser.openFile(file);
	}
}
