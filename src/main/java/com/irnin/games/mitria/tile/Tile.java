package com.irnin.games.mitria.tile;

import com.google.gson.annotations.Expose;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Tile {

    // FIELDS
    @Expose private Integer id;
    @Expose private String name;
    @Expose private boolean collision = false;
    private BufferedImage image;

    // GETTERS
    public Integer getId() {
        return id;
    }

    public boolean hasCollision() {
        return collision;
    }

    public BufferedImage getImage() {
        return image;
    }

    // METHODS
    public void setBufferedImage() throws IOException {
        String tilePath = String.format("/tiles/%s.png", name);

        try (InputStream is = getClass().getResourceAsStream(tilePath)) {
            if (is != null) {
                image = ImageIO.read(is);

                if (image == null) {
                    loadDefaultBufferedImage();
                    System.err.println("Can not load image from file: " + tilePath);
                }
            } else {
                loadDefaultBufferedImage();
                System.err.println("Can not find image: " + tilePath);
            }

        } catch (IOException e) {
            loadDefaultBufferedImage();
            System.err.println("Error loading image: " + tilePath);
            e.printStackTrace();
        }
    }

    private void loadDefaultBufferedImage() {
        String tilePath = "/tiles/noTexture.png";

        try (InputStream is = getClass().getResourceAsStream(tilePath)) {
            // This file could always be available
            assert is != null;
            image = ImageIO.read(is);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
