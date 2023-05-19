package Object;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.SpaceStation;
import Game.GamePanel;

public class OBJ_SpaceStation extends SpaceStation{
		GamePanel gp;
	public OBJ_SpaceStation(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 28*gp.Tilesize;
		centerx=gp.Tilesize*76;
		centery=gp.Tilesize*56;
        name = "SpaceStation";
        speed=1;
        maxLife = 2000;
        life = maxLife;
        solidArea = new Rectangle();
		solidArea.x = 44;
		solidArea.y = 44;
		solidArea.width = 120;
		solidArea.height = 120;
		System.out.println(solidArea.getBounds());
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
        IsAlive = false;
        
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
        int i=5;
				up1 = setup("/space_station/spaceup1", gp.Tilesize*i, gp.Tilesize*i);
				down1 = setup("/space_station/spacedown1", gp.Tilesize*i, gp.Tilesize*i);
				right1 = setup("/space_station/spaceright1", gp.Tilesize*i, gp.Tilesize*i);
				left1 = setup("/space_station/spaceleft1", gp.Tilesize*i, gp.Tilesize*i);
				upleft1 = setup("/space_station/spaceupleft1", gp.Tilesize*i, gp.Tilesize*i);
				upright1 = setup("/space_station/spaceupright1", gp.Tilesize*i, gp.Tilesize*i);
				downleft1 = setup("/space_station/spacedownleft1", gp.Tilesize*i, gp.Tilesize*i);
				downright1 = setup("/space_station/spacedownright1", gp.Tilesize*i, gp.Tilesize*i);
				
				up2 = setup("/space_station/spaceup2", gp.Tilesize*i, gp.Tilesize*i);
				down2 = setup("/space_station/spacedown2", gp.Tilesize*i, gp.Tilesize*i);
				right2 = setup("/space_station/spaceright2", gp.Tilesize*i, gp.Tilesize*i);
				left2 = setup("/space_station/spaceleft2", gp.Tilesize*i, gp.Tilesize*i);
				upleft2 = setup("/space_station/spaceupleft2", gp.Tilesize*i, gp.Tilesize*i);
				upright2 = setup("/space_station/spaceupright2", gp.Tilesize*i, gp.Tilesize*i);
				downleft2 = setup("/space_station/spacedownleft2", gp.Tilesize*i, gp.Tilesize*i);
				downright2 = setup("/space_station/spacedownright2", gp.Tilesize*i, gp.Tilesize*i);
				
				up3 = setup("/space_station/spaceup3", gp.Tilesize*i, gp.Tilesize*i);
				down3 = setup("/space_station/spacedown3", gp.Tilesize*i, gp.Tilesize*i);
				right3 = setup("/space_station/spaceright3", gp.Tilesize*i, gp.Tilesize*i);
				left3 = setup("/space_station/spaceleft3", gp.Tilesize*i, gp.Tilesize*i);
				upleft3 = setup("/space_station/spaceupleft3", gp.Tilesize*i, gp.Tilesize*i);
				upright3 = setup("/space_station/spaceupright3", gp.Tilesize*i, gp.Tilesize*i);
				downleft3 = setup("/space_station/spacedownleft3", gp.Tilesize*i, gp.Tilesize*i);
				downright3 = setup("/space_station/spacedownright3", gp.Tilesize*i, gp.Tilesize*i);
				
			
		
       
    }
		public BufferedImage setup(String filename) {
			BufferedImage image = null;
			try{
				image = ImageIO.read(getClass().getResourceAsStream("/space_station/" + filename + ".png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			
			return image;
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
