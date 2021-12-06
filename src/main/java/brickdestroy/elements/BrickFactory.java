package brickdestroy.elements;

import java.awt.Dimension;
import java.awt.Point;

public class BrickFactory {

    public static final int CLAY = 1;
    public static final int STEEL = 2;
    public static final int CEMENT = 3;

    public static Brick makeBrick(Point point, Dimension size, int type) {
        switch (type) {
            case CLAY:
                return new BrickClay(point, size.width, size.height);
            case STEEL:
                return new BrickSteel(point, size.width, size.height);
            case CEMENT:
                return new BrickCement(point, size.width, size.height);
            default:
                throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        }
    }

}