package Game;

import Object.OBJ_AlienSpaceship;
import Object.OBJ_Asteroid;
import Object.OBJ_AsteroidBelt;
import Object.OBJ_Comet;
import java.util.Random;
public class AssetSetter {
	GamePanel gp;
	Random random2 = new Random();
	int[] j = new int [100];
	int[] k = new int [100];
	int[] l = new int[15];
	int[] m = new int[15];
	double[] o = new double[350];
	double[] p = new double[350];
	int[] r = new int[20];
	int[] s =new int[20];
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
				gp.asteroids[i].set(worldx,worldy,"asteroid",true,null);
			}
		
	}
	public void setAsteroidBelt() {
		for(int i=0; i<o.length; i++) {
			o[i] = (random2.nextDouble(100));
			p[i] = (random2.nextDouble(100));
			gp.asteroidBelt[i] = new OBJ_AsteroidBelt(gp);
			int worldx = (int) (gp.Tilesize*250+ gp.Tilesize*o[i]-gp.Tilesize*p[i]
					+10*p[i]-5*o[i]);
			int worldy = (int) (gp.Tilesize*250+ gp.Tilesize*o[i]-gp.Tilesize*p[i]
					+10*p[i]-5*o[i]);
			gp.asteroidBelt[i].set(worldx,worldy,"ABelt",true,null);
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
			gp.comets[i].set(worldx,worldy,"comet",true,null);
		}
	
}
	public void setAlienSpaceship() {
		for(int i=0; i<r.length; i++) {
			r[i] = (random2.nextInt(5)*(max-min)+min);
			s[i] = (random2.nextInt(5)*(max2-min2)+min2);
			gp.alienrocket[i] = new OBJ_AlienSpaceship(gp);
			int worldx = gp.Tilesize*250+ gp.Tilesize*r[i]-gp.Tilesize*s[i]
					+10*s[i]-5*r[i];
			int worldy = gp.Tilesize*250+ gp.Tilesize*r[i]-gp.Tilesize*k[i]
					+10*s[i]-5*r[i];
			gp.alienrocket[i].set(worldx,worldy,"alienrocket",true,null);
		}

	}
	public void SetSol() {
		gp.sol.set(gp.Tilesize*250,gp.Tilesize*250,"planet",true,null);
	}
}
