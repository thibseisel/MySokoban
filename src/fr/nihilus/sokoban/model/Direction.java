package fr.nihilus.sokoban.model;

import java.util.Locale;

public enum Direction {
	LEFT, UP, RIGHT, DOWN;

	/**
	 * Traduit la chaine de caract�re en tant que Direction.
	 * 
	 * @return Direction correspondant � la chaine, ou null si aucune
	 *         correspondance ne peut �tre faite.
	 */
	public static Direction parse(String direction) {
		try {
			return Direction.valueOf(Direction.class, direction.trim().toUpperCase(Locale.US));
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	/**
	 * Effectue une translation des coordonn�es dans cette direction.
	 * @param coord 
	 * @param steps nombre de pas � faire dans cette direction
	 * @return coordonn�es de la position translat�e.
	 */
	public int[] translatedPosition(int[] coord, int steps) {
		if(coord.length != 2 || steps < 0) {
			throw new IllegalArgumentException();
		}
		switch (this) {
		case LEFT:
			return new int[] { coord[0] - steps, coord[1] };
		case UP:
			return new int[] { coord[0], coord[1] - steps };
		case RIGHT:
			return new int[] { coord[0] + steps, coord[1] };
		case DOWN:
			return new int[] { coord[0], coord[1] + steps };
		default:
			return null;
		}
	}

	/**
	 * Donne la direction oppos�e.
	 */
	public static Direction opposite(Direction direction) {
		switch (direction) {
		case LEFT:
			return RIGHT;
		case UP:
			return DOWN;
		case RIGHT:
			return LEFT;
		case DOWN:
			return UP;
		default:
			return null;
		}
	}
}
