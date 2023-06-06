package Game;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Mainframe extends JFrame{

	
	private static final long serialVersionUID = 1L;
	
	public static JFrame window;
	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		window = new JFrame();
		ImageIcon icon = new ImageIcon("res/player/chonker.png");
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Rocket Game");
		window.setIconImage(icon.getImage());
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
//		window.setUndecorated(true);
//		window.setExtendedState(MAXIMIZED_BOTH);
		window.pack();
		window.setVisible(true);
	}
	
	
}
