package com.irnin.games.mitria.entity;
import java.util.Random;

public class Map {
    private com.irnin.games.mitria.engine.Config Config;
    private static Map mapInstance;
    int map_width = 1280; //Config.MAP_X_WIDTH;
    int map_height = 720; // Config.MAP_Y_HEIGHT;
    int block_offset = 16; //Config.BLOCK_OFFSET;
    int[][] map_array;


    public Map() {
        int[][] map_array = new int[map_width][map_height];
        this.map_array = map_array;



    }



    public void drawMap() {
        if (Map.getMapInstance() == null) {
            System.out.println("getMapInstance  is null although everyone says it cannot be.");
        }

        int i = 0, j = 0, k = 0;
        Random rand = new Random();
        int random_number_in_0_1 = rand.nextInt(2);

        while (i < map_width-1) {


            while (j < map_height) {

                if ((j += block_offset) < map_height) {
                    j += block_offset;
                    mapInstance.map_array[i][j] = random_number_in_0_1;
                    System.out.print(map_array[i][j]);
                    random_number_in_0_1 = rand.nextInt(2);
                }
            }
            j = 0;
            System.out.println(" ");
            i += block_offset;
        }

    }
    @Override
    public String toString() {
        return "not-null";
    }
    public static Map getMapInstance() {
        if (mapInstance == null) {
            mapInstance = new Map();
        }
        return mapInstance;
    }
}
