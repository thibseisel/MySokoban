package fr.nihilus.sokoban.cli;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

import fr.nihilus.sokoban.IGame;
import fr.nihilus.sokoban.cli.ICommand.Factory;
import fr.nihilus.sokoban.model.Labyrinth;
import fr.nihilus.sokoban.model.Labyrinth.Builder;
import fr.nihilus.sokoban.model.TargetBlock;
import fr.nihilus.sokoban.model.WallBlock;

/**
 * Une partie de Sokoban qui se joue en lignes de commande.
 * 
 * @author Thib
 */
public class GameCLI implements IGame {

	private boolean isRunning;
	private Labyrinth labyrinth;
	private Deque<ICommand> commandHistory;
	private ICommand.Factory cmdFactory;
	private boolean isWon = false;

	public static void main(String[] args) {
		new GameCLI().start();
	}

	public GameCLI() {
		Labyrinth.Builder builder = new Builder(8, 8);
		labyrinth = builder.setSquare(1, 2, new TargetBlock()).setSquare(2, 4, new TargetBlock())
				.setSquare(4, 5, new WallBlock()).setPlayerInitialPosition(3, 2).addBoxInitialPosition(2, 2)
				.addBoxInitialPosition(5, 5).create();
		commandHistory = new LinkedList<>();
		cmdFactory = new Factory(this);
	}

	@Override
	public void start() {
		isRunning = true;
		System.out.println("Welcome to Sokoban !");
		refreshDisplay();
		try (Scanner sc = new Scanner(System.in)) {
			do {
				System.out.print("> ");
				String line = sc.nextLine();
				processCmd(line);
			} while (isRunning);

			if (isWon) {
				System.out.println("Félicications, vous avez gagné !");
				System.out.println("Appuyer sur une touche pour quitter...");
				sc.nextLine();
			}
			System.out.println("Bye !");
		}
	}

	private void processCmd(String line) {
		ICommand cmd = cmdFactory.parseLine(line).create();
		if (cmd != null && cmd.execute()) {
			commandHistory.addFirst(cmd);
		}
	}

	public Deque<ICommand> getCommandHistory() {
		return commandHistory;
	}

	@Override
	public void stop(boolean hasWon) {
		isWon = hasWon;
		isRunning = false;
	}

	@Override
	public Labyrinth getLabyrinth() {
		return labyrinth;
	}

	@Override
	public void printMessage(String msg) {
		System.out.println(msg);
	}

	@Override
	public void refreshDisplay() {
		System.out.println(labyrinth);
	}
}
