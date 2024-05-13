package com.irnin.games.mitria.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTools {
    public static BufferedImage scaleImage(BufferedImage original) {
        int scale = GamePanel.tileSize;

        BufferedImage scaledImage = new BufferedImage(scale, scale, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, scale, scale, null);
        g2.dispose();

        return scaledImage;
    }
}
