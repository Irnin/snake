package com.irnin.games.snake.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    private Model model;

    public Controller(Model model) {

        this.model = model;
    }

    public void handleInput() {


        if (model.upPressed) {
            model.movePlayer(0, -1);
        }
        else if (model.downPressed) {
            model.movePlayer(0, 1);
        }
        else if (model.leftPressed) {
            model.movePlayer(-1, 0);
        }
        else if (model.rightPressed) {
            model.movePlayer(1, 0);
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

            case KeyEvent.VK_UP -> model.upPressed = true;
            case KeyEvent.VK_DOWN -> model.downPressed = true;
            case KeyEvent.VK_LEFT -> model.leftPressed = true;
            case KeyEvent.VK_RIGHT -> model.rightPressed = true;

            case KeyEvent.VK_SPACE -> model.spacePressed = true;

            case KeyEvent.VK_PERIOD -> model.speedUP = true;
            case KeyEvent.VK_COMMA -> model.speedDown = true;

            case KeyEvent.VK_F1 -> model.displayDebugInfo = !model.displayDebugInfo;
            case KeyEvent.VK_F2 -> model.displayDebugGrid = !model.displayDebugGrid;
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

            case KeyEvent.VK_SPACE -> model.spacePressed = false;

            case KeyEvent.VK_PERIOD -> model.speedUP = false;
            case KeyEvent.VK_COMMA -> model.speedDown = false;
        }
    }
}