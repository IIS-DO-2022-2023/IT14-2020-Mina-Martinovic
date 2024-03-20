package strategy;

public class FileManager implements IFileStrategy{

	private IFileStrategy fileStrategy;
	
	public FileManager(IFileStrategy fileStrategy)
	{
		this.fileStrategy = fileStrategy;
	}
	
	@Override
	public void save() {
		
		fileStrategy.save();
	}

	@Override
	public void open() {
		
		fileStrategy.open();
	}

}
