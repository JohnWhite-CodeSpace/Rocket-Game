package Entity;



import java.awt.geom.AffineTransform;

import Game.GamePanel;

public class SpaceStation extends Entity{
	Entity user;
	int counter=0;
	int SpSangle;
	public SpaceStation(GamePanel gp) {
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
		
			
			
	        	 
		int newX = (int) ((radius * Math.cos(SpsAngle)) + radius + centerx);
        int newY = (int) ((radius * Math.sin(SpsAngle)) + radius + centery);
       
       
		//gp.CollisionCheck.checkEntity(this, gp.player);
		if(collisionOn==false) {
			if(SpsAngle>=2*Math.PI) {
				SpsAngle=0;
			}
			switch(direction) {
				case "space_station":worldx=newX;
				worldy=newY;break;
				
				
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
		public AffineTransform rotatedImage() {
		    int screenX = worldx - gp.player.worldx + gp.player.screenx;
		    int screenY = worldy - gp.player.worldy + gp.player.screeny;

		    AffineTransform transform = new AffineTransform();
		    transform.translate(screenX, screenY);
		    transform.rotate(Math.toRadians(SpsAngle*59), gp.Tilesize*5/2, gp.Tilesize*5/2);

		    return transform;
	}

}