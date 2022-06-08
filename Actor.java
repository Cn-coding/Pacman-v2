import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Actor {
	
	protected static final int NORTH = 90;
	protected static final int EAST = 0;
	protected static final int SOUTH = 270;
	protected static final int WEST = 180;

	protected IntCoord tile;
	protected char tileChar;
	protected Coord centre;
	protected Color color = Color.yellow;
	protected int angleDirection; // decimal of a block size
	protected double speed;
	protected IntCoord direction;
	protected Coord moveIncrement;
	
	Actor(int x, int y, double speed){  // Everything based off centre
		this.tile = new IntCoord(x, y);
		this.centre = new Coord((double) x+0.5, (double) y+0.5);
		this.speed = speed;
		this.direction = new IntCoord(1,0);
		this.moveIncrement = new Coord(0,0);
	}

	public abstract void drawActor(Graphics2D g2, double blockSize);
	
	
	public void move(char[][] map) {
		this.direction.setX((int)Math.sin(Math.toRadians(90+this.angleDirection)));
		this.direction.setY((int)  Math.sin(Math.toRadians(this.angleDirection))*-1);
		this.moveIncrement.setX(this.direction.X()*speed);
		this.moveIncrement.setY(this.direction.Y()*speed);
		
		// do this for all four directions
		// maybe something with sin and just do it once??
		System.out.println(map[this.tile.Y()][this.tile.X()]);
		System.out.println("next: " + map[(int) Math.floor(this.centre.Y()+moveIncrement.Y()+0.0)][(int) Math.floor(this.centre.X()+moveIncrement.X()+0.5)]);
		this.updatePosition(moveIncrement);
	}
	
	
	public void updatePosition(Coord c) {
		this.centre.addX(c.X());
		this.centre.addY(c.Y());
		this.tile.setFloored(centre);
	}
	
	public IntCoord getTile() {
		return tile;
	}

	public void setTile(IntCoord tile) {
		this.tile = tile;
	}

	public Coord getCentre() {
		return centre;
	}

	public void setCentre(Coord centre) {
		this.centre = centre;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public IntCoord getDirection() {
		return direction;
	}

	public void setDirection(IntCoord direction) {
		this.direction = direction;
	}

	public static int getNORTH() {
		return NORTH;
	}

	public static int getEAST() {
		return EAST;
	}

	public static int getSOUTH() {
		return SOUTH;
	}

	public static int getWEST() {
		return WEST;
	}

	public int getAngleDirection() {
		return angleDirection;
	}

	public void setAngleDirection(int angleDirection) {
		this.angleDirection = angleDirection;
	}
	
}
