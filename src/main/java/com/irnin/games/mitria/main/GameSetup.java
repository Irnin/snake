package com.irnin.games.mitria.main;

public class GameSetup {
    private static boolean displayPlayer;
    private static boolean displayPlayerCollisionBlock;
    private static boolean displayWorldGrid;
    private static boolean printFPS;

    public static void loadConfiguration() {
        displayPlayer = true;
        displayPlayerCollisionBlock = false;
        displayWorldGrid = false;
        printFPS = true;
    }

    public static boolean isDisplayPlayer() {
        return displayPlayer;
    }

    public static boolean isDisplayPlayerCollisionBlock() {
        return displayPlayerCollisionBlock;
    }

    public static boolean isDisplayWorldGrid() {
        return displayWorldGrid;
    }

    public static boolean isPrintFPS() {
        return printFPS;
    }
}
