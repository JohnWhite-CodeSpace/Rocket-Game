package entity;

import game.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;

public class Planets extends Entity{
	GamePanel gp;
	double change = 0;
	public Planets(GamePanel gp, int X, int Y, int Radius, int orbitCoeff) {
		super(gp);
		this.gp = gp;
		planetSolidArea = new Circle();
		planetSolidArea.setCenterX(X);
		planetSolidArea.setCenterY(Y);
		planetSolidArea.setRadius(Radius);
		orbitRadius = orbitCoeff*gp.tileSize;
		orbitCenterX= 500*gp.tileSize;
		orbitCenterY = 500*gp.tileSize;
	}
	
	public void setSpawnPosition(int orbitCenterX, int orbitCenterY, int orbitRadius, double angle, boolean alive) {
		this.worldX = orbitCenterX;
		this.worldY = orbitCenterY;
		this.angle = angle;
		this.alive = alive;
		this.life = 5;
		this.velocity = 3;
		this.orbitRadius = orbitRadius;
		this.orbitCenterX = orbitCenterX;
		this.orbitCenterY = orbitCenterY;
	}
	
	public void update() {
		collisionOn = false;
        
        angle = (angle + 360) % 360;
        
        if(collisionOn == false) {
        	angle+=0.01;
            worldX = (int) (orbitRadius * Math.cos(Math.toRadians(angle)) + orbitCenterX);
            worldY = (int) (orbitRadius * Math.sin(Math.toRadians(angle)) + orbitCenterY);
        } 
			spriteCounter++;
		if(spriteCounter>10) {
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
	
    public void SetAction() {};
    
    public void drawPlanet(GraphicsContext gc) {
    	
    }
	
}
