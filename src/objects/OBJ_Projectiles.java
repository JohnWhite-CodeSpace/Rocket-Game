package objects;

import entity.Projectiles;
import game.GamePanel;

public class OBJ_Projectiles extends Projectiles{
	GamePanel gp;
    public OBJ_Projectiles(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "bullet";
		getImage();
		// TODO Auto-generated constructor stub
	}

	public void getImage() {
        Entity1=(setup("/objects/pellet", gp.tileSize, gp.tileSize));
        Entity2=(setup("/objects/pellet", gp.tileSize, gp.tileSize));
        Entity3=(setup("/objects/pellet", gp.tileSize, gp.tileSize));
    }
}
