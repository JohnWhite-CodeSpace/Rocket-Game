package entity;

import java.util.Arrays;
import java.util.List;

import game.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import utility.UtilityTool;

public class Entity{
	//FOR PLAYER
	public int worldX, worldY;
    double angularAcceleration = 3;
    double acceleration = 0.1;
    public double angle = 0;
    double velocity = 0;
    int maxLife;
    int life;
    public String name;
    public boolean alive;
    int spriteCounter = 0;
    int spriteNum = 1;
    public Image Entity1, Entity2, Entity3, Entity4, DeathImage1, DeathImage2, DeathImage3;
    public UtilityTool Utility = new UtilityTool();
    public GamePanel gp;
    public Rectangle solidArea;
    public int orbitRadius;
    public int planetRadius;
	public boolean collisionOn = false;
	public int orbitCenterY;
	public int orbitCenterX;
	public Circle planetSolidArea;
	public double orbitEccentricity;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public Image setup(String path, int width, int height) {
		Image org_image = new Image(getClass().getResourceAsStream(path+".png"));
		Image out_image = Utility.scaleImage(org_image, width, height);
		return out_image;
	}
	
	public void update() {};
	
	public void SetAction() {};
	
	public void draw(GraphicsContext gc) {
	    int screenX = worldX - gp.player.worldX + gp.player.screenX;
	    int screenY = worldY - gp.player.worldY + gp.player.screenY;

	    if (isInView()) {
	        gc.save();

	        if (alive) {
	            drawSprite(gc, screenX, screenY);
	        }

	        gc.restore();
	    }
	}
	private boolean isInView() {
	    int range = 15 * gp.tileSize;
	    return worldX + range > gp.player.worldX - gp.player.screenX &&
	           worldX - range < gp.player.worldX + gp.player.screenX &&
	           worldY + range > gp.player.worldY - gp.player.screenY &&
	           worldY - range < gp.player.worldY + gp.player.screenY;
	}
	
	private void drawSprite(GraphicsContext gc, int screenX, int screenY) {
	    Image entityImage = getEntityImage();
	    if (entityImage == null) return;

	    if (isNonRotatingObject(name)) {
	        drawNonRotatingObject(gc, entityImage, screenX, screenY);
	    } else if ("AsteroidBelt".equals(name)) {
	        drawAsteroidBelt(gc, entityImage, screenX, screenY);
	    } else {
	        gc.translate(screenX, screenY);
	        gc.rotate(angle);
	        drawRotatingObject(gc, entityImage);
	    }
	}
	
	private Image getEntityImage() {
	    switch (spriteNum) {
	        case 1: return Entity1;
	        case 2: return Entity2;
	        case 3: return Entity3;
	        case 4: return Entity4;
	        default: return null;
	    }
	}
	
	private boolean isNonRotatingObject(String objectName) {
	    List<String> nonRotatingObjects = Arrays.asList(
	        "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", 
	        "Uranus", "Neptune", "Pluto", "Star", "AsteroidBelt"
	    );
	    return nonRotatingObjects.contains(objectName);
	}
	
	private void drawNonRotatingObject(GraphicsContext gc, Image entityImage, int screenX, int screenY) {
	    if ("AsteroidBelt".equals(name)) {
	        double imageX = screenX - solidArea.getWidth() / 2.0;
	        double imageY = screenY - solidArea.getHeight() / 2.0;
	        gc.drawImage(entityImage, imageX, imageY, solidArea.getWidth(), solidArea.getHeight());

	        drawHitbox(gc, screenX, screenY, solidArea.getWidth(), false);
	    } else {
	        double imageX = screenX - planetSolidArea.getRadius();
	        double imageY = screenY - planetSolidArea.getRadius();
	        gc.drawImage(entityImage, imageX, imageY, planetRadius, planetRadius);

	        drawHitbox(gc, screenX, screenY, planetSolidArea.getRadius(), true);
	    }
	}
	
	private void drawAsteroidBelt(GraphicsContext gc, Image entityImage, int screenX, int screenY) {
	    gc.drawImage(entityImage, -gp.tileSize / 2.0, -gp.tileSize / 2.0, gp.tileSize, gp.tileSize);
	    drawHitbox(gc, screenX, screenY, solidArea.getWidth(), false);
	}
	
	private void drawRotatingObject(GraphicsContext gc, Image entityImage) {
	    gc.drawImage(entityImage, -gp.tileSize / 2.0, -gp.tileSize / 2.0, gp.tileSize, gp.tileSize);
	    drawHitbox(gc, 0, 0, solidArea.getWidth(), false);
	}
	
	private void drawHitbox(GraphicsContext gc, int screenX, int screenY, double size, boolean isCircle) {
	    gc.setStroke(Color.RED);
	    gc.setLineWidth(2);

	    if (isCircle) {
	        gc.strokeOval(
	            screenX - size,
	            screenY - size,
	            size,
	            size
	        );
	    } else {
	        gc.strokeRect(
	            screenX + solidArea.getX() - worldX - gp.tileSize / 2.0,
	            screenY + solidArea.getY() - worldY - gp.tileSize / 2.0,
	            solidArea.getWidth(),
	            solidArea.getHeight()
	        );
	    }
	}

	
}
