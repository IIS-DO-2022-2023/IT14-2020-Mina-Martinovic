package strategy;

import command.Command;

public class ControllerLog {

	    private CommandLog commandLog;
	    private LogView logView;

	    public void Controller(CommandLog commandLog, LogView logView) {
	        this.commandLog = commandLog;
	        this.logView = logView;
	    }

	    public void executeAndLogCommand(Command command) {
	    	commandLog.executeCommand(command);
	        logView.displayLog(commandLog.getCommandLog());
	    }

}
