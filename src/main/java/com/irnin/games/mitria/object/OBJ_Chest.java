package com.irnin.games.mitria.object;

import com.irnin.games.mitria.main.UtilityTools;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
    public OBJ_Chest() {
        name = "Chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            image = UtilityTools.scaleImage(image);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
