package Entity;

import java.awt.geom.AffineTransform;

import Game.GamePanel;


public class Asteroid extends Entity{
	
	Entity user;
	public Asteroid(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
	}

	
	public void set(int Worldx, int Worldy, String direction, boolean alive, Entity user) {
		this.worldx = Worldx;
		this.worldy = Worldy;
		this.direction = direction;
		this.IsAlive=alive;
		this.life = this.maxLife;
		this.user = user;
	}
	public void update() {
		collisionOn=false;
		gp.CollisionCheck.CheckTile(this);
		boolean contactPlayer = gp.CollisionCheck.playerCheck(this);
		if(this.type ==2 && contactPlayer==true) {
			if(gp.player.invincible==false) {
				gp.ui.showMessage("Asteroid collision detected!");
				gp.player.life-=1;
				gp.player.invincible=true;
			}
		}
		double newy= velocity * Math.cos(Math.toRadians(Aangle));
		double newx= velocity * Math.sin(Math.toRadians(Aangle));
		if(collisionOn==false) {
//			if(Aangle>=360||Aangle<=-360) {
//				Aangle=0;
//			}
			switch(direction) {
				case "asteroid":worldy-=(int) newy; worldx += (int) newx;break;
			}
		}
			if(invincible==true) {
				
				invincibleCounter++;
				System.out.println(invincibleCounter);
				if(invincibleCounter>30) {
					invincible=false;
					invincibleCounter = 0;
				}
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
	public AffineTransform rotatedImage() {
	    int screenX = worldx - gp.player.worldx + gp.player.screenx;
	    int screenY = worldy - gp.player.worldy + gp.player.screeny;

	    AffineTransform transform = new AffineTransform();
	    transform.translate(screenX, screenY);
	    transform.rotate(Math.toRadians(Aangle), gp.Tilesize/2, gp.Tilesize/2);

	    return transform;
}
		
}

