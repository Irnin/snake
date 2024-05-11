package com.irnin.games.mitria.main;

import com.irnin.games.mitria.entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;

        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case NORTH -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                if (gp.tileM.tiles.get(tileNum1).hasCollision() || gp.tileM.tiles.get(tileNum2).hasCollision())
                    entity.collisionOn = true;
            }
            case SOUTH -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tiles.get(tileNum1).hasCollision() || gp.tileM.tiles.get(tileNum2).hasCollision())
                    entity.collisionOn = true;
            }
            case WEST -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                if (gp.tileM.tiles.get(tileNum1).hasCollision() || gp.tileM.tiles.get(tileNum2).hasCollision())
                    entity.collisionOn = true;
            }
            case EAST -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tiles.get(tileNum1).hasCollision() || gp.tileM.tiles.get(tileNum2).hasCollision())
                    entity.collisionOn = true;
            }
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for(int i = 0; i < gp.objects.length; i++) {
            if(gp.objects[i] != null) {
                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get the object's solid area position
                gp.objects[i].solidArea.x = gp.objects[i].worldX + gp.objects[i].solidArea.x;
                gp.objects[i].solidArea.y = gp.objects[i].worldY + gp.objects[i].solidArea.y;

                switch (entity.direction) {
                    case NORTH -> {
                        entity.solidArea.y -= entity.speed;

                        if(entity.solidArea.intersects(gp.objects[i].solidArea)) {
                            if(gp.objects[i].collision)
                                entity.collisionOn = true;

                            if(player)
                                index = i;
                        }
                    }
                    case SOUTH -> {
                        entity.solidArea.y += entity.speed;

                        if(entity.solidArea.intersects(gp.objects[i].solidArea)) {
                            if(entity.solidArea.intersects(gp.objects[i].solidArea)) {
                                if(gp.objects[i].collision)
                                    entity.collisionOn = true;

                                if(player)
                                    index = i;
                            }
                        }
                    }
                    case WEST -> {
                        entity.solidArea.x -= entity.speed;

                        if(entity.solidArea.intersects(gp.objects[i].solidArea)) {
                            if(entity.solidArea.intersects(gp.objects[i].solidArea)) {
                                if(gp.objects[i].collision)
                                    entity.collisionOn = true;

                                if(player)
                                    index = i;
                            }
                        }
                    }
                    case EAST -> {
                        entity.solidArea.x += entity.speed;

                        if(entity.solidArea.intersects(gp.objects[i].solidArea)) {
                            if(entity.solidArea.intersects(gp.objects[i].solidArea)) {
                                if(gp.objects[i].collision)
                                    entity.collisionOn = true;

                                if(player)
                                    index = i;
                            }
                        }
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.objects[i].solidArea.x = gp.objects[i].solidAreaDefaultX;
                gp.objects[i].solidArea.y = gp.objects[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
