package ou41;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	private Vector pos;
	private Vector vel;
	private double r;
	private Color c;
	private Box box;
	private int type;
	
	public Ball(Box box, Vector pos, Vector vel, double r, int type){
		this.pos = pos;
		this.vel = vel;
		this.r = r;
		this.type = type;
		if (type == 1) {
			this.c = Color.LIGHT_GRAY;
		}else if(type == 2) {
			this.c = Color.red;
		}else {
			this.c = Color.GREEN;
		}
		this.box = box;
	}
	
	public void move(){
		 pos = this.pos.add(this.vel);
		 vel = new Vector(this.vel.getX(), this.vel.getY());
		 
		 //floor
		 if (this.pos.getY() > (box.getHeight() - this.r -1) && this.vel.getY() > 0){
			 vel = this.vel.flipSignY();
		 }
		 
		 // roof
		 if (this.pos.getY() < this.r+1 && this.vel.getY() < 0){
			 vel = this.vel.flipSignY();
		 }
		 // right wall
		 if (this.pos.getX() > (box.getWidth() -this.r -1) && this.vel.getX() > 0){
			 vel = this.vel.flipSignX();
		 }
		 // left wall
		 if (this.pos.getX() < this.r+1 && this.vel.getX() < 0){
			 vel = this.vel.flipSignX();
		 }
		 
	}
	public void collide(Ball ball) {
		Ball small = null;
		Ball large = null;
		
		if (this.type == ball.type) {
			if(this.r < ball.r) {
				small = this;
				large = ball;
			} else {
				small = ball;
				large = this;
			}
			large.r += small.r;
			
			Vector newVel = small.vel.sub(large.vel);
			Vector newPos = small.pos.sub(large.pos);
			double scaleVel = (newVel.dot(newPos))/(newPos.dot(newPos));
			//double ratio = small.r/large.r; // might use this in the new large vel
			large.vel = large.vel.add(newPos.scale(scaleVel*0.5));
			
			small.r = 0;
		} else {
			Vector newVel = ball.vel.sub(this.vel);
			Vector newPos = ball.pos.sub(this.pos);
			double scaleVel = (newVel.dot(newPos))/(newPos.dot(newPos));
			//double ratio = ball.r/this.r;
			
			Vector newVelBall = this.vel.sub(ball.vel);
			Vector newPosBall = newPos; //this.pos.sub(this.pos);
			double scaleVelBall = (newVelBall.dot(newPosBall))/(newPosBall.dot(newPosBall));
			//double ratioBall = this.r/ball.r;
			
			this.vel = this.vel.add(newPos.scale(scaleVel));
			ball.vel = ball.vel.add(newPos.scale(scaleVelBall));
		}
		
	}
	
	public boolean isCollision(Ball b) {
		return this.pos.distance(b.pos) <= this.r + b.r;
	}
	
	public boolean isRadiusZero() {
		return this.r == 0.0;
	}
	
	public double getRadius(){
		return this.r;
	}
	
	public Vector getPos(){
		return this.vel;
	}
	
	public Vector getVel(){
		return this.vel;
	}
	
	public Ball merge(Ball b){
		return new Ball(this.box, this.pos, this.vel.add(b.getVel()), this.r + b.getRadius(), 265);
	}
	
	public void paint(Graphics g){
		g.setColor(c);
		g.fillOval((int)(pos.getX() - r), (int)(pos.getY() - r),(int)(2*r), (int)(2*r));
	}
	

}
