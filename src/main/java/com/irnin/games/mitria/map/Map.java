package com.irnin.games.mitria.map;

import com.irnin.games.mitria.engine.Config;
import com.irnin.games.mitria.tile.Tiles;

import java.util.Random;

public class Map {

    // VARIABLES
    private int columns;
    private int rows;
    private int startupX;
    private int startupY;
    private static Map mapInstance;
    public Tiles[][] tileGrid;

    public Map(int columns, int rows) {

        if(columns < Config.columnsOnScreen || rows < Config.rowsOnScreen) throw new IllegalArgumentException("Columns and rows must be greater than screen size");

        this.columns = columns;
        this.rows = rows;
        tileGrid = new Tiles[columns][rows];

        mapInstance = this;
    }

    public Map() {
        this.columns = Config.columnsOnScreen;
        this.rows = Config.rowsOnScreen;
        tileGrid = new Tiles[columns][rows];

        mapInstance = this;
    }

    public static Map getMapInstance() {
        if (mapInstance == null) {
            mapInstance = new Map();
        }
        return mapInstance;
    }

    // GETTERS
    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public int getStartupX() {
        return startupX;
    }

    public int getStartupY() {
        return startupY;
    }

    // TODO implement map generation
    public void generateMap() {
        // variables from config
        int map_width = 1280;
        int map_height = 720;
        int block_offset = Config.tileSize;

        tileGrid = new Tiles[map_width][map_height];
        mapInstance = this;

        int counter_rows = 0, counter_columns = 0;
        int position_in_row = 0, column_no = 0, k = 0;
        Random rand = new Random();
        int random_number_in_0_1 = rand.nextInt(2);

        while (column_no < map_height) {

            //  if ((column_no + block_offset) < map_height) {

            while (position_in_row < map_width) {

                //    if ((position_in_row + block_offset) < map_width) {
                switch (random_number_in_0_1) {
                    case 0 -> mapInstance.tileGrid[position_in_row][column_no] = Tiles.GRASS;
                    case 1 -> mapInstance.tileGrid[position_in_row][column_no] = Tiles.SAND;
                }

                System.out.print(tileGrid[position_in_row][column_no]);
                random_number_in_0_1 = rand.nextInt(2);
                if (column_no == 0 ) {counter_rows++;}
                position_in_row += Config.tileSize;
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
        //printArray(map_array);
    }

}
