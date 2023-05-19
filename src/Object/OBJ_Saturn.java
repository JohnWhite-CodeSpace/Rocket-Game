package Object;

import java.awt.Rectangle;
import Entity.Saturn;
import Game.GamePanel;

public class OBJ_Saturn extends Saturn{
	GamePanel gp;
	public OBJ_Saturn(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 58*gp.Tilesize;
		centerx=gp.Tilesize*75;
		centery=gp.Tilesize*30;
        name = "Saturn";
        speed=1;
        maxLife = 2000;
        life = maxLife;
        solidArea = new Rectangle();
		solidArea.x = 248;
		solidArea.y = 148;
		solidArea.width = 192;
		solidArea.height =192;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
        IsAlive = false;
        
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		 	int i=15;
		 	int j=10;
				up1 = setup("/planets/Saturn1", gp.Tilesize*i, gp.Tilesize*j);
				down1=up1 = setup("/planets/Saturn1", gp.Tilesize*i, gp.Tilesize*j);
				left1=up1 = setup("/planets/Saturn1", gp.Tilesize*i, gp.Tilesize*j);
				right1=up1 = setup("/planets/Saturn1", gp.Tilesize*i, gp.Tilesize*j);
				upleft1=setup("/planets/Saturn1", gp.Tilesize*i, gp.Tilesize*j);
				upright1=setup("/planets/Saturn1", gp.Tilesize*i, gp.Tilesize*j);
				downright1=setup("/planets/Saturn1", gp.Tilesize*i, gp.Tilesize*j);
				downleft1=setup("/planets/Saturn1", gp.Tilesize*i, gp.Tilesize*j);
				
				up2 = setup("/planets/Saturn2", gp.Tilesize*i, gp.Tilesize*j);
				down2= setup("/planets/Saturn2", gp.Tilesize*i, gp.Tilesize*j);
				left2= setup("/planets/Saturn2", gp.Tilesize*i, gp.Tilesize*j);
				right2= setup("/planets/Saturn2", gp.Tilesize*i, gp.Tilesize*j);
				upleft2= setup("/planets/Saturn2", gp.Tilesize*i, gp.Tilesize*j);
				upright2= setup("/planets/Saturn2", gp.Tilesize*i, gp.Tilesize*j);
				downright2= setup("/planets/Saturn2", gp.Tilesize*i, gp.Tilesize*j);
				downleft2= setup("/planets/Saturn2", gp.Tilesize*i, gp.Tilesize*j);
				
				up3 = setup("/planets/Saturn3", gp.Tilesize*i, gp.Tilesize*j);
				down3=setup("/planets/Saturn3", gp.Tilesize*i, gp.Tilesize*j);
				left3=setup("/planets/Saturn3", gp.Tilesize*i, gp.Tilesize*j);
				right3=setup("/planets/Saturn3", gp.Tilesize*i, gp.Tilesize*j);
				upleft3=setup("/planets/Saturn3", gp.Tilesize*i, gp.Tilesize*j);
				upright3=setup("/planets/Saturn3", gp.Tilesize*i, gp.Tilesize*j);
				downright3=setup("/planets/Saturn3", gp.Tilesize*i, gp.Tilesize*j);
				downleft3=setup("/planets/Saturn3", gp.Tilesize*i, gp.Tilesize*j);
				
				up4 = setup("/planets/Saturn4", gp.Tilesize*i, gp.Tilesize*j);
				down4= setup("/planets/Saturn4", gp.Tilesize*i, gp.Tilesize*j);
				left4=setup("/planets/Saturn4", gp.Tilesize*i, gp.Tilesize*j);
				right4=setup("/planets/Saturn4", gp.Tilesize*i, gp.Tilesize*j);
				upleft4=setup("/planets/Saturn4", gp.Tilesize*i, gp.Tilesize*j);
				upright4=setup("/planets/Saturn4", gp.Tilesize*i, gp.Tilesize*j);
				downright4=setup("/planets/Saturn4", gp.Tilesize*i, gp.Tilesize*j);
				downleft4=setup("/planets/Saturn4", gp.Tilesize*i, gp.Tilesize*j);
				
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
