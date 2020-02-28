package ou41;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

public class Box extends JPanel {
	private ArrayList<Ball> ballList;
	private int width;
	private int height;
	private boolean paused = false;
	private boolean gameOver = false;
	private JLabel gameOverLabel = new JLabel("GAME OVER!");
	private JLabel pause = new JLabel("PAUSED");

	public Box(int height, int width, Vector pos, Vector vel, int numberOfBalls, double radiusFactor) {
		ballList = new ArrayList<Ball>();
		this.width = width;
		this.height = height;
		while (ballList.size() < numberOfBalls) {
			ballList.add(new Ball(this, new Vector(Math.random() * width, Math.random() * height),
					vel.scale(Math.random() * 2), Math.random() * radiusFactor, (int) Math.round(Math.random() * 2)));
		}
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.BLACK);
		this.setBorder(new LineBorder(Color.white, 2));
		this.setLayout(null);

		pause.setForeground(Color.white);
		pause.setFont(new Font("San Serif", Font.BOLD, 40));
		pause.setHorizontalAlignment(JLabel.CENTER);
		pause.setVisible(false);
		pause.setBounds(10, 10, width, height);

		gameOverLabel.setForeground(Color.white);
		gameOverLabel.setFont(new Font("San Serif", Font.BOLD, 40));
		gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
		gameOverLabel.setVisible(false);
		gameOverLabel.setBounds(10, 10, width, height);

		this.add(pause);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < ballList.size(); i++) {
			ballList.get(i).paint(g);
		}

	}

	public void paused(boolean pause) {
		this.paused = pause;
	}

	public void step() {

		if (!gameOver) {
			if (paused) {
				pause.setVisible(true);
			} else {
				pause.setVisible(false);

				for (int i = 0; i < ballList.size(); i++) {
					ballList.get(i).move();

					for (int j = 0; j < ballList.size(); j++) {
						if (j != i && ballList.get(i).isCollision(ballList.get(j))) {
							ballList.get(i).collide(ballList.get(j));
						}
						if (ballList.get(i).isRadiusZero()) {
							ballList.remove(i);
							if (i != 0) {
								i--;
							}
							if (j >= i && j != 0) {
								j--;
							}
						}
						if (ballList.get(j).isRadiusZero()) {
							ballList.remove(j);
							if (j != 0) {
								j--;
							}
							if (i >= j && i != 0) {
								i--;
							}
						}
					}
				}
				repaint();
			}
		} else {
			gameOverLabel.setVisible(gameOver);
		}
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public void collision() {
		if (ballList.size() > 1) {
			for (int i = 0; i < ballList.size(); i++) {
				for (int j = 0; j < ballList.size(); j++) {
					if (ballList.get(i).getPos().equals(ballList.get(j).getPos())) {
						Ball temp = ballList.get(i).merge(ballList.get(j));
						System.out.println(i);
						System.out.println(j);
						ballList.remove(i);
						ballList.remove(j);
						ballList.add(temp);
					}
				}
			}
		}
	}
}
