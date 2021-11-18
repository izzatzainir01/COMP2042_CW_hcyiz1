package brickdestroy.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import brickdestroy.elements.*;

public class Game {

    private int frameW = GameFrame.WIDTH;
    private int frameH = GameFrame.HEIGHT;

    private Brick[] bricks;
    private Brick[][] levels;
    private int brickCount = 30;

    private Point2D initialPos;
    private int initialPosX;
    private int initialPosY;

    private Player player;

    private BallRubber ball;
    private int ballSpeed = 7;

    private int level = 0;
    private int attempts = 3;
    private boolean stopGame = false;
    private boolean ballLost = false;

    private String message;

    private Random rand = new Random();

    public Game() {

        // Creating the levels
        Rectangle frameBounds = new Rectangle(frameW, frameH);
        this.levels = new Levels().makeLevels(frameBounds, brickCount, 3, 6 / 2);

        // Defining the Player and Ball's initial position
        this.initialPosX = frameW / 2;
        this.initialPosY = (int) (frameH * 0.95);
        this.initialPos = new Point(initialPosX, initialPosY);

        // Defining the player
        this.player = new Player(initialPos, (int) (frameW * 1.0 / 4.0), (int) (frameH * 1.0 / 45.0));

        // Defining the Ball and setting its properties
        this.ball = new BallRubber(initialPos, (int) (frameW * 1.0 / 60.0));
        setInitialPos();
        randomBallAngle();

        // Initialise the first level
        nextLevel();

        message = String.format("Bricks: %d Balls %d", brickCount, attempts);
    }

    public void tick() {
        player.move();
        ball.move();
        checkImpacts();
        message = String.format("Bricks: %d Balls %d", brickCount, attempts);
        if (ballLost) {
            if (attempts == 0) {
                wallReset();
                message = "Game over";
            }
            ballReset();
            stopGame = true;
        } else if (brickCount == 0) {
            if (level < levels.length) {
                message = "Go to Next Level";
                stopGame = true;
                ballReset();
                wallReset();
                nextLevel();
            } else {
                message = "ALL WALLS DESTROYED";
                stopGame = true;
            }
        }

    }

    // Check for all impacts and decide what to do upon any of the impacts
    private void checkImpacts() {
        // Check Ball's impacts
        if (checkBallBorderImpact() == -1) // Border horizontal
            ball.reverseX();
        if (checkBallBorderImpact() == 1) // Border vertical
            ball.reverseY();
        if (ball.getDown().getY() >= frameH) { // Ball hits bottom border
            attempts--;
            ballLost = true;
        }
        // Check Player's impacts
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
        Rectangle2D ballBounds = ball.getBounds();
        Rectangle2D brickBounds;
        // Check every brick
        for (Brick b : bricks) {
            if (!b.isBroken()) {
                brickBounds = b.getBounds();
                if (ballBounds.intersects(brickBounds)) {
                    // Vertical impact
                    if (brickBounds.contains(ball.getDown())) {
                        ball.reverseY();
                        return b.setImpact(ball.getDown(), "up");
                    }
                    if (brickBounds.contains(ball.getUp())) {
                        ball.reverseY();
                        return b.setImpact(ball.getUp(), "down");
                    }
                    // Horizontal
                    if (brickBounds.contains(ball.getLeft())) {
                        ball.reverseX();
                        return b.setImpact(ball.getLeft(), "left");
                    }
                    if (brickBounds.contains(ball.getRight())) {
                        ball.reverseX();
                        return b.setImpact(ball.getRight(), "right");
                    }
                }
            }
        }
        return false;
    }

    public void wallReset() {
        for (Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        attempts = 3;
        message = String.format("Bricks: %d Balls %d", brickCount, attempts);
    }

    public void ballReset() {
        setInitialPos();
        randomBallAngle();
        ballLost = false;
    }

    public void nextLevel() {
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    public void resetBallCount() {
        attempts = 3;
    }

    // Set the initial position of the Player and the Ball
    private void setInitialPos() {
        player.setLocation(initialPos);
        ball.setLocation(initialPosX, initialPosY - ball.getBounds().getWidth());
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

    public void movePlayerLeft(boolean b) {
        player.moveLeft(b);
    }

    public void movePlayerRight(boolean b) {
        player.moveRight(b);
    }

    public boolean isGameStopped() {
        return stopGame;
    }

    public void setGameStart() {
        stopGame = false;
    }

    // Methods used by the debug panel
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

        for (Brick b : bricks) {
            if (!b.isBroken()) {
                b.render(g2d);
            }
        }

        g2d.setFont(new Font("Impact", Font.PLAIN, (int) (frameW * 0.03)));
        int fontWidth = g2d.getFontMetrics().stringWidth(message);
        g2d.setColor(Color.BLUE);
        g2d.drawString(message, frameW / 2 - fontWidth / 2, frameH / 2);

        player.render(g2d);

        ball.render(g2d);
    }

}
