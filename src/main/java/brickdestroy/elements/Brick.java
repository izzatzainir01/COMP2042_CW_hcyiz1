package brickdestroy.elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

abstract public class Brick {

    private Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;
    private boolean broken;

    /**
     * The Brick abstract class provides a template that allows different types of
     * Bricks to be created. It is reponsible for defining its shape and location.
     * The colour of the Brick is left to its children to define.
     * <p>
     * While it doesn't define its behaviour in the game within itself, it does, however,
     * define some simple behaviour regarding its health.
     * 
     * @param pos      - The top left corner position.
     * @param width    - The width.
     * @param height   - The height.
     * @param strength - The strength or 'health'.
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
     * An abstract method for creating the Brick's {@code Shape}.
     * 
     * @param pos    - The top-left corner.
     * @param width  - The width.
     * @param height - The height.
     * @return
     */
    protected abstract Shape makeBrickFace(Point2D pos, int width, int height);

    /**
     * An abstract method for getting the child classes' {@code Shape}.
     * 
     * @return The {@code Shape} of the child class.
     */
    protected abstract Shape getBrick();

    /**
     * Get the shape of the parent {@code Shape}.
     * 
     * @return The {@code Shape} of the parent.
     */
    public Shape getSuperShape() {
        return brickFace;
    }

    /**
     * Get the bounds of the Brick.
     * 
     * @return A {@code Rectangle2D} of the Brick's bounds.
     */
    public Rectangle2D getBounds() {
        return brickFace.getBounds2D();
    }

    /**
     * This method is called when the Game detects a collision between the Ball and
     * the Brick. It calls the {@code impact()} method.
     * 
     * @param point - The point of impact.
     * @param dir   - The direction at which the {@code Crack} will be created.
     * @return A {@code boolean} value of the Brick's state. {@code false} if the
     *         Brick is broken.
     */
    public boolean setImpact(Point2D point, String dir) {
        impact();

        if (broken)
            return false;

        return broken;
    }

    /**
     * Decrements the strength or 'health' of the Brick and updates the Brick's
     * {@code broken} status.
     */
    protected void impact() {
        strength--;
        broken = (strength == 0);
    }

    /**
     * Sets the Brick's {@code broken} status to {@code true} and restores its full
     * strength.
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * Get the {@code broken} status of the Brick.
     * 
     * @return A {@code boolean} value of the {@code broken} status of the Brick.
     */
    public final boolean isBroken() {
        return broken;
    }

    /**
     * Render the Brick's inner and border colours.
     * 
     * @param g - A {@code Graphics2D} object.
     */
    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the interior colour
        g2d.setColor(inner);
        g2d.fill(getBrick());

        // Set the border colour
        g2d.setColor(border);
        g2d.draw(getBrick());
    }

}
