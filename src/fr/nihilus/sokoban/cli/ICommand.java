package fr.nihilus.sokoban.cli;

import java.util.Locale;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Abstraction d'une commande qui permet de piloter le jeu. Pour permettre leur
 * annulation, les commandes peuvent être placées dans une {@link Queue}.
 * 
 * @author Thib
 */
public interface ICommand {
	/**
	 * Exécute la commande.
	 * 
	 * @return true si la commande peut être annulée, false sinon. Si une
	 *         commande échoue, elle doit retourner false.
	 */
	boolean execute();

	/**
	 * Annule l'exécution de la commande. Cela consiste à effectuer le même
	 * travail que {@link #execute()}, mais dans l'autre sens.
	 * 
	 * @throws UnsupportedOperationException si la commande ne peut pas être
	 *             annulée.
	 */
	void undo() throws UnsupportedOperationException;

	/**
	 * Classe de Factory permettant de construire des commandes.
	 * 
	 * @author Thib
	 */
	static class Factory {

		private GameCLI game;
		private String name;
		private String[] params;

		public Factory(GameCLI game) {
			this.game = game;
		}

		public Factory parseLine(String line) {
			if (line == null || line.isEmpty()) {
				return this;
			}
			StringTokenizer tokenizer = new StringTokenizer(line);
			name = tokenizer.nextToken().toLowerCase(Locale.US);

			params = new String[tokenizer.countTokens()];
			int paramPos = 0;
			while (tokenizer.hasMoreTokens()) {
				params[paramPos] = tokenizer.nextToken();
				paramPos++;
			}
			return this;
		}

		public ICommand create() {
			if (name == null)
				return new RepeatCommand(game);
			switch (name) {
			case "help":
				return new HelpCommand(game);
			case "move":
				return new MoveCommand(game, params);
			case "undo":
				return new UndoCommand(game);
			case "quit":
				return new QuitCommand(game);
			default:
				game.printMessage("Commande inconnue '" + name + "'");
				game.printMessage("Saisir 'help' pour voir la liste des commandes.");
				return null;
			}
		}
	}
}
