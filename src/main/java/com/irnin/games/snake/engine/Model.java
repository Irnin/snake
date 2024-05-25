package com.irnin.games.snake.engine;

import com.irnin.games.snake.enums.Speed;
import com.irnin.games.snake.tile.Tile;
import com.irnin.games.snake.enums.Tiles;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class Model {
    private double timeAccumulator = 0;
    // INPUTS
    public boolean upPressed, downPressed, leftPressed, rightPressed, displayDebugInfo, displayDebugGrid, speedUP, speedDown, spacePressed;
    public boolean borderCollision;
    private boolean gameOver;
    public boolean madeMove;
    private Speed speedLevel;

    // SNAKE
    private Point headPoint;
    private int[][] board;
    public int FPS = 0;
    int snakeLength;
    int directionX, directionY;

    // TILES
    public final HashMap<Tiles, Tile> tiles = new HashMap<Tiles, Tile>();
    public final HashMap<Speed, Integer> speedLevelMap = new HashMap<>();

    public Model() {
        // Array stores game board where -1 is fruit, 0 is empty space and positive number is snake body
        board = new int[Config.rowsOnScreen][Config.columnsOnScreen];

        startNewGame();

        // Loading tiles
        tiles.put(Tiles.SNAKE, new Tile(0, "snake", "/tiles/earth.png"));
        tiles.put(Tiles.GRASS, new Tile(1, "grass", "/tiles/grass.png"));
        tiles.put(Tiles.APPLE, new Tile(2, "apple", "/tiles/tree.png"));
        tiles.put(Tiles.HEAD, new Tile(3, "head", "/tiles/sand.png"));

        speedLevelMap.put(Speed.S1, 20);
        speedLevelMap.put(Speed.S2, 18);
        speedLevelMap.put(Speed.S3, 16);
        speedLevelMap.put(Speed.S4, 14);
        speedLevelMap.put(Speed.S5, 12);
        speedLevelMap.put(Speed.S6, 10);
        speedLevelMap.put(Speed.S7, 9);
        speedLevelMap.put(Speed.S8, 8);
        speedLevelMap.put(Speed.S9, 7);
    }

    public void update(double deltaTime) {
        if(gameOver && spacePressed) startNewGame();
        if(gameOver) return;

        if(speedUP) {
            if(speedLevel == Speed.S9) return;
            speedLevel = Speed.values()[speedLevel.ordinal() + 1];
            speedUP = false;
        }

        if(speedDown) {
            if(speedLevel == Speed.S1) return;
            speedLevel = Speed.values()[speedLevel.ordinal() - 1];
            speedDown = false;
        }

        timeAccumulator += deltaTime;

        if (timeAccumulator >= speedLevelMap.get(speedLevel)) {
            updateBoard();

            timeAccumulator = 0;
        }
    }

    // METHODS
    private void startNewGame() {
        speedLevel = Speed.S5;
        gameOver = false;
        borderCollision = false;

        // Set direction to up
        directionY = -1;
        directionX = 0;

        // Empty the board
        for(int i = 0; i < Config.rowsOnScreen; i++) {
            for(int j = 0; j < Config.columnsOnScreen; j++) {
                board[i][j] = 0;
            }
        }

        // Setting up snake's body
        int y = Config.columnsOnScreen / 2;
        headPoint = new Point(y, 5);
        snakeLength = 3;
        board[7][y] = 1;
        board[6][y] = 2;
        board[5][y] = 3;

        // Adding fruit
        addFruit();
    }

    public void movePlayer(int dx, int dy) {
        // prevent eating the head
        if(directionY == -1 && dy == 1) return;
        if(directionY == 1 && dy == -1) return;
        if(directionX == -1 && dx == 1) return;
        if(directionX == 1 && dx == -1) return;

        // Prevent multiple moves in one turn
        if(madeMove) return;

        // Update move direction
        directionX = dx;
        directionY = dy;
        madeMove = true;
    }

    public void updateBoard() {
        madeMove = false;

        headPoint.y += directionY;
        headPoint.x += directionX;

        // Wrap on border or game over
        if(headPoint.y < 0) {
            if(borderCollision) {
                gameOver = true;
                return;
            }
            headPoint.y = Config.rowsOnScreen - 1;
        }
        if(headPoint.y >= Config.rowsOnScreen) {
            if(borderCollision) {
                gameOver = true;
                return;
            }
            headPoint.y = 0;
        }
        if(headPoint.x < 0) {
            if(borderCollision) {
                gameOver = true;
                return;
            }
            headPoint.x = Config.columnsOnScreen - 1;
        }
        if(headPoint.x >= Config.columnsOnScreen) {
            if(borderCollision) {
                gameOver = true;
                return;
            }
            headPoint.x = 0;
        }

        // eatting apple
        if(board[headPoint.y][headPoint.x] == -1) {
            addFruit();
            snakeLength ++;
            board[headPoint.y][headPoint.x] = snakeLength;
            return;
        }

        // Snake bite himself
        else if(board[headPoint.y][headPoint.x] > 1) {
            gameOver = true;
        }

        // Move snake
        for(int i = 0; i < Config.rowsOnScreen; i++) {
            for(int j = 0; j < Config.columnsOnScreen; j++) {
                if(board[i][j] > 0) board[i][j] -= 1;
            }
        }

        board[headPoint.y][headPoint.x] = snakeLength;
    }

    private void addFruit() {
        Random rand = new Random();
        int min = 0;
        int max;

        // X random
        max = Config.columnsOnScreen - 1;
        int x = rand.nextInt((max - min) + 1) + min;

        // Y random
        max = Config.rowsOnScreen - 1;
        int y = rand.nextInt((max - min) + 1) + min;

        if(board[y][x] == 0) board[y][x] = -1;
        else addFruit();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return snakeLength - 3;
    }

    public int getFromBoardAt(int x, int y) {
        return board[x][y];
    }

    public int getSnakeLength() {
        return snakeLength;
    }

    public int getSpeedLevel() {
        int speed = 0;

        switch(speedLevel) {
            case S1 -> speed = 1;
            case S2 -> speed = 2;
            case S3 -> speed = 3;
            case S4 -> speed = 4;
            case S5 -> speed = 5;
            case S6 -> speed = 6;
            case S7 -> speed = 7;
            case S8 -> speed = 8;
            case S9 -> speed = 9;
        }

        return speed;
    }
}

