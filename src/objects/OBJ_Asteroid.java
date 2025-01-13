package objects;

import entity.Asteroid;
import game.GamePanel;

public class OBJ_Asteroid extends Asteroid{
	GamePanel gp;
	public OBJ_Asteroid(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Asteroid";
		getImage();
	}
	
    public void getImage() {
        Entity1=(setup("/asteroids/Asteroidup", gp.tileSize, gp.tileSize));
        Entity2=(setup("/asteroids/Asteroidup2", gp.tileSize, gp.tileSize));
        Entity3=(setup("/asteroids/Asteroidup3", gp.tileSize, gp.tileSize));
        
        DeathImage1 = (setup("/asteroids/death1", gp.tileSize, gp.tileSize));
        DeathImage2 = (setup("/asteroids/death2", gp.tileSize, gp.tileSize));
        DeathImage3 = (setup("/asteroids/death3", gp.tileSize, gp.tileSize));
       
    }
}
