package Object;


import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import Entity.Jupiter;
import Entity.Mars;
import Game.GamePanel;

public class OBJ_Mars extends Mars{
	GamePanel gp;
	public OBJ_Mars(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 100*gp.Tilesize;
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
		Xcircle=0;
        Ycircle=0;
        Radcircle=gp.Tilesize*4;
        Xcircle=0;
	    Ycircle=0;
		planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
        IsAlive = false;
        angle=4.5;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 4;	
		Planet1 = setup("/planets/Mars1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Planet2 = setup("/planets/Mars1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Planet3 = setup("/planets/Mars1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Planet4 = setup("/planets/Mars1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
    }
		
    	public void SetAction() {
    			direction = "planet";
    		}
}

