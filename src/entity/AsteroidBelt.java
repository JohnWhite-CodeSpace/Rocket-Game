package entity;

import game.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AsteroidBelt extends Entity{
	GamePanel gp;
	double change = 0;
	public AsteroidBelt(GamePanel gp) {
		super(gp);
		this.gp = gp;
		solidArea = new Rectangle();
	    solidArea.setX(12);
	    solidArea.setY(12);
	    solidArea.setWidth(gp.tileSize - 24);
	    solidArea.setHeight(gp.tileSize - 24);
		orbitRadius = 430*gp.tileSize;
		orbitCenterX= 5000*gp.tileSize;
		orbitCenterY = 5000*gp.tileSize;
	}
	
	public void setSpawnPosition(int radius_coeff, double angle, int offset, boolean alive) {
		this.worldX = orbitCenterX;
		this.worldY = orbitCenterY;
		this.angle = angle;
		this.alive = alive;
		this.life = 5;
		this.velocity = 3;
		orbitRadius = orbitRadius+ gp.tileSize*radius_coeff + offset;
	}
	
	@Override
	public void update() {
	    collisionOn = false;
	    gp.cChecker.checkEntity(this, gp.player);
	    angle = (angle + 360) % 360;

	    if (!collisionOn) {
	        angle += 0.01;
	        worldX = (int) (orbitRadius * Math.cos(Math.toRadians(angle)) + orbitCenterX);
	        worldY = (int) (orbitRadius * Math.sin(Math.toRadians(angle)) + orbitCenterY);
		    solidArea.setX(worldX + solidArea.getWidth()/2); //To jest ok
		    solidArea.setY(worldY + solidArea.getHeight()/2); //To jest ok
	    }
	    spriteCounter++;
	    if (spriteCounter > 10) {
	        spriteNum = (spriteNum % 3) + 1;
	        spriteCounter = 0;
	    }
	}
	
    public void SetAction() {};
}
