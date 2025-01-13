package entity;

import java.util.Random;

import game.GamePanel;
import javafx.scene.shape.Rectangle;

public class Asteroid extends Entity{
	GamePanel gp;
	int ActionLockCounter =0;
	int i=0;
	int j=0;
	double change = 0;
	public Asteroid(GamePanel gp) {
		super(gp);
		this.gp = gp;
		solidArea = new Rectangle();
		solidArea.setX(12);
		solidArea.setY(12);
		solidArea.setWidth(gp.tileSize-24);
		solidArea.setHeight(gp.tileSize-24);
	}
	
	public void setSpawnPosition(int worldX, int worldY, double angle, boolean alive) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.angle = angle;
		this.alive = alive;
		this.life = 5;
		this.velocity = 3;
	}
	
	public void update() {
		collisionOn = false;
		gp.cChecker.checkMapBoundaries(this);
		gp.cChecker.checkEntity(this, gp.player);
        double deltaY= velocity * Math.cos(Math.toRadians(angle));
		double deltaX= velocity * Math.sin(Math.toRadians(angle));
        
        angle = (angle + 360) % 360;
        if(collisionOn == false) {
            worldX += deltaX;
            worldY -= deltaY;
        }
		solidArea.setX(12 + worldX);
		solidArea.setY(12 + worldY);

        
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

	}
	
    public void SetAction() {
    	if(ActionLockCounter==0) {
    		Random random = new Random();
            i = random.nextInt(2)+1;
            Random random2 = new Random();
            j = random2.nextInt(10)+1;
    	}
    	ActionLockCounter++;
        

        if (ActionLockCounter < 40+4*j) {
        	
    		if(j>0 && j<=5) {
    			angle+=i;
    		}
    		if(j>5&&j<=10) {
    			angle-=i;
    		}
        }
        if(ActionLockCounter==480) {
			ActionLockCounter=0;
		}
    }
	
}
