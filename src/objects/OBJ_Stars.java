package objects;

import entity.Stars;
import game.GamePanel;

public class OBJ_Stars extends Stars{
	GamePanel gp;
	public OBJ_Stars(GamePanel gp, int X, int Y, int Radius, String starName) {
		super(gp, X, Y, Radius);
		this.gp = gp;
		name = "Star";
		getImage(starName);
	}
	
    public void getImage(String starName) {
    	System.out.println("/planets/" + starName + "1");
        Entity1=(setup("/planets/" + starName + "1", gp.tileSize, gp.tileSize));
        Entity2=(setup("/planets/" + starName + "2", gp.tileSize, gp.tileSize));
        Entity3=(setup("/planets/" + starName + "3", gp.tileSize, gp.tileSize));
        Entity4=(setup("/planets/" + starName + "4", gp.tileSize, gp.tileSize));
       
    }
}
