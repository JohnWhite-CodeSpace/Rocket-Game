package game;

import java.util.Random;
import objects.OBJ_Asteroid;
import objects.OBJ_AsteroidBelt;
import objects.OBJ_Comet;

public class AssetSetter {

    GamePanel gp;
    Random random = new Random();
    int maxOffsetX = 0;
    int minOffsetX = 0;
    int maxOffsetY = 0;
    int minOffsetY = 0;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
        maxOffsetX = 1000 * gp.tileSize;
        minOffsetX = 0;
        maxOffsetY = 1000 * gp.tileSize;
        minOffsetY = 0;
    }

    private int generateRandomCoordinate(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private int calculateWorldCoordinate(int base, int offset) {
        return base + offset;
    }

    public void setAsteroids() {
        for (int i = 0; i < 100; i++) {
            int offsetX = generateRandomCoordinate(minOffsetX, maxOffsetX);
            int offsetY = generateRandomCoordinate(minOffsetY, maxOffsetY);

            OBJ_Asteroid asteroid = new OBJ_Asteroid(gp);

            int worldX = calculateWorldCoordinate(gp.tileSize * 250, offsetX);
            int worldY = calculateWorldCoordinate(gp.tileSize * 250, offsetY);

            double angle = random.nextDouble() * 360;

            asteroid.setSpawnPosition(worldX, worldY, angle, true);
            gp.entityList.add(asteroid);
        }
    }

    public void setComet() {
        for (int i = 0; i < 100; i++) {
            int offsetX = generateRandomCoordinate(minOffsetX, maxOffsetX);
            int offsetY = generateRandomCoordinate(minOffsetY, maxOffsetY);

            OBJ_Comet comet = new OBJ_Comet(gp);

            int worldX = calculateWorldCoordinate(gp.tileSize * 250, offsetX);
            int worldY = calculateWorldCoordinate(gp.tileSize * 250, offsetY);

            double angle = random.nextDouble() * 360;

            comet.setSpawnPosition(worldX, worldY, angle, true);
            gp.entityList.add(comet);
        }
    }
    public void setAsteroidBelt() {
        for (int i = 0; i < 50; i++) {
            OBJ_AsteroidBelt asteroid = new OBJ_AsteroidBelt(gp);

            double angle = random.nextInt(360);
            int radius_width_coeff = random.nextInt(8);
            asteroid.setSpawnPosition(radius_width_coeff, angle, true);
            gp.entityList.add(asteroid);
        }
    }
    	
}
