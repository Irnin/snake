package com.irnin.games.mitria.entity;

import com.irnin.games.mitria.Directions;
import com.irnin.games.mitria.engine.GameSetup;

public class Player extends Entity{
    // FIXED position on map
    public final int screenX;
    public final int screenY;


    public Player() {
        // STARTUP TILE ON MAP
        worldX = GameSetup.tileSize * 23;
        worldY = GameSetup.tileSize * 21;

        // FIXED POSITION ON SCREEN
        screenX = GameSetup.screenWidth / 2 - (GameSetup.tileSize / 2);
        screenY = GameSetup.screenHeight / 2 - (GameSetup.tileSize / 2);

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
}
