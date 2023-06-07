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
	public String ammoType;
	String info = null;
	int Choice = 0;
	int timespan;
	public int recharge;
	public int maxrecharge;
	public int rechargeCounter;
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		screenx = gp.screenWidth/2 - (gp.Tilesize/2);
		screeny = gp.screenHeight/2 - 3*gp.Tilesize -  (gp.Tilesize/2);
		this.gp =gp;
		this.keyH =keyH;
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 10;
		solidArea.width = 40;
		solidArea.height = 40;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		AsteroidCollision=0;

	}
	public void ChooseRocket() {
		if(keyH.choice ==1) {
			getPlayer1Image();
			HyperAcceleration = 0.15;
			Choice = 1;
			MaxSpeed=10;
			maxLife = 14;
			maxfuel=280;
			life=maxLife;
			fuel=maxfuel;
			
		}
		else if(keyH.choice==2) {
			getPlayer2Image();
			HyperAcceleration = 0.25;
			MaxSpeed=14;
			Choice=2;
			maxLife = 10;
			maxfuel = 200;
			life=maxLife;
			fuel=maxfuel;
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
		ChooseRocket();
		worldx=gp.Tilesize*400;
		worldy=gp.Tilesize*470;
		speed=5;
		ammoType="bullet1";
		direction = "player1";
		velocity =0;
		PlayerAngle = 0;
		Acceleration=0.1;
		AngularVelocity=3;
		timespan=20;
		maxrecharge=120;
		recharge=maxrecharge;
		rechargeCounter=0;
		fuelconsumption=0;
		Weapon = "Rocket";
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
			
		spriteCounter++;
		collisionOn=false;
		gp.CollisionCheck.CheckTile(this);
		boolean IsRefueling = gp.CollisionCheck.checkEntity(this, gp.spacestation);
		FuelRefill(IsRefueling);
		gp.CollisionCheck.PlanetPlayerCheck(this, gp.saturn);
		gp.CollisionCheck.PlanetPlayerCheck(this, gp.neptune);
		gp.CollisionCheck.PlanetPlayerCheck(this, gp.pluto);
		gp.CollisionCheck.PlanetPlayerCheck(this, gp.uranus);
		gp.CollisionCheck.PlanetPlayerCheck(this, gp.jupiter);
		gp.CollisionCheck.PlanetPlayerCheck(this, gp.mars);
		gp.CollisionCheck.PlanetPlayerCheck(this, gp.earth);
		gp.CollisionCheck.PlanetPlayerCheck(this, gp.venus);
		gp.CollisionCheck.PlanetPlayerCheck(this, gp.mercury);
		gp.CollisionCheck.PlanetPlayerCheck(this, gp.sol);
		int asteroidindex = gp.CollisionCheck.checkEntity(this, gp.asteroids);
		interactAsteroid(asteroidindex);
		int cometindex = gp.CollisionCheck.checkEntity(this, gp.comets);
		interactComet(cometindex);
		int asteroidbelt = gp.CollisionCheck.checkEntity(this, gp.asteroidBelt);
		interactAsteroidBelt(asteroidbelt);
		if(PlayerAngle>=360||PlayerAngle<=-360) {
			PlayerAngle=0;
		}
		double newy= velocity * Math.cos(Math.toRadians(PlayerAngle));
		double newx= velocity * Math.sin(Math.toRadians(PlayerAngle));
		
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
			rechargeCounter++;
			if(rechargeCounter>=120) {
				rein=false;
				rechargeCounter = 0;
				recharge=120;
				gp.ui.showMessage("Ready to fire!");
				if(Weapon.equals("Bullets")) {
					Weapon="Rocket";
				}else if(Weapon.equals("Rocket")) {
					Weapon="Bullets";
				}
			}
		}
		if(life==0) {
			gp.gameState=gp.GameOverState;
		}
		
	}

	public void interactAsteroid(int i) {
		// TODO Auto-generated method stub
		if(i!=999) {
			if(gp.player.invincible==false) {
			gp.ui.showMessage("Asteroid collision detected!");
			life-=1;
			gp.asteroids[i].life=0;
			gp.asteroids[i].dying=true;
			gp.playSE(2);
			gp.player.invincible = true;
			}
			if(life==0) {
				gp.gameState=gp.GameOverState;
			}
		}
	}
	public void interactComet(int i) {
		// TODO Auto-generated method stub
		if(i!=999) {
			if(gp.player.invincible==false) {
			gp.ui.showMessage("Comet collision detected!");
			life-=3;
			gp.comets[i].life=0;
			gp.comets[i].dying = true;
			gp.playSE(2);
			gp.player.invincible = true;
			}
			if(life==0) {
				gp.gameState=gp.GameOverState;
			}
		}
	}
	public void interactAsteroidBelt(int i) {
		// TODO Auto-generated method stub
		if(i!=999) {
			if(gp.player.invincible==false) {
			gp.ui.showMessage("Asteroid collision detected!");
			life-=1;
			gp.asteroidBelt[i].life=0;
			gp.asteroidBelt[i].dying = true;
			gp.playSE(2);
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
	public void damageComet(int i) {
		if(i!=999) {
			if(gp.comets[i].invincible==false) {
				gp.ui.showMessage("Comet destroyed!");
				if(ammoType.equals("bullet1")) {
					gp.comets[i].invincible=true;
					gp.comets[i].life =-10;
					
					if(gp.comets[i].life<=0) {
						gp.comets[i].dying = true;
						gp.playSE(2);
					}
				}
				if(ammoType.equals("pellet")) {
					gp.comets[i].life =-1;
					gp.comets[i].invincible=true;
					if(gp.comets[i].life<=0) {
						gp.comets[i].dying = true;
						gp.playSE(2);
					}
				}
			}
		}
	}
	public void damageAstroidBelt(int i) {
		if(i!=999) {
			if(gp.asteroidBelt[i].invincible==false) {
				gp.ui.showMessage("Asteroid destroyed!");
				if(ammoType.equals("bullet1")) {
					gp.asteroidBelt[i].invincible=true;
					gp.asteroidBelt[i].life =-10;
					
					if(gp.asteroidBelt[i].life<=0) {
						gp.asteroidBelt[i].dying = true;
						gp.playSE(2);
					}
				}
				if(ammoType.equals("pellet")) {
					gp.asteroidBelt[i].life =-1;
					gp.asteroidBelt[i].invincible=true;
					if(gp.asteroidBelt[i].life<=0) {
						gp.asteroidBelt[i].dying = true;
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
	public void FuelUsage() {
		if(fuelconsumption==300) {
			fuel-=1;
			fuelconsumption=0;
			
		}
	}
	public void FuelRefill(boolean IsRefueling) {
		if(IsRefueling==true) {
			fuel+=1;
		}
	}
	public void setDefaultPositions() {
		worldx=gp.Tilesize*400;
		worldy=gp.Tilesize*470;
		direction = "player1";
	}
	public void restoreDefaultValues() {
		velocity =0;
		PlayerAngle = 0;
		Weapon = "Rocket";
		fuelconsumption=0;
		invincible=false;
		ammoType="bullet1";
	}
	public void GameStatus() {
			if(gp.player.fuel>50) {
				gp.gameState=gp.WinState;
			}
			if(gp.player.fuel<=50){
				gp.gameState=gp.GameOverState;
			}
	}
	
}