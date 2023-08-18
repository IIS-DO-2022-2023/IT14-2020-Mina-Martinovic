package log;
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
}
