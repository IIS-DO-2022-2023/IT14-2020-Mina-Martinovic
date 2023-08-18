package strategy;

import java.util.List;

import command.Command;

public class LogView {
    public void displayLog(List<Command> commandLog) {
        for (Command command : commandLog) {
            System.out.println("Executed command: " + command.toString());
        }
    }
}

