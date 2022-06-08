import javax.swing.JFrame;

public class PacRunner extends JFrame {

	public static void main(String[] args) {
		
		Map map = new Map("Map.txt");
//		map.loadMap("Map.txt");
		
		JFrame window = new JFrame();
		window.setContentPane(map);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(966,966);
		map.setFocusable(true);
		window.setVisible(true);
		window.setResizable(true);	
		window.setLocationRelativeTo(null);
	}
}