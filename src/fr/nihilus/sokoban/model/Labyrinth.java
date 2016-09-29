package fr.nihilus.sokoban.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Labyrinth {

	protected final int width;
	protected final int height;
	protected final IBlock[][] matrix;
	protected int[] playerPos;
	protected BoxBlock[] boxes;

	protected Labyrinth(int w, int h) {
		width = w;
		height = h;
		matrix = new IBlock[w][h];
		playerPos = new int[2];

		// Remplit la matrices de cases vides
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				matrix[i][j] = new EmptyBlock();
			}
		}
	}
	
	/**
	 * Indique si les conditions de victoire sont réunies.
	 * @return true si la partie est gagnée, false sinon.
	 */
	public boolean isVictorious() {
		boolean victorious = true;
		for (BoxBlock box : boxes) {
			victorious &= box.isOnTarget();
		}
		return victorious;
	}

	/**
	 * Obtient les coordonnées de la case adjacente dans la direction donnée.
	 */
	protected int[] getRelativePosition(int[] coord, Direction dir) {
		return getRelativePosition(coord, dir, 1);
	}

	/**
	 * Obtient les coordonnées d'une case relativement à la direction donnée.
	 * @param steps nombre de pas
	 * @return
	 */
	protected int[] getRelativePosition(int[] coord, Direction dir, int steps) {
		int[] adj = dir.translatedPosition(coord, steps);
		assertCorrectCoordinates(adj[0], adj[1]);
		return adj;
	}
	
	public int[] getPlayerPosition() {
		return playerPos;
	}
	
	public void moveOnto(int[] from, int[] to) {
		MoveableBlock toMove = (MoveableBlock) getBlock(from);
		IBlock below = toMove.below();
		IBlock dest = getBlock(to);
		toMove.moveOnto(dest);
		
		matrix[to[0]][to[1]] = toMove;
		matrix[from[0]][from[1]] = below;
	}
	
	public boolean movePlayer(Direction dir) {
		Player player = (Player) matrix[playerPos[0]][playerPos[1]];
		return player.move(this, dir);
	}

	public IBlock getBlock(int x, int y) {
		assertCorrectCoordinates(x, y);
		return matrix[x][y];
	}

	public IBlock getBlock(int[] xy) {
		if (xy.length != 2) {
			throw new IllegalArgumentException("Coordinates must have x and y values.");
		}
		assertCorrectCoordinates(xy[0], xy[1]);
		return matrix[xy[0]][xy[1]];
	}

	protected void assertCorrectCoordinates(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			throw new IllegalStateException(String.format("Illegal coordinates : (%d;%d)", x, y));
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < width; j++) {
			for (int i = 0; i < height; i++) {
				sb.append(matrix[i][j].toString());
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	/**
	 * Classe permettant de simplifier la création d'un nouveau labyrinthe.
	 * 
	 * @author Thib
	 */
	public static class Builder {

		private final int width;
		private final int height;
		private final Map<int[], IBlock> blocks;
		private int[] playerInitialPos;
		private List<int[]> rockInitialPositions;

		/**
		 * Crée un nouveau constructeur de Labyrinthe.
		 * 
		 * @param w
		 *            largeur du labyrinthe, strictement positive
		 * @param h
		 *            hauteur du labyrinthe, strictement positive
		 * @throws IllegalArgumentException
		 *             si les dimensions sont négatives ou nulles
		 */
		public Builder(int w, int h) {
			if (w < 1 || h < 1) {
				throw new IllegalArgumentException("Cannot create Labyrinth with dimensions [" + w + ";" + h + "]");
			}
			width = w;
			height = h;
			blocks = new HashMap<int[], IBlock>();
			rockInitialPositions = new LinkedList<>();
		}

		/**
		 * Définit la nature d'une case du labyrinthe. Cette méthode doit être
		 * appelée répétitivement jusqu'à obtenir le labyrinthe désiré.
		 * Attention: comme le labyrinthe est fermé, ses contours sont forcément
		 * des murs, il est donc inutile de définir manuellement le contour du
		 * labyrinthe.
		 * 
		 * @throws IllegalArgumentException
		 *             si la case est en dehors du labyrinthe ou fait partie de
		 *             la bordure.
		 */
		public Labyrinth.Builder setSquare(int posX, int posY, IBlock sqr) {
			if (posX < 0 || posX > width) {
				throw new IllegalArgumentException("Cannot put block at index X=" + posX);
			}
			if (posY < 0 || posY > height) {
				throw new IllegalArgumentException("Cannot put block at index Y=" + posY);
			}
			blocks.put(new int[] { posX, posY }, sqr);
			return this;
		}

		/**
		 * Définit la position initiale du joueur dans le labyrinthe.
		 * 
		 * @throws IllegalArgumentException
		 *             si la position du joueur est en dehors du labyrinthe ou
		 *             fait partie de la bordure.
		 */
		public Labyrinth.Builder setPlayerInitialPosition(int posX, int posY) {
			if (posX < 1 || posX > (width - 1)) {
				throw new IllegalArgumentException("Cannot put player at index X=" + posX);
			}
			if (posY < 1 || posY > (height - 1)) {
				throw new IllegalArgumentException("Cannot put player at index Y=" + posY);
			}
			playerInitialPos = new int[] { posX, posY };
			return this;
		}

		public Labyrinth.Builder addBoxInitialPosition(int posX, int posY) {
			if (posX < 1 || posX > (width - 1)) {
				throw new IllegalArgumentException("Cannot put box at index X=" + posX);
			}
			if (posY < 1 || posY > (height - 1)) {
				throw new IllegalArgumentException("Cannot put box at index Y=" + posY);
			}
			rockInitialPositions.add(new int[] { posX, posY });
			return this;
		}

		/**
		 * Crée une nouvelle instance du Labyrinthe.
		 * 
		 * @return labyrinthe avec l'ensemble des cases définies.
		 * @throws IllegalStateException
		 *             si la position initiale du joueur n'a pas été définie.
		 */
		public Labyrinth create() {
			Labyrinth laby = new Labyrinth(width, height);

			// Place les cases définies manuellement
			Set<int[]> keys = blocks.keySet();
			for (int[] coord : keys) {
				laby.matrix[coord[0]][coord[1]] = blocks.get(coord);
			}

			// Place les bordures
			for (int i = 0; i < width; i++) {
				laby.matrix[i][0] = new WallBlock();
				laby.matrix[i][height - 1] = new WallBlock();
			}
			for (int j = 0; j < height; j++) {
				laby.matrix[0][j] = new WallBlock();
				laby.matrix[width - 1][j] = new WallBlock();
			}

			if (playerInitialPos == null) {
				throw new IllegalStateException("You must set the Player initial position !");
			}
			laby.playerPos = playerInitialPos;
			int playerX = playerInitialPos[0];
			int playerY = playerInitialPos[1];
			Player player = new Player(laby.matrix[playerX][playerY]);
			laby.matrix[playerX][playerY] = player;
			
			if(rockInitialPositions.size() == 0) {
				throw new IllegalStateException("There's no rock in the labyrinth.");
			}
			
			laby.boxes = new BoxBlock[rockInitialPositions.size()];
			int rockCount = 0;
			for (int[] pos : rockInitialPositions) {
				IBlock underRock = laby.matrix[pos[0]][pos[1]];
				BoxBlock rock = new BoxBlock(underRock);
				laby.matrix[pos[0]][pos[1]] = rock;
				laby.boxes[rockCount++] = rock;
			}

			return laby;
		}
	}
}
