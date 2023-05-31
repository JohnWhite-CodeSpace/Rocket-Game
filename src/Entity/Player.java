package Entity;


import java.awt.Rectangle;
import Game.GamePanel;
import Game.KeyHandler;
import Object.OBJ_Projectile;
public class Player extends Entity {
	
	public KeyHandler keyH;
	public final int screenx;
	public final int screeny;
	public boolean speedy, chonker;
	public int rocketnum;
	public String ammoType;
	int lifecounter = 0;
	int counter;
	String info = null;
	int Choice = 0;
	int timespan;
	public int recharge;
	public int maxrecharge;
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
			HyperAcceleration = 0.15;
			HyperSpeed=6;
			Choice = 1;
			MaxSpeed=10;
			maxLife = 14;
		}
		else if(choice==2) {
			getPlayer2Image();
			HyperSpeed=16;
			HyperAcceleration = 0.25;
			MaxSpeed=14;
			Choice=2;
			maxLife = 10;
		}
	}
	public void getPlayer1Image() {

			Player1 = setup("/player/chonker", gp.Tilesize, gp.Tilesize);			
			Player2 = setup("/player/chonker2", gp.Tilesize, gp.Tilesize);
			Player3 = setup("/player/chonker3", gp.Tilesize, gp.Tilesize);

			
		
	}
	public void getPlayer2Image() {
			Player1 = setup("/player/speedy1", gp.Tilesize, gp.Tilesize);
			Player2 = setup("/player/speedy2", gp.Tilesize, gp.Tilesize);
			Player3 = setup("/player/speedy3", gp.Tilesize, gp.Tilesize);

			
		}
	public void setDefaultValues() {
		worldx=gp.Tilesize*96;
		worldy=gp.Tilesize*72;
		speed=5;
		maxLife=1;
		ammoType="bullet1";
		direction = "player1";
		life=maxLife;
		velocity =0;
		PlayerAngle = 0;
		Acceleration=0.1;
		AngularVelocity=3;
		timespan=20;
		maxrecharge=120;
		recharge=maxrecharge;
		//asteroid = new OBJ_Asteroid(gp);
	}
	public void update() {
		if(Choice!=0) {
		if(keyH.upPressed==true || keyH.downPressed==true || keyH.leftPressed==true || keyH.rightPressed==true|| keyH.speedPressed==true) {
		
			if(keyH.speedPressed==true) {
				Acceleration=HyperAcceleration;
			}
			if(keyH.speedPressed==false) {
				Acceleration=0.1;
			}
			
		previousX = worldx;
		previousY = worldy;
		spriteCounter++;
		lifecounter++;
		collisionOn=false;
		gp.CollisionCheck.CheckTile(this);
		gp.CollisionCheck.checkEntity(this, gp.spacestation);
		gp.CollisionCheck.checkEntity(this, gp.saturn);
		gp.CollisionCheck.checkEntity(this, gp.neptune);
		gp.CollisionCheck.checkEntity(this, gp.pluto);
		gp.CollisionCheck.checkEntity(this, gp.uranus);
		int asteroidindex = gp.CollisionCheck.checkEntity(this, gp.asteroids);
		interactAsteroid(asteroidindex);
		if(PlayerAngle>=360||PlayerAngle<=-360) {
			PlayerAngle=0;
		}
		//System.out.println(PlayerAngle);
		double newy= velocity * Math.cos(Math.toRadians(PlayerAngle));
		double newx= velocity * Math.sin(Math.toRadians(PlayerAngle));
		//System.out.println(worldx + ", "+ worldy);
		
		
		if(collisionOn==false) {
			switch(direction) {
			case "player1":worldy-=newy;worldx+=newx;break;
			}
		}
	}
		
			if(gp.keyH.shotsFired==true && ShotAveilableCounter==timespan && recharge==120) {
				projectile = new OBJ_Projectile(gp);
				projectile.set(worldx, worldy, ammoType, true, this, PlayerAngle);
				
				
				gp.projectileList.add(projectile);
				
				gp.playSE(1);
				ShotAveilableCounter = 0;
			}
			if(ShotAveilableCounter<timespan) {
				ShotAveilableCounter++;
			}
			if(ShotAveilableCounter>timespan) {
				ShotAveilableCounter--;
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
		if(rein==true) {
			rechargeLoading();
			invincibleCounter++;
			if(invincibleCounter>120) {
				rein=false;
				invincibleCounter = 0;
				recharge=120;
				gp.ui.showMessage("Ready to fire!");
			}
		}
		
	}

	public void interactAsteroid(int i) {
		// TODO Auto-generated method stub
		if(i!=999) {
			if(gp.player.invincible==false) {
			gp.ui.showMessage("Asteroid collision detected!");
			life-=1;
			gp.player.invincible = true;
			}
			if(life==0) {
				gp.gameState=gp.GameOverState;
			}
		}
	}
	public void damageAsteroid(int i) {
		if(i!=999) {
			if(gp.asteroids[i].invincible==false) {
				gp.ui.showMessage("Asteroid destroyed!");
				if(ammoType.equals("bullet1")) {
					gp.asteroids[i].invincible=true;
					gp.asteroids[i].life =-10;
					
					if(gp.asteroids[i].life<=0) {
						gp.asteroids[i].dying = true;
						gp.playSE(2);
					}
				}
				if(ammoType.equals("pellet")) {
					gp.asteroids[i].life =-1;
					gp.asteroids[i].invincible=true;
					if(gp.asteroids[i].life<=0) {
						gp.asteroids[i].dying = true;
						gp.playSE(2);
					}
				}
				
				
				
			}
		}
	}
	public void rechargeLoading() {
		if(rein==true) {
			if(ammoType.equals("pellet")) {
				timespan=5;
				recharge--;
				if(recharge<0) {
					recharge=0;
				}
			}
			if(ammoType.equals("bullet1")) {
				timespan=20;
				recharge--;
				if(recharge<0) {
					recharge=0;
				}
			}
		}
	}
	
}