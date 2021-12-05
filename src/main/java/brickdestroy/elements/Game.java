package brickdestroy.elements;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import brickdestroy.gui.MainFrame;

public class Game {

    private int width = MainFrame.WIDTH;
    private int height = MainFrame.HEIGHT;

    private Brick[] bricks;
    private Brick[][] levels;
    private int brickCount = 30;

    private Point2D initialPos;
    private int initialPosX;
    private int initialPosY;

    private Player player;

    private BallRubber ball;
    private double ballSpeed = 0;

    private int level = 0;
    private int attempts = 3;
    private int score = 0;
    private int totalScore = 0;
    private boolean stopped = true;
    private boolean ballLost = false;
    private boolean completed = false;

    public Game() {

        // Creating the levels
        Rectangle frameBounds = new Rectangle(width, height);
        this.levels = new Levels().makeLevels(frameBounds, brickCount, 3, 6 / 2);

        // Defining the Player and Ball's initial position
        this.initialPosX = width / 2;
        this.initialPosY = (int) (height * 0.95);
        this.initialPos = new Point(initialPosX, initialPosY);

        // Defining the player and initialising its speed
        this.player = new Player(initialPos, (int) (width * 1.0 / 4.0), (int) (height * 1.0 / 45.0));
        player.setSpeed((int) (width * 0.0065));

        // Defining the Ball and setting its properties
        this.ball = new BallRubber(initialPos, (int) (width * 1.0 / 60.0));
        this.ballSpeed = width * 0.0091;
        setInitialPos();
        randomBallAngle();

        // Initialise the first level
        nextLevel();
    }

    public void tick() {
        if (!stopped) {
            player.move();
            ball.move();
            checkImpacts();
            if (ballLost) {
                if (attempts == 0) {
                    totalScore += score;
                }
                stopped = true;
                ballReset();
            } else if (brickCount == 0) {
                if (level < levels.length) {
                    stopped = true;
                } else {
                    completed = true;
                    stopped = true;
                }
                totalScore += score;
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
        if (ball.getDown().getY() >= height) { // Ball hits bottom border
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
        if (ball.getLeft().getX() + ball.getSpeedX() <= 0 || ball.getRight().getX() + ball.getSpeedX() >= width) {
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
        String brickName;
        boolean state = false;

        // Check every brick
        for (Brick b : bricks) {
            if (!b.isBroken()) {
                brickName = b.getClass().getSimpleName();
                brickBounds = b.getBounds();
                if (ballBounds.intersects(brickBounds)) {

                    // Vertical impact
                    if (brickBounds.contains(ball.getDown())) {
                        ball.reverseY();
                        state = b.setImpact(ball.getDown(), "up");
                    } else if (brickBounds.contains(ball.getUp())) {
                        ball.reverseY();
                        state = b.setImpact(ball.getUp(), "down");
                    }
                    // Horizontal
                    else if (brickBounds.contains(ball.getLeft())) {
                        ball.reverseX();
                        state = b.setImpact(ball.getLeft(), "left");
                    } else if (brickBounds.contains(ball.getRight())) {
                        ball.reverseX();
                        state = b.setImpact(ball.getRight(), "right");
                    }

                    // Determining score based on brick type
                    if (brickName.equals("BrickClay")) {
                        score += 10;
                    } else if (brickName.equals("BrickCement")) {
                        score += 20;
                    } else if (brickName.equals("BrickSteel")) {
                        score += 30;
                    }

                    return state;
                }
            }
        }
        return false;
    }

    // Set the initial position of the Player and the Ball
    private void setInitialPos() {
        player.setLocation(initialPos);
        ball.setLocation(initialPosX, initialPosY - ball.getBounds().getWidth());
    }

    // Use the right-angle triangle formula to determine the Ball's launch angle
    private void randomBallAngle() {
        Random rand = new Random();
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
        return stopped;
    }

    public void setGameStopped(boolean b) {
        stopped = b;
    }

    public void nextLevel() {
        if (level < levels.length) {
            bricks = levels[level++];
            this.brickCount = bricks.length;
            ballReset();
            score = 0;
        }
    }

    public void levelReset() {
        for (Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        score = 0;
        ballReset();
        resetBallCount();
    }

    public void ballReset() {
        setInitialPos();
        randomBallAngle();
        ballLost = false;
    }

    public void resetBallCount() { // change name to resetAttempts
        attempts = 3;
    }

    public int getBrickCount() {
        return brickCount;
    }

    public int getAttemptCount() {
        return attempts;
    }

    public int getScore() {
        return score;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public boolean isGameCompleted() {
        return completed;
    }

    // Methods used by the debug panel
    public void setBallSpeed(double speed) {
        double ratio = speed / ballSpeed;

        ball.setSpeed(ball.getSpeedX() * ratio, ball.getSpeedY() * ratio);
        this.ballSpeed = speed;
    }

    public double getBallSpeed() {
        return ballSpeed;
    }

    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Render the bricks
        for (Brick b : bricks) {
            if (!b.isBroken()) {
                b.render(g2d);
            }
        }

        // Render the player and the ball
        player.render(g2d);
        ball.render(g2d);
    }

}
