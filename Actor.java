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
	protected int angleNextDirection = -1;  // -1 is the null value
	protected Coord nextDirectionCoords;
	protected double speed;
	protected IntCoord direction;
	protected Coord moveIncrement;
	protected char[] moveableArray = new char[5];
	protected boolean moved = false;
	protected double size = 0.1;

	
	Actor(int x, int y, double speed){  // Everything based off centre
		this.tile = new IntCoord(x, y);
		this.centre = new Coord((double) x+0.5, (double) y+0.5);
		this.speed = speed;
		this.direction = new IntCoord(1,0);
		this.moveIncrement = new Coord(0,0);
		this.nextDirectionCoords = new Coord(-10, -10);
		moveableArray[0] = 'b';
		moveableArray[1] = 'e';
		moveableArray[2] = 'g';
		moveableArray[3] = 'c';
		moveableArray[4] = 'i';
	}

	public abstract void drawActor(Graphics2D g2, double blockSize);
	
	
	public void move(char[][] map) {
		moved = false;
		this.direction.setX((int) Math.sin(Math.toRadians(90+this.angleDirection)));
		this.direction.setY((int) Math.sin(Math.toRadians(this.angleDirection))*-1);
		this.moveIncrement.setX(this.direction.X()*speed);
		this.moveIncrement.setY(this.direction.Y()*speed);
		
		this.speed = round(speed, 4);
		this.centre.setX(round(this.centre.X(), 4));
		this.centre.setY(round(this.centre.Y(), 4));
		this.moveIncrement.setX(round(this.moveIncrement.X(), 4));
		this.moveIncrement.setY(round(this.moveIncrement.Y(), 4));

		for (char ch: moveableArray) {
			if (moved == false && map[(int) Math.floor(this.centre.Y()+moveIncrement.Y()+(0.5*direction.Y()))][(int) Math.floor(this.centre.X()+moveIncrement.X()+(0.5*direction.X()))] == ch) {
				this.updatePosition(moveIncrement);
				this.moved = true;
			}
		}

		if (moved == false) {
			this.setPosition(this.tile.X()+0.5, this.tile.Y()+0.5);
		}
		
		if (angleNextDirection != -1) {
			if (this.centre.pythag(this.tile.X()+0.5, this.tile.Y()+0.5) <=0.15){
				if (inMoveable(map[this.tile.Y()+(int) Math.sin(Math.toRadians(this.angleNextDirection))*-1][this.tile.X()+(int) Math.sin(Math.toRadians(90+this.angleNextDirection))])) {
					this.setPositionCentre();
					this.angleDirection = this.angleNextDirection;
					this.angleNextDirection = -1;
				}
				
			}
		}
		
		if (this.nextDirectionCoords.pythag(this.centre) >=1.5) {
			this.nextDirectionCoords.set(-10, -10);
			this.angleNextDirection = -1;
		}
	}
	
	public char[][] dotReplacement(char[][] map) {
		
		if (within(this.centre.X()-this.tile.X(), 0.35, 0.65) && within(this.centre.Y()-this.tile.Y(), 0.35, 0.65)) {
			if (map[this.tile.Y()][this.tile.X()] == 'b') {
				map[this.tile.Y()][this.tile.X()] = 'i';
				this.size = Math.random()-0.55;  
			}
			
			if (map[this.tile.Y()][this.tile.X()] == 'c') {
				map[this.tile.Y()][this.tile.X()] = 'i';	
				this.size = Math.random()*10-15.55;

			}
		}
		
		return map;
	}
	
	private boolean inMoveable(char c) {
		for (char ch: this.moveableArray) {
			if (ch == c){
				return true;
			}
		}
		return false;
	}
	
	private static boolean within(double n, double l, double h) {
		if (n>=l && n<=h) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static double round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}	
	
	public void updatePosition(Coord c) {
		this.centre.add(c);
		this.tile.setFloored(centre);
	}
	
	public void setPosition(double x, double y) {
		this.centre.setX(x);
		this.centre.setY(y);
		this.tile.setX(x);
		this.tile.setY(y);
	}
	
	public void setPositionCentre() {
		this.centre.setX(this.tile.X()+0.5);
		this.centre.setY(this.tile.Y()+0.5);
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

	public int getAngleNextDirection() {
		return angleNextDirection;
	}

	public void setAngleNextDirection(int angleNextDirection) {
		this.angleNextDirection = angleNextDirection;
		this.nextDirectionCoords.set(this.centre.X(), this.centre.Y());
		if (this.angleNextDirection - 180 == this.angleDirection || this.angleDirection - 180 == this.angleNextDirection) {
			this.angleDirection = this.angleNextDirection;
			this.angleNextDirection = -1;
			this.nextDirectionCoords.set(-10, -10);
		}
	}
	
}
