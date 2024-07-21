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
        if ((this.direction == Direction.LEFT || this.direction == Direction.RIGHT) && snakeParts.get(0).x == snakeParts.get(1).x) {
            return;
        }
        if ((this.direction == Direction.UP || this.direction == Direction.DOWN) && snakeParts.get(0).y == snakeParts.get(1).y) {
            return;
        }

        if (direction == Direction.UP && this.direction == Direction.DOWN) {
            return;
        } else if (direction == Direction.LEFT && this.direction == Direction.RIGHT) {
            return;
        } else if (direction == Direction.RIGHT && this.direction == Direction.LEFT) {
            return;
        } else if (direction == Direction.DOWN && this.direction == Direction.UP) {
            return;
        }

        this.direction = direction;
    }




    public Direction getDirection() {
        return direction;
    }

    public void move(Apple apple) {
        setDirection(Direction.LEFT);
        GameObject newHead = createNewHead();
        if (checkCollision(newHead)) {
            isAlive = false;
            return;
        }
        if (apple.x == newHead.x && apple.y == newHead.y) {
            apple.isAlive = false;
            snakeParts.add(new GameObject(snakeParts.get(snakeParts.size()- 1).x + 1, y ));
        } else {
            if (newHead.x < 0 || newHead.x > 14 || newHead.y < 0 || newHead.y > 14) {
                isAlive = false;
                return;
            }
            snakeParts.add(0, newHead);
            removeTail();
        }
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

    public boolean checkCollision(GameObject gameObject) {
        for (int i = 0; i < snakeParts.size(); i++) {
            if (gameObject.x == snakeParts.get(i).x && gameObject.y == snakeParts.get(i).y) {
                return true;
            }
        }
        return false;
    }

    public int getLength() {
        return snakeParts.size();
    }
}
