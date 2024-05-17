package com.irnin.games.mitria.engine;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private Model model;
    Font arial_40 = new Font("Arial", Font.PLAIN, 40);

    public View(Model model) {
        this.model = model;
    }

    public void displayDebugInfo() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(model.player.getSprite(), model.player.screenX, model.player.screenY,  null);

        if(model.displayDebugInfo) {
            g.setFont(arial_40);
            g.setColor(Color.YELLOW);
            g.drawString("FPS: " + model.FPS, 0, 40);
        }
    }
}
