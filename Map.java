import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.Timer;
import javax.swing.JPanel;

public class Map extends JPanel implements KeyListener, ActionListener {

	private int mapWidth =0;
	private int mapHeight =0;
	private char[][] map;
	private Graphics2D g2;
	private double blockWidth;
	private double blockHeight;
	private double blockSize;
	private Timer paintTimer = new Timer(10,this);						
	private Timer mouthTimer = new Timer(40,this);
	private Timer moveTimer = new Timer(40,this);												
	private double startX;
	private double startY;
	private int pacStartX;
	private int pacStartY;
	private Color Cbackground = new Color(25, 25, 166);
	private char[] moveableArray = new char[5];
	Pacman pac;

	
	Map(String filename){
		loadMap(filename);
		paintTimer.start();
		mouthTimer.start();
		moveTimer.start();
		pac = new Pacman(this.pacStartX, this.pacStartY);
		addKeyListener(this);
		moveableArray[0] = 'b';
		moveableArray[1] = 'e';
		moveableArray[2] = 'g';
		moveableArray[3] = 'c';
		moveableArray[4] = 'i';

	}

	

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			pac.setAngleNextDirection(Actor.getWEST());
		}

	    if (key == KeyEvent.VK_RIGHT) {
			pac.setAngleNextDirection(Actor.getEAST());
	    }

	    if (key == KeyEvent.VK_UP) {
			pac.setAngleNextDirection(Actor.getNORTH());
	    }

	    if (key == KeyEvent.VK_DOWN) {
			pac.setAngleNextDirection(Actor.getSOUTH());
	    }	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	
	public void drawTile(Graphics2D g2, char tile, double startX, double startY, int x, int y) {
		switch (tile) {
		
		case 'a':
			g2.setColor(Color.BLACK);
			g2.fillRect((int) startX, (int) startY, (int) blockSize, (int) blockSize);
			g2.setColor(Cbackground);
			if (x != 0 && y!=0 && x!= mapWidth-1 && y!= mapHeight-1) {
				if (map[y][x-1] != 'a') {
					g2.drawLine((int) startX, (int) startY, (int) startX, (int) (startY+blockSize));
				}
				
				if (map[y][x+1] != 'a') {
					g2.drawLine((int) (startX+blockSize-1), (int) startY, (int) (startX+blockSize-1), (int) (startY+blockSize));
				}
				
				if (map[y-1][x] != 'a') {
					g2.drawLine((int) startX, (int) startY, (int) (startX+blockSize), (int) (startY));
				}
				
				if (map[y+1][x] != 'a') {
					g2.drawLine((int) startX, (int) (startY+blockSize-1), (int) (startX+blockSize), (int) (startY+blockSize-1));
				}
			}
			
			if (x == 0) {
				if (map[y][1] !='a') {
					g2.drawLine((int) (startX+blockSize-1), (int) (startY), (int) (startX+blockSize-1), (int) (startY+blockSize));
				}
			}
			
			if (x == mapWidth-1) {
				if (map[y][(int) (mapWidth-2)] !='a') {
					g2.drawLine((int) (startX+1), (int) (startY), (int) (startX+1), (int) (startY+blockSize));
				}
			}
			
			if (y == mapHeight-1) {
				if (map[y-1][x] !='a') {
					g2.drawLine((int) (startX), (int) (startY+1), (int) (startX+blockSize), (int) (startY+1));
				}
			}
			
			if (y == 0) {
				if (map[1][x] !='a') {
					g2.drawLine((int) startX, (int) (startY-1+blockSize), (int) (startX+blockSize), (int) (startY-1+blockSize));
				}
			}
			
			
			break;
			
		case 'b':
			g2.setColor(Color.BLACK);
			g2.fillRect((int) startX, (int) startY, (int) blockSize, (int) blockSize);
			g2.setColor(Color.white);
			g2.fillOval((int) (startX+0.4*blockSize), (int) (startY+0.4*blockSize), (int) (blockSize-0.8*blockSize), (int) (blockSize-0.8*blockSize));
			break;
		
		case 'c':
			g2.setColor(Color.BLACK);
			g2.fillRect((int) startX, (int) startY, (int) blockSize, (int) blockSize);
			g2.setColor(Color.white);
			g2.fillOval((int) (startX+0.15*blockSize), (int) (startY+0.15*blockSize), (int) (blockSize-0.3*blockSize), (int) (blockSize-0.3*blockSize));
			break;
		
		case 'd':
			g2.setColor(Color.BLACK);
			g2.fillRect((int) startX, (int) startY, (int) blockSize, (int) blockSize);
			break;
		
		case 'f':
			g2.setColor(Color.BLACK);
			g2.fillRect((int) startX, (int) startY, (int) blockSize, (int) blockSize);
			break;
			
		case 'g':
			g2.setColor(Color.BLACK);
			g2.fillRect((int) startX, (int) startY, (int) blockSize, (int) blockSize);	
			break;
			
		case 'h':
			g2.setColor(Color.BLACK);
			g2.fillRect((int) startX, (int) startY, (int) blockSize, (int) blockSize);	
			break;
		
		case 'p':
				g2.setColor(Color.BLACK);
				g2.fillRect((int) startX, (int) startY, (int) blockSize, (int) blockSize);	
				g2.setColor(Color.ORANGE);
				g2.fillOval((int) startX, (int) startY, (int) blockSize, (int) blockSize);
				break;
		}
	}
	
	
	public void drawMap(Graphics2D g2, double blockSize) {

		
		char[] line;
		char tile;
		for (int y=0; y<mapHeight; y++) {
			for (int x=0; x<mapWidth; x++) {
				tile = map[y][x];
				startX = x * blockSize;
				startY = y * blockSize;
				g2.setColor(Color.green);
				
				drawTile(g2, tile, startX, startY, x, y);
				

			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		
		blockWidth = dp2((double)getWidth()/(double)mapWidth);
		blockHeight = dp2((double)getHeight()/(double)mapHeight);
		blockSize = Math.min(blockWidth,  blockHeight);
		
		setBackground(Color.black);
		
		drawMap(g2, blockSize);
		
		pac.drawActor(g2, blockSize);

	}
	
	
	public double dp1(double num) {
		double temp = num*10;
		return (double)(Math.floor(temp)/10);
	}
	
	public double dp2(double num) {
		double temp = num*100;
		return (double)(Math.floor(temp)/100);
	}
	
	
	public void loadMap(String filename) {
		
		System.out.println(filename);
		try {
			BufferedReader reader = new BufferedReader( new FileReader(filename));
			String line = reader.readLine();
			mapWidth = line.length();
			while (line != null) {
				line = reader.readLine();
				mapHeight ++;
			}
			
			System.out.println("Map Width: " + mapWidth + ", Map Height: " + mapHeight);
			map = new char[mapHeight][mapWidth];
			
			reader = new BufferedReader( new FileReader(filename));
			line = reader.readLine();
			for (int i=0; i<mapHeight; i++) {
				map[i] = line.toCharArray();
				line = reader.readLine();
			}
			
			for (char[] readLine: map) {
				System.out.println(readLine);
			}
			
		}	
		
		catch(FileNotFoundException f) {
			System.out.println("Unable To Load Map");
		}
		
		catch(IOException e) {
			System.out.println("Error Reading The File");
		}
		
		
		
		// Location finders (pac start, ghost bay etc)
		for (int y=0; y<mapHeight; y++) {
			for (int x=0; x<mapWidth; x++) {
				if (map[y][x] == 'g'){
					pacStartX = x;
					pacStartY = y;
				}
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == paintTimer) {
			repaint();
		}
		if (e.getSource() == mouthTimer) {
			pac.updateMouthAngle();
		}
		if (e.getSource() == moveTimer) {
			pac.move(map);
			pac.dotReplacement(map);
		}
	}
	
	
	
}
