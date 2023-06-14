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
		infoArea = new Ellipse2D.Double(Xcircle-Radcircle,Ycircle-Radcircle,Radcircle*2,Radcircle*2);
        IsAlive = false;
        angle=5;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 5;	
		Entity1 = setup("/planets/Earth1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Entity2 = setup("/planets/Earth2", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Entity3 = setup("/planets/Earth3", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Entity4 = setup("/planets/Earth4", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
    }
		
    public void SetAction() {
    	direction = "planet";
    }
}


