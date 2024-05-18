package com.irnin.games.mitria.entity;

import com.irnin.games.mitria.Directions;
import com.irnin.games.mitria.engine.Config;

public class Player extends Entity{
    // FIXED position on map
    public final int screenX;
    public final int screenY;

    public Player() {
        // FIXED POSITION ON SCREEN
        screenX = Config.screenWidth / 2 - (Config.tileSize / 2);
        screenY = Config.screenHeight / 2 - (Config.tileSize / 2);

        // SETUP VARIABLES
        walkingSpeed = 7;

        // LOADING GRAPHICS
        String[] spritesPaths = {
                "/player/boy_up_1.png",
                "/player/boy_up_2.png",
                "/player/boy_down_1.png",
                "/player/boy_down_2.png",
                "/player/boy_left_1.png",
                "/player/boy_left_2.png",
                "/player/boy_right_1.png",
                "/player/boy_right_2.png"};

        addGraphics(spritesPaths);
    }

    public void movePlayer(int dx, int dy, Directions direction) {
        walkAnimation();

        this.worldX += (dx * walkingSpeed);
        this.worldY += (dy * walkingSpeed);

        setDirection(direction);
    }

    public void setPosition(int x, int y) {
        this.worldX = Config.tileSize * x;
        this.worldY = Config.tileSize * y;
    }
}
