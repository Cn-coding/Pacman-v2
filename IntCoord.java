
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
	
	public void setFloored(Coord c) {
		this.x = (int) Math.floor(c.X());
		this.y = (int) Math.floor(c.Y());
	}
	
	public void printCoords() {
		System.out.println("X: " + x + ", Y: " + y);
	}
}
