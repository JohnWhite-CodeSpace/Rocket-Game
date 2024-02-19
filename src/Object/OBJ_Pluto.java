package Object;


import java.awt.geom.Ellipse2D;

import Entity.Pluto;
import Game.GamePanel;

public class OBJ_Pluto extends Pluto{
	GamePanel gp;
	public OBJ_Pluto(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 480*gp.Tilesize;
		centerx=gp.Tilesize*500-2*gp.Tilesize;
		centery=gp.Tilesize*500-2*gp.Tilesize;
        name = "Pluto";
        Xcircle=Radcircle/2;
	    Ycircle=Radcircle/2;
	    AdjustX = (int) Radcircle/2;
	    AdjustY=(int) Radcircle/2;
	    Radcircle=gp.Tilesize*4;
	    planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
	    infoArea = new Ellipse2D.Double(Xcircle-Radcircle,Ycircle-Radcircle,Radcircle*2,Radcircle*2);
        IsAlive = false;
        angle=0.1;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 4;
			Entity1 = setup("/planets/Pluto1", gp.Tilesize*i, gp.Tilesize*i);
			Entity2 = setup("/planets/Pluto2", gp.Tilesize*i, gp.Tilesize*i);
			Entity3 = setup("/planets/Pluto3", gp.Tilesize*i, gp.Tilesize*i);
			Entity4 = setup("/planets/Pluto4", gp.Tilesize*i, gp.Tilesize*i);
    }
    public void SetAction() {
    	direction = "planet";
    }
}
