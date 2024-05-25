package com.irnin.games.snake.engine;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tools {

    public static BufferedImage getImage(String path) {
        BufferedImage image = null;
        try {
            image = javax.imageio.ImageIO.read(Tools.class.getResource(path));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    public static BufferedImage scaleImage(BufferedImage original) {
        int scale = Config.tileSize;

        BufferedImage scaledImage = new BufferedImage(scale, scale, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, scale, scale, null);
        g2.dispose();

        return scaledImage;
    }
}
