package com.javarush.task.jdk13.task53.task5303;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private int turnDelay;
    private Apple apple;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        apple = new Apple(5, 5);
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
        /*Apple apple = new Apple(7, 7);
        apple.draw(this);*/
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
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
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