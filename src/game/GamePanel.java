package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import tilemap_manager.Minimap;
import tilemap_manager.TileManager;
import entity.Entity;
import entity.Player;
import entity.Projectiles;

public class GamePanel extends Pane implements Runnable {
    // SCREEN SETTINGS
    public final int originalTileSize = 16;
    public final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; // 48x48
    public final int maxScreenCol = 32;
    public final int maxScreenRow = 24;
    public final int screenWidth = tileSize * maxScreenCol; // 768px
    public final int screenHeight = tileSize * maxScreenRow; // 576px
    public final int maxWorldCol = 10000;
    public final int maxWorldRow = 10000;
    
    public int FPS = 60;
    
    private final Canvas canvas;
    public KeyHandler keyH = new KeyHandler(this);
    public Player player = new Player(this, keyH);
    public TileManager tileM = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Soundtracks songs = new Soundtracks();
    public UI ui = new UI(this);
    public SFX sfx = new SFX();
    public AssetSetter ASetter;
    public Minimap minimap;
    public BestRoute bestRoute;
    //OBJECTS:
	public ArrayList<Entity> entityList = new ArrayList<>();
	public ArrayList<Entity> planetList = new ArrayList<>();
	public ArrayList<Entity> stationsList = new ArrayList<>();
    public ArrayList<Projectiles> projectiles = new ArrayList<>();
    private Thread gameThread;
    private boolean isEntityListModified = false;
    public int menu_state = 1, play_state = 2, rocket_state = 3, settings_state = 4, pause_state = 5, multi_state = 6;
    public int game_state;
    public boolean refresh_screen;
    public GamePanel() {
        this.setPrefSize(screenWidth, screenHeight);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        canvas = new Canvas(screenWidth, screenHeight);
        this.getChildren().add(canvas);
        this.setOnKeyPressed(keyH);
        this.setOnKeyReleased(keyH);
        this.setFocusTraversable(true);
        this.setupGame();
        this.startGameThread();
    }
    
    public void setupGame() {
    	game_state = menu_state;
    	minimap = new Minimap(this);
    	bestRoute = new BestRoute(this);
    	player.setDefaultValues();
    	ASetter = new AssetSetter(this);
    	ASetter.setAsteroids();
    	ASetter.setComet();
    	ASetter.setAsteroidBelt();
    	for(int i=1; i<10; i++) {
    		ASetter.setPlanet(i);
    	}
    	System.out.println("Planety w liście:");
    	planetList.forEach(planet -> System.out.println(" - " + planet.name));
    	ASetter.setStars(1);
    	ASetter.setSpaceStations(1);
		try {
			songs.PlayMusic();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		songs.Play();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public synchronized void stopGameThread() {
        if (gameThread != null && gameThread.isAlive()) {
            gameThread.interrupt();
        }
        gameThread = null;
    }
    
    public void addEntity(Entity entity) {
        if (entity != null) {
            entityList.add(entity);
            isEntityListModified = true;
        }
    }

    public void removeEntity(Entity entity) {
        if (entityList.remove(entity)) {
            isEntityListModified = true;
        }
    }

    private void sortEntityListIfNeeded() {
        if (isEntityListModified) {
            entityList.sort(Comparator.comparingInt(e -> e.worldY));
            isEntityListModified = false;
        }
    }

    @Override
    public void run() {
    	double drawInterval = 1000000000/FPS;
    	double delta = 0;
    	long lastTime = System.nanoTime();
    	long currentTime;
        while (gameThread != null) {
        	currentTime = System.nanoTime();
        	delta+=(currentTime - lastTime)/drawInterval;
        	
        	lastTime = currentTime;
        	if(delta>=1) {
        		update();
        		paint();
        		delta--;
        	}
        }
    }





    public void update() {
        player.update();

        for (int i = 0; i < entityList.size(); i++) {
            Entity entity = entityList.get(i);

            if (entity != null) {
                entity.update();
                entity.SetAction();

                if (!entity.alive) {
                    entityList.remove(i);
                    i--;
                }
            }
        }
        for (int i = 0; i < planetList.size(); i++) {
            Entity entity = planetList.get(i);

            if (entity != null) {
                entity.update();
            }
        }
        for (int i = 0; i < stationsList.size(); i++) {
            Entity entity = stationsList.get(i);

            if (entity != null) {
                entity.update();
            }
        }
    }

    public void paint() {
    GraphicsContext gc = canvas.getGraphicsContext2D();
	if(refresh_screen) {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		refresh_screen = !refresh_screen;
	}
    if(game_state == menu_state) {
    	Platform.runLater(() -> {ui.MainMenu(gc);});
    }
    else if(game_state == play_state) {
    		Platform.runLater(() -> {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                tileM.draw(gc);
    			sortEntityListIfNeeded();
                entityList.forEach(e -> e.draw(gc));
                planetList.forEach(e -> e.draw(gc));
                stationsList.forEach(e-> e.draw(gc));
                minimap.drawMiniMap(gc);
                bestRoute.draw(gc);
                player.drawPlayer(gc);
                minimap.drawFullMapScreen(gc);
            });
    	}
        
    }
	public void playSE(int i) {
		sfx.setFile(i);
		sfx.play();
	}
	public void exit() {
        stopGameThread();
        System.exit(0);
	}

}
