package Object;

import java.awt.geom.Ellipse2D;

import Entity.Neptune;
import Game.GamePanel;

public class OBJ_Neptune extends Neptune{

	GamePanel gp;
	public OBJ_Neptune(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 420*gp.Tilesize;
		centerx=gp.Tilesize*500-5*gp.Tilesize;
		centery=gp.Tilesize*500-5*gp.Tilesize;
        name = "Neptune";
        Xcircle=Radcircle/2;
	    Ycircle=Radcircle/2;
	    AdjustX = (int) Radcircle/2;
	    AdjustY=(int) Radcircle/2;
        Radcircle=gp.Tilesize*10;
        planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
        infoArea = new Ellipse2D.Double(Xcircle-Radcircle,Ycircle-Radcircle,Radcircle*2,Radcircle*2);
		solidAreaDefaultX = (int) planetSolidArea.getX();
		solidAreaDefaultY = (int) planetSolidArea.getY();
        IsAlive = false;
        angle=5;
        
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		 	int i = 10;
			Entity1 = setup("/planets/Neptune1", gp.Tilesize*i, gp.Tilesize*i);
			Entity2 = setup("/planets/Neptune2", gp.Tilesize*i, gp.Tilesize*i);
			Entity3 = setup("/planets/Neptune3", gp.Tilesize*i, gp.Tilesize*i);
			Entity4 = setup("/planets/Neptune4", gp.Tilesize*i, gp.Tilesize*i);
    }
		
    	public void SetAction() {	
    		direction="planet";

    		}

}
