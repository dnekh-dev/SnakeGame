package com.javarush.task.jdk13.task53.task5303;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
    }
}