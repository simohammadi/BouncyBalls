package ou41;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;

public class Interface extends JFrame implements ActionListener{



	private JLabel label = new JLabel();
	
	//Buttons
	private JButton startB = new JButton("Start");
	private JButton pauseB = new JButton("Pause");
	private JButton quitB = new JButton("Quit");
	private JButton stopB = new JButton("Stop");
	
	//input fields
	private JTextField window = new JTextField("1000",10);
	private JTextField balls = new JTextField("30" ,10);
	private JTextField radius = new JTextField("20", 10);
	private JTextField delay = new JTextField("20", 10);
	
	//labels
	private JLabel windHeight= new JLabel("Window dim (int)");
	private JLabel numBalls = new JLabel("Number of balls (int)");
	private JLabel ballRadi = new JLabel("Max radius for balls (double)");
	private JLabel delayLabel = new JLabel("Delay (int)");
	private JLabel error = new JLabel("Wrong inputs!");
	//frame
	private Frame frame;

	public Interface() {
		super("Bouncy balls");
		
		startB.addActionListener(this);
		stopB.addActionListener(this);
		pauseB.addActionListener(this);
		quitB.addActionListener(this);

		label.setPreferredSize(new Dimension(200, 200));
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.setForeground(Color.RED);

		JPanel buttons = new JPanel(new GridLayout(2, 2));
		buttons.add(startB);
		buttons.add(stopB);
		buttons.add(pauseB);
		buttons.add(quitB);

		JPanel inputs = new JPanel(new GridLayout(4, 2));
		inputs.add(windHeight);
		inputs.add(window);
		inputs.add(ballRadi);
		inputs.add(radius);
		inputs.add(numBalls);
		inputs.add(balls);
		inputs.add(delayLabel);
		inputs.add(delay);
		
		error.setFont(new Font("San Serif", Font.BOLD, 30));
		error.setForeground(Color.RED);
		error.setVisible(false);

		this.setLayout(new FlowLayout());
		this.add(buttons);
		this.add(inputs);
		this.add(error);
		this.setPreferredSize(new Dimension(400,400));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}


	public void actionPerformed(ActionEvent e) {
		
		Scanner scanWindow = new Scanner(window.getText());
		Scanner scanBalls = new Scanner(balls.getText());
		Scanner scanRadius = new Scanner(radius.getText());
		Scanner scanDelay = new Scanner(delay.getText());
		
		Vector pos = new Vector(500,500);
		Vector vel = new Vector(2,10);
		if (scanBalls.hasNextInt() && scanRadius.hasNextDouble() &&
				scanWindow.hasNextInt() && scanDelay.hasNextInt()){
			
			int delay = scanDelay.nextInt();
			int wind = scanWindow.nextInt();
			double radi = scanRadius.nextDouble();
			int numberOfBalls = scanBalls.nextInt();
			
			
			if (e.getSource() == quitB){
				System.exit(0);
			}
			else if (e.getSource() == startB){
				error.setVisible(false);
				frame = new Frame(wind, wind, delay, pos, vel, numberOfBalls, radi);
			}
			else if (e.getSource() == pauseB && pauseB.getText().equals("Pause")){
				pauseB.setText("Resume");
				frame.getBox().paused(true);
			}
			else if (e.getSource() == pauseB && pauseB.getText().equals("Resume")) {
				pauseB.setText("Pause");
				frame.getBox().paused(false);
			}
			else if (e.getSource() == stopB){
				frame.dispose();
			}
		} else {
			error.setVisible(true);
			throw new InputException("There is some mismatch in inputs");
		}
	}
	
	public static void main(String[] args){
		try {
			Interface gui = new Interface();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

}
