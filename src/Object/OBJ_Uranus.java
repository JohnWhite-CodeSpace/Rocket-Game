package Object;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import Entity.Uranus;
import Game.GamePanel;

public class OBJ_Uranus extends Uranus{

	GamePanel gp;
	public OBJ_Uranus(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 195*gp.Tilesize;
		centerx=gp.Tilesize*250;
		centery=gp.Tilesize*250;
        name = "Uranus";
        speed=1;
        maxLife = 2000;
        life = maxLife;
        solidArea = new Rectangle();
		solidArea.x = 140;
		solidArea.y = 150;
		solidArea.width = 200;
		solidArea.height = 200;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		Radcircle=gp.Tilesize*5;
		Xcircle=gp.Tilesize*2;
	    Ycircle=gp.Tilesize*2;
		planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
        IsAlive = false;
        angle=0.4;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		 	int i =9;
				Planet1 = setup("/planets/Uranus1", gp.Tilesize*i, gp.Tilesize*i);
				Planet2 = setup("/planets/Uranus2", gp.Tilesize*i, gp.Tilesize*i);
				Planet3 = setup("/planets/Uranus3", gp.Tilesize*i, gp.Tilesize*i);
				Planet4 = setup("/planets/Uranus4", gp.Tilesize*i, gp.Tilesize*i);
       
    }
		
    	public void SetAction() {
    		direction="planet";
    		}


}
