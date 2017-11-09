package lab2;

public class Point {
	protected double X;
	protected double Y;
	protected double R;
	protected boolean hit;
	
	public Point(double X, double Y, double R) {
		this.X = X;
		this.Y = Y;
		this.R = R;
	}
	
	public double getX() {
		return this.X;
	}
	
	public double getY() {
		return this.Y;
	}
	
	public double getR() {
		return this.R;
	}
	
	public boolean getHit() {
		return this.hit;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}

}
