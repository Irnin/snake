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

    public void displayDebugInfo() {

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
            g.drawString( model.player.worldX + "x " + model.player.worldY + "y" , 0, 80);
        }
    }

    private void DrawMap(Graphics g) {

        // Loop variables
        int worldCol = 0;
        int worldRow = 0;

        // Loop through all tiles
        while (worldCol < model.map.columns && worldRow < model.map.rows) {

            Tiles tile = model.map.tiles[worldCol][worldRow];

            int worldX = worldCol * GameSetup.tileSize;
            int worldY = worldRow * GameSetup.tileSize;
            int screenX = worldX - model.player.worldX + model.player.screenX;
            int screenY = worldY - model.player.worldY + model.player.screenY;

            // Display only tiles that are visible
            if(worldX + GameSetup.tileSize > model.player.worldX - model.player.screenX &&
                    worldX - GameSetup.tileSize < model.player.worldX + model.player.screenX &&
                    worldY + GameSetup.tileSize > model.player.worldY - model.player.screenY &&
                    worldY - GameSetup.tileSize < model.player.worldY + model.player.screenY) {

                // Getting image for tile
                BufferedImage image = model.tiles.get(tile).getTexture();

                // Displaying
                g.drawImage(image, screenX, screenY, null);
            }

            // Increment the loop
            worldCol++;

            if(worldCol == model.map.rows) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
