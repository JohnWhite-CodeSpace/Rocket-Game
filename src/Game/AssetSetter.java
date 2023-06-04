package Game;

import Object.OBJ_Asteroid;
import Object.OBJ_Comet;
import java.util.Random;
public class AssetSetter {
	GamePanel gp;
	Random random2 = new Random();
	int[] j = new int [100];
	int[] k = new int [100];
	int[] l = new int[15];
	int[] m = new int[15];
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
				int worldx = gp.Tilesize*250+ gp.Tilesize*j[i]-gp.Tilesize*k[i]
						+10*k[i]-5*j[i];
				int worldy = gp.Tilesize*250+ gp.Tilesize*j[i]-gp.Tilesize*k[i]
						+10*k[i]-5*j[i];
				gp.asteroids[i].set(worldx,worldy,"up",true,null);
			}
		
	}
	public void setComet() {
		for(int i=0; i<l.length; i++) {
			l[i] = (random2.nextInt(5)*(max-min)+min);
			m[i] = (random2.nextInt(5)*(max2-min2)+min2);
			gp.comets[i] = new OBJ_Comet(gp);
			int worldx = gp.Tilesize*260+ gp.Tilesize*l[i]-2*gp.Tilesize*m[i]
					+9*m[i]-5*l[i];
			int worldy = gp.Tilesize*240+ 2*gp.Tilesize*l[i]-gp.Tilesize*k[i]
					+11*m[i]-6*l[i];
			gp.comets[i].set(worldx,worldy,"up",true,null);
		}
	
}
	public void SetSpaceStation() {
		gp.spacestation.set(gp.Tilesize*94,gp.Tilesize*75,"space_station",true,null);
	}
	public void SetUranus() {
		gp.uranus.set(gp.Tilesize*90,gp.Tilesize*70,"planet",true,null);
	}
	public void SetSaturn() {
		gp.pluto.set(gp.Tilesize*120,gp.Tilesize*80,"planet",true,null);
	}
	public void SetPluto() {
		gp.pluto.set(gp.Tilesize*150,gp.Tilesize*75,"planet",true,null);
	}
	public void SetNeptune() {
		gp.neptune.set(gp.Tilesize*60,gp.Tilesize*70,"planet",true,null);
	}
	public void SetJupiter() {
		gp.jupiter.set(gp.Tilesize*130,gp.Tilesize*60,"planet",true,null);
	}
}
