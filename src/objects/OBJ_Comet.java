package objects;

import entity.Comet;
import game.GamePanel;

public class OBJ_Comet extends Comet{
	GamePanel gp;
	public OBJ_Comet(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Asteroid";
		getImage();
	}
	
    public void getImage() {
        Entity1=(setup("/asteroids/Cometup1", gp.tileSize, gp.tileSize));
        Entity2=(setup("/asteroids/Cometup2", gp.tileSize, gp.tileSize));
        Entity3=(setup("/asteroids/Cometup3", gp.tileSize, gp.tileSize));
        
        DeathImage1 = (setup("/asteroids/Cometdeath1", gp.tileSize, gp.tileSize));
        DeathImage2 = (setup("/asteroids/Cometdeath2", gp.tileSize, gp.tileSize));
        DeathImage3 = (setup("/asteroids/Cometdeath3", gp.tileSize, gp.tileSize));
       
    }
}
