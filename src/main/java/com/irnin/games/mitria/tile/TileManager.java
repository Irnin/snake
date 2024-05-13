package com.irnin.games.mitria.tile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.irnin.games.mitria.main.GamePanel;
import com.irnin.games.mitria.main.GameSetup;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TileManager {
    GamePanel gp;
    public ArrayList<Tile> tiles = new ArrayList<>();
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldCol];

        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/tiles/tiles.json")));
            Type tileListType = new TypeToken<ArrayList<Tile>>() {}.getType();
            tiles = gson.fromJson(json, tileListType);

            for(Tile t: tiles) {
                t.setBufferedImage();
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // TODO change format from txt to json... make it more professional
    public void loadMap(String mapName) {

        // TODO rewrite loop
        try{
            InputStream is = getClass().getResourceAsStream(mapName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while(col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

            br.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g2) {
        int worldCol = 0;
        int worldRow = 0;

        // TODO rewrite draw loop to use for

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                    // Getting image for tile
                    BufferedImage image = tiles.stream()
                            .filter(t -> t.getId() != null && t.getId() - 1 == tileNum)
                            .findFirst().get().getImage();

                    // Displaying
                    g2.drawImage(image, screenX, screenY, null);

                    if(GameSetup.isDisplayWorldGrid())
                        g2.drawRect(screenX, screenY, GamePanel.tileSize, GamePanel.tileSize);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
