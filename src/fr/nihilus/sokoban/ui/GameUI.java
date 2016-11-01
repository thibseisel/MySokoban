package fr.nihilus.sokoban.ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.nihilus.sokoban.IGame;
import fr.nihilus.sokoban.model.Labyrinth;

public class GameUI implements IGame {

	public static void main(String[] args) {
		new GameUI().start();
	}
	
	public GameUI() {
		
	}

	@Override
	public void start() {
		SwingUtilities.invokeLater(uiThread);
	}

	@Override
	public void stop(boolean hasWon) {
		// TODO Auto-generated method stub

	}

	@Override
	public Labyrinth getLabyrinth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printMessage(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refreshDisplay() {
		// TODO Auto-generated method stub

	}

	private final Runnable uiThread = new Runnable() {

		private SokobanWindow window;

		@Override
		public void run() {
			window = new SokobanWindow();
			window.setVisible(true);
		}
	};

}
