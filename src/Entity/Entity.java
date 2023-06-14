package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import Game.GamePanel;
import Object.OBJ_PlayerFuel;
import Object.OBJ_PlayerLife;
import Utility.UtilityTool;

public class Entity {

	public int worldx, worldy;
	public int fuel, fuelconsumption,maxfuel;
	public double velocity;
	public double PlayerAngle;
	public double SpsAngle;
	public int Aangle;
	public double Acceleration, HyperAcceleration;
	public double AngularVelocity;
	public int MaxSpeed;
	GamePanel gp;
	public BufferedImage bar1,bar2, fuel100, fuel75,fuel50, fuel25, fuel0;
	public BufferedImage Entity1, Entity2, Entity3, Entity4,DeathImage1, DeathImage2, DeathImage3;
	public String name;
	public String Weapon;
	public int type;
	public int AsteroidCollision;
	public String direction = "up";
	public boolean collision = false;
	public boolean invincible = false;
	public boolean rein = false;
	public boolean dialogOn=false;
	public int reinCounter=0;
	public int invincibleCounter = 0;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int maxLife;
	public int life;
	public int planettoken=0;
	public boolean IsAlive = true;
	public boolean dying = false;
	public Rectangle solidArea;
	public Ellipse2D planetSolidArea, infoArea;
	public double Xcircle, Ycircle,Radcircle;
	public boolean collisionOn = false;
	public int DyingCounter;
	public int ShotAveilableCounter = 0;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public Projectile projectile;
	public Asteroid asteroid;
	public Asteroid_Belt asteroidBelt;
	public AlienSpaceship alienrocket;
	public Comet comet;
	public SpaceStation spacestation;
	public Pluto pluto;
	public Neptune neptune;
	public Uranus uranus;
	public Saturn saturn;
	public Jupiter jupiter;
	public Mars mars;
	public Earth earth;
	public Venus venus;
	public Mercury mercury;
	public Sol sol;
	public OBJ_PlayerLife lifebar;
	public OBJ_PlayerFuel PFuel;
	public int ActionLockCounter = 0;
	public double angle =0;
	public int radius;
	public int centerx, centery;
	public Entity(GamePanel gp) {
		this.gp=gp;
	}
	 
	public void set() {}
	public void SetAction(){};
	public void update(){collisionOn=false;
    gp.CollisionCheck.CheckTile(this);
	}
	public void getPlanetInfo(Graphics2D g2){};
		
	    
	    public BufferedImage setup(String imagePath, int width, int height) {
	        BufferedImage image = null;
	        UtilityTool uTool = new UtilityTool();
	        
	        try {
	    
	        	image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return uTool.scaleImage(image, width, height);
	    }
	    
	    public void draw(Graphics2D g2) {
	    	
			BufferedImage image = null;
			int screenX = worldx - gp.player.worldx + gp.player.screenx;
	        int screenY = worldy - gp.player.worldy + gp.player.screeny;
	        if(worldx + 12*gp.Tilesize> gp.player.worldx - gp.player.screenx&&
	        		worldx-12*gp.Tilesize< gp.player.worldx+gp.player.screenx&&
	        		worldy+12*gp.Tilesize>gp.player.worldy-gp.player.screeny&&
	        		worldy-12*gp.Tilesize< gp.player.worldy+gp.player.screeny
	        		) {
			switch(direction) {

			case "player1","bullet1","space_station","comet","asteroid","alienrocket":
				if(spriteNum==1) {
					image = Entity1;
				}
				if(spriteNum==2) {
					image = Entity2;
				}
				if(spriteNum==3) {
					image=Entity3;
				}
				break;
			case "pellet":
				if(spriteNum==1) {
					image = Entity4;
				}
				if(spriteNum==2) {
					image = Entity4;
				}
				if(spriteNum==3) {
					image=Entity4;
				}
				break;
			case "planet":
				if(spriteNum==1) {
					image = Entity1;
				}
				if(spriteNum==2) {
					image = Entity2;
				}
				if(spriteNum==3) {
					image=Entity3;
				}
				if(spriteNum==4) {
					image=Entity4;
				}
				break;
			case "ABelt":
				if(spriteNum==1) {
					image = Entity1;
				}
				if(spriteNum==2) {
					image = Entity1;
				}
				if(spriteNum==3) {
					image = Entity1;
				}
			break;
			}
			
			
			if(image!=null) {
			if(direction=="player1") {
				AffineTransform transform = new AffineTransform();
		        transform.translate(screenX, screenY);
		        transform.rotate(Math.toRadians(gp.player.PlayerAngle),gp.Tilesize/2,gp.Tilesize/2);
		        g2.drawImage(image, transform, null);
			
			}
			else if(direction=="bullet1"||direction=="pellet") {
				for(int i=0; i<gp.projectileList.size(); i++)
		        g2.drawImage(image, ((Projectile) gp.projectileList.get(i)).rotatedImage(i), null);
			
			}
			else if(direction=="space_station") {
		        g2.drawImage(image, gp.spacestation.rotatedImage(), null);
			}
			else if(direction=="alienrocket") {
				for(int i=0;i<gp.alienrocket.length;i++) {
					if(gp.alienrocket[i]!=null) {
						g2.drawImage(image, gp.alienrocket[i].rotatedImage(), null);
					}
				}    
			}
			else if(direction=="comet") {
				for(int i=0;i<gp.comets.length;i++) {
					if(gp.comets[i]!=null) {
						g2.drawImage(image, gp.comets[i].rotatedImage(), null);
					}
				}
		        
			}
			else {
				g2.drawImage(image, screenX, screenY, null);
			}
			if(dying==true) {
				dyingAnimation(g2,screenX,screenY);
			}
	        }
			
			
			
	    }
	}
	    public void dyingAnimation(Graphics2D g2, int sX, int sY) {
	    	DyingCounter++;
	    	if(DyingCounter<=10) {
	    		g2.drawImage(DeathImage1, sX, sY, null);
	    	}
			if(DyingCounter>10 && DyingCounter<=20) {
				g2.drawImage(DeathImage2, sX, sY, null);    		
			}
			if(DyingCounter>20 && DyingCounter<=30) {
				g2.drawImage(DeathImage3, sX, sY, null);
	    	}
			if(DyingCounter>30) {
				dying = false;
				IsAlive = false;
			}
	    }
	    
}
