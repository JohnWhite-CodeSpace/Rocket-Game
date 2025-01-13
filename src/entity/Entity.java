package entity;

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
        if(worldX + 15*gp.tileSize> gp.player.worldX - gp.player.screenX&&
        		worldX-15*gp.tileSize< gp.player.worldX+gp.player.screenX&&
        		worldY+15*gp.tileSize>gp.player.worldY-gp.player.screenY&&
        		worldY-15*gp.tileSize< gp.player.worldY+gp.player.screenY
        		) {
	        gc.save();
	        gc.translate(screenX, screenY);
	        gc.rotate(angle);
	        if(alive) {
		        switch(spriteNum) {
		        	case 1:
		        		gc.drawImage(Entity1, -gp.tileSize / 2.0, -gp.tileSize / 2.0, gp.tileSize, gp.tileSize);
		        	break;
		        	case 2:
		        		gc.drawImage(Entity2, -gp.tileSize / 2.0, -gp.tileSize / 2.0, gp.tileSize, gp.tileSize);
		        	break;
		        	case 3:
		        		gc.drawImage(Entity3, -gp.tileSize / 2.0, -gp.tileSize / 2.0, gp.tileSize, gp.tileSize);
		        	break;
		        	case 4:
		        		gc.drawImage(Entity4, -gp.tileSize / 2.0, -gp.tileSize / 2.0, gp.tileSize, gp.tileSize);
		        	break;
		        }
		        gc.restore();
		        gc.setStroke(Color.RED);
		        gc.setLineWidth(2);
		        gc.strokeRect(
		            screenX + solidArea.getX() - worldX - gp.tileSize / 2.0,
		            screenY + solidArea.getY() - worldY - gp.tileSize / 2.0,
		            solidArea.getWidth(),
		            solidArea.getHeight()
		        );
	        }
        }
	}
}
