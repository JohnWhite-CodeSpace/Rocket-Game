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
    public final int maxWorldCol = 1000;
    public final int maxWorldRow = 1000;
    
    public int FPS = 60;
    
    private final Canvas canvas;
    public KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);
    public TileManager tileM = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Soundtracks songs = new Soundtracks();
    public SFX sfx = new SFX();
    public AssetSetter ASetter;
    //OBJECTS:
	public ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Projectiles> projectiles = new ArrayList<>();
    private Thread gameThread;
    private boolean isEntityListModified = false;

    public GamePanel() {
        this.setPrefSize(screenWidth, screenHeight);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        canvas = new Canvas(screenWidth, screenHeight);
        this.getChildren().add(canvas);
        this.setOnKeyPressed(keyH::handleKeyPressed);
        this.setOnKeyReleased(keyH::handleKeyReleased);
        this.setFocusTraversable(true);
        this.setupGame();
        this.startGameThread();
    }
    
    public void setupGame() {
    	player.setDefaultValues();
    	ASetter = new AssetSetter(this);
    	ASetter.setAsteroids();
    	ASetter.setComet();
    	ASetter.setAsteroidBelt();
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
    }

    public void paint() {
        Platform.runLater(() -> {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            tileM.draw(gc);
			sortEntityListIfNeeded();
            entityList.forEach(e -> e.draw(gc));
            player.drawPlayer(gc);
        });
    }
	public void playSE(int i) {
		sfx.setFile(i);
		sfx.play();
	}

}
