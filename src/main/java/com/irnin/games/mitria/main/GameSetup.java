package com.irnin.games.mitria.main;

public class GameSetup {
    private static boolean displayPlayer;
    private static boolean displayPlayerCollisionBlock;
    private static boolean displayWorldGrid;
    private static boolean printFPS;
    private static boolean playMusic;
    private static boolean playSE;

    public static void loadConfiguration() {
        displayPlayer = true;
        displayPlayerCollisionBlock = true;
        displayWorldGrid = true;
        printFPS = true;
        playMusic = false;
        playSE = true;
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

    public static boolean isPlayMusic() {
        return playMusic;
    }

    public static boolean isPlaySE() {
        return playSE;
    }
}
