package Object;

import java.awt.geom.Ellipse2D;
import Entity.Venus;
import Game.GamePanel;

public class OBJ_Venus extends Venus{
	GamePanel gp;
	public OBJ_Venus(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 60*gp.Tilesize;
		centerx=gp.Tilesize*250;
		centery=gp.Tilesize*250;
        name = "Venus";
        Radcircle=gp.Tilesize*4+gp.Tilesize/2;
        Xcircle=0;
	    Ycircle=0;
		planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
		infoArea = new Ellipse2D.Double(Xcircle-Radcircle,Ycircle-Radcircle,Radcircle*2,Radcircle*2);
        IsAlive = false;
        angle=1;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 4;	
		Entity1 = setup("/planets/Venus1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Entity2 = setup("/planets/Venus1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Entity3 = setup("/planets/Venus1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Entity4 = setup("/planets/Venus1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
    }
		
    	public void SetAction() {
    			direction = "planet";
    		}
}



