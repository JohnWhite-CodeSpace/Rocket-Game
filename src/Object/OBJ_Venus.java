package Object;


import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import Entity.Venus;
import Game.GamePanel;

public class OBJ_Venus extends Venus{
	GamePanel gp;
	public OBJ_Venus(GamePanel gp) {
		super(gp);
		this.gp = gp;
		radius = 60*gp.Tilesize;
		centerx=gp.Tilesize*250;
		centery=gp.Tilesize*250;
        name = "Earth";
        speed=1;
        maxLife = 2000;
        life = maxLife;
        solidArea = new Rectangle();
		solidArea.x = 76;
		solidArea.y = 76;
		solidArea.width = 350;
		solidArea.height = 350;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		Xcircle=0;
        Ycircle=0;
        Radcircle=gp.Tilesize*4+gp.Tilesize/2;
        Xcircle=0;
	    Ycircle=0;
		planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
        IsAlive = false;
        angle=1;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		int i = 4;	
		Planet1 = setup("/planets/Venus1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Planet2 = setup("/planets/Venus1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Planet3 = setup("/planets/Venus1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
		Planet4 = setup("/planets/Venus1", gp.Tilesize*i+gp.Tilesize/2, gp.Tilesize*i+gp.Tilesize/2);	
    }
		
    	public void SetAction() {
    			direction = "planet";
    		}
}



