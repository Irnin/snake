package com.irnin.games.snake.tile;

import com.irnin.games.snake.engine.Tools;

import java.awt.image.BufferedImage;

public class Tile {
    int id;
    String name;
    BufferedImage texture;

    public Tile(int id, String name, String path) {
        this.id = id;
        this.name = name;
        this.texture = Tools.getImage(path);
        this.texture = Tools.scaleImage(texture);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getTexture() {
        return texture;
    }
}
