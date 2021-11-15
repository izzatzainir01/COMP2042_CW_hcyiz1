package brickdestroy.elements;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class Levels {

    private final int CLAY = 1;
    private final int STEEL = 2;
    private final int CEMENT = 3;

    Brick[][] bricks;
    private int lines;

    public Levels(Rectangle frameBounds, Brick[][] bricks, int width, int height){

    }

    public void getLevel1() {
        /*
         * if brickCount is not divisible by line count,brickCount is adjusted to the
         * biggest multiple of lineCount smaller then brickCount
         */
        brickCount -= brickCount % lineCount;

        int brickOnLine = brickCount / lineCount;

        double brickLen = drawArea.getWidth() / brickOnLine;
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

    private void getLines(Brick[][] bricks) {

    }
    
}
