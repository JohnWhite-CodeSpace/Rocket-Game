package Entity;

import Game.GamePanel;

public class Neptune extends Entity {

	Entity user;
	int counter=0;
	public Neptune(GamePanel gp) {
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
		//gp.CollisionCheck.playerCheck(this); 
		gp.CollisionCheck.playerPlanetCheck(this);
		int newX = (int) ((radius * Math.cos(angle))  + centerx);
        int newY = (int) ((radius * Math.sin(angle))  + centery);
        System.out.println(worldx/gp.Tilesize+", "+worldy/gp.Tilesize);
		if(collisionOn==false) {
			angle+=0.0001;
			if(angle>=2*Math.PI) {
				angle=0;
			}
			switch(direction) {
				case "planet":worldx=newX;
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
