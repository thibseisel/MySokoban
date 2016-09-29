package fr.nihilus.sokoban;

import fr.nihilus.sokoban.model.Labyrinth;

/**
 * Repr�sentation d'une partie de Sokoban.
 * @author Thib
 */
public interface IGame {

	/**
	 * D�marre la partie.
	 * Elle continuera jusqu'� un appel � la m�thode {@link #stop(boolean)}.
	 */
	void start();

	/**
	 * Met fin � la partie, que ce soit parce que le joueur quitte le jeu ou parce qu'il a gagn�.
	 * @param hasWon true si la partie s'arr�te par la victoire du joueur
	 */
	void stop(boolean hasWon);

	/**
	 * @return le labyrinthe associ� � cette partie.
	 */
	Labyrinth getLabyrinth();

	/**
	 * Affiche un message � l'�cran.
	 * @param msg corps de texte du message � afficher
	 */
	void printMessage(String msg);

	/**
	 * Met � jour l'�cran du jeu.
	 */
	void refreshDisplay();

}