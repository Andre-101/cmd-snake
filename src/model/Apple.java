package model;

import java.util.Random;

public class Apple {
    Random rd = new Random();
    private final int x;
    private final int y;
    public Apple() {
        x = rd.nextInt(Parameters.WIDTH.getValue()-2)+1;
        y = rd.nextInt(Parameters.HEIGHT.getValue()-2)+1;
    }

    public int getPositionX() {
        return x;
    }

    public int getPositionY() {
        return y;
    }
}
