package com.irnin.games.mitria.entity;

import com.irnin.games.mitria.Directions;
import com.irnin.games.mitria.main.GamePanel;
import com.irnin.games.mitria.main.GameSetup;
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
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
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

        // CHECK OBJECT COLLISION
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        if(!collisionOn) {
            switch (direction) {
                case NORTH -> worldY -= speed;
                case SOUTH -> worldY += speed;
                case WEST -> worldX -= speed;
                case EAST -> worldX += speed;
            }
        }

        // Walking animation
        spriteCounter ++;
        if(spriteCounter > 10) {
            spriteNum = (spriteNum + 2) % 2 + 1;
            spriteCounter = 0;
        }
    }

    public void pickUpObject(int i) {
        if(i != 999) {
            String objectName = gp.objects[i].name;

            switch(objectName) {
                case "Key" -> {
                    gp.playSE(1);
                    hasKey++;
                    gp.objects[i] = null;
                    gp.ui.showMessage("You got a key!");
                }

                case "Door" -> {
                    if(hasKey > 0) {
                        gp.playSE(2);
                        gp.objects[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door!");
                    }
                    else {
                        gp.ui.showMessage("You need a keey");
                    }
                }

                case "Boots" -> {
                    gp.playSE(2);
                    speed += 2;
                    gp.objects[i] = null;
                    gp.ui.showMessage("I'm speed");
                }

                case "Chest" -> {
                    gp.ui.gameFinished = true;

                    if(GameSetup.isPlayMusic())
                        gp.stopMusic();

                    if(GameSetup.isPlaySE())
                        gp.playSE(4);
                }
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case NORTH -> {
                switch(spriteNum) {
                    case 1 -> image = up1;
                    case 2 -> image = up2;
                }
            }
            case SOUTH -> {
                switch(spriteNum) {
                    case 1 -> image = down1;
                    case 2 -> image = down2;
                }
            }
            case WEST -> {
                switch(spriteNum) {
                    case 1 -> image = left1;
                    case 2 -> image = left2;
                }
            }
            case EAST -> {
                switch(spriteNum) {
                    case 1 -> image = right1;
                    case 2 -> image = right2;
                }
            }
        }

        if(GameSetup.isDisplayPlayer())
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        if(GameSetup.isDisplayPlayerCollisionBlock())
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
