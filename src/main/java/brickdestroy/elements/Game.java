package brickdestroy.elements;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class Game {

    private static final int LEVELS_COUNT = 4;

    private final int CLAY = 1;
    private final int STEEL = 2;
    private final int CEMENT = 3;

    private Rectangle frameBounds;
    private int frameW;
    private int frameH;

    private Brick[] bricks;
    private Brick[][] levels;
    private int level;
    private int brickCount = 30;
    private int lineCount = 3;
    private double brickDimensionRatio = 6 / 2;

    private Player player;
    private Point2D playerPos;
    private int playerPosX;
    private int playerPosY;

    private BallRubber ball;
    private Point2D ballPos;
    private int ballPosX;
    private int ballPosY;

    private int attempts;
    private boolean ballLost;

    private Random rand = new Random();

    public Game(Rectangle frameBounds) {

        // Defining the frame's size
        this.frameBounds = frameBounds;
        this.frameW = (int) frameBounds.getWidth();
        this.frameH = (int) frameBounds.getHeight();

        // Creating the levels
        this.levels = makeLevels(frameBounds, brickCount, lineCount, brickDimensionRatio);

        // Defining the Player's position and creating them
        this.playerPosX = frameW / 2;
        this.playerPosY = (int) (frameH * 0.85);
        this.playerPos = new Point(playerPosX, playerPosY);
        this.player = new Player(playerPos, 150, 10);

        // Defining the Ball's position and creating it
        this.ballPosX = frameW / 2;
        this.ballPosY = playerPosY - 10;
        this.ballPos = new Point(ballPosX, ballPosY);
        this.ball = new BallRubber(ballPos);
        ball.setSpeed(5, -3);

        // Initialising some game properties
        level = 0;
        attempts = 3;
        ballLost = false;

        this.frameBounds = frameBounds;

    }

    private Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio) {
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY);
        tmp[1] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CEMENT);
        tmp[2] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, STEEL);
        tmp[3] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, STEEL, CEMENT);
        return tmp;
    }

    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio,
            int type) {
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

    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, int typeA,
            int typeB) {
        /*
         * if brickCount is not divisible by line count,brickCount is adjusted to the
         * biggest multiple of lineCount smaller then brickCount
         */
        brickCount -= brickCount % lineCount;

        int brickOnLine = brickCount / lineCount;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

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

    public void move() {
        player.move();
        ball.move();
    }

    public void findImpacts() {
        if (player.checkBallImpact(ball)) {
            ball.reverseY();
        } else if (impactWall()) {
            /*
             * for efficiency reverse is done into method impactWall because for every brick
             * program checks for horizontal and vertical impacts
             */
            brickCount--;
        } else if (impactBorder()) {
            ball.reverseX();
        } else if (ball.getPosition().getY() < frameBounds.getY()) {
            ball.reverseY();
        } else if (ball.getPosition().getY() > frameBounds.getY() + frameBounds.getHeight()) {
            attempts--;
            ballLost = true;
        }
    }

    private boolean impactWall() {
        for (Brick b : bricks) {

            switch (b.findImpact(ball)) {
            // Vertical Impact
            case Brick.UP:
                ball.reverseY();
                return b.setImpact(ball.getDown(), Crack.UP);

            case Brick.DOWN:
                ball.reverseY();
                return b.setImpact(ball.getUp(), Crack.DOWN);

            // Horizontal Impact
            case Brick.LEFT:
                ball.reverseX();
                return b.setImpact(ball.getRight(), Crack.RIGHT);

            case Brick.RIGHT:
                ball.reverseX();
                return b.setImpact(ball.getLeft(), Crack.LEFT);
            }
        }
        return false;
    }

    private boolean impactBorder() {
        Point2D p = ball.getPosition();
        return ((p.getX() < frameBounds.getX()) || (p.getX() > (frameBounds.getX() + frameBounds.getWidth())));
    }

    public int getBrickCount() {
        return brickCount;
    }

    public int getBallCount() {
        return attempts;
    }

    public boolean isBallLost() {
        return ballLost;
    }

    public void ballReset() {
        player.setLocation(ballPos);
        ball.setLocation(ballPos);
        int speedX, speedY;
        do {
            speedX = rand.nextInt(5) - 2;
        } while (speedX == 0);
        do {
            speedY = -rand.nextInt(3);
        } while (speedY == 0);

        ball.setSpeed(7, -3);
        ballLost = false;
    }

    public void wallReset() {
        for (Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        attempts = 3;
    }

    public boolean ballEnd() {
        return attempts == 0;
    }

    public boolean isDone() {
        return brickCount == 0;
    }

    public void nextLevel() {
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    public boolean hasLevel() {
        return level < levels.length;
    }

    public void setBallXSpeed(int s) {
        ball.setXSpeed(s);
    }

    public void setBallYSpeed(int s) {
        ball.setYSpeed(s);
    }

    public void resetBallCount() {
        attempts = 3;
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
