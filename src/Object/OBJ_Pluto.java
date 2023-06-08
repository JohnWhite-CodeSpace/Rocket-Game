package Object;


import java.awt.geom.Ellipse2D;

import Entity.Pluto;
import Game.GamePanel;

public class OBJ_Pluto extends Pluto{
	GamePanel gp;
	public OBJ_Pluto(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 245*gp.Tilesize;
		centerx=gp.Tilesize*250;
		centery=gp.Tilesize*250;
        name = "Pluto";
		Xcircle=0;
	    Ycircle=0;
	    Radcircle=gp.Tilesize*3;
	    planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
	    infoArea = new Ellipse2D.Double(Xcircle-Radcircle,Ycircle-Radcircle,Radcircle*2,Radcircle*2);
        IsAlive = false;
        angle=0.1;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 3;
			Planet1 = setup("/planets/Pluto1", gp.Tilesize*i, gp.Tilesize*i);
			Planet2 = setup("/planets/Pluto2", gp.Tilesize*i, gp.Tilesize*i);
			Planet3 = setup("/planets/Pluto3", gp.Tilesize*i, gp.Tilesize*i);
			Planet4 = setup("/planets/Pluto4", gp.Tilesize*i, gp.Tilesize*i);
    }
    public void SetAction() {
    	direction = "planet";
    }
}
