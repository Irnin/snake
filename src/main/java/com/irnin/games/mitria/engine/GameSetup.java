package com.irnin.games.mitria.engine;

public class GameSetup {

    static final int originalTileSize = 16;
    static final int scale = 4;
    static public final int tileSize = originalTileSize * scale;
    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 12;
    public static final int screenWidth = maxScreenCol * tileSize;
    public static final int screenHeight = maxScreenRow * tileSize;
    public static final int FPSLimit = 60;
}
