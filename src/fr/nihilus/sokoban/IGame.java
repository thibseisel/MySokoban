package fr.nihilus.sokoban;

import fr.nihilus.sokoban.model.Labyrinth;

/**
 * Représentation d'une partie de Sokoban.
 * @author Thib
 */
public interface IGame {

	/**
	 * Démarre la partie.
	 * Elle continuera jusqu'à un appel à la méthode {@link #stop(boolean)}.
	 */
	void start();

	/**
	 * Met fin à la partie, que ce soit parce que le joueur quitte le jeu ou parce qu'il a gagné.
	 * @param hasWon true si la partie s'arrête par la victoire du joueur
	 */
	void stop(boolean hasWon);

	/**
	 * @return le labyrinthe associé à cette partie.
	 */
	Labyrinth getLabyrinth();

	/**
	 * Affiche un message à l'écran.
	 * @param msg corps de texte du message à afficher
	 */
	void printMessage(String msg);

	/**
	 * Met à jour l'écran du jeu.
	 */
	void refreshDisplay();

}