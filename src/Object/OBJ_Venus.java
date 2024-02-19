package Object;

import java.awt.geom.Ellipse2D;
import Entity.Venus;
import Game.GamePanel;

public class OBJ_Venus extends Venus{
	GamePanel gp;
	public OBJ_Venus(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 120*gp.Tilesize;
		centerx=gp.Tilesize*500-(7*gp.Tilesize)/2;
		centery=gp.Tilesize*500-(7*gp.Tilesize)/2;
        name = "Venus";
        Radcircle=gp.Tilesize*7;
        Xcircle=Radcircle/2;
	    Ycircle=Radcircle/2;
	    AdjustX = (int) Radcircle/2;
	    AdjustY=(int) Radcircle/2;
		planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
		infoArea = new Ellipse2D.Double(Xcircle-Radcircle,Ycircle-Radcircle,Radcircle*2,Radcircle*2);
        IsAlive = false;
        angle=1;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 7;	
		Entity1 = setup("/planets/Venus1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Entity2 = setup("/planets/Venus2", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Entity3 = setup("/planets/Venus3", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Entity4 = setup("/planets/Venus4", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
    }
		
    public void SetAction() {
    			direction = "planet";
    }
}



