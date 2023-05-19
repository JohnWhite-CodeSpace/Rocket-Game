package Game;

import Object.OBJ_Asteroid;

import java.util.Random;
public class AssetSetter {
	GamePanel gp;
	Random random2 = new Random();
	int[] j = new int [100];
	int[] k = new int [100];
	int max = 15;
	int min = -15;
	int max2 = 8;
	int min2 = -8;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	public void setAsteroid() {
			for(int i=0; i<j.length; i++) {
				j[i] = (random2.nextInt(5)*(max-min)+min);
				k[i] = (random2.nextInt(5)*(max2-min2)+min2);
				gp.asteroids[i] = new OBJ_Asteroid(gp);
				gp.asteroids[i].worldx = gp.Tilesize*250+ gp.Tilesize*j[i]-gp.Tilesize*k[i]
						+10*k[i]-5*j[i];
				gp.asteroids[i].worldy = gp.Tilesize*250+ gp.Tilesize*j[i]-gp.Tilesize*k[i]
						+10*k[i]-5*j[i];
			}
		
	}
	public void SetSpaceStation() {
		//gp.spacestation = new OBJ_SpaceStation(gp);
		gp.spacestation.worldx = gp.Tilesize*94;
		gp.spacestation.worldy = gp.Tilesize*75;
	}
	public void SetPluto() {
		gp.pluto.worldx = gp.Tilesize*50;
		gp.pluto.worldy = gp.Tilesize*50;
	}
	public void SetNeptune() {
		gp.neptune.worldx = gp.Tilesize*75;
		gp.neptune.worldy = gp.Tilesize*46;
	}
	public void SetUranus() {
		gp.uranus.worldx = gp.Tilesize*68;
		gp.uranus.worldy = gp.Tilesize*40;
	}
	public void SetSaturn() {
		gp.saturn.worldx = gp.Tilesize*20;
		gp.saturn.worldy = gp.Tilesize*10;
	}
}
