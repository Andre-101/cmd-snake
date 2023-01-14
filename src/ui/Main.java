package ui;

import model.SnakeController;

import java.io.IOException;

public class Main {
    private static boolean stop;
    private int direction;
    private final SnakeController controller;
    private static Main m;

    public Main() {
        controller = new SnakeController();
        direction = 0;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        m = new Main();
        m.startGame();
    }

    private void startGame() throws IOException, InterruptedException {
        controller.showScreen();
        stop = false;
        while (!stop) {
            int key = System.in.read();
            if (key == 'c' || key == 'C') stop = true;
            else if (key == 'a' || key == 'A') direction = 2;
            else if (key == 'w' || key == 'W') direction = 1;
            else if (key == 'd' || key == 'D') direction = 0;
            else if (key == 's' || key == 'S') direction = 3;

            if (direction == 0) {
                controller.setDirection(1, 0);
            } else if (direction == 1) {
                controller.setDirection(0, -1);
            } else if (direction == 2) {
                controller.setDirection(-1, 0);
            } else if (direction == 3) {
                controller.setDirection(0, 1);
            }

            controller.move();
            if (!stop) controller.showScreen();

            Thread.sleep(100);
        }
    }

    public static void endGame() {
        stop = true;
    }
}