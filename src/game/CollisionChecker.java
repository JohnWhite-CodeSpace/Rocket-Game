package game;

import java.util.ArrayList;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkMapBoundaries(Entity entity) {
        int mapWidth = gp.maxWorldCol * gp.tileSize;
        int mapHeight = gp.maxWorldRow * gp.tileSize;

        if (entity.worldX < 0) {
            entity.worldX = 0;
        } else if (entity.worldX >= mapWidth) {
            entity.worldX = mapWidth - 1;
        }

        if (entity.worldY < 0) {
            entity.worldY = 0;
        } else if (entity.worldY >= mapHeight) {
            entity.worldY = mapHeight - 1;
        }

        entity.collisionOn = false;
    }



    public boolean checkEntity(Entity entity, Entity target) {
        if (target != null) {
            if (entity.solidArea.intersects(target.solidArea.getBoundsInLocal())) {
                double entityCenterX = entity.worldX + entity.solidArea.getX() + entity.solidArea.getWidth() / 2;
                double entityCenterY = entity.worldY + entity.solidArea.getY() + entity.solidArea.getHeight() / 2;

                double targetCenterX = target.worldX + target.solidArea.getX() + target.solidArea.getWidth() / 2;
                double targetCenterY = target.worldY + target.solidArea.getY() + target.solidArea.getHeight() / 2;

                double angleToTarget = Math.toDegrees(Math.atan2(targetCenterY - entityCenterY, targetCenterX - entityCenterX));
                if (angleToTarget < 0) angleToTarget += 360;

                double entityAngle = entity.angle % 360;
                if (entityAngle < 0) entityAngle += 360;

                double angleDifference = Math.abs(entityAngle - angleToTarget);
                if (angleDifference > 180) angleDifference = 360 - angleDifference;

                if (angleDifference > 90) {
                    entity.collisionOn = false;
                    return false;
                }

                entity.collisionOn = true;
                return true;
            }
        }

        return false;
    }

    public boolean checkEntities(Entity entity, ArrayList<Entity> targets) {
        for (Entity target : targets) {
            if (target != null) {
                if (checkEntity(entity, target)) {
                    return true;
                }
            }
        }
        return false;
    }
}