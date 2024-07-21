package com.javarush.task.jdk13.task53.task5303;

import com.javarush.engine.cell.*;

/**
 * The main class for the Snake game.
 */
public class SnakeGame extends Game {

    // Game field dimensions
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake; // The snake object
    private int turnDelay; // Delay between game turns in milliseconds
    private Apple apple; // The apple object
    private boolean isGameStopped; // Flag to check if the game is stopped
    private static final int GOAL = 28; // The goal length of the snake to win
    private int score; // The score of the game

    private static final String GAME_OVER_MESSAGE = "GAME OVER!";
    private static final String WIN_MESSAGE = "YOU WIN!";

    /**
     * Initializes the game screen and starts the game.
     */
    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT); // Set the size of the game screen
        createGame(); // Start a new game
    }

    /**
     * Creates a new game, initializes the snake, apple, and other game variables.
     */
    private void createGame() {
        snake = new Snake(WIDTH / 2, HEIGHT / 2); // Initialize the snake in the center of the game field
        isGameStopped = false; // Set the game as not stopped
        score = 0; // Initialize the score
        setScore(score); // Display the initial score
        createNewApple(); // Create the first apple
        drawScene(); // Draw the initial game scene
        turnDelay = 300; // Set the initial turn delay
        setTurnTimer(turnDelay); // Start the game timer
    }

    /**
     * Draws the entire game scene including the snake and the apple.
     */
    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.GRAY, ""); // Clear the game field
            }
        }
        snake.draw(this); // Draw the snake on the game field
        apple.draw(this); // Draw the apple on the game field
    }

    /**
     * Updates the game state on each turn.
     *
     * @param step The current step of the game.
     */
    @Override
    public void onTurn(int step) {
        snake.move(apple); // Move the snake and check for apple consumption
        if (!apple.isAlive) { // If the apple was eaten
            score += 5; // Increase the score
            setScore(score); // Update the displayed score
            turnDelay -= 10; // Speed up the game
            setTurnTimer(turnDelay); // Update the game timer
            createNewApple(); // Create a new apple
        }
        if (!snake.isAlive) { // If the snake is dead
            gameOver(); // End the game
        }
        if (snake.getLength() > GOAL) { // If the snake reaches the goal length
            win(); // The player wins the game
        }
        drawScene(); // Redraw the game scene
    }

    /**
     * Handles key press events for controlling the snake and restarting the game.
     *
     * @param key The key that was pressed.
     */
    @Override
    public void onKeyPress(Key key) {
        if (key == Key.SPACE && isGameStopped) { // If SPACE is pressed and the game is stopped
            createGame(); // Restart the game
        }
        if (snake != null) { // If the snake exists
            switch (key) {
                case LEFT:
                    snake.setDirection(Direction.LEFT);
                    break;
                case UP:
                    snake.setDirection(Direction.UP);
                    break;
                case RIGHT:
                    snake.setDirection(Direction.RIGHT);
                    break;
                case DOWN:
                    snake.setDirection(Direction.DOWN);
                    break;
                default:
                    break;
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
            newX = getRandomNumber(WIDTH); // Generate a random x-coordinate
            newY = getRandomNumber(HEIGHT); // Generate a random y-coordinate
            apple = new Apple(newX, newY); // Create a new apple
        }
        while (snake.checkCollision(apple)); // Repeat if the apple is placed on the snake
    }

    /**
     * Ends the game and displays a "GAME OVER" message.
     */
    private void gameOver() {
        stopTurnTimer(); // Stop the game timer
        isGameStopped = true; // Set the game as stopped
        showMessageDialog(Color.BLACK, GAME_OVER_MESSAGE, Color.WHITE, 75); // Display "GAME OVER!" message
    }

    /**
     * Ends the game and displays a "YOU WIN!" message.
     */
    private void win() {
        stopTurnTimer(); // Stop the game timer
        isGameStopped = true; // Set the game as stopped
        showMessageDialog(Color.BLUEVIOLET, WIN_MESSAGE, Color.FLORALWHITE, 75); // Display "YOU WIN!" message
    }
}
