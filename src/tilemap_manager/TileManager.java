package tilemap_manager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import utility.UtilityTool;
import game.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	int tilenumber = 26;
	public int mapTileNum[][];
	private Map<Integer, Image> tileCache = new HashMap<>();
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[tilenumber];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		System.out.println("Loading images");
		getTileImage();
		System.out.println("Loading map");
		LoadMap("/maps/GameMap.txt");
	}
	
	private Image loadImage(String path) {
	    return tileCache.computeIfAbsent(path.hashCode(), k -> new Image(getClass().getResourceAsStream(path)));
	}

	public void getTileImage() {
	    for (int i = 0; i < tilenumber; i++) {
	        String path = "/tiles/" + i + ".png";
	        boolean collision = (i == 0); // Tylko kafelek 0 ma kolizję
	        tile[i] = createTile(path, collision);
	    }
	}

	private Tile createTile(String imagePath, boolean collision) {
	    UtilityTool UTool = new UtilityTool();
	    Tile newTile = new Tile();
	    newTile.image = UTool.scaleImage(loadImage(imagePath), gp.tileSize, gp.tileSize);
	    newTile.collision = collision;
	    
	    // Jeżeli kafelek ma kolizję, ustawiamy Rectangle
	    if (collision) {
	        // Ustawienie współrzędnych Rectangle na podstawie rozmiaru kafelka
	        newTile.collisionArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
	    }
	    
	    return newTile;
	}
	
	public void LoadMap(String filePath) {
	    try (InputStream is = getClass().getResourceAsStream(filePath);
	         BufferedReader bufread = new BufferedReader(new InputStreamReader(is))) {
	         	
	        for (int row = 0; row < gp.maxWorldRow; row++) {
	            String[] numbers = bufread.readLine().split(" ");
	            for (int col = 0; col < gp.maxWorldCol; col++) {
	                mapTileNum[col][row] = Integer.parseInt(numbers[col]);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void draw(GraphicsContext gc) {
	    int startCol = Math.max(gp.player.worldX / gp.tileSize - (gp.screenWidth / gp.tileSize) / 2, 0);
	    int endCol = Math.min(startCol + (gp.screenWidth / gp.tileSize) + 2, gp.maxWorldCol);

	    int startRow = Math.max(gp.player.worldY / gp.tileSize - (gp.screenHeight / gp.tileSize) / 2, 0);
	    int endRow = Math.min(startRow + (gp.screenHeight / gp.tileSize) + 2, gp.maxWorldRow);

	    for (int row = startRow; row < endRow; row++) {
	        for (int col = startCol; col < endCol; col++) {
	            int tileNum = mapTileNum[col][row];
	            int screenX = col * gp.tileSize - gp.player.worldX + gp.player.screenX;
	            int screenY = row * gp.tileSize - gp.player.worldY + gp.player.screenY;
	            gc.drawImage(tile[tileNum].image, screenX, screenY);
	            if (tile[tileNum].collision) {
	                tile[tileNum].collisionArea.setX(screenX);
	                tile[tileNum].collisionArea.setY(screenY);
	                gc.setStroke(Color.RED);
	                gc.strokeRect(tile[tileNum].collisionArea.getX(), tile[tileNum].collisionArea.getY(),
	                              tile[tileNum].collisionArea.getWidth(), tile[tileNum].collisionArea.getHeight());
	            }
	        }
	    }
	}
}
