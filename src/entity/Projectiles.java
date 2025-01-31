package entity;

import game.GamePanel;
import javafx.scene.shape.Rectangle;

public class Projectiles extends Entity{
	GamePanel gp;
	int alive_timer = 0;
	public Projectiles(GamePanel gp) {
		super(gp);
		this.gp = gp;
		solidArea = new Rectangle();
		solidArea.setX(20);
		solidArea.setY(20);
		solidArea.setWidth(gp.tileSize-40);
		solidArea.setHeight(gp.tileSize-40);
		name = "bullet";
		// TODO Auto-generated constructor stub
	}
	public void setSpawnPosition(int worldX, int worldY, double angle, boolean alive) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.angle = angle;
		this.alive = alive;
		this.life = 1;
		this.velocity = 8;
	}
	
	public void update() {
		collisionOn = false;
		gp.cChecker.checkMapBoundaries(this);
		//gp.cChecker.checkEntity(this, gp.player);
        double deltaY= velocity * Math.cos(Math.toRadians(angle));
		double deltaX= velocity * Math.sin(Math.toRadians(angle));
        
        angle = (angle + 360) % 360;
        if(collisionOn == false) {
            worldX += deltaX;
            worldY -= deltaY;
        }
		solidArea.setX(16 + worldX);
		solidArea.setY(16 + worldY);

        
		spriteCounter++;
		if(spriteCounter>10) {
			if(spriteNum==1) {
				spriteNum=2;
			}
			else if(spriteNum==2) {
				spriteNum=3;
			}
			else if(spriteNum==3) {
				spriteNum=1;
			}
			spriteCounter=0;
		}
		
		if(alive_timer>420) {
			life = 0;
			alive = false;
		}
		alive_timer++;

	}

}
