package fr.nihilus.sokoban.model;

public class TargetBlock implements IBlock {

	@Override
	public boolean move(Labyrinth laby, Direction d) {
		return false;
	}

	@Override
	public boolean canGoThrough() {
		return true;
	}

	@Override
	public String toString() {
		return "x";
	}
}
