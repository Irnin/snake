package com.irnin.games.mitria.engine;

import javax.swing.*;
import java.awt.*;

public class Game {
    // VARIABLES
    private boolean running;
    private long lastTime;
    private final Model model;
    private final View view;
    private final Controller controller;
    public static JLabel debugInfo;
    private static Game gameInstance;

    // INITIALIZATION
    public Game() {
        running = true;
        lastTime = System.nanoTime();
        model = new Model();
        view = new View(model);
        controller = new Controller(model);

        // TODO mapa michała
        //map = Map.getMapInstance();
        //map.drawMap();

        initializeGameFrame();
    }

    // TODO display debug info console on F2
    private void initializeGameFrame() {
        //define player position panel
        JPanel debugInfoConsole = new JPanel();
        debugInfo = new JLabel("");
        debugInfo.repaint();

        debugInfoConsole.add(debugInfo);
        debugInfoConsole.repaint();

        //define game Master panel
        JPanel gameMasterPanel  = new JPanel();
        gameMasterPanel.setLayout(new BorderLayout());
        gameMasterPanel.add(view, BorderLayout.CENTER);
        gameMasterPanel.add(debugInfoConsole, BorderLayout.NORTH);
        gameMasterPanel.setBackground(Color.BLACK);

        //define game Frame
        JFrame gameFrame = new JFrame("Mitria");
        gameFrame.setLayout(new BorderLayout());
        gameFrame.add(gameMasterPanel, BorderLayout.CENTER);
        //gameFrame dimensions as initialized constants + margin
        gameFrame.setSize(Config.screenWidth, Config.screenHeight);
        gameFrame.setLocationRelativeTo(null); //app launches at screen center
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.addKeyListener(controller);
        gameFrame.setVisible(true);
    }

    public void run() {
        double drawInterval = (float)1_000_000_000 / Config.FPSLimit;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while(running) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                controller.handleInput();
                model.update(delta);
                view.repaint();
                delta --;
                drawCount ++;
            }

            if(timer >= 1_000_000_000) {
                model.FPS = drawCount;

                drawCount = 0;
                timer = 0;
            }
        }
    }

    public static Game getGameInstance() {
        if (gameInstance == null) {
            gameInstance = new Game();
        }
        return gameInstance;
    }

    public static void main() {
        gameInstance = getGameInstance();
        gameInstance.run();
    }
}