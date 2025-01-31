package objects;

import entity.Entity;
import entity.SpaceStation;
import game.GamePanel;

public class OBJ_SpaceStation extends SpaceStation{
	GamePanel gp;
	public OBJ_SpaceStation(GamePanel gp, int X, int Y, int Radius, int orbitCoeff, double Eccentricity, String planetName, Entity parentPlanet) {
		super(gp, X, Y, Radius, orbitCoeff, Eccentricity, planetName, parentPlanet);
		this.gp = gp;
		name = "SpaceStation";
		getImage(planetName);
	}
	
    public void getImage(String planetName) {
    	System.out.println("/space_station/" + planetName + "1");
        Entity1=(setup("/space_station/" + planetName + "1", gp.tileSize, gp.tileSize));
        Entity2=(setup("/space_station/" + planetName + "2", gp.tileSize, gp.tileSize));
        Entity3=(setup("/space_station/" + planetName + "3", gp.tileSize, gp.tileSize));
       
    }
}
