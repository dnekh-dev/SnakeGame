package com.javarush.task.jdk13.task53.task5303;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake extends GameObject {

    private List<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";

    public Snake(int x, int y) {
        super(x, y);
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void draw(Game game) {
        for (int i = 0; i < snakeParts.size(); i++) {
            if (i == 0) {
                game.setCellValue(snakeParts.get(i).x, snakeParts.get(i).y, HEAD_SIGN);
            } else {
                game.setCellValue(snakeParts.get(i).x, snakeParts.get(i).y, BODY_SIGN);
            }
        }
    }
}
