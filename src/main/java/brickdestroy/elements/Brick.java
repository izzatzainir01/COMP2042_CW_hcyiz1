package brickdestroy.elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * An abstract class that represents a brick in the game. It provides a template
 * that child classes can extend to easily create different types of bricks. It
 * is responsible for storing data that allows the {@link Game} to make
 * decisions during gameplay.
 * <p>
 * The {@code Brick} has a strength, or a 'life', where after it depletes, the
 * {@code Brick} is considered to be 'broken'.
 */
abstract public class Brick {

    protected Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;
    private boolean broken;

    /**
     * An abstract class that represents a brick in the game. It provides a template
     * that child classes can extend to easily create different types of bricks. It
     * is responsible for storing data that allows the {@link Game} to make
     * decisions during gameplay.
     * <p>
     * The {@code Brick} has a strength, or a 'health', where after it depletes, the
     * {@code Brick} is considered to be 'broken'.
     * 
     * @param pos      The top-left corner position
     * @param width    The width
     * @param height   The height
     * @param strength The strength or 'health'
     */
    public Brick(Point2D pos, int width, int height, int strength, Color border, Color inner) {

        // Define colours
        this.border = border;
        this.inner = inner;

        // Define properties
        this.fullStrength = strength;
        this.strength = fullStrength;
        this.broken = false;

        // Create the brick
        brickFace = makeBrickFace(pos, width, height);
    }

    /**
     * Creates the {@code Shape} of this {@code Brick}. As the return value is of a
     * {@code Shape} object, child classes are technically allowed to create bricks
     * of various shapes, including of a circle (for example) if they wish to do
     * so.
     * 
     * @param pos    The top-left corner
     * @param width  The width
     * @param height The height
     * @return
     */
    protected abstract Shape makeBrickFace(Point2D pos, int width, int height);

    /**
     * Gets the bounds of this {@code Brick}.
     * 
     * @return A {@code Rectangle2D} of this {@code Brick's} bounds
     */
    public Rectangle2D getBounds() {
        return brickFace.getBounds2D();
    }

    /**
     * Sets the actions taken upon taking impact. This method is called when the
     * Game detects a collision between a {@link Ball} and this {@code Brick}. It
     * calls the {@code impact} method.
     * 
     * @param point The point of impact
     * @param dir   The direction at which the {@link Crack} will be created
     * @return A {@code boolean} value of this {@code Brick's} state, {@code false}
     *         if it is broken
     */
    public boolean setImpact(Point2D point, String dir) {
        if (broken)
            return false;
        impact();

        return broken;
    }

    /**
     * Decrements the strength or 'health' of this {@code Brick} and updates the
     * {@code broken} status.
     */
    protected void impact() {
        strength--;
        broken = (strength == 0);
    }

    /**
     * Sets this {@code Brick's} {@code broken} status to {@code true} and restores
     * its full strength.
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * Gets the {@code broken} status of this {@code Brick}.
     * 
     * @return A {@code boolean} value of the {@code broken} status
     */
    public final boolean isBroken() {
        return broken;
    }

    /**
     * Render this {@code Brick's} inner and border colours.
     * <p>
     * While this method doesn't draw the {@code Brick} by itself, it is intended to
     * be used inside a {@link JComponent} within the {@code paint} method in order
     * to be drawn onto the GUI. This method is defined within the {@code Brick}
     * class to ensure that other classes have limited access to the fields
     * required to draw the {@code Brick}. Thus, minimising the need for getters.
     * 
     * @param g - A {@code Graphics2D} object
     */
    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the interior colour
        g2d.setColor(inner);
        g2d.fill(brickFace);

        // Set the border colour
        g2d.setColor(border);
        g2d.draw(brickFace);
    }

}
