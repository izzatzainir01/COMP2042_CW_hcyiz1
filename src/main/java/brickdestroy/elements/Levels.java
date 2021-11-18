package brickdestroy.elements;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class Levels {

    private static final int LEVELS_COUNT = 4;

    private final int CLAY = 1;
    private final int STEEL = 2;
    private final int CEMENT = 3;

    /**
     * The {@code Levels} class creates levels for the Game. I separated these
     * methods from the {@code Game} class as I personally believe that the Game
     * should not be responsible for <b>creating</b> levels.
     */
    public Levels() {
    }

    public Brick[][] makeLevels(Rectangle frameBounds, int brickCount, int lineCount, double brickDimensionRatio) {
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(frameBounds, brickCount, lineCount, brickDimensionRatio, CLAY);
        tmp[1] = makeChessboardLevel(frameBounds, brickCount, lineCount, brickDimensionRatio, CLAY, CEMENT);
        tmp[2] = makeChessboardLevel(frameBounds, brickCount, lineCount, brickDimensionRatio, CLAY, STEEL);
        tmp[3] = makeChessboardLevel(frameBounds, brickCount, lineCount, brickDimensionRatio, STEEL, CEMENT);
        return tmp;
    }

    private Brick[] makeSingleTypeLevel(Rectangle frameBounds, int brickCount, int lineCount,
            double brickDimensionRatio, int type) {
        /*
         * If brickCount is not divisible by line count,brickCount is adjusted to the
         * biggest multiple of lineCount smaller then brickCount
         */
        brickCount -= brickCount % lineCount;

        int brickOnLine = brickCount / lineCount;

        double brickLen = frameBounds.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickDimensionRatio;

        brickCount += lineCount / 2;

        Brick[] tmp = new Brick[brickCount];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCount)
                break;
            double x = (i % brickOnLine) * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);
            tmp[i] = makeBrick(p, brickSize, type);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = new BrickClay(p, brickSize.width, brickSize.height);
        }
        return tmp;

    }

    private Brick[] makeChessboardLevel(Rectangle frameBounds, int brickCount, int lineCount,
            double brickDimensionRatio, int typeA, int typeB) {
        /*
         * if brickCount is not divisible by line count,brickCount is adjusted to the
         * biggest multiple of lineCount smaller then brickCount
         */
        brickCount -= brickCount % lineCount;

        int brickOnLine = brickCount / lineCount;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = frameBounds.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickDimensionRatio;

        brickCount += lineCount / 2;

        Brick[] tmp = new Brick[brickCount];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCount)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ? makeBrick(p, brickSize, typeA) : makeBrick(p, brickSize, typeB);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = makeBrick(p, brickSize, typeA);
        }
        return tmp;
    }

    private Brick makeBrick(Point point, Dimension size, int type) {
        Brick out;
        switch (type) {
        case CLAY:
            out = new BrickClay(point, size.width, size.height);
            break;
        case STEEL:
            out = new BrickSteel(point, size.width, size.height);
            break;
        case CEMENT:
            out = new BrickCement(point, size.width, size.height);
            break;
        default:
            throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        }
        return out;
    }

}
