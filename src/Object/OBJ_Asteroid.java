package Object;
import java.awt.Rectangle;
import java.util.Random;

import Entity.Asteroid;
import Game.GamePanel;

public class OBJ_Asteroid extends Asteroid {
		GamePanel gp;
		int i=0;
		int j=0;
		int turncounter;
    public OBJ_Asteroid(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "Asteroid";
        maxLife = 10;
        life = maxLife;
        solidArea = new Rectangle();
		solidArea.x = 4;
		solidArea.y = 4;
		solidArea.width = 40;
		solidArea.height = 40;
        IsAlive = false;
        Random random = new Random();
        Aangle = 45*random.nextInt(8);
        velocity=3;
        
        getImage();
    }

    public void getImage() {
        Entity1=(setup("/asteroids/Asteroidup", gp.Tilesize, gp.Tilesize));
        Entity2=(setup("/asteroids/Asteroidup2", gp.Tilesize, gp.Tilesize));
        Entity3=(setup("/asteroids/Asteroidup3", gp.Tilesize, gp.Tilesize));
        
        DeathImage1 = (setup("/asteroids/death1", gp.Tilesize, gp.Tilesize));
        DeathImage2 = (setup("/asteroids/death2", gp.Tilesize, gp.Tilesize));
        DeathImage3 = (setup("/asteroids/death3", gp.Tilesize, gp.Tilesize));
       
    }
    public void SetAction() {
    	if(ActionLockCounter==0) {
    		Random random = new Random();
            i = random.nextInt(2)+1;
            Random random2 = new Random();
            j = random2.nextInt(10)+1;
    	}
    	ActionLockCounter++;
        

        if (ActionLockCounter < 40+2*j) {
        	
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
    

}



