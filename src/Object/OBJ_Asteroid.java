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
        name = "Asteroid";
        speed=3;
        maxLife = 2000;
        life = maxLife;
        solidArea = new Rectangle();
		solidArea.x = 4;
		solidArea.y = 4;
		solidArea.width = 40;
		solidArea.height = 40;
        
        IsAlive = false;
        
        getImage();
    }

    public void getImage() {
        up1=(setup("/asteroids/Asteroidup", gp.Tilesize, gp.Tilesize));
        upright1=(setup("/asteroids/uprightAsteroid", gp.Tilesize, gp.Tilesize));
        upleft1 =(setup("/asteroids/upleftAsteroid", gp.Tilesize, gp.Tilesize));
        down1 =(setup("/asteroids/Asteroiddown", gp.Tilesize, gp.Tilesize));
        downright1=(setup("/asteroids/downrightAsteroid", gp.Tilesize, gp.Tilesize));
        downleft1 = (setup("/asteroids/downleftAsteroid", gp.Tilesize, gp.Tilesize));
        right1 = (setup("/asteroids/Asteroidright", gp.Tilesize, gp.Tilesize));
        left1 = (setup("/asteroids/Asteroidleft", gp.Tilesize, gp.Tilesize));
        
        up2=(setup("/asteroids/Asteroidup2", gp.Tilesize, gp.Tilesize));
        upright2=(setup("/asteroids/uprightAsteroid", gp.Tilesize, gp.Tilesize));
        upleft2 =(setup("/asteroids/upleftAsteroid2", gp.Tilesize, gp.Tilesize));
        down2 =(setup("/asteroids/Asteroiddown2", gp.Tilesize, gp.Tilesize));
        downright2=(setup("/asteroids/downrightAsteroid2", gp.Tilesize, gp.Tilesize));
        downleft2 = (setup("/asteroids/downleftAsteroid2", gp.Tilesize, gp.Tilesize));
        right2 = (setup("/asteroids/Asteroidright2", gp.Tilesize, gp.Tilesize));
        left2 = (setup("/asteroids/Asteroidleft2", gp.Tilesize, gp.Tilesize));
        
        up3=(setup("/asteroids/Asteroidup3", gp.Tilesize, gp.Tilesize));
        upright3=(setup("/asteroids/uprightAsteroid3", gp.Tilesize, gp.Tilesize));
        upleft3 =(setup("/asteroids/upleftAsteroid3", gp.Tilesize, gp.Tilesize));
        down3 =(setup("/asteroids/Asteroiddown3", gp.Tilesize, gp.Tilesize));
        downright3=(setup("/asteroids/downrightAsteroid3", gp.Tilesize, gp.Tilesize));
        downleft3 = (setup("/asteroids/downleftAsteroid3", gp.Tilesize, gp.Tilesize));
        right3 = (setup("/asteroids/Asteroidright3", gp.Tilesize, gp.Tilesize));
        left3 = (setup("/asteroids/Asteroidleft3", gp.Tilesize, gp.Tilesize));
        
        
       
    }
    	public void SetAction() {
    		
    		ActionLockCounter++;
    		if(ActionLockCounter==240) {
    			
    			Random random = new Random();
    			int i = random.nextInt(240)+1;
		
    			if(i<=30) {
    				direction = "up";
    			}
    			if(i>30 && i<=60) {
    				direction = "down";
    			}
    			if(i>60 && i<=90) {
    				direction = "left";
    			}
    			if(i>90 && i<=120) {
    				direction = "right";
    			}
    			if(i>120 && i<=150) {
    				direction = "upright";
    			}
    			if(i>150 && i<=180) {
    				direction = "upleft";
    			}
    			if(i>180 && i<=210) {
    				direction = "downleft";
    			}
    			if(i>210 && i<=230) {
    				direction = "downright";
    			}
    			ActionLockCounter=0;
    			}
    		}
    

}



