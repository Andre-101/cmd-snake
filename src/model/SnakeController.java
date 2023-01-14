package model;

import ui.View;

import java.util.LinkedList;
import java.util.List;

public class SnakeController {
    private final List<SnakeBody> snake;
    private int dx;
    private int dy;
    private static volatile Apple apple;
    private final View vw;

    public SnakeController() {
        snake = new LinkedList<>();
        vw = new View();
        snake.add(new SnakeBody(vw.center()[0],vw.center()[1]));
        dx = 1;
        dy = 0;
        apple = new Apple();
        while (checkingOverlap())
            apple = new Apple();
    }

    public void move() {
        SnakeBody head = snake.get(0);
        checkingUpdate(head.getX() + dx, head.getY() + dy);
        snake.add(0, new SnakeBody(head.getX() + dx, head.getY() + dy));
    }

    public void setDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void showScreen() {
        while (checkingOverlap())
            apple = new Apple();
        vw.drawControl(snake, apple);
    }

    private void checkingUpdate(int x, int y) {
        if (checkingOverprint()) vw.winGame();
        if(checkingCrash(x, y)) {
            vw.endGame();
        } checkingEat(x,y);
    }

    private boolean checkingOverprint() {
        return snake.size() == vw.playZone() - 1;
    }

    private boolean checkingOverlap() {
        if (snake.size() > 1) {
            for (SnakeBody body : snake)
                if (apple.getPositionX() == body.getX() && apple.getPositionY() == body.getY()) return true;
        }
        return false;
    }

    private void checkingEat(int x, int y) {
        if (x == apple.getPositionX() && y == apple.getPositionY()) {
            apple = new Apple();
        } else {
            snake.remove(snake.size() - 1);
        }
    }

    private boolean checkingCrash(int x, int y) {
        if (vw.outOfLimits(x,y)) return true;
        for (int i = 1; i < snake.size(); i++) {
            SnakeBody body = snake.get(i);
            if (x == body.getX() && y == body.getY()) return true;
        }
        return false;
    }
}
