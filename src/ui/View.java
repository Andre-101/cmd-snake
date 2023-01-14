package ui;

import model.Apple;
import model.SnakeBody;

import java.util.List;

import static model.Parameters.*;

public class View {
    public int[] center() {
        return new int[]{WIDTH.getValue() / 2, HEIGHT.getValue() / 2};
    }
    public int playZone() {
        return SCREEN_AREA.getValue() - SCREEN_PERIMETER.getValue();
    }

    public boolean outOfLimits(int x, int y) {
        return x < 1 || x >= WIDTH.getValue()-1 || y < 1 || y >= HEIGHT.getValue()-1;
    }

    public void winGame() {
        System.out.println(
                """
                        *****************
                        ***  You win  ***
                        *****************"""
        );
        Main.endGame();
    }

    public void endGame() {
        System.out.println(
                """
                        *****************
                        *** Game over ***
                        *****************"""
        );
        Main.endGame();
    }

    public void drawControl(List<SnakeBody> snake, Apple apple) {
        clearScreen();
        for (int y = 0; y < HEIGHT.getValue(); y++) {
            for (int x = 0; x < WIDTH.getValue(); x++) {
                if (drawApple(x,y,apple)) {
                    System.out.print((char) APPLE.getValue());
                } else if (drawSnakeHead(x,y,snake)) {
                    System.out.print((char) SNAKE_HEAD.getValue());
                } else if (drawSnakeBody(x,y,snake)) {
                    System.out.print((char) SNAKE_BODY.getValue());
                } else if (drawEdge(x,y)) {
                    System.out.print((char) EDGE.getValue());
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
        drawScore(snake.size()-1);
    }

    private void drawScore(int score) {
        System.out.println(
                "" +
                        "------------\n" +
                        "  Score: " + score + "\n" +
                        "------------"
        );
    }

    private boolean drawEdge(int x, int y) {
        return x == 0 || y == 0 || x == WIDTH.getValue() - 1 || y == HEIGHT.getValue() - 1;
    }

    private boolean drawSnakeBody(int x, int y, List<SnakeBody> snake) {
        if (snake.size() <= 1) return false;
        for (int i = 1; i < snake.size(); i++) {
            SnakeBody body = snake.get(i);
            if (x == body.getX() && y == body.getY()) {
                return true;
            }
        }
        return false;
    }

    private boolean drawSnakeHead(int x, int y, List<SnakeBody> head) {
        return x == head.get(0).getX() && y == head.get(0).getY();
    }

    private boolean drawApple(int x, int y, Apple apple) {
        return x == apple.getPositionX() && y == apple.getPositionY();
    }

    public void clearScreen() {
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
