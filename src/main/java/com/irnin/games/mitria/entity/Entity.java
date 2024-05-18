package com.irnin.games.mitria.entity;

import com.irnin.games.mitria.Directions;
import com.irnin.games.mitria.engine.Tools;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Entity {
    // GRAPHICS OF ENTITY
    private final HashMap<EntitySprites, BufferedImage> graphics = new HashMap<EntitySprites, BufferedImage>();

    Directions direction = Directions.SOUTH;
    int animationCounter = 0;
    int walkingAnimationCounter = 1;

    // Position on map
    public int worldX, worldY;

    int walkingSpeed;

    public Entity() {

    }

    // SETTERS
    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    // LOADING GRAPHICS
    public void addGraphics(String[] paths) {

        addGraphic(EntitySprites.UP1, paths[0]);
        addGraphic(EntitySprites.UP2, paths[1]);
        addGraphic(EntitySprites.DOWN1, paths[2]);
        addGraphic(EntitySprites.DOWN2, paths[3]);
        addGraphic(EntitySprites.LEFT1, paths[4]);
        addGraphic(EntitySprites.LEFT2, paths[5]);
        addGraphic(EntitySprites.RIGHT1, paths[6]);
        addGraphic(EntitySprites.RIGHT2, paths[7]);
    }

    private void addGraphic(EntitySprites entitySprites, String path) {
        BufferedImage image = Tools.getImage(path);
        image = Tools.scaleImage(image);
        graphics.put(entitySprites, image);
    }

    public BufferedImage getSprite() {
        BufferedImage sprite = null;
        switch (direction) {
            case NORTH -> {
                switch (walkingAnimationCounter) {
                    case 1 -> sprite = graphics.get(EntitySprites.UP1);
                    case 2 -> sprite = graphics.get(EntitySprites.UP2);
                }
            }
            case SOUTH -> {
                switch (walkingAnimationCounter) {
                    case 1 -> sprite = graphics.get(EntitySprites.DOWN1);
                    case 2 -> sprite = graphics.get(EntitySprites.DOWN2);
                }
            }
            case WEST -> {
                switch (walkingAnimationCounter) {
                    case 1 -> sprite = graphics.get(EntitySprites.LEFT1);
                    case 2 -> sprite = graphics.get(EntitySprites.LEFT2);
                }
            }
            case EAST -> {
                switch (walkingAnimationCounter) {
                    case 1 -> sprite = graphics.get(EntitySprites.RIGHT1);
                    case 2 -> sprite = graphics.get(EntitySprites.RIGHT2);
                }
            }
        }

        return sprite;
    }

    public void walkAnimation() {
        animationCounter ++;
        if(animationCounter > 10) {
            walkingAnimationCounter = (walkingAnimationCounter + 2) % 2 + 1;
            animationCounter = 0;
        }
    }

}
