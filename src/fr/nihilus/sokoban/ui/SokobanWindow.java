package fr.nihilus.sokoban.ui;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SokobanWindow extends JFrame {

	private static final long serialVersionUID = 6500165252672048656L;
	
	private Image[] images;
	
	private JPanel panel;
	private GridLayout gridLayout;

	public SokobanWindow() {
		super();
		setTitle("My Sokoban");
		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		panel = new JPanel();
		
		setContentPane(panel);
		
		try {
			images = new Image[5];
			images[0] = ImageIO.read(new File("assets/player.png"));
			images[1] = ImageIO.read(new File("assets/rock.png"));
			images[2] = ImageIO.read(new File("assets/target.png"));
			images[3] = ImageIO.read(new File("assets/wall.png"));
			images[4] = ImageIO.read(new File("assets/rockset.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createGridFromLabyrinth(int rows, int cols) {
		gridLayout = new GridLayout(rows, cols);
		panel.setLayout(gridLayout);
	}
}
