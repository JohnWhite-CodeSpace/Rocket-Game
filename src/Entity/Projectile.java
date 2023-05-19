package Entity;


import Game.GamePanel;

public class Projectile extends Entity{
	//protected GamePanel gp;
	Entity user;
	
	public Projectile(GamePanel gp) {
		super(gp);
		
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
		switch(direction) {
		case "up":worldy-=speed;break;
		case "down":worldy+=speed;break;
		case "left":worldx-=speed;break;
		case "right":worldx+=speed;break;
		case "upright":worldy-=speed; worldx+=speed;break;
		case "upleft": worldy-=speed; worldx-=speed;break;
		case "downright":worldy+=speed; worldx+=speed; break;
		case "downleft":worldy+=speed; worldx-=speed;break;
		case "fastup":worldy-=speed;break;
		case "fastdown":worldy+=speed;break;
		case "fastleft":worldx-=speed;break;
		case "fastright":worldx+=speed;break;
		case "fastupleft":worldy-=speed; worldx-=speed; break;
		case "fastdownleft":worldy+=speed; worldx-=speed;break;
		case "fastdownright":worldy+=speed; worldx+=speed;break;
		case "fastupright":worldy-=speed; worldx+=speed;break;
		
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
	
}
