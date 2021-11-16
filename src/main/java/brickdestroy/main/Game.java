package brickdestroy.main;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

import brickdestroy.elements.*;

public class Game {

    private int frameW;
    private int frameH;

    private Brick[] bricks;
    private Brick[][] levels;

    private int brickCount = 30;
    private int lineCount = 3;
    private double brickDimensionRatio = 6 / 2;

    private Point2D initialPos;
    private int initialPosX;
    private int initialPosY;

    public Player player;
    private int playerW;
    private int playerH;

    private BallRubber ball;
    private int ballW;
    private int ballSpeed = 6;

    private int level;
    private int attempts;
    private boolean ballLost;

    private Random rand = new Random();

    public Game(Rectangle frameBounds) {

        // Defining the frame's size
        this.frameW = (int) frameBounds.getWidth();
        this.frameH = (int) frameBounds.getHeight();

        // Creating the levels
        this.levels = new Levels().makeLevels(frameBounds, brickCount, lineCount, brickDimensionRatio);

        // Defining the Player and Ball's initial position
        this.initialPosX = frameW / 2;
        this.initialPosY = (int) (frameH * 0.95);
        this.initialPos = new Point(initialPosX, initialPosY);

        // Defining the Player's size and creating it
        this.playerW = (int) (frameW * 1.0 / 4.0);
        this.playerH = (int) (frameH * 1.0 / 45.0);
        this.player = new Player(initialPos, playerW, playerH);

        // Defining the Ball's width, creating it and setting its properties
        this.ballW = (int) (frameW * 1.0 / 60.0);
        this.ball = new BallRubber(initialPos, ballW);
        setInitialPos();
        randomBallAngle();

        // Initialising some game properties
        level = 0;
        attempts = 3;
        ballLost = false;
    }

    public void nextLevel() {
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    public boolean hasLevel() {
        return level < levels.length;
    }

    public void move() {
        player.move();
        ball.move();
    }

    // Check for all impacts and decide what to do upon any of the impacts
    public void checkImpacts() {

        // Check Ball's impacts
        if (checkBallBorderImpact() == -1) // Border horizontal
            ball.reverseX();
        if (checkBallBorderImpact() == 1) // Border vertical
            ball.reverseY();
        if (ball.getDown().getY() >= frameH) { // Ball hits bottom border
            attempts--;
            ballLost = true;
        }

        if (checkPlayerImpact()) // Ball hits Player
            ball.reverseY();

        // Check bricks' impacts
        if (checkBrickImpact()) // Ball hits any brick
            brickCount--;

    }

    // Check for the Ball's impact with the frame's borders
    private int checkBallBorderImpact() {
        // Check for horizontal collision
        if (ball.getLeft().getX() + ball.getSpeedX() <= 0 || ball.getRight().getX() + ball.getSpeedX() >= frameW) {
            return -1;
        }
        // Check for top vertical collision
        if (ball.getUp().getY() + ball.getSpeedY() < 0) {
            return 1;
        }
        return 0;
    }

    // Check for the Ball's impact with the Player
    private boolean checkPlayerImpact() {
        Rectangle playerBounds = player.getBounds();
        if (playerBounds.intersects(ball.getBounds()) && playerBounds.contains(ball.getDown())) {
            return true;
        }
        return false;
    }

    // Check for the Ball's impact with the Bricks
    private boolean checkBrickImpact() {
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

    public void wallReset() {
        for (Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        attempts = 3;
    }

    public void ballReset() {
        // Set the Player and Ball's locations
        setInitialPos();
        // Set random Ball launch angle
        randomBallAngle();

        ballLost = false;
    }

    // Set the initial position of the Player and the Ball
    private void setInitialPos() {
        player.setLocation(initialPos);
        ball.setLocation(initialPosX, initialPosY - ball.getWidth());
    }

    // Use the right-angle triangle formula to determine the Ball's launch angle
    private void randomBallAngle() {
        double tempX, tempY;

        tempX = ballSpeed * rand.nextDouble(0.2, 0.6); // X speed = random ratio of ballSpeed
        tempY = Math.sqrt((ballSpeed * ballSpeed) - (tempX * tempX));// y = sqrt(z^2 - x^2)

        tempX *= rand.nextBoolean() ? 1 : -1; // randomly determine left or right
        tempY *= -1; // Ball starts off going up

        ball.setSpeed(tempX, tempY);
    }

    public void resetBallCount() {
        attempts = 3;
    }

    public boolean isBallLost() {
        return ballLost;
    }

    public boolean ballEnd() {
        return attempts == 0;
    }

    public boolean isDone() {
        return brickCount == 0;
    }

    public int getBrickCount() {
        return brickCount;
    }

    public int getBallCount() {
        return attempts;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBallXSpeed(int s) {
        ball.setXSpeed(s);
    }

    public void setBallYSpeed(int s) {
        ball.setYSpeed(s);
    }

    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        ball.render(g2d);

        for (Brick b : bricks)
            if (!b.isBroken())
                b.render(g2d);

        player.render(g2d);
    }

}
