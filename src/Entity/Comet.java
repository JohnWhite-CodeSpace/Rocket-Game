package Entity;

import Game.GamePanel;


public class Comet extends Entity{
	
	Entity user;
	public Comet(GamePanel gp) {
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
				gp.ui.showMessage("Comet collision detected!");
				gp.player.life-=1;
				gp.player.invincible=true;
			}
		}
		
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
		
	}
//	public void interactAsteroid() {
//		// TODO Auto-generated method stub
//		
//			gp.ui.showMessage("Asteroid collision detected");
//		
//	}
		
}


