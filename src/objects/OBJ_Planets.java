package objects;

import entity.Planets;
import game.GamePanel;

public class OBJ_Planets extends Planets{
	GamePanel gp;
	public OBJ_Planets(GamePanel gp, int X, int Y, int Radius, int orbitCoeff, double Eccentricity, String planetName) {
		super(gp, X, Y, Radius, orbitCoeff, Eccentricity, planetName);
		this.gp = gp;
		name = planetName;
		getImage(planetName);
	}
	
    public void getImage(String planetName) {
    	System.out.println("/planets/" + planetName + "1");
        Entity1=(setup("/planets/" + planetName + "1", gp.tileSize, gp.tileSize));
        Entity2=(setup("/planets/" + planetName + "2", gp.tileSize, gp.tileSize));
        Entity3=(setup("/planets/" + planetName + "3", gp.tileSize, gp.tileSize));
        Entity4=(setup("/planets/" + planetName + "4", gp.tileSize, gp.tileSize));
       
    }
}
