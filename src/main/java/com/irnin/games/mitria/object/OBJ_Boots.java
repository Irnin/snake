package com.irnin.games.mitria.object;

import com.irnin.games.mitria.main.UtilityTools;
import jdk.jshell.execution.Util;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends SuperObject{
    public OBJ_Boots() {
        name = "Boots";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            image = UtilityTools.scaleImage(image);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
