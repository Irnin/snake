package com.irnin.games.mitria.engine;

import com.irnin.games.mitria.Directions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    private Model model;

    public Controller(Model model) {

        this.model = model;
    }

    public void handleInput() {
        if (model.upPressed) {
            model.movePlayer(0, -1, Directions.NORTH);
        }
        else if (model.downPressed) {
            model.movePlayer(0, 1, Directions.SOUTH);
        }
        else if (model.leftPressed) {
            model.movePlayer(-1, 0, Directions.WEST);
        }
        else if (model.rightPressed) {
            model.movePlayer(1, 0, Directions.EAST);
        }
    }

    // INPUT

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_W -> model.upPressed = true;
            case KeyEvent.VK_S -> model.downPressed = true;
            case KeyEvent.VK_A -> model.leftPressed = true;
            case KeyEvent.VK_D -> model.rightPressed = true;
            case KeyEvent.VK_F1 -> model.displayDebugInfo = !model.displayDebugInfo;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_W -> model.upPressed = false;
            case KeyEvent.VK_S -> model.downPressed = false;
            case KeyEvent.VK_A -> model.leftPressed = false;
            case KeyEvent.VK_D -> model.rightPressed = false;
        }
    }
}