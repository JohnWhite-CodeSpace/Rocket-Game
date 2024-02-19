package Object;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import Entity.Uranus;
import Game.GamePanel;

public class OBJ_Uranus extends Uranus{

	GamePanel gp;
	public OBJ_Uranus(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 390*gp.Tilesize;
		centerx=gp.Tilesize*500-6*gp.Tilesize;
		centery=gp.Tilesize*500-6*gp.Tilesize;
        name = "Uranus";
		Radcircle=gp.Tilesize*12;
		Xcircle=Radcircle/2;
	    Ycircle=Radcircle/2;
	    AdjustX = (int) Radcircle/2;
	    AdjustY=(int) Radcircle/2;
		planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
		infoArea = new Ellipse2D.Double(Xcircle-Radcircle,Ycircle-Radcircle,Radcircle*2,Radcircle*2);
        IsAlive = false;
        angle=0.4;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		 	int i =12;
				Entity1 = setup("/planets/Uranus1", gp.Tilesize*i, gp.Tilesize*i);
				Entity2 = setup("/planets/Uranus2", gp.Tilesize*i, gp.Tilesize*i);
				Entity3 = setup("/planets/Uranus3", gp.Tilesize*i, gp.Tilesize*i);
				Entity4 = setup("/planets/Uranus4", gp.Tilesize*i, gp.Tilesize*i);
       
    }
		
    	public void SetAction() {
    		direction="planet";
    		}


}
