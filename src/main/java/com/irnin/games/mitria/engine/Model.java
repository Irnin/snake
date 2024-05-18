package com.irnin.games.mitria.engine;

import com.google.gson.Gson;
import com.irnin.games.mitria.Directions;
import com.irnin.games.mitria.entity.Player;
import com.irnin.games.mitria.map.Map;
import com.irnin.games.mitria.tile.Tile;
import com.irnin.games.mitria.tile.Tiles;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Model {
    public Player player;

    // INPUTS
    public boolean upPressed, downPressed, leftPressed, rightPressed, displayDebugInfo;
    public int FPS = 0;

    // TILES
    public final HashMap<Tiles, Tile> tiles = new HashMap<Tiles, Tile>();
    public Map map;

    public Model() {
        player = new Player();

        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        displayDebugInfo = false;

        // Loading tiles
        tiles.put(Tiles.NOTEXTURE, new Tile(0, "noTexture", "/tiles/noTexture.png"));
        tiles.put(Tiles.GRASS, new Tile(1, "grass", "/tiles/grass.png"));
        tiles.put(Tiles.EARTH, new Tile(2, "earth", "/tiles/earth.png"));
        tiles.put(Tiles.SAND, new Tile(3, "sand", "/tiles/sand.png"));
        tiles.put(Tiles.WATER, new Tile(4, "water", "/tiles/water.png"));
        tiles.put(Tiles.TREE, new Tile(5, "tree", "/tiles/tree.png"));
        tiles.put(Tiles.WALL, new Tile(6, "wall", "/tiles/wall.png"));

        // Loading map 01
        loadMap("/maps/map01.json");

        // Generate map DOESN'T WORK
//        map = new Map(30, 20);
//        map.generateMap();
    }

    public void update(double deltaTime) {

    }

    // METHODS
    public void movePlayer(int dx, int dy, Directions direction) {
        player.movePlayer(dx, dy, direction);
        Tools.updateDebugInfo("X = " + player.getWorldX() + " Y = " + player.getWorldY());
    }

    private void loadMap(String path) {
        try {
            Gson gson = new Gson();
            InputStream is = getClass().getResourceAsStream(path);
            InputStreamReader reader = new InputStreamReader(is);
            map = gson.fromJson(reader, Map.class);

            player.setPosition(map.getStartupX(), map.getStartupY());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

