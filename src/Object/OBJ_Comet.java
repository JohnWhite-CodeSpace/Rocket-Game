package Object;
import java.awt.Rectangle;
import java.util.Random;

import Entity.Comet;
import Game.GamePanel;

public class OBJ_Comet extends Comet {
		GamePanel gp;
		int turncounter;
		int i=0;
		int j=0;
    public OBJ_Comet(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "Asteroid";
        maxLife = 10;
        life = maxLife;
        solidArea = new Rectangle();
		solidArea.x = 4;
		solidArea.y = 4;
		solidArea.width = 60;
		solidArea.height = 60;
        IsAlive = false;
        Random random = new Random();
        Aangle = random.nextInt(360);
        velocity=10;
        getImage();
    }

    public void getImage() {
        Entity1=(setup("/asteroids/Cometup1", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));    
        Entity2=(setup("/asteroids/Cometup2", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        Entity3=(setup("/asteroids/Cometup3", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        
        DeathImage1 = (setup("/asteroids/Cometdeath1", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        DeathImage2 = (setup("/asteroids/Cometdeath2", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        DeathImage3 = (setup("/asteroids/Cometdeath3", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
       
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



