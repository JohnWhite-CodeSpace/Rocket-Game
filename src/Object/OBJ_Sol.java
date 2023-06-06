package Object;

import java.awt.geom.Ellipse2D;
import Entity.Sol;
import Game.GamePanel;

public class OBJ_Sol extends Sol{
	GamePanel gp;
	public OBJ_Sol(GamePanel gp) {
		super(gp);
		this.gp = gp;
		centerx=gp.Tilesize*243+gp.Tilesize/2;
		centery=gp.Tilesize*243+gp.Tilesize/2;
        name = "Sol";
		Xcircle=0;
	    Ycircle=0;
	    Radcircle=gp.Tilesize*13;
	    planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
        IsAlive = false;
        
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 13;
			Planet1 = setup("/planets/Sol1", gp.Tilesize*i, gp.Tilesize*i);
			Planet2 = setup("/planets/Sol1", gp.Tilesize*i, gp.Tilesize*i);
			Planet3 = setup("/planets/Sol1", gp.Tilesize*i, gp.Tilesize*i);
			Planet4 = setup("/planets/Sol1", gp.Tilesize*i, gp.Tilesize*i);
		
       
    }
    public void SetAction() {
    	direction = "planet";
    }
}

