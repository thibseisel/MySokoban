package fr.nihilus.sokoban.model;

public class Player implements MoveableBlock {

	private IBlock underFeet;

	public Player(IBlock initialBlock) {
		if (initialBlock == null) {
			throw new NullPointerException("initialBlock cannot be null.");
		}
		underFeet = initialBlock;
	}

	@Override
	public boolean move(Labyrinth laby, Direction d) {
		int[] dest = laby.getRelativePosition(laby.playerPos, d, 1);
		IBlock adjacentBlock = laby.getBlock(dest);

		// Bloc traversable, ou qu'on a réussi à déplacer
		if (adjacentBlock.canGoThrough() || adjacentBlock.move(laby, d)) {
			laby.moveOnto(laby.playerPos, dest);
			laby.playerPos = dest;
			return true;
		}
		return false;
	}

	@Override
	public boolean canGoThrough() {
		return false;
	}

	@Override
	public IBlock below() {
		return underFeet;
	}

	@Override
	public void moveOnto(IBlock block) {
		underFeet = block;
	}

	@Override
	public String toString() {
		return "&";
	}

}
