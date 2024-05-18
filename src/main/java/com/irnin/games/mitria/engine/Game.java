package com.irnin.games.mitria.engine;

import javax.swing.*;

public class Game {
    private final int GAME_WIDTH = 1280;
    private final int GAME_HEIGHT = 720;
    private boolean running;
    private long lastTime;
    private Model model;
    private View view;
    private Controller controller;
    private static Game gameInstance;



    public Game() {
        //gameInstance = new Game();
        String toString =  new String("Dupa");
        running = true;
        lastTime = System.nanoTime();
        model = new Model();
        view = new View(model);
        controller = new Controller(model);

        JFrame frame = new JFrame("Mitria");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.setSize(GAME_WIDTH, GAME_HEIGHT); //frame dimensions as initialized constants
        frame.setLocationRelativeTo(null); //app launches at screen center
        frame.setVisible(true);
        frame.addKeyListener(controller);

    }


    public int getGAME_WIDTH()  { return GAME_WIDTH;  }
    public int getGAME_HEIGHT() { return GAME_HEIGHT; }


    public void run() {
        double drawInterval = (float)1_000_000_000 / GameSetup.FPSLimit;
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