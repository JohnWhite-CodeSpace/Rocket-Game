package Object;

import java.awt.Rectangle;

import Entity.Jupiter;
import Game.GamePanel;

public class OBJ_Jupiter extends Jupiter{
	GamePanel gp;
	public OBJ_Jupiter(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 110*gp.Tilesize;
		centerx=gp.Tilesize*250;
		centery=gp.Tilesize*250;
        name = "Jupiter";
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
		int i = 10;	
		Planet1 = setup("/planets/Jupiter1", gp.Tilesize*i, gp.Tilesize*i);	
		Planet2 = setup("/planets/Jupiter1", gp.Tilesize*i, gp.Tilesize*i);	
		Planet3 = setup("/planets/Jupiter1", gp.Tilesize*i, gp.Tilesize*i);	
		Planet4 = setup("/planets/Jupiter1", gp.Tilesize*i, gp.Tilesize*i);	
    }
		
    	public void SetAction() {
    			direction = "planet";
    		}
}
