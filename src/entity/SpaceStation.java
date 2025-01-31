package entity;

import game.GamePanel;
import javafx.scene.shape.Circle;

public class SpaceStation extends Entity {
    GamePanel gp;
    Entity parentPlanet; // Referencja do planety, wokół której stacja ma orbitować
    
    public SpaceStation(GamePanel gp, int X, int Y, int Radius, int orbitCoeff, double Eccentricity, String stationName, Entity parentPlanet) {
        super(gp);
        this.gp = gp;
        this.parentPlanet = parentPlanet; // Ustawienie planety
        planetSolidArea = new Circle();
        planetSolidArea.setCenterX(X);
        planetSolidArea.setCenterY(Y);
        planetSolidArea.setRadius(Radius);
        orbitRadius = orbitCoeff * gp.tileSize;
        planetRadius = Radius;
        orbitEccentricity = Eccentricity;
        name = stationName;
    }
	public void setSpawnPosition(int orbitCenterX, int orbitCenterY, double angle, boolean alive) {
		this.worldX = orbitCenterX;
		this.worldY = orbitCenterY;
		this.angle = angle;
		this.alive = alive;
		this.life = 5;
		this.orbitCenterX = orbitCenterX;
		this.orbitCenterY = orbitCenterY;
	}
    @Override
    public void update() {
        collisionOn = false;

        // Aktualizuj środek orbity na podstawie aktualnej pozycji planety
        orbitCenterX = parentPlanet.worldX;
        orbitCenterY = parentPlanet.worldY;

        // Kontynuuj logikę obliczania pozycji
        angle = (angle + 0.05) % 360;
        double radians = Math.toRadians(angle);
        double r = (orbitRadius * (1 - orbitEccentricity * orbitEccentricity)) / 
                   (1 + orbitEccentricity * Math.cos(radians));

        worldX = (int) (r * Math.cos(radians) + orbitCenterX);
        worldY = (int) (r * Math.sin(radians) + orbitCenterY);

        planetSolidArea.setCenterX(worldX);
        planetSolidArea.setCenterY(worldY);

        spriteCounter++;
        if (spriteCounter > 30) {
            spriteNum = (spriteNum % 3) + 1; // Cykl animacji
            spriteCounter = 0;
        }
    }
}
