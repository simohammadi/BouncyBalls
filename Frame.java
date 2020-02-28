package ou41;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFrame;


public class Frame extends JFrame implements ActionListener{
	
	private Box box;
	private Timer timer;
	
	public Frame(int width, int height, int delay, Vector pos, Vector vel, int numberOfBalls, double radius){
		box = new Box(width, height, pos, vel, numberOfBalls, radius);
		timer = new Timer(delay, this);
		add(box);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timer.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		box.step();
	}
	public void stop() {
		System.exit(0);
	}
	
	public Box getBox() {
		return this.box;
	}
	/*
	public static void main(String[] args) {
		Vector pos = new Vector(100,100);
		Vector vel = new Vector(10,5);
		
		Frame frame = new Frame(1000, 600, 30, pos, vel,2);
	}
	*/

}
