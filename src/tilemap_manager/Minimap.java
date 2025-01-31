package tilemap_manager;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import objects.OBJ_AsteroidBelt;

import java.util.ArrayList;

import entity.Entity;
import game.GamePanel;

public class Minimap extends TileManager {
    private GamePanel gp;
    private Canvas worldMapCanvas;
    private Font font4;

    public Minimap(GamePanel gp) {
        super(gp);
        this.gp = gp;
        createMiniMap();
        font4 = Font.font("Arial", 15);
    }

    public void createMiniMap() {
        int worldMapWidth = 1600;
        int worldMapHeight = 1600;
        worldMapCanvas = new Canvas(worldMapWidth, worldMapHeight);
        GraphicsContext gc = worldMapCanvas.getGraphicsContext2D();

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            int tileNum = mapTileNum[col][row];
            int x = 16 * col / 10;
            int y = 16 * row / 10;

            Image tileImage = tile[tileNum].image;
            if (tileImage != null) {
                gc.drawImage(tileImage, x, y, 16, 16);
            }

            col += 10;
            if (col >= gp.maxWorldCol) {
                col = 0;
                row += 10;
            }
        }
    }

    public void drawFullMapScreen(GraphicsContext gc) {
        if (gp.keyH.FullMap) {

            gc.setFill(Color.rgb(0, 0, 0, 0.4));
            gc.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            int width = 700;
            int height = 700;
            int x = gp.screenWidth / 2 - width / 2;
            int y = gp.screenHeight / 2 - height / 2;

            gc.setFill(Color.GREEN);
            gc.fillRect(x - 10, y - 10, width + 20, height + 20);

            gc.drawImage(worldMapCanvas.snapshot(null, null), x, y, width, height);

            double scale = (double) (gp.tileSize * gp.maxWorldCol) / width;

            int playerX = (int) (x + gp.player.worldX / scale - gp.tileSize / 4);
            int playerY = (int) (y + gp.player.worldY / scale - gp.tileSize / 4);

            for (Entity planet : gp.planetList) {
                double orbitRadius = planet.orbitRadius;
                double planetX = x + planet.worldX / scale - gp.tileSize / 4;
                double planetY = y + planet.worldY / scale - gp.tileSize / 4;

                double orbitX = x + (planet.orbitCenterX - orbitRadius) / scale;
                double orbitY = y + (planet.orbitCenterY - orbitRadius) / scale;
                double orbitDiameter = 2 * (orbitRadius / scale);

                gc.setStroke(Color.GRAY);
                gc.setLineWidth(0.5);
                gc.strokeOval(orbitX, orbitY, orbitDiameter, orbitDiameter);

                int planetSize = gp.tileSize / 4 + (planet.planetRadius / (gp.tileSize * 2));
                gc.drawImage(planet.Entity1, planetX, planetY, planetSize, planetSize);
            }

            for (Entity asteroid : gp.entityList) {
                double asteroidX = x + asteroid.worldX / scale - gp.tileSize / 8;
                double asteroidY = y + asteroid.worldY / scale - gp.tileSize / 8;
                gc.drawImage(asteroid.Entity1, asteroidX, asteroidY, gp.tileSize / 8, gp.tileSize / 8);
            }

            for (Entity station : gp.stationsList) {
                double stationX = x + station.worldX / scale - gp.tileSize / 4;
                double stationY = y + station.worldY / scale - gp.tileSize / 4;

                double orbitRadius = station.orbitRadius;
                double orbitX = x + (station.orbitCenterX - orbitRadius) / scale;
                double orbitY = y + (station.orbitCenterY - orbitRadius) / scale;
                double orbitDiameter = 2 * (orbitRadius / scale);

                gc.setStroke(Color.LIGHTGRAY);
                gc.setLineWidth(0.4);
                gc.strokeOval(orbitX, orbitY, orbitDiameter, orbitDiameter);

                gc.drawImage(station.Entity1, stationX, stationY, gp.tileSize / 2, gp.tileSize / 2);
            }

            gc.setLineWidth(2);
            ArrayList<Integer> bestRoute = gp.bestRoute.getBestRoute();

            if (bestRoute != null) {
                double startX = playerX + gp.tileSize / 4;
                double startY = playerY + gp.tileSize / 4;

                for (int i = 0; i < bestRoute.size(); i++) {
                	if(i==0) {
                		gc.setStroke(Color.GREEN);
                	}else {
                		gc.setStroke(Color.WHITE);
                	}
                    Entity planet = gp.planetList.get(bestRoute.get(i));
                    double endX = x + planet.worldX / scale;
                    double endY = y + planet.worldY / scale;

                    gc.strokeLine(startX, startY, endX, endY);

                    startX = endX;
                    startY = endY;
                }

                Entity endPlanet = gp.planetList.get(bestRoute.get(bestRoute.size() - 1));
                double endPlanetX = x + endPlanet.worldX / scale;
                double endPlanetY = y + endPlanet.worldY / scale;
                Entity earth = getEarth(); 
                double earthX = x + earth.worldX / scale;
                double earthY = y + earth.worldY / scale;
                gc.setStroke(Color.RED);
                gc.strokeLine(endPlanetX, endPlanetY, earthX, earthY);
            }
            gc.drawImage(gp.player.playerImage1, playerX, playerY, gp.tileSize / 2, gp.tileSize / 2);

            gc.setFill(Color.WHITE);
            gc.setFont(Font.font("Arial", 30));
            gc.fillText("World Map", gp.tileSize, gp.tileSize);
        }
    }

    public void drawMiniMap(GraphicsContext gc) {
        if (gp.keyH.MinimapOn) {

            int width = 350;
            int height = 350;
            int x = gp.screenWidth - width - 20;
            int y = gp.tileSize * 2;

            gc.setGlobalAlpha(0.9);
            gc.drawImage(worldMapCanvas.snapshot(null, null), x, y, width, height);

            double scale = (double) (gp.tileSize * gp.maxWorldCol) / width;


            int playerX = (int) (x + gp.player.worldX / scale - gp.tileSize / 4);
            int playerY = (int) (y + gp.player.worldY / scale - gp.tileSize / 4);

            for (Entity planet : gp.planetList) {
                double orbitRadius = planet.orbitRadius;
                double planetX = x + planet.worldX / scale - gp.tileSize / 4;
                double planetY = y + planet.worldY / scale - gp.tileSize / 4;

                double orbitX = x + (planet.orbitCenterX - orbitRadius) / scale;
                double orbitY = y + (planet.orbitCenterY - orbitRadius) / scale;
                double orbitDiameter = 2 * (orbitRadius / scale);

                gc.setStroke(Color.GRAY);
                gc.setLineWidth(0.2);
                gc.strokeOval(orbitX, orbitY, orbitDiameter, orbitDiameter);

                int planetSize = gp.tileSize / 4 + (planet.planetRadius / (gp.tileSize * 2));
                gc.drawImage(planet.Entity1, planetX, planetY, planetSize, planetSize);
            }

            for (Entity asteroid : gp.entityList) {
                double asteroidX = x + asteroid.worldX / scale - gp.tileSize / 8;
                double asteroidY = y + asteroid.worldY / scale - gp.tileSize / 8;
                gc.drawImage(asteroid.Entity1, asteroidX, asteroidY, gp.tileSize / 8, gp.tileSize / 8);
            }

            gc.setStroke(Color.WHITE);
            gc.setLineWidth(1);

            ArrayList<Integer> bestRoute = gp.bestRoute.getBestRoute();
            if (bestRoute != null) {

                double startX = playerX + gp.tileSize / 4;
                double startY = playerY + gp.tileSize / 4;

                for (int i = 0; i < bestRoute.size(); i++) {
                	if(i==0) {
                		gc.setStroke(Color.GREEN);
                	}else {
                		gc.setStroke(Color.WHITE);
                	}
                    Entity planet = gp.planetList.get(bestRoute.get(i));
                    double endX = x + planet.worldX / scale;
                    double endY = y + planet.worldY / scale;

                    gc.strokeLine(startX, startY, endX, endY);

                    startX = endX;
                    startY = endY;
                }

                Entity endPlanet = gp.planetList.get(bestRoute.get(bestRoute.size() - 1));
                double endPlanetX = x + endPlanet.worldX / scale;
                double endPlanetY = y + endPlanet.worldY / scale;
                Entity earth = getEarth();
                double earthX = x + earth.worldX / scale;
                double earthY = y + earth.worldY / scale;
                gc.setStroke(Color.RED);
                gc.strokeLine(endPlanetX, endPlanetY, earthX, earthY);
            }

            gc.drawImage(gp.player.playerImage1, playerX, playerY, gp.tileSize / 2, gp.tileSize / 2);
            gc.setGlobalAlpha(1.0);
        }
    }
    public Entity getEarth() {
        for (Entity planet : gp.planetList) {
            if ("Earth".equals(planet.name)) {
                return planet;
            }
        }
        return null;
    }
}