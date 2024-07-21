package com.javarush.task.jdk13.task53.task5303;

import com.javarush.engine.cell.*;

/**
 * The main class for the Snake game.
 */
public class SnakeGame extends Game {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private static final int GOAL = 28;
    private int score;

    /**
     * Initializes the game screen and starts the game.
     */
    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    /**
     * Creates a new game, initializes the snake, apple, and other game variables.
     */
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

    /**
     * Draws the entire game scene including the snake and the apple.
     */
    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.GRAY, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    /**
     * Updates the game state on each turn.
     *
     * @param step The current step of the game.
     */
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

    /**
     * Handles key press events for controlling the snake and restarting the game.
     *
     * @param key The key that was pressed.
     */
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

    /**
     * Creates a new apple at a random position on the game field.
     * Ensures that the apple does not collide with the snake.
     */
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

    /**
     * Ends the game and displays a "GAME OVER" message.
     */
    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "GAME OVER!", Color.WHITE, 75);
    }

    /**
     * Ends the game and displays a "YOU WIN!" message.
     */
    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.BLUEVIOLET, "YOU WIN!", Color.FLORALWHITE, 75);
    }
}
