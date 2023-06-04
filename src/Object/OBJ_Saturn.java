package Object;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import Entity.Saturn;
import Game.GamePanel;

public class OBJ_Saturn extends Saturn{
	GamePanel gp;
	public OBJ_Saturn(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 145*gp.Tilesize;
		centerx=gp.Tilesize*250;
		centery=gp.Tilesize*250;
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
		Radcircle=gp.Tilesize*4;
		Xcircle=gp.Tilesize*5;
	    Ycircle=gp.Tilesize*2+gp.Tilesize/2;
		planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
        IsAlive = false;
        angle=2;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		 	int i=14;
		 	int j=9;
				Planet1 = setup("/planets/Saturn1", gp.Tilesize*i, gp.Tilesize*j);
				Planet2 = setup("/planets/Saturn2", gp.Tilesize*i, gp.Tilesize*j);
				Planet3 = setup("/planets/Saturn3", gp.Tilesize*i, gp.Tilesize*j);
				Planet4 = setup("/planets/Saturn4", gp.Tilesize*i, gp.Tilesize*j);
				
    }
		
    public void SetAction() {
    	direction="planet";
    }
}
