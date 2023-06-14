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
			Entity1 = setup("/planets/Sol1", gp.Tilesize*i, gp.Tilesize*i);
			Entity2 = setup("/planets/Sol2", gp.Tilesize*i, gp.Tilesize*i);
			Entity3 = setup("/planets/Sol3", gp.Tilesize*i, gp.Tilesize*i);
			Entity4 = setup("/planets/Sol4", gp.Tilesize*i, gp.Tilesize*i);
		
       
    }
    public void SetAction() {
    	direction = "planet";
    }
}

