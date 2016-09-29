package fr.nihilus.sokoban.cli;

public class UndoCommand implements ICommand {

	private GameCLI game;
	
	public UndoCommand(GameCLI game) {
		this.game = game;
	}
	
	@Override
	public boolean execute() {
		ICommand toUndo = game.getCommandHistory().poll();
		if(toUndo != null) {
			toUndo.undo();
			return false;
		}
		game.printMessage("Aucune commande à annuler.");
		return false;
	}

	@Override
	public void undo() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Command 'undo' cannot be undone (well, that's tricky).");
	}
}
