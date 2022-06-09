
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
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void addX(double x) {
		this.x += x;
	}
	
	public void addY(double y) {
		this.y += y;
	}
	
	public void add(Coord c) {
		this.x += c.X();
		this.y += c.Y();
	}
	public void add(IntCoord c) {
		this.x += c.X();
		this.y += c.Y();
	}
	
	public void printCoords() {
		System.out.println("X: " + x + ", Y: " + y);
	}
	
	public double pythag(Coord c) {
		double xDiff = c.X() - this.x;
		double yDiff = c.Y() - this.y;
		return round(Math.sqrt((xDiff*xDiff) + (yDiff * yDiff)), 2);
	}
	
	public double pythag(double x, double y) {
		double xDiff = x - this.x;
		double yDiff = y - this.y;
		return round(Math.sqrt((xDiff*xDiff) + (yDiff * yDiff)), 2);
	}
	
	public double pythag(int x, int y) {
		double xDiff = x - this.x;
		double yDiff = y - this.y;
		return round(Math.sqrt((xDiff*xDiff) + (yDiff * yDiff)), 2);
	}
	
	public double pythag(IntCoord c) {
		double xDiff = c.X() - this.x;
		double yDiff = c.Y() - this.y;
		return round(Math.sqrt((xDiff*xDiff) + (yDiff * yDiff)), 2);
	}
	
	private static double round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}	
	
}

