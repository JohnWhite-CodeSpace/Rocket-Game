package Game;

import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Mainframe extends JFrame{

	/**
	 * 
	 */
	GamePanel gamePanel;
	JMenuBar menubar;
	JMenu menu;
	JMenuItem Author, Exit, Options, Chonker, Speedy, MusicOn, MusicOff;
	ImageIcon icon;
	//public static JFrame window;
	private static final long serialVersionUID = 1L;
	
	public Mainframe() throws IOException {
		icon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/chonker.png"))));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Rocket Game");
		gamePanel = new GamePanel(this);
		this.add(gamePanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setIconImage(icon.getImage());
		//CreateUIComponent();
		
}
//	public void CreateUIComponent() {
//		
//		menubar = new JMenuBar();
//		menu = new JMenu("Menu");
//		Author = new JMenuItem("Author");
//		Options = new JMenuItem("Options");
//		Exit = new JMenuItem("Exit");
//		Chonker = new JMenuItem("Chonker");
//		Speedy = new JMenuItem("Speedy");
//		MusicOn = new JMenuItem("Music On");
//		MusicOff = new JMenuItem("Music Off");
//		Chonker.addActionListener(e->gamePanel.player.ChooseRocket(1));
//		Speedy.addActionListener(e->gamePanel.player.ChooseRocket(2));
//		MusicOn.addActionListener(e->gamePanel.songs.Play());
//		MusicOff.addActionListener(e->gamePanel.songs.Stop());
//		Exit.addActionListener(e->Exit());
//		Author.addActionListener(e->Author());
//		menu.add(MusicOn);
//		menu.add(MusicOff);
//		menu.add(Chonker);
//		menu.add(Speedy);
//		menu.add(Options);
//		menu.add(Author);
//		menu.add(Exit);
//		menubar.add(menu);
//		this.setJMenuBar(menubar);
//	}

//	public void Exit() {
//		System.exit(0);
//	}
//	public void Author() {
//		JOptionPane.showMessageDialog(this,"Author: Jan Bialy nr 323614","Author Window", JOptionPane.INFORMATION_MESSAGE);
//	}
	public int GetWidth() {
		return getWidth();
	}
	public int GetHeight() {
		return getHeight();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Mainframe window = new Mainframe();
		window.setVisible(true);
	}
	
}
