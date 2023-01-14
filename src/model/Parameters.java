package model;

public enum Parameters {

    WIDTH(20),  //52
    HEIGHT(10), //22
    SNAKE_HEAD('Q'), //Q
    SNAKE_BODY('x'), //x
    APPLE('O'), //O
    EDGE('*'), //*


    SCREEN_AREA(WIDTH.value * HEIGHT.value),
    SCREEN_PERIMETER(2 * WIDTH.value + 2 * HEIGHT.value - 4);


    private final int value;

    Parameters(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
