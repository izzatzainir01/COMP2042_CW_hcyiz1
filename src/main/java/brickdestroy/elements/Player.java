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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import brickdestroy.gui.MainFrame;

/**
 * A class the represents the player's model in the game, a paddle. The
 * {@code Player} in the game is represented by a rectangular shape with a dark
 * green border and a lighter green inner colour. It is responsible for storing
 * data that allows the {@link Game} to make decisions during gameplay.
 * <p>
 * Similar to the {@link Ball}, the {@code Player} uses its center point as
 * its location, rather than the top-left corner, for similar reasons as the
 * {@code Ball}.
 * <p>
 * While not ideal, the {@code Player} also checks for its collisions with the
 * frame's borders. It is the only game element that checks for any collisions
 * within itself.
 */
public class Player {

    private final Color BORDER = Color.GREEN.darker().darker();
    private final Color INNER = Color.GREEN;

    private Rectangle playerFace;
    private int width;
    private int height;

    private Point2D center;
    private int centerX;
    private int centerY;

    private int speed = 0;
    private boolean moveLeft;
    private boolean moveRight;

    /**
     * A class the represents the player's model in the game, a paddle. The
     * {@code Player} in the game is represented by a rectangular shape with a dark
     * green border and a lighter green inner colour. It is responsible for storing
     * data that allows the {@link Game} to make decisions during gameplay.
     * <p>
     * Similar to the {@link Ball}, the {@code Player} uses its center point as
     * its location, rather than the top-left corner, for similar reasons as the
     * {@code Ball}.
     * <p>
     * While not ideal, the {@code Player} also checks for its collisions with the
     * frame's borders. It is the only game element that checks for any collisions
     * within itself.
     * 
     * @param center - The center position
     * @param width  - The width
     * @param height - The height
     */
    public Player(Point2D center, int width, int height) {
        // Define size
        this.width = width;
        this.height = height;

        // Define location (center)
        this.centerX = (int) center.getX();
        this.centerY = (int) center.getY();
        this.center = (Point2D) center.clone();

        // Create the Player's Shape
        playerFace = makeRectangle(center, width, height);
    }

    /**
     * Increments the {@code Player's} position by {@code SPEED}. This method calls
     * the {@code setLocation} method to move the {@code Player}.
     */
    public void move() {
        // Check when Player hits the left side of the frame
        if (playerFace.getX() <= 0) {
            moveLeft = false;
        }
        // Check when Player hits the right side of the frame
        if (playerFace.getX() + width >= MainFrame.WIDTH) {
            moveRight = false;
        }

        if (moveLeft)
            centerX -= speed;
        if (moveRight)
            centerX += speed;

        setLocation(new Point(centerX, centerY));
    }

    /**
     * Sets the {@code moveLeft} variable to the specified {@code boolean value}.
     * 
     * @param bool - A {@code boolean} value
     */
    public void moveLeft(boolean bool) {
        moveLeft = bool;
    }

    /**
     * Sets the {@code moveRight} variable to the specified {@code boolean value}.
     * 
     * @param bool - A {@code boolean} value
     */
    public void moveRight(boolean bool) {
        moveRight = bool;
    }

    /**
     * Sets a new center location of the {@code Player}.
     * 
     * @param p - The new center location
     */
    public void setLocation(Point2D p) {

        // Set center location
        centerX = (int) p.getX();
        centerY = (int) p.getY();
        center.setLocation(centerX, centerY);

        // Set top left corner location of the Player Shape
        int tempX = centerX - width / 2;
        int tempY = centerY - height / 2;
        playerFace.setLocation(tempX, tempY);
    }

    /**
     * Sets the speed of the {@code Player}.
     * 
     * @param speed The speed of the player
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Gets the bounds of the {@code Player}.
     * 
     * @return A {@code Rectangle} of the Player's bounds
     */
    public Rectangle getBounds() {
        return playerFace.getBounds();
    }

    /**
     * Render the Player's inner and border colours.
     * <p>
     * While this method doesn't draw the {@code Player} by itself, it is intended
     * to be used inside a {@link JComponent} within the {@code paint} method in
     * order to be drawn onto the GUI. This method is defined within the
     * {@code Player} class to ensure that other classes have limited access to the
     * fields required to draw the {@code Player}. Thus, minimising the need for
     * getters.
     * 
     * @param g - A {@code Graphics2D} object
     */
    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the interior colour
        g2d.setColor(INNER);
        g2d.fill(playerFace);

        // Set the border colour
        g2d.setColor(BORDER);
        g2d.draw(playerFace);
    }

    /**
     * Creates the {@link Shape} of the {@code Player}, which is a
     * {@link Rectangle}.
     * 
     * @param center - The center position
     * @param width  - The width
     * @param height - The height
     * @return A {@code Rectangle} object
     */
    private Rectangle makeRectangle(Point2D center, int width, int height) {
        // Getting the top left corner of the Shape
        int tempX = (int) center.getX() - width / 2;
        int tempY = (int) center.getY() - height / 2;

        return new Rectangle(tempX, tempY, width, height);
    }
}
