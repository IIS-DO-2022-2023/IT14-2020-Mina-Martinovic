package command;

import java.util.Stack;

public class UndoRedo<Shape> implements Command{
	
	//private Shape s = //>> poslednji dodat shape
	protected Stack<Command> undoStack = new Stack<Command>();
	protected Stack<Command> redoStack = new Stack<Command>();
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}

	public void InsertInUndoRedo() {
		
	}
	
	public void undo (int levels) {
		/*
		if(undoStack.isEmpty())
		{
			return;
		}
		else {
			undoStack.pop();
			redoStack.push(s);
			<Shape>.unexecute();
		}
		*/
		 for (int i = 1; i <= levels; i++)
         {
             if (!undoStack.isEmpty())
             {
                 Command command = undoStack.pop();
                 command.unexecute();
                 redoStack.push(command);
             }

         }
	}

	public void redo(int levels) {
		/*
		if(redoStack.isEmpty())
		{
			return;
		}
		else {
			redoStack.pop();
			undoStack.push(s);
			<Shape>.execute();
		}
		*/
		for (int i = 1; i <= levels; i++)
        {
            if (!redoStack.isEmpty())
            {
                Command command = redoStack.pop();
                command.execute();
                undoStack.push(command);
            }

        }
	}
}
