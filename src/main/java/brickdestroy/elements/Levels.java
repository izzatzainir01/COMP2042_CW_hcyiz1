package brickdestroy.elements;

import java.awt.Dimension;
import java.awt.Point;

import brickdestroy.gui.MainFrame;

/**
 * A class for creating levels for the {@link Game}. The methods used in this
 * class were originally from the {@code Game} class but I decided to separate
 * them as I personally believe that the {@code Game} shouldn't be responsible
 * for creating levels, rather only getting them.
 * <p>
 * As of writing this, there are current two types of levels:
 * <ul>
 * <li><b>Single</b> type level</li>
 * <li><b>Chessboard</b> type level</li>
 * </ul>
 * <p>
 * It is responsible for defining level types and creating an array of levels.
 */
public class Levels {

    private static final int frameW = MainFrame.WIDTH;

    private static final int LEVELS_COUNT = 4;
    private static final int sizeRatio = 3;

    /**
     * Creates 4 levels, one of which is a single type level while the other three
     * are chessboard type levels with varying types of bricks.
     * 
     * @param brickCount The number of bricks
     * @param lineCount  The number of rows of bricks
     * @return A 2D array of {@code Bricks}
     */
    public static Brick[][] makeLevels(int brickCount, int lineCount) {
        // Minimum amount of bricks is six
        if (brickCount < 6)
            brickCount = 6;

        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(brickCount, lineCount, BrickFactory.CLAY);
        tmp[1] = makeChessboardLevel(brickCount, lineCount, BrickFactory.CLAY,
                BrickFactory.CEMENT);
        tmp[2] = makeChessboardLevel(brickCount, lineCount, BrickFactory.CLAY, BrickFactory.STEEL);
        tmp[3] = makeChessboardLevel(brickCount, lineCount, BrickFactory.STEEL,
                BrickFactory.CEMENT);

        return tmp;
    }

    private static Brick[] makeSingleTypeLevel(int brickCount, int lineCount, int type) {
        /*
         * If brickCount is not divisible by line count,brickCount is adjusted to the
         * biggest multiple of lineCount smaller then brickCount
         */
        brickCount -= brickCount % lineCount;

        int brickOnLine = brickCount / lineCount;

        double brickLen = frameW / brickOnLine;
        double brickHgt = brickLen / sizeRatio;

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
            tmp[i] = BrickFactory.makeBrick(p, brickSize, type);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = new BrickClay(p, brickSize.width, brickSize.height);
        }
        return tmp;

    }

    private static Brick[] makeChessboardLevel(int brickCount, int lineCount, int typeA, int typeB) {
        /*
         * if brickCount is not divisible by line count,brickCount is adjusted to the
         * biggest multiple of lineCount smaller then brickCount
         */
        brickCount -= brickCount % lineCount;

        int brickOnLine = brickCount / lineCount;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = frameW / brickOnLine;
        double brickHgt = brickLen / sizeRatio;

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
            tmp[i] = b ? BrickFactory.makeBrick(p, brickSize, typeA) : BrickFactory.makeBrick(p, brickSize, typeB);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = BrickFactory.makeBrick(p, brickSize, typeA);
        }
        return tmp;
    }

}
