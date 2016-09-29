package fr.nihilus.sokoban.cli;

import fr.nihilus.sokoban.IGame;

public class QuitCommand implements ICommand {

	private IGame game;
	
	public QuitCommand(IGame game) {
		this.game = game;
	}
	
	@Override
	public boolean execute() {
		game.stop(false);
		return false;
	}

	@Override
	public void undo() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Command 'quit' cannot be undone.");
	}
	
	@Override
	public String toString() {
		return "quit";
	}
}
