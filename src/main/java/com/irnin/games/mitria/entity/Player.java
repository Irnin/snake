package com.irnin.games.mitria.entity;

import com.irnin.games.mitria.Directions;
import com.irnin.games.mitria.main.GamePanel;
import com.irnin.games.mitria.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;

        speed = 7;
        direction = Directions.SOUTH;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(!(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)) {
            return;
        }

        // MOVE PLAYER

        if(keyH.upPressed) {
            direction = Directions.NORTH;
        }
        else if(keyH.downPressed) {
            direction = Directions.SOUTH;
        }
        else if(keyH.leftPressed) {
            direction = Directions.WEST;
        }
        else if(keyH.rightPressed) {
            direction = Directions.EAST;
        }

        // Check Tile collision
        collisionOn = false;
        gp.cChecker.checkTile(this);

        if(!collisionOn) {
            switch(direction) {
                case NORTH:
                    worldY -= speed;
                    break;
                case SOUTH:
                    worldY += speed;
                    break;
                case WEST:
                    worldX -= speed;
                    break;
                case EAST:
                    worldX += speed;
                    break;
            }
        }

        // Walking animation
        spriteCounter ++;
        if(spriteCounter > 10) {
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else if(spriteNum == 2) {
                spriteNum = 1;
            }

            spriteCounter = 0;
        }


    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction) {
            case NORTH:
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }

                break;
            case SOUTH:
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }

                break;
            case WEST:
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }

                break;
            case EAST:
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }

                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        // Display colision box
        //g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
