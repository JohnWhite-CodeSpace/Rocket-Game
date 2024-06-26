package Entity;

import Game.GamePanel;

public class Asteroid_Belt extends Entity{

	Entity user;
	int counter=0;
	public Asteroid_Belt(GamePanel gp) {
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
		boolean contactPlayer = gp.CollisionCheck.playerCheck(this);
		if(this.type ==2 && contactPlayer==true) {
			if(gp.player.invincible==false) {
				gp.ui.showMessage("Asteroid collision detected!");
				gp.player.life-=1;
				gp.player.invincible=true;
			}
		}
	        	 
		int newX = (int) ((radius * Math.cos(angle)) + centerx);
        int newY = (int) ((radius * Math.sin(angle)) + centery);

		if(collisionOn==false) {
			angle+=0.0002;
			if(angle>=2*Math.PI) {
				angle=0;
			}
			switch(direction) {
				case "ABelt":worldx=newX;
				worldy=newY;break;
				}
				spriteCounter++;
			if(spriteCounter>30) {
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

}

