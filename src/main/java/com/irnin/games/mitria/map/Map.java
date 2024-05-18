package com.irnin.games.mitria.map;

import com.irnin.games.mitria.engine.GameSetup;
import com.irnin.games.mitria.tile.Tiles;

public class Map {
    public int columns;
    public int rows;
    public Tiles[][] tiles;

    public Map(int columns, int rows) {

        if(columns < GameSetup.maxScreenCol || rows < GameSetup.maxScreenRow) throw new IllegalArgumentException("Width and height must be greater than 0");

        this.columns = columns;
        this.rows = rows;
        tiles = new Tiles[columns][rows];
    }

    public void putMap(Tiles[][] tiles) {
        this.tiles = tiles;
    }
}
