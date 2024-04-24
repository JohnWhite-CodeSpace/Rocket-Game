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
	public int Alienangle;
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
	public boolean onPath = false;
	public Ellipse2D planetSolidArea, infoArea, AGRarea;
	public double Xcircle, Ycircle,Radcircle;
	public boolean collisionOn = false;
	public boolean AgressionOn = false;
	public int DyingCounter;
	public int ShotAveilableCounter = 0;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public Projectile projectile;
	public Asteroid asteroid;
	public Asteroid_Belt asteroidBelt;
	public AlienSpaceship aliens;
	public Comet comet;
	public SpaceStation spacestation;
	public Sol sol;
	public OBJ_PlayerLife lifebar;
	public OBJ_PlayerFuel PFuel;
	public int ActionLockCounter = 0;
	public double angle =0;
	public int radius;
	public int centerx, centery;
	public int AdjustX=0;
	public int AdjustY=0;
	public Entity(GamePanel gp) {
		this.gp=gp;
	}
	 
	public void set() {}
	public void SetAction(){};
	public void update(){collisionOn=false;
    gp.CollisionCheck.CheckTile(this);
	}
	public void searchPath(int goalCol, int goalRow) {
		int startCol = (worldx + solidArea.x)/gp.Tilesize;
		int startRow = (worldy + solidArea.y)/gp.Tilesize;
		gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);
		
		if(gp.pFinder.search()==true) {
			int nextx = gp.pFinder.pathList.get(0).col*gp.Tilesize;
			int nexty = gp.pFinder.pathList.get(0).row*gp.Tilesize;
			
			int enLeftx = worldx + solidArea.x;
			int enRightx = worldx + solidArea.x + solidArea.width;
			int enTopy = worldy + solidArea.y;
			int enBottomy = worldy + solidArea.y+solidArea.height;
			if(enTopy>nexty && enLeftx >= nextx && enRightx < nextx + gp.Tilesize) {
				Alienangle = 0;
			}
			else if(enTopy<nexty && enLeftx >= nextx && enRightx < nextx + gp.Tilesize) {
				Alienangle = 180;
			}
			else if(enTopy>=nexty && enBottomy<nexty + gp.Tilesize) {
				if(enLeftx>nextx) {
					Alienangle = 270;
				}
				if(enLeftx<nextx) {
					Alienangle = 90;
				}
			}
			else if(enTopy>nexty && enLeftx>nextx) {
				Alienangle = 0;
				if(collisionOn ==true) {
					Alienangle = 270;
				}
			}
			else if(enTopy>nexty && enLeftx < nextx) {
				
			}
		}
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
	        if(worldx + 15*gp.Tilesize> gp.player.worldx - gp.player.screenx&&
	        		worldx-15*gp.Tilesize< gp.player.worldx+gp.player.screenx&&
	        		worldy+15*gp.Tilesize>gp.player.worldy-gp.player.screeny&&
	        		worldy-15*gp.Tilesize< gp.player.worldy+gp.player.screeny
	        		) {
			switch(direction) {

			case "player1","bullet1","space_station","comet","asteroid","aliens", 
			"clientprojection", "serverprojection":
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
		        transform.rotate(Math.toRadians(PlayerAngle),gp.Tilesize/2,gp.Tilesize/2);
		        g2.drawImage(image, transform, null);
			
			}
			else if(direction=="bullet1"||direction=="pellet") {
				for(int i=0; i<gp.projectileList.size(); i++)
		        g2.drawImage(image, ((Projectile) gp.projectileList.get(i)).rotatedImage(i), null);
			
			}
			else if(direction=="space_station") {
		        g2.drawImage(image, gp.spacestation.rotatedImage(), null);
			}
			else if(direction=="serverprojection") {
		        g2.drawImage(image, gp.serverProjection.rotatedImage(), null);
			}
			else if(direction=="clientprojection") {
		        g2.drawImage(image, gp.clientProjection.rotatedImage(), null);
			}
			else if(direction=="comet") {
				for(int i=0;i<gp.comets.length;i++) {
					if(gp.comets[i]!=null) {
						g2.drawImage(image, gp.comets[i].rotatedImage(), null);
					}
				}
		        
			}
			else if(direction=="asteroid") {
				for(int i=0;i<gp.asteroids.length;i++) {
					if(gp.asteroids[i]!=null) {
						g2.drawImage(image, gp.asteroids[i].rotatedImage(), null);
					}
				}
		        
			}
			else if(direction=="aliens") {
				for(int i=0;i<gp.aliens.length;i++) {
					if(gp.aliens[i]!=null) {
						g2.drawImage(image, gp.aliens[i].rotatedImage(), null);
					}
				}
		        
			}
			else {
				g2.drawImage(image, screenX + AdjustX, screenY+ AdjustY, null);
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
