package com.irnin.games.mitria.main;

import com.irnin.games.mitria.object.OBJ_Chest;
import com.irnin.games.mitria.object.OBJ_Door;
import com.irnin.games.mitria.object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.objects[0] = new OBJ_Key();
        gp.objects[0].worldX = 23 * gp.tileSize;
        gp.objects[0].worldY = 7 * gp.tileSize;

        gp.objects[1] = new OBJ_Key();
        gp.objects[1].worldX = 23 * gp.tileSize;
        gp.objects[1].worldY = 40 * gp.tileSize;

        gp.objects[2] = new OBJ_Key();
        gp.objects[2].worldX = 37 * gp.tileSize;
        gp.objects[2].worldY = 7 * gp.tileSize;

        gp.objects[3] = new OBJ_Door();
        gp.objects[3].worldX = 10 * gp.tileSize;
        gp.objects[3].worldY = 11 * gp.tileSize;

        gp.objects[4] = new OBJ_Door();
        gp.objects[4].worldX = 8 * gp.tileSize;
        gp.objects[4].worldY = 28 * gp.tileSize;

        gp.objects[5] = new OBJ_Door();
        gp.objects[5].worldX = 12 * gp.tileSize;
        gp.objects[5].worldY = 22 * gp.tileSize;

        gp.objects[6] = new OBJ_Chest();
        gp.objects[6].worldX = 10 * gp.tileSize;
        gp.objects[6].worldY = 7 * gp.tileSize;
    }
}
