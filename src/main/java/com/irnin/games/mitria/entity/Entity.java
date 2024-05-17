package com.irnin.games.mitria.entity;

import com.irnin.games.mitria.Directions;
import com.irnin.games.mitria.engine.Tools;

import java.awt.image.BufferedImage;
import java.util.Dictionary;
import java.util.HashMap;

public class Entity {
    // GRAPHICS OF ENTITY
    private HashMap<EntityGraphics, BufferedImage> graphics = new HashMap<EntityGraphics, BufferedImage>();

    Directions direction = Directions.SOUTH;
    int animationCounter = 0;
    int walkingAnimationCounter = 1;

    public Entity() {

    }

    // SETTERS
    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    // LOADING GRAPHICS
    public void addGraphics(String[] paths) {

        addGraphic(EntityGraphics.UP1, paths[0]);
        addGraphic(EntityGraphics.UP2, paths[1]);
        addGraphic(EntityGraphics.DOWN1, paths[2]);
        addGraphic(EntityGraphics.DOWN2, paths[3]);
        addGraphic(EntityGraphics.LEFT1, paths[4]);
        addGraphic(EntityGraphics.LEFT2, paths[5]);
        addGraphic(EntityGraphics.RIGHT1, paths[6]);
        addGraphic(EntityGraphics.RIGHT2, paths[7]);
    }

    private void addGraphic(EntityGraphics entityGraphics, String path) {
        BufferedImage image = Tools.getImage(path);
        image = Tools.scaleImage(image);
        graphics.put(entityGraphics, image);
    }

    public BufferedImage getSprite() {
        BufferedImage sprite = null;
        switch (direction) {
            case NORTH -> {
                switch (walkingAnimationCounter) {
                    case 1 -> sprite = graphics.get(EntityGraphics.UP1);
                    case 2 -> sprite = graphics.get(EntityGraphics.UP2);
                }
            }
            case SOUTH -> {
                switch (walkingAnimationCounter) {
                    case 1 -> sprite = graphics.get(EntityGraphics.DOWN1);
                    case 2 -> sprite = graphics.get(EntityGraphics.DOWN2);
                }
            }
            case WEST -> {
                switch (walkingAnimationCounter) {
                    case 1 -> sprite = graphics.get(EntityGraphics.LEFT1);
                    case 2 -> sprite = graphics.get(EntityGraphics.LEFT2);
                }
            }
            case EAST -> {
                switch (walkingAnimationCounter) {
                    case 1 -> sprite = graphics.get(EntityGraphics.RIGHT1);
                    case 2 -> sprite = graphics.get(EntityGraphics.RIGHT2);
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
