package Object;

import java.awt.geom.Ellipse2D;
import Entity.Earth;
import Game.GamePanel;

public class OBJ_Earth extends Earth{
	GamePanel gp;
	public OBJ_Earth(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 78*gp.Tilesize;
		centerx=gp.Tilesize*250;
		centery=gp.Tilesize*250;
        name = "Earth";
        Radcircle=gp.Tilesize*5+gp.Tilesize/2;
        Xcircle=0;
	    Ycircle=0;
		planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
        IsAlive = false;
        angle=5;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 5;	
		Planet1 = setup("/planets/Earth1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Planet2 = setup("/planets/Earth1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Planet3 = setup("/planets/Earth1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Planet4 = setup("/planets/Earth1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
    }
		
    public void SetAction() {
    	direction = "planet";
    }
}

