package Entity;


import java.awt.geom.AffineTransform;

import Game.GamePanel;

public class Projectile extends Entity{
	Entity user;
	public Projectile(GamePanel gp) {
		super(gp);
		
	}
	public void set(int Worldx, int Worldy, String direction, boolean alive, Entity user, double angle) {
		this.worldx = Worldx;
		this.worldy = Worldy;
		this.direction = direction;
		this.IsAlive=alive;
		this.life = this.maxLife;
		this.user = user;
		this.angle = angle;
	}
	
	public void update() {
		if(user==gp.player) {
			int Asteroidindex = gp.CollisionCheck.checkEntity(this, gp.asteroids);
			int Cometindex = gp.CollisionCheck.checkEntity(this, gp.comets);
			int AsteroidBeltindex = gp.CollisionCheck.checkEntity(this, gp.asteroidBelt);
			if(Asteroidindex!=999) {
				gp.player.damageAsteroid(Asteroidindex);
				IsAlive = false;
			}
			if(Cometindex!=999) {
				gp.player.damageComet(Cometindex);
				IsAlive = false;
			}
			if(AsteroidBeltindex!=999) {
				gp.player.damageAstroidBelt(AsteroidBeltindex);
				IsAlive = false;
			}
		}
		
		
		
		
			int newy= (int) (velocity * Math.cos(Math.toRadians(angle)));
			int newx= (int) (velocity * Math.sin(Math.toRadians(angle)));
			if(direction.equals("bullet1")) {
				worldy-=newy;worldx+=newx;}
			if(direction.equals("pellet")) {
				worldy-=newy;worldx+=newx;
			//case "player2":worldy-=newy;worldx+=newx; break;
		}
		
		
		life--;
		if(life <=0) {
			IsAlive = false;
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
				spriteNum=1;
			}
			spriteCounter=0;
		}
	}
	 public AffineTransform rotatedImage(int i) {
		 int screenX = worldx - gp.player.worldx + gp.player.screenx;
	     int screenY = worldy - gp.player.worldy + gp.player.screeny;
	     AffineTransform tr = new AffineTransform();
	        tr.translate(screenX, screenY);
	        tr.rotate(Math.toRadians(angle),gp.Tilesize/2,gp.Tilesize/2);
 	return tr;
 }
}
