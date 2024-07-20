package com.javarush.task.jdk13.task53.task5303;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake extends GameObject {

    private List<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        super(x, y);
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void draw(Game game) {
        for (int i = 0; i < snakeParts.size(); i++) {
            if (i == 0) {
                game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, HEAD_SIGN, isAlive ? Color.GREEN : Color.RED, 75);
            } else {
                game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, isAlive ? Color.BLACK : Color.RED, 75);
            }
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        setDirection(Direction.UP);
        GameObject newHead = createNewHead();
        if (newHead.x < 0 || newHead.x > 14 || newHead.y < 0 || newHead.y > 14) {
            isAlive = false;
            return;
        }
        snakeParts.add(0, newHead);
        removeTail();
    }

    public GameObject createNewHead() {
        if (direction == Direction.RIGHT) {
            return new GameObject(snakeParts.get(0).x + 1, snakeParts.get(0).y);
    } else if (direction == Direction.DOWN) {
            return new GameObject(snakeParts.get(0).x, snakeParts.get(0).y + 1);
        } else if (direction == Direction.LEFT) {
            return new GameObject(snakeParts.get(0).x - 1, snakeParts.get(0).y);
        }
        return new GameObject(snakeParts.get(0).x, snakeParts.get(0).y - 1);
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }
}
