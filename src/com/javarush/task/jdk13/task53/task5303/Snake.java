package com.javarush.task.jdk13.task53.task5303;

import com.javarush.engine.cell.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the snake in the Snake game.
 */
public class Snake extends GameObject {

    private List<GameObject> snakeParts = new ArrayList<>(); // List to store the parts of the snake
    private static final String HEAD_SIGN = "\uD83D\uDC7E"; // Unicode symbol for the snake's head
    private static final String BODY_SIGN = "\u26AB"; // Unicode symbol for the snake's body
    public boolean isAlive = true; // Flag to check if the snake is alive
    private Direction direction = Direction.LEFT; // Initial direction of the snake

    /**
     * Constructs a Snake object at the specified coordinates and initializes its body parts.
     *
     * @param x The x-coordinate of the snake's head.
     * @param y The y-coordinate of the snake's head.
     */
    public Snake(int x, int y) {
        super(x, y);
        snakeParts.add(new GameObject(x, y)); // Add the head of the snake
        snakeParts.add(new GameObject(x + 1, y)); // Add the first body part
        snakeParts.add(new GameObject(x + 2, y)); // Add the second body part
    }

    /**
     * Draws the snake on the game field.
     *
     * @param game The game instance where the snake is drawn.
     */
    public void draw(Game game) {
        for (int i = 0; i < snakeParts.size(); i++) {
            if (i == 0) { // If it's the head
                game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, HEAD_SIGN, isAlive ? Color.GREEN : Color.RED, 75);
            } else { // If it's the body
                game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, isAlive ? Color.BLACK : Color.RED, 75);
            }
        }
    }

    /**
     * Sets the direction of the snake's movement.
     *
     * @param direction The new direction of the snake.
     */
    public void setDirection(Direction direction) {
        if ((this.direction == Direction.LEFT || this.direction == Direction.RIGHT) && snakeParts.get(0).x == snakeParts.get(1).x ||
                (this.direction == Direction.UP || this.direction == Direction.DOWN) && snakeParts.get(0).y == snakeParts.get(1).y) {
            return;
        }
        if ((direction == Direction.UP && this.direction != Direction.DOWN) ||
                (direction == Direction.DOWN && this.direction != Direction.UP) ||
                (direction == Direction.LEFT && this.direction != Direction.RIGHT) ||
                (direction == Direction.RIGHT && this.direction != Direction.LEFT)) {
            this.direction = direction; // Set the new direction
        }
    }

    /**
     * Gets the current direction of the snake.
     *
     * @return The current direction of the snake.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Moves the snake. If the snake eats an apple, it grows. If it collides with itself or the walls, it dies.
     *
     * @param apple The apple that the snake can eat.
     */
    public void move(Apple apple) {
        GameObject newHead = createNewHead(); // Create a new head for the snake based on its current direction
        if (checkCollision(newHead) || newHead.x < 0 || newHead.x >= SnakeGame.WIDTH || newHead.y < 0 || newHead.y >= SnakeGame.HEIGHT) {
            isAlive = false; // The snake dies if it collides with itself or moves out of bounds
            return;
        }
        if (apple.x == newHead.x && apple.y == newHead.y) { // Check if the snake eats the apple
            apple.isAlive = false; // The apple is eaten
        } else {
            snakeParts.remove(snakeParts.size() - 1); // Remove the tail to keep the snake length constant if no apple is eaten
        }
        snakeParts.add(0, newHead); // Move the snake by adding the new head at the beginning
    }

    /**
     * Creates a new head for the snake based on its current direction.
     *
     * @return The new head of the snake.
     */
    public GameObject createNewHead() {
        GameObject head = snakeParts.get(0);
        switch (direction) {
            case RIGHT:
                return new GameObject(head.x + 1, head.y);
            case DOWN:
                return new GameObject(head.x, head.y + 1);
            case LEFT:
                return new GameObject(head.x - 1, head.y);
            case UP:
                return new GameObject(head.x, head.y - 1);
            default:
                return head; // This case should never occur
        }
    }

    /**
     * Removes the tail of the snake, making it shorter.
     */
    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1); // Remove the last part of the snake
    }

    /**
     * Checks if the snake collides with the given game object.
     *
     * @param gameObject The game object to check for collision.
     * @return True if the snake collides with the game object, false otherwise.
     */
    public boolean checkCollision(GameObject gameObject) {
        for (GameObject part : snakeParts) {
            if (gameObject.x == part.x && gameObject.y == part.y) {
                return true; // Collision detected
            }
        }
        return false; // No collision
    }

    /**
     * Gets the length of the snake.
     *
     * @return The length of the snake.
     */
    public int getLength() {
        return snakeParts.size(); // Return the size of the snake parts list
    }
}
