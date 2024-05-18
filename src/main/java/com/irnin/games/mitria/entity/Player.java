package com.irnin.games.mitria.entity;

import com.irnin.games.mitria.Directions;
import com.irnin.games.mitria.engine.Game;

import javax.imageio.ImageIO;

public class Player extends Entity{

    private static Game game = Game.getGameInstance();
    static int gameWidth = game.getGAME_WIDTH();
    static int gameHeight = game.getGAME_HEIGHT();


    public int screenX;
    public int screenY;

    public Player() {
        this.screenX = gameWidth/2;
        this.screenY = gameHeight/2;

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
}
