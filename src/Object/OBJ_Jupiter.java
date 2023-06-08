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
		radius = 120*gp.Tilesize;
		centerx=gp.Tilesize*250;
		centery=gp.Tilesize*250;
        name = "Jupiter";
        Radcircle=gp.Tilesize*10;
        Xcircle=0;
	    Ycircle=0;
		planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
		infoArea = new Ellipse2D.Double(Xcircle-Radcircle,Ycircle-Radcircle,Radcircle*2,Radcircle*2);
        IsAlive = false;
        angle=3;
        planettoken=1;
        
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 10;	
		Planet1 = setup("/planets/Jupiter1", gp.Tilesize*i, gp.Tilesize*i);	
		Planet2 = setup("/planets/Jupiter2", gp.Tilesize*i, gp.Tilesize*i);	
		Planet3 = setup("/planets/Jupiter3", gp.Tilesize*i, gp.Tilesize*i);	
		Planet4 = setup("/planets/Jupiter4", gp.Tilesize*i, gp.Tilesize*i);	
    }
		
    public void SetAction() {
    	direction = "planet";
    }
}
