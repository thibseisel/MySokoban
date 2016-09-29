package fr.nihilus.sokoban.model;

/**
 * Implémentation d'un mur du labyrinthe.
 * Comme on pourrait s'y attendre de la part d'un mur, il ne bouge pas et ne peut pas être traversé.
 * @author Thib
 */
public class WallBlock implements IBlock {

	@Override
	public boolean move(Labyrinth laby, Direction d) {
		return false;
	}

	@Override
	public boolean canGoThrough() {
		return false;
	}
	
	@Override
	public String toString() {
		return "#";
	}
}
