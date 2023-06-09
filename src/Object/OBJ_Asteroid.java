package Object;
import java.awt.Rectangle;
import java.util.Random;

import Entity.Asteroid;
import Game.GamePanel;

public class OBJ_Asteroid extends Asteroid {
		GamePanel gp;
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
        Aangle = random.nextDouble(360);
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



