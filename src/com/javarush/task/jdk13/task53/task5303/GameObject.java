package com.javarush.task.jdk13.task53.task5303;

/**
 * Represents a generic game object with x and y coordinates.
 */
public class GameObject {

    public int x;
    public int y;

    /**
     * Constructs a GameObject at the specified coordinates.
     *
     * @param x The x-coordinate of the object.
     * @param y The y-coordinate of the object.
     */
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
