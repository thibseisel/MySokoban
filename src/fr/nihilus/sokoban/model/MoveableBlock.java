package fr.nihilus.sokoban.model;

/**
 * Interface repr�sentant un bloc mobile.
 * Un bloc mobile a la particularit� de pouvoir recouvrir d'autres blocs.
 * @author Thib
 */
public interface MoveableBlock extends IBlock {
	
	/**
	 * @return la case sur laquelle le bloc mobile se trouve.
	 */
	IBlock below();
	
	/**
	 * Place ce bloc sur un autre.
	 * @param block bloc sur lequel se placer.
	 */
	void moveOnto(IBlock block);
}
