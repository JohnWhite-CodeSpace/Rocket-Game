package game;

import java.util.Random;

import entity.Entity;
import objects.OBJ_Asteroid;
import objects.OBJ_AsteroidBelt;
import objects.OBJ_Comet;
import objects.OBJ_Planets;
import objects.OBJ_SpaceStation;
import objects.OBJ_Stars;

public class AssetSetter {

    GamePanel gp;
    Random random = new Random();
    int maxOffsetX = 0;
    int minOffsetX = 0;
    int maxOffsetY = 0;
    int minOffsetY = 0;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
        maxOffsetX = 10000 * gp.tileSize;
        minOffsetX = 0;
        maxOffsetY = 10000 * gp.tileSize;
        minOffsetY = 0;
    }

    private int generateRandomCoordinate(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private int calculateWorldCoordinate(int base, int offset) {
        return base + offset;
    }

    public void setAsteroids() {
        for (int i = 0; i < 200; i++) {
            int offsetX = generateRandomCoordinate(minOffsetX, maxOffsetX);
            int offsetY = generateRandomCoordinate(minOffsetY, maxOffsetY);

            OBJ_Asteroid asteroid = new OBJ_Asteroid(gp);

            int worldX = calculateWorldCoordinate(gp.tileSize * 50, offsetX);
            int worldY = calculateWorldCoordinate(gp.tileSize * 50, offsetY);

            double angle = random.nextDouble() * 360;

            asteroid.setSpawnPosition(worldX, worldY, angle, true);
            gp.entityList.add(asteroid);
        }
    }

    public void setComet() {
        for (int i = 0; i < 200; i++) {
            int offsetX = generateRandomCoordinate(minOffsetX, maxOffsetX);
            int offsetY = generateRandomCoordinate(minOffsetY, maxOffsetY);

            OBJ_Comet comet = new OBJ_Comet(gp);

            int worldX = calculateWorldCoordinate(gp.tileSize * 50, offsetX);
            int worldY = calculateWorldCoordinate(gp.tileSize * 50, offsetY);

            double angle = random.nextDouble() * 360;

            comet.setSpawnPosition(worldX, worldY, angle, true);
            gp.entityList.add(comet);
        }
    }
    public void setAsteroidBelt() {
        for (int i = 0; i < 5000; i++) {
            OBJ_AsteroidBelt asteroid = new OBJ_AsteroidBelt(gp);
            double angle = random.nextDouble() * 360;
            int radius_width_coeff = random.nextInt(20);
            int offset = random.nextInt(50);
            asteroid.setSpawnPosition(radius_width_coeff, angle, offset, true);
            gp.entityList.add(asteroid);
        }
    }
    public void setPlanet(int planet) {
    	double angle = random.nextInt(360);
    	switch(planet) {
    		case 1:
	    		OBJ_Planets Mercury = new OBJ_Planets(gp, 0, 0,gp.tileSize*7, 99, 0.025 , "Mercury");
	    		Mercury.setSpawnPosition(5000*gp.tileSize, 5000*gp.tileSize, angle, true);
	    		gp.planetList.add(Mercury);
    		break;
    		case 2:
        		OBJ_Planets Venus = new OBJ_Planets(gp, 0 ,0 ,gp.tileSize*10, 142, 0.007, "Venus");
        		Venus.setSpawnPosition(5000*gp.tileSize, 5000*gp.tileSize, angle, true);
        		gp.planetList.add(Venus);
        		break;
    		case 3:
        		OBJ_Planets Earth = new OBJ_Planets(gp, 0 ,0 ,gp.tileSize*11, 177, 0.017, "Earth");
        		Earth.setSpawnPosition(5000*gp.tileSize, 5000*gp.tileSize, angle, true);
        		gp.planetList.add(Earth);
        		break;
    		case 4:
        		OBJ_Planets Mars = new OBJ_Planets(gp, 0 ,0 ,gp.tileSize*9, 243, 0.093, "Mars");
        		Mars.setSpawnPosition(5000*gp.tileSize, 5000*gp.tileSize, angle, true);
        		gp.planetList.add(Mars);
        		break;
    		case 5:
        		OBJ_Planets Jupiter = new OBJ_Planets(gp, 0 ,0 ,gp.tileSize*20, 708, 0.049, "Jupiter");
        		Jupiter.setSpawnPosition(5000*gp.tileSize, 5000*gp.tileSize, angle, true);
        		gp.planetList.add(Jupiter);
        		break;
    		case 6:
        		OBJ_Planets Saturn = new OBJ_Planets(gp, 0 ,0 ,gp.tileSize*16, 1260, 0.056, "Saturn");
        		Saturn.setSpawnPosition(5000*gp.tileSize, 5000*gp.tileSize, angle, true);
        		gp.planetList.add(Saturn);
        		break;
    		case 7:
        		OBJ_Planets Uranus = new OBJ_Planets(gp, 0 ,0 ,gp.tileSize*16, 2483, 0.046, "Uranus");
        		Uranus.setSpawnPosition(5000*gp.tileSize, 5000*gp.tileSize, angle, true);
        		gp.planetList.add(Uranus);
        		break;
    		case 8:
        		OBJ_Planets Neptune = new OBJ_Planets(gp, 0 ,0 ,gp.tileSize*14, 3853, 0.009, "Neptune");
        		Neptune.setSpawnPosition(5000*gp.tileSize, 5000*gp.tileSize, angle, true);
        		gp.planetList.add(Neptune);
        		break;
    		case 9:
        		OBJ_Planets Pluto = new OBJ_Planets(gp, 0 ,0 ,gp.tileSize*6, 4887, 0.044, "Pluto");
        		Pluto.setSpawnPosition(5000*gp.tileSize, 5000*gp.tileSize, angle, true);
        		gp.planetList.add(Pluto);
        		break;
    	}
    }
    public void setStars(int star) {
    	switch(star) {
    	case 1:
    		OBJ_Stars Sun = new OBJ_Stars(gp, 0, 0, gp.tileSize*30, "Sol");
    		Sun.setSpawnPosition(5000*gp.tileSize, 5000*gp.tileSize, 0, true);
    		gp.planetList.add(Sun);
    		break;
    	}
    }
    public void setSpaceStations(int SpaceStation) {
        switch (SpaceStation) {
            case 1:
                double angle = random.nextInt(360);
                Entity Saturn = gp.planetList.stream()
                        .filter(p -> "Saturn".equals(p.name))
                        .findFirst()
                        .orElse(null);

                if (Saturn != null) {
                    OBJ_SpaceStation S_Station = new OBJ_SpaceStation(gp, 0, 0, gp.tileSize * 6, 300, 0.09, "SpaceStation", Saturn);
                    S_Station.setSpawnPosition(Saturn.worldX, Saturn.worldY, angle, true);
                    gp.stationsList.add(S_Station);
                }
                break;
        }
    }

    	
}
