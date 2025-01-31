package entity;

import game.GamePanel;
import javafx.scene.shape.Circle;

public class Planets extends Entity{
	GamePanel gp;
	double change = 0;
	public Planets(GamePanel gp, int X, int Y, int Radius, int orbitCoeff, double Eccentricity, String planetName) {
		super(gp);
		this.gp = gp;
		planetSolidArea = new Circle();
		planetSolidArea.setCenterX(X);
		planetSolidArea.setCenterY(Y);
		planetSolidArea.setRadius(Radius);
		orbitRadius = orbitCoeff*gp.tileSize;
		orbitCenterX= 500*gp.tileSize;
		orbitCenterY = 500*gp.tileSize;
		planetRadius = Radius;
		orbitEccentricity = Eccentricity;
		name = planetName;
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
        
		angle = (angle + 0.01) % 360;

	    double radians = Math.toRadians(angle);
	    double r = (orbitRadius * (1 - orbitEccentricity * orbitEccentricity)) / 
	               (1 + orbitEccentricity * Math.cos(radians));

	    worldX = (int) (r * Math.cos(radians) + orbitCenterX);
	    worldY = (int) (r * Math.sin(radians) + orbitCenterY);
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
	
    public void SetAction() {};
    
	
}
