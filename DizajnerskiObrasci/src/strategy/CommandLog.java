package strategy;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import command.Command;

public class CommandLog {

	    private List<Command> commandLog;

	    public CommandLog() {
	        commandLog = new ArrayList<>();
	    }

	    public void executeCommand(Command command) {
	        command.redo();
	        commandLog.add(command);
	    }

	    public List<Command> getCommandLog() {
	        return commandLog;
	    }
	    
	    public void saveLogToFile(String filePath) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	            for (Command command : commandLog) {
	                writer.write(command.toString() + "\n");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	/*    com.myapp
	    |-- model
	    |-- view
	    |-- controller
	    |-- storage
	    |   |-- CommandLog.java
	    |   |-- DrawingStorageStrategy.java
	    |   |-- JsonStorageStrategy.java
	    |   |-- XmlStorageStrategy.java
	    |   |-- ... (ostale klase vezane za Ä?uvanje podataka)
	    |-- Main.java
*/

}
