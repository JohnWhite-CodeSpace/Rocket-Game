package Entity;

import Game.GamePanel;

public class Sol extends Entity{

	Entity user;
	int counter=0;
	public Sol(GamePanel gp) {
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
	        	 collision=false;
	        	 if(collision==false) {
	        		 switch(direction) {
	        		 case "planet":
	        			 worldx=centerx;
		        		 worldy=centery;
		        		break;
	        		 }
	        		 
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

