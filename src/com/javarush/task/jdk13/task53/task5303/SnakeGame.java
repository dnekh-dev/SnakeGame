package com.javarush.task.jdk13.task53.task5303;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private static final int GOAL = 28;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        isGameStopped = false;
        score = 0;
        setScore(score);
        createNewApple();
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
    }

    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.GRAY, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    @Override
    public void onTurn(int step) {
        snake.move(apple);
        if (!apple.isAlive) {
            score += 5;
            setScore(score);
            turnDelay -= 10;
            setTurnTimer(turnDelay);
            createNewApple();
        }
        if (!snake.isAlive) {
            gameOver();
        }
        if (snake.getLength() > GOAL) {
            win();
        }
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.SPACE && isGameStopped) {
            createGame();
        }
        if (snake != null) {
            if (key == Key.LEFT) {
                snake.setDirection(Direction.LEFT);
            } else if (key == Key.UP) {
                snake.setDirection(Direction.UP);
            } else if (key == Key.RIGHT) {
                snake.setDirection(Direction.RIGHT);
            } else if (key == Key.DOWN) {
                snake.setDirection(Direction.DOWN);
            }
        }
    }

    private void createNewApple() {
        int newX;
        int newY;
        do {
            newX = getRandomNumber(WIDTH);
            newY = getRandomNumber(HEIGHT);
            apple = new Apple(newX, newY);
        }
        while (snake.checkCollision(apple));
    }

    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "GAME OVER!", Color.WHITE, 75);
    }

    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.BLUEVIOLET, "YOU WIN!", Color.FLORALWHITE, 75);
    }
}