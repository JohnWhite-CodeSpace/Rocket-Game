package Object;


import java.awt.geom.Ellipse2D;
import Entity.Mercury;
import Game.GamePanel;

public class OBJ_Mercury extends Mercury{
	GamePanel gp;
	public OBJ_Mercury(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 48*gp.Tilesize;
		centerx=gp.Tilesize*250;
		centery=gp.Tilesize*250;
        name = "Mercury";
        Radcircle=gp.Tilesize*3+gp.Tilesize/2;
        Xcircle=0;
	    Ycircle=0;
		planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
		infoArea = new Ellipse2D.Double(Xcircle-Radcircle,Ycircle-Radcircle,Radcircle*2,Radcircle*2);
        IsAlive = false;
        angle=4.5;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 3;	
		Entity1 = setup("/planets/Mercury1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Entity2 = setup("/planets/Mercury1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Entity3 = setup("/planets/Mercury1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Entity4 = setup("/planets/Mercury1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
    }
		
    	public void SetAction() {
    			direction = "planet";
    		}
}


