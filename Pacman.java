import java.awt.Graphics2D;
import java.awt.Color;

public class Pacman extends Actor {
	
	private int mouthAngle = 0;
	private int mouthIncrement = 2;
	
	Pacman(int x, int y){  // Everything based off centre
		super(x, y, 0.05);
	}
	

	public double dp1(double num) {
		double temp = num*10;
		return (double)(Math.floor(temp)/10);
	}
	
	public double dp2(double num) {
		double temp = num*100;
		return (double)(Math.floor(temp)/100);
	}	
	
	
	public void drawActor(Graphics2D g2, double blockSize) {
		g2.setColor(this.color);
		g2.fillArc((int) ((0.1*blockSize)+(this.centre.X()-0.5)*blockSize), (int) ((0.1*blockSize)+(this.centre.Y()-0.5)*blockSize), (int) (0.8*blockSize), (int) (0.8*blockSize), this.angleDirection+mouthAngle, 360-2*mouthAngle);
	}
	
	public void updateMouthAngle() {
		if (mouthAngle <=0) {
			mouthIncrement = 5;
		}
		else if (mouthAngle >= 40) {
			mouthIncrement = -5;
		}
		mouthAngle += mouthIncrement;
	}
	
	public IntCoord getTile() {
		return this.tile;
	}
	
	public void setTile(IntCoord tile) {
		this.tile = tile;
	}

	public Coord getCentre() {
		return this.centre;
	}

	public void setCentre(Coord centre) {
		this.centre = centre;
	}
	
	public void printTileCoords() {
		this.tile.printCoords();
	}
	
	public void printCentreCoords() {
		this.centre.printCoords();
	}
	
}
