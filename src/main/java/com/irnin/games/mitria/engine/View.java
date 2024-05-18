package com.irnin.games.mitria.engine;

import com.irnin.games.mitria.tile.Tiles;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class View extends JPanel {
    private Model model;

    // FONTS
    Font arial_40 = new Font("Arial", Font.PLAIN, 40);

    public View(Model model) {
        this.model = model;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background
        DrawMap(g);

        // Draw the player
        g.drawImage(model.player.getSprite(), model.player.screenX, model.player.screenY,  null);

        // Draw the FPS
        if(model.displayDebugInfo) {
            g.setFont(arial_40);
            g.setColor(Color.YELLOW);
            g.drawString("FPS: " + model.FPS, 0, 40);
            g.drawString( model.player.getWorldX() + "x " + model.player.getWorldY() + "y" , 0, 80);
        }
    }

    private void DrawMap(Graphics g) {

        // Loop variables
        int worldCol = 0;
        int worldRow = 0;

        // Loop through all tiles
        while (worldCol < model.map.getColumns() && worldRow < model.map.getRows()) {

            Tiles tile = model.map.tileGrid[worldCol][worldRow];

            int worldX = worldCol * Config.tileSize;
            int worldY = worldRow * Config.tileSize;
            int screenX = worldX - model.player.getWorldX() + model.player.screenX;
            int screenY = worldY - model.player.getWorldY() + model.player.screenY;

            // Display only tiles that are visible

            if(     worldX + Config.tileSize > model.player.getWorldX() - model.player.screenX &&
                    worldX - Config.tileSize < model.player.getWorldX() + model.player.screenX &&
                    worldY + Config.tileSize > model.player.getWorldY() - model.player.screenY &&
                    worldY - Config.tileSize < model.player.getWorldY() + model.player.screenY) {

                // Getting image for tile
                BufferedImage image = model.tiles.get(tile).getTexture();

                // Displaying
                g.drawImage(image, screenX, screenY, null);
            }

            // Increment the loop
            worldCol++;

            if(worldCol == model.map.getRows()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
