package Object;

import java.awt.geom.Ellipse2D;
import Entity.Sol;
import Game.GamePanel;

public class OBJ_Sol extends Sol{
	GamePanel gp;
	public OBJ_Sol(GamePanel gp) {
		super(gp);
		this.gp = gp;
		centerx=gp.Tilesize*500-9*gp.Tilesize;
		centery=gp.Tilesize*500-9*gp.Tilesize;
        name = "Sol";
		Xcircle=0;
	    Ycircle=0;
	    Radcircle=gp.Tilesize*16;
	    planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
        IsAlive = false;
        
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 18;
			Entity1 = setup("/planets/sun", gp.Tilesize*i, gp.Tilesize*i);
			Entity2 = setup("/planets/sun2", gp.Tilesize*i, gp.Tilesize*i);
			Entity3 = setup("/planets/sun3", gp.Tilesize*i, gp.Tilesize*i);
			Entity4 = setup("/planets/sun4", gp.Tilesize*i, gp.Tilesize*i);
		
       
    }
    public void SetAction() {
    	direction = "planet";
    }
}

