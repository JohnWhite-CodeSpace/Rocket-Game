package Object;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import Entity.AlienSpaceship;
import Game.GamePanel;

public class OBJ_AlienSpaceship extends AlienSpaceship{
	GamePanel gp;
	int i=0;
	int j=0;
	int turncounter;
public OBJ_AlienSpaceship(GamePanel gp) {
    super(gp);
    this.gp = gp;
    type = 2;
    name = "Asteroid";
    maxLife = 10;
    life = maxLife;
    solidArea = new Rectangle();
	solidArea.x = 4;
	solidArea.y = 4;
	solidArea.width = 55;
	solidArea.height = 55;
    IsAlive = false;
    Xcircle=0;
    Ycircle=0;
    Radcircle=gp.Tilesize*3;
    AGRarea = new Ellipse2D.Double(Xcircle-Radcircle,Ycircle-Radcircle,Radcircle*2,Radcircle*2);
    Random random = new Random();
    Alienangle = 36*random.nextInt(10);
    velocity=7;
    getImage();
}

public void getImage() {
    Entity1=(setup("/objects/alienspaceship1", gp.Tilesize, gp.Tilesize));
    Entity2=(setup("/objects/alienspaceship2", gp.Tilesize, gp.Tilesize));
    Entity3=(setup("/objects/alienspaceship3", gp.Tilesize, gp.Tilesize));
    
    DeathImage1 = (setup("/asteroids/death1", gp.Tilesize, gp.Tilesize));
    DeathImage2 = (setup("/asteroids/death2", gp.Tilesize, gp.Tilesize));
    DeathImage3 = (setup("/asteroids/death3", gp.Tilesize, gp.Tilesize));
   
}
	public void SetAction() {
		if(AgressionOn==false) {
			if(ActionLockCounter==0) {
				Random random = new Random();
		        i = random.nextInt(2)+1;
		        Random random2 = new Random();
		        j = random2.nextInt(10)+1;
			}
			ActionLockCounter++;
		    
		
		    if (ActionLockCounter < 40+4*j) {
		    	
				if(j>0 && j<=5) {
					Aangle+=i;
				}
				if(j>5&&j<=10) {
					Aangle-=i;
				}
		    }
		    if(ActionLockCounter==240) {
				ActionLockCounter=0;
			}
		}
		if(AgressionOn==true) {
			Alienangle = (int) (gp.player.PlayerAngle-180);
			
//			if(Math.abs(gp.player.worldx+3*gp.Tilesize)>=worldx && 
//					Math.abs(gp.player.worldy+3*gp.Tilesize)>=worldy) {
//				velocity=0;
//			}
//			else {
//				velocity=7;
//			}
		}
	}
}
