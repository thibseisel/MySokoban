package fr.nihilus.sokoban.model;

public interface IBlock {
	/**
	 * Déplace cet élément dans la direction donnée.
	 * @param d direction dans laquelle déplacer cet élément.
	 * @return true si cet élément a été déplacé, false s'il n'a pas pu être déplacé.
	 */
	boolean move(Labyrinth laby, Direction d);
	/**
	 * Indique si ce élément peut être traversé.
	 * @return true si cet élément peut être traversé.
	 */
	boolean canGoThrough();
}
