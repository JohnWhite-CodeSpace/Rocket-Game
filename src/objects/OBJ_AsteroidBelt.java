package objects;

import entity.AsteroidBelt;
import game.GamePanel;

public class OBJ_AsteroidBelt extends AsteroidBelt{
	GamePanel gp;
	public OBJ_AsteroidBelt(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "AsteroidBelt";
		getImage();
	}
	
    public void getImage() {
        Entity1=(setup("/asteroids/Asteroidbelt", gp.tileSize, gp.tileSize));
        Entity2=(setup("/asteroids/Asteroidbelt", gp.tileSize, gp.tileSize));
        Entity3=(setup("/asteroids/Asteroidbelt", gp.tileSize, gp.tileSize));
        
        DeathImage1 = (setup("/asteroids/AsteroidBeltDying1", gp.tileSize, gp.tileSize));
        DeathImage2 = (setup("/asteroids/AsteroidBeltDying2", gp.tileSize, gp.tileSize));
        DeathImage3 = (setup("/asteroids/AsteroidBeltDying3", gp.tileSize, gp.tileSize));
       
    }
}
