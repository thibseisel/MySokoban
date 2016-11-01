package fr.nihilus.sokoban.cli;

public class RepeatCommand implements ICommand {

	private ICommand lastCmd;
	
	public RepeatCommand(GameCLI game) {
		lastCmd = game.getCommandHistory().peek();
	}
	
	@Override
	public boolean execute() {
		if (lastCmd == null) return false;
		return lastCmd.execute();
	}

	@Override
	public void undo() throws UnsupportedOperationException {
		lastCmd.undo();
	}

	@Override
	public String toString() {
		return lastCmd.toString();
	}
}
