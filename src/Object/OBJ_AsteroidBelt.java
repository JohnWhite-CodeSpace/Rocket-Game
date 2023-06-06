package Object;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import Entity.Asteroid_Belt;
import Game.GamePanel;

public class OBJ_AsteroidBelt extends Asteroid_Belt{
	GamePanel gp;
	public OBJ_AsteroidBelt(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		centerx=gp.Tilesize*250;
		centery=gp.Tilesize*250;
        name = "Asteroid_Belt";
        speed=1;
        maxLife = 1;
        life = maxLife;
        solidArea = new Rectangle();
		solidArea.x = 4;
		solidArea.y = 4;
		solidArea.width = 40;
		solidArea.height = 40;
        IsAlive = false;
        Random random = new Random();
        double RDangle = random.nextDouble(7);
        int RDradius = random.nextInt(250);
        angle=RDangle;
        radius = 100*gp.Tilesize+RDradius;
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {	
		Random random = new Random();
        int i = random.nextInt(2)+1;
		AsteroidBelt = setup("/asteroids/Asteroidbelt", gp.Tilesize*i, gp.Tilesize*i);	
//		Planet2 = setup("/asteroids/Asteroidbelt", gp.Tilesize*i, gp.Tilesize*i);	
//		Planet3 = setup("/asteroids/Asteroidbelt", gp.Tilesize*i, gp.Tilesize*i);	
//		Planet4 = setup("/asteroids/Asteroidbelt", gp.Tilesize*i, gp.Tilesize*i);	
		DeathImage1 = setup("/asteroids/AsteroidBeltDying1", gp.Tilesize*i, gp.Tilesize*i);
        DeathImage2 = setup("/asteroids/AsteroidBeltDying2", gp.Tilesize*i, gp.Tilesize*i);
        DeathImage3 = setup("/asteroids/AsteroidBeltDying3", gp.Tilesize*i, gp.Tilesize*i);
    }
		
    	public void SetAction() {
    			direction = "ABelt";
//    			Random random = new Random();
//    			int RDradius = random.nextInt(40)+1;
//    			radius = radius+RDradius;
    		}
}

