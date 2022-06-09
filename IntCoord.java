
public class IntCoord {

	private int x;
	private int y;
	
	IntCoord(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int X() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int Y() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setX(double x) {
		this.x = (int) Math.floor(x);
	}
	
	public void setY(double y) {
		this.y = (int) y;
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setFloored(Coord c) {
		this.x = (int) Math.floor(c.X());
		this.y = (int) Math.floor(c.Y());
	}
	
	public void add(IntCoord c) {
		this.x += c.X();
		this.y += c.Y();
	}
	
	public void printCoords() {
		System.out.println("X: " + x + ", Y: " + y);
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
