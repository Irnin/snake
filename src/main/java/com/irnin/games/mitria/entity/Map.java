package com.irnin.games.mitria.entity;
import java.util.Arrays;
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
        int counter_rows = 0, counter_columns = 0;
        int position_in_row = 0, column_no = 0, k = 0;
        Random rand = new Random();
        int random_number_in_0_1 = rand.nextInt(2);

        while (column_no < map_height) {

          //  if ((column_no + block_offset) < map_height) {

                while (position_in_row < map_width) {

                //    if ((position_in_row + block_offset) < map_width) {
                            mapInstance.map_array[position_in_row][column_no] = random_number_in_0_1;
                            System.out.print(map_array[position_in_row][column_no]);
                            random_number_in_0_1 = rand.nextInt(2);
                            if (column_no == 0 ) {counter_rows++;}
                            position_in_row += block_offset;
                //    }
                }
                position_in_row = 0;
                System.out.println(" ");
                column_no += block_offset;
                counter_columns++;
            //}
        }
        System.out.println("number of objects in a row = " + counter_rows);
        System.out.println("number of column of objects = " + counter_columns);
        printArray(map_array);
    }


    private void printArray(int[][] mapArray) {
        mapInstance.toString(mapArray);
    }


    public String toString(int[][] map_array) {
        return "Map{" +
                "map_array=" + Arrays.toString(map_array) +
                '}';
    }

    public static Map getMapInstance() {
        if (mapInstance == null) {
            mapInstance = new Map();
        }
        return mapInstance;
    }
}
