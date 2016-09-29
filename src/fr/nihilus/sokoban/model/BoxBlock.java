package fr.nihilus.sokoban.model;

public class BoxBlock implements MoveableBlock {

	private IBlock below;
	
	public BoxBlock(IBlock initialBlock) {
		if(initialBlock == null) {
			throw new NullPointerException("initialBlock cannot be null.");
		}
		below = initialBlock;
	}
	
	@Override
	public boolean move(Labyrinth laby, Direction d) {
		int[] src = laby.getRelativePosition(laby.playerPos, d, 1);
		int[] dest = laby.getRelativePosition(laby.playerPos, d, 2);
		IBlock adjacentBlock = laby.getBlock(dest);
		
		// Un bloc ne peut être déplacé que si le bloc adjacent est traversable
		if(adjacentBlock.canGoThrough()) {
			laby.moveOnto(src, dest);
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
		return below;
	}
	
	@Override
	public void moveOnto(IBlock block) {
		below = block;
	}
	
	public boolean isOnTarget() {
		return (below instanceof TargetBlock);
	}
	
	@Override
	public String toString() {
		if(isOnTarget()) return "¤";
		else return "o";
	}
}
