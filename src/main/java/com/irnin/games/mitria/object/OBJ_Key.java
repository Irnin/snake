package com.irnin.games.mitria.object;

import com.irnin.games.mitria.main.UtilityTools;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject{

    public OBJ_Key() {
        name = "Key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            image = UtilityTools.scaleImage(image);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
