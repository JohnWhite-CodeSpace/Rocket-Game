package Entity;

import Game.GamePanel;

public class Pluto extends Entity{

	Entity user;
	int counter=0;
	public Pluto(GamePanel gp) {
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
		gp.CollisionCheck.playerCheck(this);
	        	 
		int newX = (int) ((radius * Math.cos(angle)) + radius + centerx);
        int newY = (int) ((radius * Math.sin(angle)) + radius + centery);
       
       
		//gp.CollisionCheck.checkEntity(this, gp.player);
		if(collisionOn==false) {
			angle+=0.0001;
			if(angle>=2*Math.PI) {
				angle=0;
			}
			switch(direction) {
				case "up":
				worldx=newX;
				worldy=newY;break;
				case "down":worldx=newX;
				worldy=newY;break;
				case "left":worldx=newX;
				worldy=newY;break;
				case "right":worldx=newX;
				worldy=newY;break;
				case "upright":worldx=newX;
				worldy=newY;break;
				case "upleft": worldx=newX;
				worldy=newY;break;
				case "downright":worldx=newX;
				worldy=newY;break;
				case "downleft":worldx=newX;
				worldy=newY;break;
				
				}
			//System.out.println(angle);
        
				spriteCounter++;
			if(spriteCounter>30) {
				if(spriteNum==1) {
					spriteNum=2;
				}
				else if(spriteNum==2) {
					spriteNum=3;
				}
				else if(spriteNum==3) {
					spriteNum=4;
				}
				else if(spriteNum==4) {
					spriteNum=1;
				}
				spriteCounter=0;
			}
		}
	}

}
