package entity;

import game.GamePanel;
import game.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	Image playerImage1, playerImage2, playerImage3;
	int spriteTime = 0;
	int maxVelocity = 8;
	int reload_time = 60;
	public final int screenX, screenY;
	
	public Player (GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.gp = gp;
		this.keyH = keyH;
		screenX = gp.screenWidth/2;
		screenY = gp.screenHeight/2;
		solidArea = new Rectangle();
		solidArea.setX(6);
		solidArea.setY(6);
		solidArea.setWidth(gp.tileSize-12);
		solidArea.setHeight(gp.tileSize-12);
		setPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.maxWorldCol/2*gp.tileSize;
		worldY = gp.maxWorldRow/2*gp.tileSize;
		acceleration = 0.1;
	}

    public void setPlayerImage() {
        try {
            playerImage1 = new Image(getClass().getResourceAsStream("/player/chonker2.png"));
            playerImage2 = new Image(getClass().getResourceAsStream("/player/chonker.png"));
            playerImage3 = new Image(getClass().getResourceAsStream("/player/chonker3.png"));
        } catch (Exception e) {
            System.out.println("Failed to load player image: " + e.getMessage());
        }
    }
	public void update() {
        collisionOn = false;
        gp.cChecker.checkMapBoundaries(this);
        gp.cChecker.checkEntities(this, gp.entityList);
        gp.cChecker.checkEntities(this, gp.entityList);
		if (keyH.upPressed) {
            velocity += acceleration;
        }
        if (keyH.downPressed) {
            velocity -= acceleration;
        }
        if (keyH.leftPressed) {
            angle -= angularAcceleration;
        }
        if (keyH.rightPressed) {
            angle += angularAcceleration;
        }
        if (keyH.shotsFired) {
            if (reload_time == 0) {
                Projectiles bullet = new Projectiles(gp);
                bullet.setSpawnPosition(worldX, worldY, angle, true);
                gp.addEntity(bullet); // Dodanie pocisku do entityList
                reload_time = 60;
                gp.playSE(1);
            }
        }
        if (Math.abs(velocity) > maxVelocity) {
            velocity = maxVelocity * (velocity / Math.abs(velocity));
        }
        double deltaY= velocity * Math.cos(Math.toRadians(angle));
		double deltaX= velocity * Math.sin(Math.toRadians(angle));
        
        angle = (angle + 360) % 360;
        if(collisionOn == false) {
            worldX += deltaX;
            worldY -= deltaY;
        }
		solidArea.setX(6 + worldX);
		solidArea.setY(6 + worldY);
		reload_time --;
		if(reload_time<=0) {
			reload_time = 0;
		}

	}
	public void drawPlayer(GraphicsContext gc) {
		spriteTime++;
        gc.save();
        gc.translate(screenX, screenY);
        gc.rotate(angle);
    	if(spriteTime <20) {
    		gc.drawImage(playerImage1, -gp.tileSize / 2.0, -gp.tileSize / 2.0, gp.tileSize, gp.tileSize);
    	}
    	else if (spriteTime >=20 && spriteTime<40) {
    		gc.drawImage(playerImage2, -gp.tileSize / 2.0, -gp.tileSize / 2.0, gp.tileSize, gp.tileSize);
    	}
    	else if (spriteTime >=20) {
    		gc.drawImage(playerImage3, -gp.tileSize / 2.0, -gp.tileSize / 2.0, gp.tileSize, gp.tileSize);
    	}
    	if (spriteTime>=60) {
    		spriteTime = 0;
    	}
        gc.restore();
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        gc.strokeRect(
            screenX + solidArea.getX() - worldX - gp.tileSize / 2.0,
            screenY + solidArea.getY() - worldY - gp.tileSize / 2.0,
            solidArea.getWidth(),
            solidArea.getHeight()
        );
	}
}
