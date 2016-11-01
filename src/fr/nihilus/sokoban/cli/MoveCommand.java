package fr.nihilus.sokoban.cli;

import fr.nihilus.sokoban.IGame;
import fr.nihilus.sokoban.model.Direction;
import fr.nihilus.sokoban.model.IBlock;
import fr.nihilus.sokoban.model.Labyrinth;
import fr.nihilus.sokoban.model.MoveableBlock;

public class MoveCommand implements ICommand {

	private IGame game;
	private Direction direction;
	private boolean blockPushed;
	
	public MoveCommand(IGame game, String[] params) {
		this.game = game;
		if(params != null && params.length > 0) {
			this.direction = Direction.parse(params[0]);		
		}
	}
	
	@Override
	public boolean execute() {
		if(direction == null) {
			game.printMessage("Usage : move [left | up | right | down]");
			return false;
		}
		Labyrinth laby = game.getLabyrinth();
		
		// Détermine si le joueur va pousser un bloc à son prochain mouvement
		int[] playerPosition = laby.getPlayerPosition();
		IBlock block = laby.getBlock(direction.translatedPosition(playerPosition, 1));
		blockPushed = block instanceof MoveableBlock;
		
		if(!laby.movePlayer(direction)) {
			game.printMessage("Impossible de bouger dans cette direction !");
			return false;
		}
		
		game.refreshDisplay();
		if(laby.isVictorious()) {
			game.stop(true);
		}
		return true;
	}

	@Override
	public void undo() throws UnsupportedOperationException {
		Labyrinth laby = game.getLabyrinth();
		int[] oldPlayerPos = laby.getPlayerPosition();
		Direction backward = Direction.opposite(direction);
		laby.movePlayer(backward);
		if(blockPushed) {
			// Déplace le rocher à la position du joueur
			int[] from = direction.translatedPosition(oldPlayerPos, 1);
			laby.moveOnto(from, oldPlayerPos);	
		}
		game.refreshDisplay();
	}
	
	@Override
	public String toString() {
		return "move " + direction.toString().toLowerCase();
	}

}
