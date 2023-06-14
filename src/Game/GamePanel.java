
package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import Entity.Entity;
import Entity.Player;
import Entity.Asteroid;
import Entity.SpaceStation;
import Entity.Pluto;
import Entity.Neptune;
import Entity.Jupiter;
import Entity.Uranus;
import Entity.Saturn;
import Entity.Comet;
import Entity.Mars;
import Entity.Earth;
import Entity.Venus;
import Entity.Mercury;
import Entity.Sol;
import Object.OBJ_Pluto;
import Object.OBJ_SpaceStation;
import Object.OBJ_Neptune;
import Object.OBJ_Jupiter;
import Object.OBJ_Uranus;
import Object.OBJ_Saturn;
import Object.OBJ_Mars;
import Object.OBJ_Earth;
import Object.OBJ_Venus;
import Object.OBJ_Mercury;
import Object.OBJ_Sol;
import Entity.Asteroid_Belt;
import tile.TileManager;
import tile.Minimap;
public class GamePanel extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int originalTileSize = 48;
	final int scale = 1;
	public final int Tilesize = originalTileSize;
	public final int maxScreenCol = 32;
	public final int maxScreenRow = 24;
	public final int screenWidth = Tilesize * maxScreenCol;
	public final int screenHeight = Tilesize * maxScreenRow;
	public final int maxWorldCol = 500;
	public final int maxWorldRow = 500;
	public final int WorldWidth = Tilesize*maxWorldCol;
	public final int WorldHeight = Tilesize*maxWorldRow;
	public final int maxMap = 1;
	//FULL SCREEN
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2d;
	
	
	
	Thread gamethread;
	int FPS = 60;
	String IsOn;
	public KeyHandler keyH = new KeyHandler(this);
	public Player player = new Player(this, keyH);
	public Entity obj[] = new Entity[10];
	public AssetSetter aSetter = new AssetSetter(this);
	public CollisionChecker CollisionCheck = new CollisionChecker(this);
	Sound sound = new Sound();
	Soundtracks songs = new Soundtracks();
	TileManager tileM = new TileManager(this);
	boolean musicOn = true;
	public boolean fullscreenOn = false;
	
	public Graphics2D g2;
	public ArrayList<Entity> entityList = new ArrayList<>();
	public ArrayList<Entity> projectileList = new ArrayList<>();
	public SpaceStation spacestation = new OBJ_SpaceStation(this);
	public Pluto pluto = new OBJ_Pluto(this);
	public Neptune neptune = new OBJ_Neptune(this);
	public Uranus uranus = new OBJ_Uranus(this);
	public Saturn saturn = new OBJ_Saturn(this);
	public Jupiter jupiter = new OBJ_Jupiter(this);
	public Mars mars = new OBJ_Mars(this);
	public Earth earth = new OBJ_Earth(this);
	public Venus venus = new OBJ_Venus(this);
	public Mercury mercury = new OBJ_Mercury(this);
	public Sol sol = new OBJ_Sol(this);
	public UI ui = new UI(this);
	public Asteroid asteroids[] = new Asteroid[100];
	public Asteroid_Belt asteroidBelt[] = new Asteroid_Belt[350];
	public Entity[] planets = new Entity[9];
	public Comet comets[] = new Comet[15];
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
	public final int MapState=10;
	public final int DialogState=7;
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		font4  = new Font("Arial",Font.BOLD,20);
		startGameThread();	
	}
	public void startGameThread() {
		gamethread = new Thread(this);
		gamethread.start();
//		tempScreen = new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_ARGB);
//		g2 = (Graphics2D) tempScreen.getGraphics();
		try {
			songs.PlayMusic();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aSetter.setAsteroid();
		aSetter.setAsteroidBelt();
		aSetter.setComet();
		aSetter.SetSol();
		gameState = titleState;
		planets[0]=pluto;
		planets[1]=neptune;
		planets[2]=uranus;
		planets[3]=saturn;
		planets[4]=jupiter;
		planets[5]=mars;
		planets[6]=earth;
		planets[7]=venus;
		planets[8]=mercury;
		songs.Play();
		
		
	}
	
	
	@Override
	public void run(){
		double drawInterval = 1000000000/FPS; // 1sec/60frames
		double delta = 0; //initialising delta
		long lastTime = System.nanoTime();//getting system time in nanoseconds
		long currentTime;
		long timer=0;//initialising timer
		
		while(gamethread != null) { //if thread is initialised
				currentTime = System.nanoTime(); //getting system time in nanoseconds
				delta+=(currentTime-lastTime)/drawInterval; // adding difference between to system times an dividing by draw interval (time between each drawing) 
				timer+=(currentTime-lastTime);//adding difference to timer
				lastTime = currentTime;
				if(delta>=1) {//for delta>= invoking update and repaint method and  subtracting 1 each loop
					update();
					repaint();
					delta--;
				}
				if(timer>=1000000000) { // if one sec passes setting timer to 0
					timer = 0;
				}
			}
	}
	public void update() {
		if(gameState==playState) {
			player.update();
			spacestation.update();
			spacestation.SetAction();
			pluto.update();
			pluto.SetAction();
			neptune.update();
			neptune.SetAction();
			uranus.update();
			uranus.SetAction();
			saturn.update();
			saturn.SetAction();
			jupiter.update();
			jupiter.SetAction();
			mars.update();
			mars.SetAction();
			earth.update();
			earth.SetAction();
			venus.update();
			venus.SetAction();
			mercury.update();
			mercury.SetAction();
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
		}
		if(gameState==pauseState) {
			
		}
		if(gameState==GameOverState) {
			
		}
		if(gameState==MapState) {
			
		}
		if(gameState==WinState) {
			
		}

		
	}
	public void restart() {
		player.restoreDefaultValues();
		player.setDefaultPositions();
		aSetter.setAsteroid();
		aSetter.setComet();
		aSetter.setAsteroidBelt();
		aSetter.SetSol();
		ui.playTime=0;
	}


	public synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		long LoadStart = 0;
		if(keyH.DebugMode==true) {
			LoadStart = System.nanoTime();
		}
		if(gameState == titleState) {
			ui.draw(g2);
		}
		else {
			
			tileM.draw(g2);
			entityList.add(player);
			entityList.add(spacestation);
			entityList.add(pluto);
			entityList.add(neptune);
			entityList.add(uranus);
			entityList.add(saturn);
			entityList.add(jupiter);
			entityList.add(mars);
			entityList.add(earth);
			entityList.add(venus);
			entityList.add(mercury);
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
		if(gameState==pauseState) {
			ui.draw(g2);
		}
		else if(gameState==exitpauseState) {
			ui.draw(g2);
		}
		if(gameState==MapState) {
			map.drawFullMapScreen(g2);
		}
		if(gameState==GameOverState) {
			ui.draw(g2);
		}
		if(gameState==WinState) {
			ui.draw(g2);
		}
		if(gameState==DialogState) {
			ui.draw(g2);
		}
		
	        g2.dispose();
	   
	}
	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}
	public void SaveGame() {
		
	}
	
}
