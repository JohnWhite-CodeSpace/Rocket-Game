package Object;

import java.awt.Rectangle;

import Entity.Neptune;
import Game.GamePanel;

public class OBJ_Neptune extends Neptune{

	GamePanel gp;
	public OBJ_Neptune(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 210*gp.Tilesize;
		centerx=gp.Tilesize*250;
		centery=gp.Tilesize*250;
        name = "Neptune";
        speed=1;
        maxLife = 2000;
        life = maxLife;
        solidArea = new Rectangle();
		solidArea.x = 76;
		solidArea.y = 76;
		solidArea.width = 350;
		solidArea.height = 350;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
        IsAlive = false;
        angle=0;
        
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		 	int i = 8;
			Planet1 = setup("/planets/Neptune1", gp.Tilesize*i, gp.Tilesize*i);
			Planet2 = setup("/planets/Neptune2", gp.Tilesize*i, gp.Tilesize*i);
			Planet3 = setup("/planets/Neptune3", gp.Tilesize*i, gp.Tilesize*i);
			Planet4 = setup("/planets/Neptune4", gp.Tilesize*i, gp.Tilesize*i);
    }
		
    	public void SetAction() {	
    		direction="planet";

    		}

}
