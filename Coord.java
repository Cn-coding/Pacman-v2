
public class Coord {

	private double x;
	private double y;
	
	Coord(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double X() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double Y() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void addX(double x) {
		this.x += x;
	}
	
	public void addY(double y) {
		this.y += y;
	}
	
	public void printCoords() {
		System.out.println("X: " + x + ", Y: " + y);
	}
	
}

