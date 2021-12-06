package brickdestroy.elements;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

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
    private double ballSpeed = 9;

    private int level = 0;
    private int attempts = 3;
    private int score = 0;
    private boolean stopped = true;
    private boolean ballLost = false;
    private boolean completed = false;

    public Game() {

        // Creating the levels
        this.levels = Levels.makeLevels(brickCount, 4);

        // Defining the Player and Ball's initial position
        this.initialPosX = width / 2;
        this.initialPosY = (int) (height * 0.95);
        this.initialPos = new Point(initialPosX, initialPosY);

        // Defining the player and initialising its speed
        this.player = new Player(initialPos, (int) (width * 1.0 / 4.0), (int) (height * 1.0 / 45.0));
        player.setSpeed(6);

        // Defining the Ball and setting its properties
        this.ball = new BallRubber(initialPos, (int) (width * 1.0 / 60.0));
        ball.setSpeed(ballSpeed);
        ball.randomBallAngle(true);
        setInitialPos();

        // Initialise the first level
        nextLevel();
    }

    public void tick() {
        if (!stopped) {
            player.move();
            ball.move();
            checkImpacts();
            if (ballLost) {
                stopped = true;
                ballReset();
            } else if (brickCount == 0) {
                if (level < levels.length) {
                    stopped = true;
                } else {
                    completed = true;
                    stopped = true;
                }
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
        if (checkBrickImpact()) // Ball hits any brick and is destroyed
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
        boolean state = false;

        // Check every brick
        for (Brick b : bricks) {
            if (!b.isBroken()) {
                brickBounds = b.getBounds();
                // Ball angle is randomised after every impact
                if (ballBounds.intersects(brickBounds)) {

                    // Vertical impact
                    if (brickBounds.contains(ball.getDown())) {
                        ball.reverseY();
                        ball.randomBallAngle(true);
                        state = b.setImpact(ball.getDown(), "up");
                    } else if (brickBounds.contains(ball.getUp())) {
                        ball.reverseY();
                        ball.randomBallAngle(false);
                        state = b.setImpact(ball.getUp(), "down");
                    }
                    // Horizontal
                    else if (brickBounds.contains(ball.getLeft())) {
                        ball.reverseX();
                        ball.randomBallAngle((ball.getSpeedY() < 0));
                        state = b.setImpact(ball.getLeft(), "left");
                    } else if (brickBounds.contains(ball.getRight())) {
                        ball.reverseX();
                        ball.randomBallAngle((ball.getSpeedY() < 0));
                        state = b.setImpact(ball.getRight(), "right");
                    }

                    // Determining score based on brick type
                    if (b instanceof BrickClay) {
                        score += 10;
                    } else if (b instanceof BrickCement) {
                        score += 15;
                    } else if (b instanceof BrickSteel) {
                        score += 20;
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
        }
    }

    public void levelReset() {
        for (Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballReset();
        resetBallCount();
    }

    public void ballReset() {
        setInitialPos();
        ball.randomBallAngle(true);
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

    public int getTotalScore() {
        return score;
    }

    public boolean isGameCompleted() {
        return completed;
    }

    // Methods used by the debug panel
    public void setBallSpeed(double speed) {
        ball.setSpeed(speed);
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
