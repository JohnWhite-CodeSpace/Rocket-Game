package Object;


import java.awt.geom.Ellipse2D;
import Entity.Mercury;
import Game.GamePanel;

public class OBJ_Mercury extends Mercury{
	GamePanel gp;
	public OBJ_Mercury(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 80*gp.Tilesize;
		centerx=gp.Tilesize*500-(5/2)*gp.Tilesize;
		centery=gp.Tilesize*500-(5/2)*gp.Tilesize;
        name = "Mercury";
        Radcircle=5*gp.Tilesize;
        //Change Xcircle, Ycircle params and add AdjustX and AdjustY to each planet. Mercury is ready.
        Xcircle=Radcircle/2;
	    Ycircle=Radcircle/2;
	    planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
	    AdjustX = (int) Radcircle/2;
	    AdjustY=(int) Radcircle/2;
		infoArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle*2,Radcircle*2);
        IsAlive = false;
        angle=4.5;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 5;	
		Entity1 = setup("/planets/Mercury1", gp.Tilesize*i, gp.Tilesize*i);	
		Entity2 = setup("/planets/Mercury2", gp.Tilesize*i, gp.Tilesize*i);	
		Entity3 = setup("/planets/Mercury3", gp.Tilesize*i, gp.Tilesize*i);	
		Entity4 = setup("/planets/Mercury4", gp.Tilesize*i, gp.Tilesize*i);	
    }
		
    	public void SetAction() {
    			direction = "planet";
    		}
}


