/*
 *  Brick Destroy - A simple arcade video game
 *  Copyright (C) 2021  Izzat Zainir
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package brickdestroy.elements;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import brickdestroy.gui.MainFrame;

/**
 * A class that represents the actual gameplay of the game. Formerly called
 * {@code Wall}, the {@code Game} class is where the main logic and decision
 * making of the game happens. It is responsible for creating instances of the
 * game elements and making decisions based on how they interact with each
 * other.
 * <p>
 * The {@code Game} class communicates with the {@code GameController} and the
 * {@code GameView} in order to get user input during the gameplay to control
 * the {@link Player} and display the elements to the user.
 * 
 * @see brickdestroy.gui.controller.GameController GameController
 * @see brickdestroy.gui.view.GameView GameView
 */
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

    /**
     * A class that represents the actual gameplay of the game. Formerly called
     * {@code Wall}, the {@code Game} class is where the main logic and decision
     * making of the game happens. It is responsible for creating instances of the
     * game elements and making decisions based on how they interact with each
     * other.
     * <p>
     * The {@code Game} class communicates with the
     * {@code GameController} and the
     * {@code GameView} in order to get user input
     * during the gameplay to control the {@link Player} and display the elements to
     * the
     * user.
     * 
     * @see brickdestroy.gui.controller.GameController GameController
     * @see brickdestroy.gui.view.GameView GameView
     */
    public Game() {

        // Creating the levels
        this.levels = Levels.makeLevels(brickCount, 4);

        // Defining the Player and Ball's initial position
        this.initialPosX = width / 2;
        this.initialPosY = (int) (height * 0.95);
        this.initialPos = new Point(initialPosX, initialPosY);

        // Defining the player and initialising its speed
        this.player = new Player(initialPos, (int) (width * 1.0 / 4.0), (int) (height * 1.0 / 45.0));
        player.setSpeed(7);

        // Defining the Ball and setting its properties
        this.ball = new BallRubber(initialPos, (int) (width * 1.0 / 60.0));
        ball.setSpeed(ballSpeed);
        ball.randomBallAngle(true);
        setInitialPos();

        // Initialise the first level
        nextLevel();
    }

    /**
     * Ticks the game. Every tick, this {@code Game} does the following:
     * <ul>
     * <li>Check if the game is stopped</li>
     * <li>Move the player and the ball</li>
     * <li>Check for all collisions in the game</li>
     * <li>Check if the ball is lost</li>
     * <li>Check if all bricks are destroyed</li>
     * <li>Check if all levels are completed</li>
     * </ul>
     */
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
                    attempts = 3;
                    stopped = true;
                } else {
                    completed = true;
                    stopped = true;
                }
            }
        }
    }

    /**
     * Moves the {@code Player} to the left.
     * 
     * @param b An indication of whether the {@code Player} should move left
     */
    public void movePlayerLeft(boolean b) {
        player.moveLeft(b);
    }

    /**
     * Moves the {@code Player} to the right.
     * 
     * @param b An indication of whether the {@code Player} should move right
     */
    public void movePlayerRight(boolean b) {
        player.moveRight(b);
    }

    /**
     * Indicates whether this {@code Game} is currently stopped or not.
     * 
     * @return An indication of whether this {@code Game} is stopped or not
     */
    public boolean isGameStopped() {
        return stopped;
    }

    /**
     * Sets the state of this {@code Game} to stopped or otherwise.
     * 
     * @param b An indiciation of whether the {@code Game} should be stopped or not
     */
    public void setGameStopped(boolean b) {
        stopped = b;
    }

    /**
     * Sets this {@code Game} to the next level only if there is a next level. This
     * method automatically resets the {@code Ball's} position.
     */
    public void nextLevel() {
        if (level < levels.length) {
            bricks = levels[level++];
            this.brickCount = bricks.length;
            ballReset();
        }
    }

    /**
     * Resets the number of attempts that the user has.
     */
    public void resetBallCount() { // change name to resetAttempts
        attempts = 3;
    }

    /**
     * Gets the current number of bricks left unbroken.
     * 
     * @return An {@code int} of the current number of bricks
     */
    public int getBrickCount() {
        return brickCount;
    }

    /**
     * Gets the number of attempts that the user has.
     * 
     * @return An {@code int} of the number of user attempts
     */
    public int getAttemptCount() {
        return attempts;
    }

    /**
     * Gets the current total score.
     * 
     * @return An {@code int} of the current total score
     */
    public int getTotalScore() {
        return score;
    }

    /**
     * Gets the {@code Game's} completed status.
     * 
     * @return An indication of whether this {@code Game} is completed or not
     */
    public boolean isGameCompleted() {
        return completed;
    }

    /**
     * Sets the speed of the {@code Ball}.
     * 
     * @param speed The new speed of the {@code Ball}
     */
    public void setBallSpeed(double speed) {
        ball.setSpeed(speed);
        this.ballSpeed = speed;
    }

    /**
     * Gets the speed of the {@code Ball}.
     * 
     * @return A {@code double} of the {@code Ball's} speed.
     */
    public double getBallSpeed() {
        return ballSpeed;
    }

    /**
     * Renders this {@code Game's} elemeents.
     * <p>
     * While this method doesn't draw the elements by itself, it is intended to
     * be used inside a {@link JComponent} within the {@code paint} method in order
     * for the elements to be drawn onto the GUI. This method simply calls the
     * elements' {@code render} methods to ensure that other classes have limited
     * access to the elements of this {@code Game}. Thus, minimising the need for
     * getters.
     * 
     * @param g
     */
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
        if (playerBounds.intersects(ball.getBounds2D()) && playerBounds.contains(ball.getDown())) {
            return true;
        }
        return false;
    }

    // Check for the Ball's impact with the Bricks
    private boolean checkBrickImpact() {
        Rectangle2D ballBounds = ball.getBounds2D();
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
        ball.setLocation(initialPosX, initialPosY - ball.getBounds2D().getWidth());
    }

    /**
     * Resets the {@code Ball's} state. This method resets the {@code Ball's}
     * position, sets a random angle and set its {@code lost} status to false.
     */
    private void ballReset() {
        setInitialPos();
        ball.randomBallAngle(true);
        ballLost = false;
    }

}
