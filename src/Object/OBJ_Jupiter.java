package Object;

import java.awt.geom.Ellipse2D;

import Entity.Jupiter;
import Game.GamePanel;

public class OBJ_Jupiter extends Jupiter{
	GamePanel gp;
	public int researchCount=0;
	public OBJ_Jupiter(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 250*gp.Tilesize;
		centerx=gp.Tilesize*500-7*gp.Tilesize;
		centery=gp.Tilesize*500-7*gp.Tilesize;
        name = "Jupiter";
        Radcircle=gp.Tilesize*14;
        Xcircle=Radcircle/2;
	    Ycircle=Radcircle/2;
	    AdjustX = (int) Radcircle/2;
	    AdjustY=(int) Radcircle/2;
		planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
		infoArea = new Ellipse2D.Double(Xcircle-Radcircle,Ycircle-Radcircle,Radcircle*2,Radcircle*2);
        IsAlive = false;
        angle=3;
        
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 14;	
		Entity1 = setup("/planets/Jupiter1", gp.Tilesize*i, gp.Tilesize*i);	
		Entity2 = setup("/planets/Jupiter2", gp.Tilesize*i, gp.Tilesize*i);	
		Entity3 = setup("/planets/Jupiter3", gp.Tilesize*i, gp.Tilesize*i);	
		Entity4 = setup("/planets/Jupiter4", gp.Tilesize*i, gp.Tilesize*i);	
    }
		
    public void SetAction() {
    	direction = "planet";
    }
}
