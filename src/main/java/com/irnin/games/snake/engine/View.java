package com.irnin.games.snake.engine;

import com.irnin.games.snake.enums.Tiles;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class View extends JPanel {
    private Model model;

    // FONTS
    Font arial_40 = new Font("Arial", Font.PLAIN, 40);
    Font arial_80 = new Font("Arial", Font.PLAIN, 80);
    Font arial_120_BOLD = new Font("Arial", Font.BOLD, 120);

    public View(Model model) {
        this.model = model;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        DrawMap(g);
        drawGUI(g);
        super.repaint();
    }

    private void DrawMap(Graphics g) {
        int screenX = 0;
        int screenY = 0;
        int tileSize = Config.tileSize;
        BufferedImage image;

        for(int i = 0; i < Config.rowsOnScreen; i++) {
            for(int j = 0; j < Config.columnsOnScreen; j++) {

                int tile = model.getFromBoardAt(i, j);

                switch(tile) {
                    case -1 -> image = model.tiles.get(Tiles.APPLE).getTexture();
                    case 0 -> image = model.tiles.get(Tiles.GRASS).getTexture();
                    default -> {
                        if(tile == model.getSnakeLength()) image = model.tiles.get(Tiles.HEAD).getTexture();
                        else image = model.tiles.get(Tiles.SNAKE).getTexture();
                    }
                }

                g.drawImage(image, screenX, screenY, null);

                if(model.displayDebugInfo) {
                    g.setFont(arial_40);
                    g.setColor(Color.yellow);
                    String text = "DEBUG";
                    int x = 10;
                    int y = 80;
                    g.drawString(text, x, y);

                    g.setColor(Color.white);
                    text = "border collision: " + model.borderCollision;
                    x = 10;
                    y = 140;
                    g.drawString(text, x, y);
                }

                if(model.displayDebugGrid) {
                    g.setColor(Color.YELLOW);
                    g.drawRect(screenX, screenY, tileSize, tileSize);

                    g.setColor(Color.BLACK);
                    String text = String.valueOf(tile);
                    int textWidth = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
                    int textHeight = (int)g.getFontMetrics().getStringBounds(text, g).getHeight();
                    int x = screenX + Config.tileSize / 2 - textWidth / 2;
                    int y = screenY + Config.tileSize - textHeight / 2;

                    g.drawString(String.valueOf(tile), x, y);
                }

                screenX += tileSize;
            }
            screenX = 0;
            screenY += tileSize;
        }
    }

    private void drawGUI(Graphics g) {
        g.setFont(arial_40);
        g.setColor(Color.white);

        String text = "Score: " + model.getScore();
        int x = 10;
        int y = 40;
        g.drawString(text, x, y);

        text = "Speed: " + model.getSpeedLevel();
        int textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = Config.screenWidth - textLength - 10;
        y = 40;
        g.drawString(text, x, y);

        if(model.isGameOver()) gameOverGUI(g);
    }

    private void gameOverGUI(Graphics g) {
        String text;
        int textLength;
        int x;
        int y;

        g.setFont(arial_120_BOLD);
        g.setColor(Color.red);
        text = "Game Over";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = Config.screenWidth / 2 - textLength / 2;
        y = Config.screenHeight / 2 - (Config.tileSize * 4);
        g.drawString(text, x, y);

        g.setFont(arial_40);
        g.setColor(Color.white);
        text = "Your score is " + model.getScore();
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = Config.screenWidth / 2 - textLength / 2;
        y = Config.screenHeight / 2 - (Config.tileSize * 4) + 70;
        g.drawString(text, x, y);

        g.setFont(arial_40);
        g.setColor(Color.white);
        text = "Press space to start new game";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = Config.screenWidth / 2 - textLength / 2;
        y = Config.screenHeight / 2 - (Config.tileSize * 4) + 140;
        g.drawString(text, x, y);
    }
}
