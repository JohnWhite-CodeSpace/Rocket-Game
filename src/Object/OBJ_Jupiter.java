package Object;

import java.awt.Rectangle;

import Entity.Jupiter;
import Game.GamePanel;

public class OBJ_Jupiter extends Jupiter{
	GamePanel gp;
	public OBJ_Jupiter(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 50*gp.Tilesize;
		centerx=gp.Tilesize*75;
		centery=gp.Tilesize*30;
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
        
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 15;	
		Planet1 = setup("/planets/Jupiter1", gp.Tilesize*i, gp.Tilesize*i);	
		Planet2 = setup("/planets/Jupiter1", gp.Tilesize*i, gp.Tilesize*i);	
		Planet3 = setup("/planets/Jupiter1", gp.Tilesize*i, gp.Tilesize*i);	
		Planet4 = setup("/planets/Jupiter1", gp.Tilesize*i, gp.Tilesize*i);	
    }
		
    	public void SetAction() {
    			direction = "planet";
    		}
}
