package com.javarush.task.jdk13.task53.task5303;

import com.javarush.engine.cell.*;

/**
 * Represents an apple in the Snake game.
 */
public class Apple extends GameObject {

    private static final String APPLE_SIGN = "\uD83C\uDF4E";
    public boolean isAlive = true;

    /**
     * Constructs an Apple object at the specified coordinates.
     *
     * @param x The x-coordinate of the apple.
     * @param y The y-coordinate of the apple.
     */
    public Apple(int x, int y) {
        super(x, y);
    }

    /**
     * Draws the apple on the game field.
     *
     * @param game The game instance where the apple is drawn.
     */
    public void draw(Game game) {
        game.setCellValueEx(x, y, Color.NONE, APPLE_SIGN, Color.GREEN, 75);
    }
}