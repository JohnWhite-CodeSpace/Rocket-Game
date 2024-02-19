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
		radius = 40*gp.Tilesize;
		centerx=gp.Tilesize*500;
		centery=gp.Tilesize*500;
        name = "SpaceStation";
        solidArea = new Rectangle();
		solidArea.x = 44;
		solidArea.y = 44;
		solidArea.width = 120;
		solidArea.height = 120;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		SpsAngle=0;
		AngularVelocity=0.002;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
        int i=5;
				Entity1 = setup("/space_station/spaceup1", gp.Tilesize*i, gp.Tilesize*i);
				Entity2 = setup("/space_station/spaceup2", gp.Tilesize*i, gp.Tilesize*i);
				Entity3 = setup("/space_station/spaceup3", gp.Tilesize*i, gp.Tilesize*i);
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
    	direction="space_station";
    	if(collisionOn==false) {
    		SpsAngle-=AngularVelocity;
    	}
    }
	
}
