package Object;

import java.awt.Rectangle;


import Entity.Pluto;
import Game.GamePanel;

public class OBJ_Pluto extends Pluto{
	GamePanel gp;
	public OBJ_Pluto(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 140*gp.Tilesize;
		centerx=gp.Tilesize*75;
		centery=gp.Tilesize*30;
        name = "Pluto";
        speed=1;
        maxLife = 2000;
        life = maxLife;
       // worldx=gp.Tilesize*96;
		//worldy=gp.Tilesize*72;
        solidArea = new Rectangle();
		solidArea.x = 40;
		solidArea.y = 40;
		solidArea.width = 164;
		solidArea.height = 164;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
        IsAlive = false;
        
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 5;
				up1 = setup("/planets/Pluto1", gp.Tilesize*i, gp.Tilesize*i);
				down1=setup("/planets/Pluto1", gp.Tilesize*i, gp.Tilesize*i);
				left1=setup("/planets/Pluto1", gp.Tilesize*i, gp.Tilesize*i);
				right1=setup("/planets/Pluto1", gp.Tilesize*i, gp.Tilesize*i);
				upleft1=setup("/planets/Pluto1", gp.Tilesize*i, gp.Tilesize*i);
				upright1=setup("/planets/Pluto1", gp.Tilesize*i, gp.Tilesize*i);
				downright1=setup("/planets/Pluto1", gp.Tilesize*i, gp.Tilesize*i);
				downleft1=setup("/planets/Pluto1", gp.Tilesize*i, gp.Tilesize*i);
				
				up2 = setup("/planets/Pluto2", gp.Tilesize*i, gp.Tilesize*i);
				down2= setup("/planets/Pluto2", gp.Tilesize*i, gp.Tilesize*i);
				left2= setup("/planets/Pluto2", gp.Tilesize*i, gp.Tilesize*i);
				right2= setup("/planets/Pluto2", gp.Tilesize*i, gp.Tilesize*i);
				upleft2= setup("/planets/Pluto2", gp.Tilesize*i, gp.Tilesize*i);
				upright2= setup("/planets/Pluto2", gp.Tilesize*i, gp.Tilesize*i);
				downright2= setup("/planets/Pluto2", gp.Tilesize*i, gp.Tilesize*i);
				downleft2= setup("/planets/Pluto2", gp.Tilesize*i, gp.Tilesize*i);
				
				up3 = setup("/planets/Pluto3", gp.Tilesize*i, gp.Tilesize*i);
				down3=setup("/planets/Pluto3", gp.Tilesize*i, gp.Tilesize*i);
				left3=setup("/planets/Pluto3", gp.Tilesize*i, gp.Tilesize*i);
				right3=setup("/planets/Pluto3", gp.Tilesize*i, gp.Tilesize*i);
				upleft3=setup("/planets/Pluto3", gp.Tilesize*i, gp.Tilesize*i);
				upright3=setup("/planets/Pluto3", gp.Tilesize*i, gp.Tilesize*i);
				downright3=setup("/planets/Pluto3", gp.Tilesize*i, gp.Tilesize*i);
				downleft3=setup("/planets/Pluto3", gp.Tilesize*i, gp.Tilesize*i);
				
				up4 = setup("/planets/Pluto4", gp.Tilesize*i, gp.Tilesize*i);
				down4= setup("/planets/Pluto4", gp.Tilesize*i, gp.Tilesize*i);
				left4=setup("/planets/Pluto4", gp.Tilesize*i, gp.Tilesize*i);
				right4=setup("/planets/Pluto4", gp.Tilesize*i, gp.Tilesize*i);
				upleft4=setup("/planets/Pluto4", gp.Tilesize*i, gp.Tilesize*i);
				upright4=setup("/planets/Pluto4", gp.Tilesize*i, gp.Tilesize*i);
				downright4=setup("/planets/Pluto4", gp.Tilesize*i, gp.Tilesize*i);
				downleft4=setup("/planets/Pluto4", gp.Tilesize*i, gp.Tilesize*i);
		
       
    }
    	public void SetAction() {
    		
    			if(angle>=11*Math.PI/8 && angle<=13*Math.PI/8) {
    				direction = "right";
    			}
    			if(angle>3*Math.PI/8 && angle<5*Math.PI/8) {
    				direction = "left";
    			}
    			if(angle>=7*Math.PI/8 && angle<9*Math.PI/8) {
    				direction = "up";
    			}
    			if(angle>=15*Math.PI/8 && angle<2*Math.PI || angle>=0&&angle<Math.PI/8) {
    				direction = "down";
    			}
    			if(angle>13*Math.PI/8&&angle<15*Math.PI/8) {
    				direction = "downright";
    			}
    			if(angle>9*Math.PI/8 && angle<11*Math.PI/8) {
    				direction = "upright";
    			}
    			if(angle>5*Math.PI/8 && angle<7*Math.PI/8) {
    				direction = "upleft";
    			}
    			if(angle>Math.PI/8 && angle<3*Math.PI/8) {
    				direction = "downleft";
    			}
    			
    			
    		}
}
