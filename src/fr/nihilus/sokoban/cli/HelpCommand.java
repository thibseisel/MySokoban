package fr.nihilus.sokoban.cli;

import fr.nihilus.sokoban.IGame;

public class HelpCommand implements ICommand {

	private IGame game;
	private static final String[] commandList = new String[] { "move", "undo", "help", "quit" };
	private static final String[] symbols = new String[] { "& joueur", "o caisse", "# mur", "x cible",
			"¤ rocher placé" };

	public HelpCommand(IGame game) {
		this.game = game;
	}

	@Override
	public boolean execute() {
		StringBuilder sb = new StringBuilder();
		sb.append("But du jeu : placer les caisses sur les cibles.");
		sb.append("\nSymboles :");
		for (String symb : symbols) {
			sb.append('\n').append('\t').append(symb);
		}
		sb.append("\nListe des commandes :");
		for (String cmd : commandList) {
			sb.append('\n').append('\t').append(cmd);
		}
		game.printMessage(sb.toString());
		return false;
	}

	@Override
	public void undo() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Command 'help' cannot be undone.");
	}

}
