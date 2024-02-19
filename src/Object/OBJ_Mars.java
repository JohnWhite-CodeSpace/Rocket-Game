package Object;


import java.awt.geom.Ellipse2D;
import Entity.Mars;
import Game.GamePanel;

public class OBJ_Mars extends Mars{
	GamePanel gp;
	public OBJ_Mars(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 190*gp.Tilesize;
		centerx=gp.Tilesize*500-3*gp.Tilesize;
		centery=gp.Tilesize*500-3*gp.Tilesize;
        name = "Mars";
        Radcircle=gp.Tilesize*6;
        Xcircle=Radcircle/2;
	    Ycircle=Radcircle/2;
	    AdjustX = (int) Radcircle/2;
	    AdjustY=(int) Radcircle/2;
		planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
		infoArea = new Ellipse2D.Double(Xcircle-Radcircle,Ycircle-Radcircle,Radcircle*2,Radcircle*2);
        IsAlive = false;
        angle=4.5;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 6;	
		Entity1 = setup("/planets/Mars1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Entity2 = setup("/planets/Mars2", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Entity3 = setup("/planets/Mars3", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Entity4 = setup("/planets/Mars4", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
    }
		
    public void SetAction() {
    	direction = "planet";
    }
}

