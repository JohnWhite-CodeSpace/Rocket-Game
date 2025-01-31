package entity;

import game.GamePanel;
import javafx.scene.shape.Circle;

public class Stars extends Entity{
	GamePanel gp;
	double change = 0;
	public Stars(GamePanel gp, int X, int Y, int Radius) {
		super(gp);
		this.gp = gp;
		planetSolidArea = new Circle();
		planetSolidArea.setCenterX(X);
		planetSolidArea.setCenterY(Y);
		planetSolidArea.setRadius(Radius);
		orbitCenterX= 5000*gp.tileSize;
		orbitCenterY = 5000*gp.tileSize;
		planetRadius = Radius;
	}
	
	public void setSpawnPosition(int orbitCenterX, int orbitCenterY, double angle, boolean alive) {
		this.worldX = orbitCenterX;
		this.worldY = orbitCenterY;
		this.angle = angle;
		this.alive = alive;
		this.life = 5;
		this.velocity = 3;
		this.orbitCenterX = orbitCenterX;
		this.orbitCenterY = orbitCenterY;
	}
	
	public void update() {
		collisionOn = false;
			spriteCounter++;
		if(spriteCounter>30) {
			if(spriteNum==1) {
				spriteNum=2;
			}
			else if(spriteNum==2) {
				spriteNum=3;
			}
			else if(spriteNum==3) {
				spriteNum=4;
			}else if(spriteNum==4) {
				spriteNum=1;
			}
			spriteCounter=0;
		}

	}
    
	
}
