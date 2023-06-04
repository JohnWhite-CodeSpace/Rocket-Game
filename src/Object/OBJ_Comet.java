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
        speed=5;
        maxLife = 10;
        life = maxLife;
        solidArea = new Rectangle();
		solidArea.x = 4;
		solidArea.y = 4;
		solidArea.width = 60;
		solidArea.height = 60;
        IsAlive = false;
        getImage();
    }

    public void getImage() {
        up1=(setup("/asteroids/Cometup1", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        upright1=(setup("/asteroids/uprightComet1", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        upleft1 =(setup("/asteroids/upleftComet1", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        down1 =(setup("/asteroids/Cometdown1", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        downright1=(setup("/asteroids/downrightComet1", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        downleft1 = (setup("/asteroids/downleftComet1", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        right1 = (setup("/asteroids/Cometright1", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        left1 = (setup("/asteroids/Cometleft1", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        
        up2=(setup("/asteroids/Cometup2", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        upright2=(setup("/asteroids/uprightComet2",gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        upleft2 =(setup("/asteroids/upleftComet2", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        down2 =(setup("/asteroids/Cometdown2", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        downright2=(setup("/asteroids/downrightComet2", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        downleft2 = (setup("/asteroids/downleftComet2", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        right2 = (setup("/asteroids/Cometright2", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        left2 = (setup("/asteroids/Cometleft2", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        
        up3=(setup("/asteroids/Cometup3", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        upright3=(setup("/asteroids/uprightComet3", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        upleft3 =(setup("/asteroids/upleftComet3", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        down3 =(setup("/asteroids/Cometdown3", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        downright3=(setup("/asteroids/downrightComet3", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        downleft3 = (setup("/asteroids/downleftComet3", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        right3 = (setup("/asteroids/Cometright3", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        left3 = (setup("/asteroids/Cometleft3", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        
        DeathImage1 = (setup("/asteroids/Cometdeath1", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        DeathImage2 = (setup("/asteroids/Cometdeath2", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
        DeathImage3 = (setup("/asteroids/Cometdeath3", gp.Tilesize+gp.Tilesize/2, gp.Tilesize+gp.Tilesize/2));
       
    }
    	public void SetAction() {
    		
    		ActionLockCounter++;
    		if(ActionLockCounter==300) {
    			
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



