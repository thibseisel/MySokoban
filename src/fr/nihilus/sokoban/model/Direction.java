package fr.nihilus.sokoban.model;

import java.util.Locale;

public enum Direction {
	LEFT, TOP, RIGHT, BOTTOM;

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
		case TOP:
			return new int[] { coord[0], coord[1] - steps };
		case RIGHT:
			return new int[] { coord[0] + steps, coord[1] };
		case BOTTOM:
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
		case TOP:
			return BOTTOM;
		case RIGHT:
			return LEFT;
		case BOTTOM:
			return TOP;
		default:
			return null;
		}
	}
}
