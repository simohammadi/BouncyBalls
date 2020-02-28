package ou41;

public class Vector {
	
	private double x;
	private double y;
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Vector add(Vector v) {
		return new Vector(this.x + v.x, this.y + v.y); 
	}
	
	public double angle() {
		return Math.atan2(y, x);
	}
	
	public double distance(Vector v) {
		return Math.sqrt(Math.pow(this.x - v.x, 2) + Math.pow(this.y - v.y, 2));
	}
	
	public double dot(Vector v) {
		return this.x*v.x + this.y*v.y;
	}
	
	public Vector flipSignX(){
		return new Vector(-this.x, this.y);
	}
	public Vector flipSignY(){
		return new Vector(this.x, -this.y);
	}
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	public double length(){
		return Math.hypot(this.x, this.y);
	}
	public static Vector polar(Vector v){
		return new Vector(v.length()*Math.cos(v.getX()), v.length()*Math.cos(v.getY()));
	}
	public static Vector random(double length){
		return new Vector(Math.random()*length, Math.random()*length);
	}
	public Vector scale(double scale){
		return new Vector(this.getX()*scale, this.getY()*scale);
	}
	public Vector sub(Vector v){
		return new Vector(this.x- v.x, this.y - v.y);
	}
	public String toString(){
		return "<" + this.getX() +  ", " + this.getY() + ">";
	}
}
