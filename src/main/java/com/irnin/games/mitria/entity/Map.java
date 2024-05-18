package com.irnin.games.mitria.entity;

import com.irnin.games.mitria.engine.Config;

import java.util.HashMap;

public class Map {
    private com.irnin.games.mitria.engine.Config Config;
    int map_width  = Config.MAP_X_WIDTH;
    int map_height = Config.MAP_Y_HEIGHT;


    public Map() {
        HashMap<Integer, Integer> mapHashMap = new HashMap<Integer, Integer>();
        drawMap(mapHashMap);
    };

    protected HashMap drawMap (HashMap hashmap) {



        return hashmap;
    }



}
