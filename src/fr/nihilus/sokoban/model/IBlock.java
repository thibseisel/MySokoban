package fr.nihilus.sokoban.model;

public interface IBlock {
	/**
	 * D�place cet �l�ment dans la direction donn�e.
	 * @param d direction dans laquelle d�placer cet �l�ment.
	 * @return true si cet �l�ment a �t� d�plac�, false s'il n'a pas pu �tre d�plac�.
	 */
	boolean move(Labyrinth laby, Direction d);
	/**
	 * Indique si ce �l�ment peut �tre travers�.
	 * @return true si cet �l�ment peut �tre travers�.
	 */
	boolean canGoThrough();
}
