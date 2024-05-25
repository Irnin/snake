package com.irnin.games.snake.engine;

public class Config {

    static final int originalTileSize = 16;
    static final int scale = 4;
    static public final int tileSize = originalTileSize * scale;
    public static final int columnsOnScreen = 16;
    public static final int rowsOnScreen = 12;
    public static final int screenWidth = columnsOnScreen * tileSize;
    public static final int screenHeight = rowsOnScreen * tileSize;
    public static final int FPSLimit = 60;
}
