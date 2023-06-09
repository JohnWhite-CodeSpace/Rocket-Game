package Object;
import java.awt.Rectangle;
import java.util.Random;

import Entity.Comet;
import Game.GamePanel;

public class OBJ_Comet extends Comet {
		GamePanel gp;
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
        Aangle = random.nextDouble(360);
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
    		
    		ActionLockCounter++;
    		if(ActionLockCounter==240) {
    			
    			Random random = new Random();
    			int i = random.nextInt(240)+1;
    			Random random2 = new Random();
    			int j = random2.nextInt(10)+1;
    			
    			if(Aangle==0) {
    				
    				if(j>0 && j<=5) {
        				Aangle = 45;
        			}
        			if(j>5 && j<=10) {
        				Aangle = -45;
        			}
        			
    			}
    			else if(Aangle==180) {
    				if(j>0 && j<=5) {
        				Aangle = -135;
        			}
        			if(j>5 && j<=10) {
        				Aangle =135;
        			}
    			}
    			else if(Aangle==90) {
    				if(j>0 && j<=5) {
        				Aangle = 45;
        			}
    				if(j>5 && j<=10) {
        				Aangle =135;
        			}
    			}
    			else if(Aangle==-90) {
    				if(j>0 && j<=5) {
        				Aangle = -45;
        			}
    				if(j>5 && j<=10) {
        				Aangle =135;
        			}
    			}
    			else {
    				if(i<=30) {
        				Aangle=0;
        			}
        			if(i>30 && i<=60) {
        				Aangle=180;
        			}
        			if(i>60 && i<=90) {
        				Aangle=-90;
        			}
        			if(i>90 && i<=120) {
        				Aangle = 90;
        			}
    			}
    			
    			ActionLockCounter=0;
    		}
    	}

}



