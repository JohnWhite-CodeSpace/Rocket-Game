package Object;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import Entity.Pluto;
import Game.GamePanel;

public class OBJ_Pluto extends Pluto{
	GamePanel gp;
	public OBJ_Pluto(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 240*gp.Tilesize;
		centerx=gp.Tilesize*250;
		centery=gp.Tilesize*250;
        name = "Pluto";
        speed=1;
        maxLife = 2000;
        life = maxLife;
        angle=0;
       // worldx=gp.Tilesize*96;
		//worldy=gp.Tilesize*72;
        solidArea = new Rectangle();
		solidArea.x = 40;
		solidArea.y = 40;
		solidArea.width = 164;
		solidArea.height = 164;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		Xcircle=0;
	    Ycircle=0;
	    Radcircle=gp.Tilesize*4;
	    planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
        IsAlive = false;
        
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 4;
			Planet1 = setup("/planets/Pluto1", gp.Tilesize*i, gp.Tilesize*i);
			Planet2 = setup("/planets/Pluto2", gp.Tilesize*i, gp.Tilesize*i);
			Planet3 = setup("/planets/Pluto3", gp.Tilesize*i, gp.Tilesize*i);
			Planet4 = setup("/planets/Pluto4", gp.Tilesize*i, gp.Tilesize*i);
		
       
    }
    	public void SetAction() {
    		direction = "planet";
    	}
}
