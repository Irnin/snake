package com.irnin.games.mitria.object;

import com.irnin.games.mitria.main.UtilityTools;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject{
    public OBJ_Door() {
        name = "Door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            image = UtilityTools.scaleImage(image);
        } catch(IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }
}

