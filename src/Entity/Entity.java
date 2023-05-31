package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import Game.GamePanel;
import Object.OBJ_PlayerLife;
import Utility.UtilityTool;

public class Entity {

	public int worldx, worldy;
	public int speed;
	public int HyperSpeed;
	public double velocity;
	public double PlayerAngle;
	public double Acceleration, HyperAcceleration;
	public double AngularVelocity;
	public double newx, newy;
	public int previousX, previousY;
	public int MaxSpeed;
	GamePanel gp;
	public BufferedImage up1, down1, left1, right1, upleft1, upright1, downleft1, downright1, up2, down2, left2, right2, upleft2, upright2, downleft2, downright2,
	up3, down3,right3,left3,upleft3,upright3,downright3,downleft3, up4, down4, left4, right4, upright4, upleft4, downright4, downleft4;
	public BufferedImage lifeimage1,lifeimage2,recharge1,recharge2;
	public BufferedImage Player1,Player2,Player3, Bullet1, Bullet2, Bullet3,Bullet4, DeathImage1, DeathImage2, DeathImage3;
	public String name;
	public String Life;
	public int type;
	public int AsteroidCollision;
	public String direction = "up";
	public boolean collision = false;
	public boolean invincible = false;
	public boolean rein = false;
	public int reinCounter=0;
	public int invincibleCounter = 0;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int maxLife;
	public int life;
	public boolean IsAlive = true;
	public boolean dying = false;
	public Rectangle solidArea;
	public boolean collisionOn = false;
	public int DyingCounter;
	public int ShotAveilableCounter = 0;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public Projectile projectile, projectile2;
	public Asteroid asteroid;
	public SpaceStation spacestation;
	public Pluto pluto;
	public Neptune neptune;
	public Uranus uranus;
	public Saturn saturn;
	public OBJ_PlayerLife lifebar;
	public int ActionLockCounter = 0;
	public double angle =0;
	public int radius;
	public int centerx, centery;
	public Entity(GamePanel gp) {
		this.gp=gp;
	}
	 
	public void set() {}
	    /*private void resetAlphaTo100(Graphics2D graphics2D) {
	        UtilityTool.changeAlpha(graphics2D, 1);
	    }*/
		public void SetAction(){};
	    public void update(){
	    	
	    	collisionOn=false;
	    	gp.CollisionCheck.CheckTile(this);
	    	gp.CollisionCheck.checkObject(this, false);
	    	
	    	
	    };
	    
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
			case "up", "fastup":
				if(spriteNum==1) {
					image = up1;
				}
				if(spriteNum==2) {
					image=up2;
				}
				if(spriteNum==3) {
					image=up3;
				}
				if(spriteNum==4) {
					image=up4;
				}
				break;
			case "down", "fastdown":
				if(spriteNum==1) {
					image = down1;
				}
				if(spriteNum==2) {
					image = down2;
				}
				if(spriteNum==3) {
					image=down3;
				}
				if(spriteNum==4) {
					image=down4;
				}
				break;
			case "left", "fastleft":
				if(spriteNum==1) {
					image=left1;
				}
				if(spriteNum==2) {
					image = left2;
				}
				if(spriteNum==3) {
					image=left3;
				}
				if(spriteNum==4) {
					image=left4;
				}
				break;
			case "right", "fastright":
				if(spriteNum==1) {
					image = right1;
				}
				if(spriteNum==2) {
					image = right2;
				}
				if(spriteNum==3) {
					image=right3;
				}
				if(spriteNum==4) {
					image=right4;
				}
				break;
			case "upleft", "fastupleft":
				if(spriteNum==1) {
					image = upleft1;
				}
				if(spriteNum==2) {
					image = upleft2;
				}
				if(spriteNum==3) {
					image=upleft3;
				}
				if(spriteNum==4) {
					image=upleft4;
				}
				break;
			case "upright", "fastupright":
				if(spriteNum==1) {
					image = upright1;
				}
				if(spriteNum==2) {
					image = upright2;
				}
				if(spriteNum==3) {
					image=upright3;
				}
				if(spriteNum==4) {
					image=upright4;
				}
				break;
			case "downleft", "fastdownleft":
				if(spriteNum==1) {
					image = downleft1;
				}
				if(spriteNum==2) {
					image = downleft2;
				}
				if(spriteNum==3) {
					image=downleft3;
				}
				if(spriteNum==4) {
					image=downleft4;
				}
				break;
			case "downright","fastdownright":
				if(spriteNum==1) {
					image = downright1;
				}
				if(spriteNum==2) {
					image = downright2;
				}
				if(spriteNum==3) {
					image=downright3;
				}
				if(spriteNum==4) {
					image=downright4;
				}
				break;
			case "player1":
				if(spriteNum==1) {
					image = Player1;
				}
				if(spriteNum==2) {
					image = Player2;
				}
				if(spriteNum==3) {
					image=Player3;
				}
				break;
			case "bullet1":
				if(spriteNum==1) {
					image = Bullet1;
				}
				if(spriteNum==2) {
					image = Bullet2;
				}
				if(spriteNum==3) {
					image=Bullet3;
				}
				break;
			case "pellet":
				if(spriteNum==1) {
					image = Bullet4;
				}
				if(spriteNum==2) {
					image = Bullet4;
				}
				if(spriteNum==3) {
					image=Bullet4;
				}
				break;
			}
			
			
			if(image!=null) {
			if(image.equals(Player1)||image.equals(Player2)||image.equals(Player3)) {
				AffineTransform transform = new AffineTransform();
		        transform.translate(screenX, screenY);
		        transform.rotate(Math.toRadians(gp.player.PlayerAngle),gp.Tilesize/2,gp.Tilesize/2);
		        g2.drawImage(image, transform, null);
			
			}
			else if(image.equals(Bullet1)||image.equals(Bullet2)||image.equals(Bullet3)||image.equals(Bullet4)) {
				for(int i=0; i<gp.projectileList.size(); i++)
		        g2.drawImage(image, ((Projectile) gp.projectileList.get(i)).rotatedImage(i), null);
			
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
