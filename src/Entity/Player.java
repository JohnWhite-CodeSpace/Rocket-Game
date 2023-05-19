package Entity;


import java.awt.Rectangle;
import Game.GamePanel;
import Game.KeyHandler;
import Object.OBJ_Projectile;
public class Player extends Entity {
	
	KeyHandler keyH;
	public final int screenx;
	public final int screeny;
	public boolean speedy, chonker;
	public int rocketnum;
	int lifecounter = 0;
	int counter;
	String info = null;
	int Choice = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		screenx = gp.screenWidth/2 - (gp.Tilesize/2);
		screeny = gp.screenHeight/2 - 2*gp.Tilesize -  (gp.Tilesize/2);
		this.gp =gp;
		this.keyH =keyH;
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 8;
		solidArea.width = 40;
		solidArea.height = 40;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		AsteroidCollision=0;
		counter=0;
		setDefaultValues();
		
	}
	public void ChooseRocket(int choice) {
		if(choice ==1) {
			getPlayer1Image();
			HyperSpeed=6;
			Choice = 1;
		}
		else if(choice==2) {
			getPlayer2Image();
			HyperSpeed=30;
			Choice=2;
		}
	}
	public void getPlayer1Image() {

			up1 = setup("/player/chonker", gp.Tilesize, gp.Tilesize);
			down1 = setup("/player/downchonk", gp.Tilesize, gp.Tilesize);
			right1 = setup("/player/rightchonk", gp.Tilesize, gp.Tilesize);
			left1 = setup("/player/leftchonk", gp.Tilesize, gp.Tilesize);
			upleft1 = setup("/player/upleftchonk", gp.Tilesize, gp.Tilesize);
			upright1 = setup("/player/uprightchonk", gp.Tilesize, gp.Tilesize);
			downleft1 = setup("/player/downleftchonk", gp.Tilesize, gp.Tilesize);
			downright1 = setup("/player/downrightchonk", gp.Tilesize, gp.Tilesize);
			
			up2 = setup("/player/chonker2", gp.Tilesize, gp.Tilesize);
			down2 = setup("/player/downchonk2", gp.Tilesize, gp.Tilesize);
			right2 = setup("/player/rightchonk2", gp.Tilesize, gp.Tilesize);
			left2 = setup("/player/leftchonk2", gp.Tilesize, gp.Tilesize);
			upleft2 = setup("/player/upleftchonk2", gp.Tilesize, gp.Tilesize);
			upright2 = setup("/player/uprightchonk2", gp.Tilesize, gp.Tilesize);
			downleft2 = setup("/player/downleftchonk2", gp.Tilesize, gp.Tilesize);
			downright2 = setup("/player/downrightchonk2", gp.Tilesize, gp.Tilesize);
			
			up3 = setup("/player/chonker3", gp.Tilesize, gp.Tilesize);
			down3 = setup("/player/downchonk3", gp.Tilesize, gp.Tilesize);
			right3 = setup("/player/rightchonk3", gp.Tilesize, gp.Tilesize);
			left3 = setup("/player/leftchonk3", gp.Tilesize, gp.Tilesize);
			upleft3 = setup("/player/upleftchonk3", gp.Tilesize, gp.Tilesize);
			upright3 = setup("/player/uprightchonk3", gp.Tilesize, gp.Tilesize);
			downleft3 = setup("/player/downleftchonk3", gp.Tilesize, gp.Tilesize);
			downright3 = setup("/player/downrightchonk3", gp.Tilesize, gp.Tilesize);
			
		
	}
	public void getPlayer2Image() {
			up1 = setup("/player/speedy1", gp.Tilesize, gp.Tilesize);
			down1 = setup("/player/downspeed", gp.Tilesize, gp.Tilesize);
			right1 = setup("/player/rightspeed", gp.Tilesize, gp.Tilesize);
			left1 = setup("/player/leftspeed", gp.Tilesize, gp.Tilesize);
			upleft1 = setup("/player/upleftspeed", gp.Tilesize, gp.Tilesize);
			upright1 = setup("/player/uprightspeed", gp.Tilesize, gp.Tilesize);
			downleft1 = setup("/player/downleftspeed", gp.Tilesize, gp.Tilesize);
			downright1 = setup("/player/downrightspeed", gp.Tilesize, gp.Tilesize);
			
			up2 = setup("/player/speedy2", gp.Tilesize, gp.Tilesize);
			down2 = setup("/player/downspeed2", gp.Tilesize, gp.Tilesize);
			right2 = setup("/player/rightspeed2", gp.Tilesize, gp.Tilesize);
			left2 = setup("/player/leftspeed2", gp.Tilesize, gp.Tilesize);
			upleft2 = setup("/player/upleftspeed2", gp.Tilesize, gp.Tilesize);
			upright2 = setup("/player/uprightspeed2", gp.Tilesize, gp.Tilesize);
			downleft2 = setup("/player/downleftspeed2", gp.Tilesize, gp.Tilesize);
			downright2 = setup("/player/downrightspeed2", gp.Tilesize, gp.Tilesize);
			
			up3 = setup("/player/speedy3", gp.Tilesize, gp.Tilesize);
			down3 = setup("/player/downspeed3", gp.Tilesize, gp.Tilesize);
			right3 = setup("/player/rightspeed3", gp.Tilesize, gp.Tilesize);
			left3 = setup("/player/leftspeed3", gp.Tilesize, gp.Tilesize);
			upleft3 = setup("/player/upleftspeed3", gp.Tilesize, gp.Tilesize);
			upright3 = setup("/player/uprightspeed3", gp.Tilesize, gp.Tilesize);
			downleft3 = setup("/player/downleftspeed3", gp.Tilesize, gp.Tilesize);
			downright3 = setup("/player/downrightspeed3", gp.Tilesize, gp.Tilesize);
			
		}
	public void setDefaultValues() {
		worldx=gp.Tilesize*96;
		worldy=gp.Tilesize*72;
		speed=5;
		projectile = new OBJ_Projectile(gp);
		direction = "up";
		maxLife = 13;
		maxLife =life;
		//asteroid = new OBJ_Asteroid(gp);
	}
	public void update() {
		if(Choice!=0) {
		if(keyH.upPressed==true || keyH.downPressed==true || keyH.leftPressed==true || keyH.rightPressed==true) {
		if(keyH.upPressed==true || keyH.speedPressed==true) {
			direction = "up";
			
		}
		if(keyH.downPressed==true) {
			direction = "down";
			 
		}
		if(keyH.leftPressed==true) {
			direction = "left";
			
		}
		if(keyH.rightPressed==true) {
			direction = "right";
			
		}
		if(keyH.upPressed==true&& keyH.leftPressed==true) {
			direction="upleft";
		}
		if(keyH.upPressed==true && keyH.rightPressed==true) {
			direction="upright";
		}
		if(keyH.downPressed==true && keyH.leftPressed==true) {
			direction="downleft";
		}
		if(keyH.downPressed==true && keyH.rightPressed==true) {
			direction="downright";
		}
		if(keyH.speedPressed==true && keyH.upPressed==true) {
			direction = "fastup";
		}
		if(keyH.speedPressed==true && keyH.downPressed==true) {
			direction = "fastdown";
		}
		if(keyH.speedPressed==true && keyH.leftPressed==true) {
			direction = "fastleft";
		}
		if(keyH.speedPressed==true && keyH.rightPressed==true) {
			direction = "fastright";
		}
		if(keyH.speedPressed==true && keyH.upPressed==true && keyH.rightPressed==true) {
			direction = "fastupright";
		}
		if(keyH.speedPressed==true && keyH.downPressed==true && keyH.rightPressed==true) {
			direction = "fastdownright";
		}
		if(keyH.speedPressed==true && keyH.upPressed==true && keyH.leftPressed==true) {
			direction = "fastupleft";
		}
		if(keyH.speedPressed==true && keyH.downPressed==true && keyH.leftPressed==true) {
			direction = "fastdownleft";
		}
		spriteCounter++;
		lifecounter++;
		collisionOn=false;
		gp.CollisionCheck.CheckTile(this);
		int asteroidindex = gp.CollisionCheck.checkEntity(this, gp.asteroids);
		gp.CollisionCheck.checkEntity(this, gp.spacestation);
		gp.CollisionCheck.checkEntity(this, gp.saturn);
		gp.CollisionCheck.checkEntity(this, gp.neptune);
		gp.CollisionCheck.checkEntity(this, gp.pluto);
		gp.CollisionCheck.checkEntity(this, gp.uranus);
		interactAsteroid(asteroidindex);
		if(collisionOn==false) {
			switch(direction) {
				case "up":worldy-=speed;break;
				case "down":worldy+=speed;break;
				case "left":worldx-=speed;break;
				case "right":worldx+=speed;break;
				case "upright":worldy-=speed; worldx+=speed;break;
				case "upleft": worldy-=speed; worldx-=speed;break;
				case "downright":worldy+=speed; worldx+=speed; break;
				case "downleft":worldy+=speed; worldx-=speed;break;
				case "fastup":worldy-=HyperSpeed;break;
				case "fastdown":worldy+=HyperSpeed;break;
				case "fastleft":worldx-=HyperSpeed;break;
				case "fastright":worldx+=HyperSpeed;break;
				case "fastupleft":worldy-=HyperSpeed; worldx-=HyperSpeed; break;
				case "fastdownleft":worldy+=HyperSpeed; worldx-=HyperSpeed;break;
				case "fastdownright":worldy+=HyperSpeed; worldx+=HyperSpeed;break;
				case "fastupright":worldy-=HyperSpeed; worldx+=HyperSpeed;break;
			}
		}
	}
		if(gp.keyH.shotsFired==true && ShotAveilableCounter==10) {
			projectile.set(worldx, worldy, direction, true, this);
			
			
			gp.projectileList.add(projectile);
			
			gp.playSE(1);
			ShotAveilableCounter = 0;
		}
		if(ShotAveilableCounter<10) {
			ShotAveilableCounter++;
		}

	}
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
		
		if(invincible==true) {
			invincibleCounter++;
			if(invincibleCounter>240) {
				invincible=false;
				invincibleCounter = 0;
			}
		}
		
	}

	public void interactAsteroid(int i) {
		// TODO Auto-generated method stub
		if(i!=999) {
			gp.ui.showMessage("Asteroid collision detected");

		}
			
	}
	public void AsteroidCrash() {
		for(int i=0; i<gp.asteroids.length; i++) {
			if(this.solidArea.intersects(gp.asteroids[i].solidArea)) {
				if(invincible==false) {
				AsteroidCollision = AsteroidCollision+1;
				invincible = true;
				if(AsteroidCollision>12) {
					AsteroidCollision=0;
				}
				}
			}
		}
	}
	
}
