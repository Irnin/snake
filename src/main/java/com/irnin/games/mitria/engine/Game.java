package com.irnin.games.mitria.engine;

import com.irnin.games.mitria.entity.Map;

import javax.swing.*;
import java.awt.*;

public class Game {
    private static final int GAME_WIDTH = 1280;
    private static final int GAME_HEIGHT = 720;
    private boolean running;
    private long lastTime;
    private Model model;
    private View view;
    private Controller controller;
    private static Game gameInstance;
    private static Map map;
    protected static JLabel xLabel,yLabel;


    public Game() {
        initializeGame();
    }
    private void  initializeGame() {

        String toString = new String("Game instance = " + gameInstance);
        running = true;
        lastTime = System.nanoTime();
        model = new Model();
        view = new View(model);
        controller = new Controller(model);
        map = Map.getMapInstance();
        map.drawMap();
        initializeGameFrame();
    }

    private void initializeGameFrame() {

        //define player position panel
        JPanel playerPositionPanel = new JPanel();
        xLabel = new JLabel("X = "); // X position
        yLabel = new JLabel("Y = "); // Y position
        xLabel.repaint();
        yLabel.repaint();

        playerPositionPanel.add(xLabel);
        playerPositionPanel.add(yLabel);
        playerPositionPanel.repaint();

        //define game Master panel
        JPanel gameMasterPanel  = new JPanel();
        gameMasterPanel.setLayout(new BorderLayout());
        gameMasterPanel.add(view, BorderLayout.CENTER);
        gameMasterPanel.add(playerPositionPanel, BorderLayout.NORTH);
        gameMasterPanel.setBackground(Color.BLACK);

        //define game Frame
        JFrame gameFrame = new JFrame("Mitria");
        gameFrame.setLayout(new BorderLayout());
        gameFrame.add(gameMasterPanel, BorderLayout.CENTER);
        //gameFrame dimensions as initialized constants + margin
        gameFrame.setSize(GAME_WIDTH+65, GAME_HEIGHT+120);
        gameFrame.setLocationRelativeTo(null); //app launches at screen center
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.addKeyListener(controller);
        gameFrame.setVisible(true);
    }


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

    public static int getGAME_WIDTH()  { return GAME_WIDTH;  }
    public static int getGAME_HEIGHT() { return GAME_HEIGHT; }
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