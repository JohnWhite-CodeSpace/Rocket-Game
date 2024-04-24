
package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Entity.Entity;
import Entity.Player;
import Entity.AlienSpaceship;
import Entity.Asteroid;
import Entity.SpaceStation;
import Entity.Planets;
import Entity.Comet;
import LanSupport.ClientProjection;
import LanSupport.ClientSide;
import LanSupport.ServerProjection;
import LanSupport.ServerSide;
import Entity.Sol;
import Object.OBJ_SpaceStation;
import Object.OBJ_Planets;
import Object.OBJ_Sol;
import Entity.Asteroid_Belt;
import tile.TileManager;
import tile.Minimap;
import AI.PathFinder;
import java.net.ServerSocket;
public class GamePanel extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int originalTileSize = 16;
	final int scale = 3;
	public final int Tilesize = originalTileSize*scale;
	public int maxScreenCol = 32;
	public int maxScreenRow = 20;
	public int screenWidth = Tilesize * maxScreenCol;
	public int screenHeight = Tilesize * maxScreenRow;
	public final int maxWorldCol = 1000;
	public final int maxWorldRow = 1000;
	public final int WorldWidth = Tilesize*maxWorldCol;
	public final int WorldHeight = Tilesize*maxWorldRow;
	public final int maxMap = 1;
	//FULL SCREEN
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2d;
	public ServerProjection serverProjection;
	public ClientProjection clientProjection;
	
	Thread gamethread;
	int FPS = 60;
	String IsOn;
	public KeyHandler keyH = new KeyHandler(this);
	public Player player = new Player(this, keyH);
	public Entity obj[] = new Entity[10];
	public AssetSetter aSetter = new AssetSetter(this);
	public CollisionChecker CollisionCheck = new CollisionChecker(this);
	public Sound sound = new Sound();
	public Soundtracks songs = new Soundtracks();
	public TileManager tileM = new TileManager(this);
	boolean musicOn = true;
	public boolean fullscreenOn = false;
	public SaveConfig config = new SaveConfig(this);
	public PathFinder pFinder = new PathFinder(this);
	public Graphics2D g2;
	public ArrayList<Entity> entityList = new ArrayList<>();
	public ArrayList<Entity> projectileList = new ArrayList<>();
	public SpaceStation spacestation = new OBJ_SpaceStation(this);
	public ArrayList<Planets> SolarSystem = new ArrayList<>();
	public Sol sol = new OBJ_Sol(this);
	public UI ui = new UI(this);
	public Asteroid asteroids[] = new Asteroid[100];
	public Asteroid_Belt asteroidBelt[] = new Asteroid_Belt[600];
	public Comet comets[] = new Comet[15];
	public AlienSpaceship aliens[] =new AlienSpaceship[20];
	Minimap map = new Minimap(this);
	Font font4;
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int exitpauseState = 3;
	public final int GameOverState = 4;
	public final int OptionState = 5;
	public final int WinState=6;
	public final int DialogState=7;
	public final int LoadState=8;
	public final int MultiplayerSetup = 9;
	public final int MapState=10;
	public final int IPPortScreen = 11;
	public boolean Multiplayer = false;
	
	public ClientSide Clients;
	public ServerSide Server;
	private Object lock;
	
	
	public GamePanel() {
		
		//getPreferedGraphicsScale();
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		font4  = new Font("Arial",Font.BOLD,20);
		SetupGame();
		startGameThread();
	}
	public void SetupGame() {
		try {
			songs.PlayMusic();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aSetter.setAsteroid();
		aSetter.setAsteroidBelt();
		aSetter.setComet();
		aSetter.setAlienSpaceship();
		aSetter.SetSol();
		gameState = titleState;
		for(int i=0; i<9; i++) {
			SolarSystem.add(new OBJ_Planets(this, i));
		}
		songs.Play();
		tempScreen = new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D) tempScreen.getGraphics();
		setWindowScreen();
	}
	public void startGameThread() {
		gamethread = new Thread(this);
		gamethread.start();
	}
	public void startHostGame(String IP, int PORT) {
	    try {
	        ServerSocket serverSocket = new ServerSocket(PORT);
	        clientProjection = new ClientProjection(this);
			lock = new Object();
	        Server = new ServerSide(this, serverSocket,lock);
	        Server.StartServer(); // Start the server thread
	        System.out.println("Server started...");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public void ConnectToGame(String IP, int PORT) {
	    try {
	        Socket socket = new Socket(IP, PORT);
	        serverProjection = new ServerProjection(this);
			lock = new Object();
	        Clients = new ClientSide(this, socket,lock);
	        Clients.StartClient(); // Start the client thread
	        System.out.println("Connected to server...");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	@Override
	public synchronized void run(){
		double drawInterval = 1000000000/FPS; // 1sec/60frames
		double delta = 0; //initializing delta
		long lastTime = System.nanoTime();//getting system time in nanoseconds
		long currentTime;
		
		while(gamethread != null) { //if thread is initialized
				currentTime = System.nanoTime(); //getting system time in nanoseconds
				delta+=(currentTime-lastTime)/drawInterval; // adding difference between to system times an dividing by draw interval (time between each drawing) 
				lastTime = currentTime;
				if(delta>=1) {//for delta>= invoking update and repaint method and  subtracting 1 each loop
					update();
					DrawToTempScreen();
					drawToScreen();
					delta--;
				}
//				if(lock!=null) {
//					synchronized (lock) {
//			            lock.notify(); // Notify ServerSide to start
//			            try {
//			                lock.wait(); // Wait for ClientSide to finish
//			            } catch (InterruptedException e) {
//			                e.printStackTrace();
//			            }
//			        }
//				}
			}
	}
	public void update() {
		if(gameState==playState) {
			player.update();
			if(serverProjection!=null) {
				System.out.println("updating projections");
				serverProjection.update();
				System.out.println("projections updated");
			}
			if(clientProjection!=null) {
				System.out.println("updating projections");
				clientProjection.update();
				System.out.println("projections updated");
			}
			spacestation.update();
			spacestation.SetAction();
			
			for(int i=0; i<SolarSystem.size(); i++){
				SolarSystem.get(i).update();
				SolarSystem.get(i).SetAction();
			}
			
			sol.update();
			for(int i=0; i<projectileList.size(); i++) {
				if(projectileList.get(i)!=null) {
					if(projectileList.get(i).IsAlive==true) {
						projectileList.get(i).update();
					}
					if(projectileList.get(i).IsAlive==false) {
						projectileList.remove(i);
					}
				}
			}
			
			
			for(int i=0; i<asteroids.length;i++) {
				if(asteroids[i]!=null) {
					if(asteroids[i].IsAlive==true && asteroids[i].dying ==false) {
					asteroids[i].update();
					asteroids[i].SetAction();
					}
					if(asteroids[i].IsAlive==false) {
						asteroids[i] = null;
					}
				}
			}
			for(int i=0; i<asteroidBelt.length;i++) {
				if(asteroidBelt[i]!=null) {
					if(asteroidBelt[i].IsAlive==true && asteroidBelt[i].dying ==false) {
					asteroidBelt[i].update();
					asteroidBelt[i].SetAction();
					}
					if(asteroidBelt[i].IsAlive==false) {
						asteroidBelt[i] = null;
							
						
					}
				}
			}
			for(int i=0; i<comets.length;i++) {
				if(comets[i]!=null) {
					if(comets[i].IsAlive==true && comets[i].dying ==false) {
					comets[i].update();
					comets[i].SetAction();
					}
					if(comets[i].IsAlive==false) {
						comets[i] = null;
					}
				}
			}
			for(int i=0; i<aliens.length;i++) {
				if(aliens[i]!=null) {
					if(aliens[i].IsAlive==true && aliens[i].dying ==false) {
					aliens[i].update();
					aliens[i].SetAction();
					}
					if(aliens[i].IsAlive==false) {
						aliens[i] = null;
					}
				}
			}
		}
		if(gameState==pauseState) {
			
		}
		if(gameState==GameOverState) {
			
		}
		if(gameState==MapState) {
			
		}
		if(gameState==WinState) {
			
		}
		if(gameState==MultiplayerSetup) {
			
		}
		if(gameState==IPPortScreen) {
			
		}

		
	}
	public void restart() {
		player.restoreDefaultValues();
		player.setDefaultPositions();
		aSetter.setAsteroid();
		aSetter.setComet();
		aSetter.setAsteroidBelt();
		aSetter.setAlienSpaceship();
		aSetter.SetSol();
		ui.playTime=0;
	}
	public void DrawToTempScreen() {
		long LoadStart = 0;
		if(keyH.DebugMode==true) {
			LoadStart = System.nanoTime();
		}
		if(gameState == titleState) {
			ui.draw(g2);
		}
		else if(gameState == LoadState) {
			ui.draw(g2);
		}
		else if(gameState == MultiplayerSetup) {
			ui.draw(g2);
		}
		else if(gameState==IPPortScreen) {
			ui.draw(g2);
		}
		else if(gameState==pauseState) {
			ui.draw(g2);
		}
		else if(gameState==exitpauseState) {
			ui.draw(g2);
		}
		else if(gameState==MapState) {
			map.drawFullMapScreen(g2);
		}
		else if(gameState==GameOverState) {
			ui.draw(g2);
		}
		else if(gameState==WinState) {
			ui.draw(g2);
		}
		else if(gameState==DialogState) {
			ui.draw(g2);
		}
		else {
			
			tileM.draw(g2);
			entityList.add(player);
			if(serverProjection!=null) {
				System.out.println("drawing projections");
				serverProjection.draw(g2);;
				System.out.println("projections drawn");
			}
			if(clientProjection!=null) {
				System.out.println("drawing projections");
				clientProjection.draw(g2);
				System.out.println("projections drawn");
			}
			entityList.add(spacestation);
			for(int i=0; i<SolarSystem.size(); i++) {
				entityList.add(SolarSystem.get(i));
			}
			entityList.add(sol);
			
			for(int i=0; i < projectileList.size(); i++) {
				if(projectileList.get(i)!=null) {
					entityList.add(projectileList.get(i));
				}
			}
			for(int i=0; i<asteroids.length; i++) {
				if(asteroids[i]!=null) {
					entityList.add(asteroids[i]);
				}
			}
			for(int i=0; i<asteroidBelt.length; i++) {
				if(asteroidBelt[i]!=null) {
					entityList.add(asteroidBelt[i]);
				}
			}
			for(int i=0; i<comets.length; i++) {
				if(comets[i]!=null) {
					entityList.add(comets[i]);
				}
			}
			for(int i=0; i<aliens.length; i++) {
				if(aliens[i]!=null) {
					entityList.add(aliens[i]);
				}
			}
			Collections.sort(entityList, new Comparator<Entity>() {
				
				@Override
				public int compare(Entity o1, Entity o2) {
					// TODO Auto-generated method stub
					int result = Integer.compare(o1.worldy, o2.worldy);
					return result;
				}
				
			});
			
			
			for(int i = 0; i< entityList.size();i++) {
				entityList.get(i).draw(g2);
			}
			
			entityList.clear();
			
			ui.draw(g2);
			map.drawMiniMap(g2);
			map.drawPlanetTokens(g2);
			if(keyH.DebugMode==true) {
				long LoadEnd = System.nanoTime();
				long passed = LoadEnd-LoadStart;
				g2.setColor(Color.white);
				g2.setFont(font4);;
				g2.drawString("Draw time: " + passed,15 , Tilesize*13);
				System.out.println("Draw time: " + passed);
				g2.drawString("DEBUG MODE", 15, Tilesize*13-20);
			}
			
		}
	}
	public void drawToScreen() {
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();
	}
	
	public void setFullScreen() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		screenWidth2 = gd.getDisplayMode().getWidth();
		screenHeight2 = gd.getDisplayMode().getHeight();
		//getPreferedGraphicsScale();
		Mainframe.window.setSize(screenWidth2,screenHeight2);
		Mainframe.window.setLocationRelativeTo(null);
		SwingUtilities.updateComponentTreeUI(Mainframe.window);
		fullscreenOn = true;
		
	}
	public void setWindowScreen() {
		screenWidth2 = screenWidth;
		screenHeight2 = screenHeight;
		Mainframe.window.setSize(screenWidth2,screenHeight2);
		Mainframe.window.setLocationRelativeTo(null);
		SwingUtilities.updateComponentTreeUI(Mainframe.window);
		fullscreenOn = false;
		
	}
	public void getPreferedGraphicsScale() {
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		int DeviceDisplayWidth = gd.getDisplayMode().getWidth();
		int DeviceDisplayHeight = gd.getDisplayMode().getHeight();
		System.out.println(DeviceDisplayWidth + "x" + DeviceDisplayHeight);
		maxScreenCol = (int) DeviceDisplayWidth/Tilesize;
		maxScreenRow = (int) DeviceDisplayHeight/Tilesize;
		screenWidth2 = Tilesize * maxScreenCol;
		screenHeight2 = Tilesize * maxScreenRow;
		System.out.println(screenWidth+"x"+screenHeight);
		System.out.println(maxScreenCol + "x" + maxScreenRow);
		
	}
	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}
	
}
