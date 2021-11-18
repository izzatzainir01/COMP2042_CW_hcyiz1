package brickdestroy.elements;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * The abstract class Brick provides a 'template' for the different types of
 * Bricks in the game. It is reponsible for defining its shape, looks and
 * location
 */
abstract public class Brick {

    private Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;

    public Brick(Point2D pos, int width, int height, int strength) {

        // Define colours
        this.border = setBorderColour();
        this.inner = setInnerColour();

        // Define properties
        this.fullStrength = strength;
        this.strength = fullStrength;
        this.broken = false;

        // Create the brick
        brickFace = makeBrickFace(pos, width, height);
    }

    // Abstract method for creating the brick
    protected abstract Shape makeBrickFace(Point2D pos, int width, int height);

    // Abstract method for setting the border colour
    protected abstract Color setBorderColour();

    // Abstract method for setting the inner colour
    protected abstract Color setInnerColour();

    // Abstract method for getting the children bricks' Shape
    public abstract Shape getBrick();

    // Get super Shape
    public Shape getSuperShape() {
        return brickFace;
    }

    // Get the brick's bounds
    public Rectangle2D getBounds() {
        return brickFace.getBounds2D();
    }

    public boolean setImpact(Point2D point, String dir) {
        if (broken)
            return false;
        impact();
        return broken;
    }

    public void impact() {
        strength--;
        broken = (strength == 0);
    }

    public final boolean isBroken() {
        return broken;
    }

    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(inner);
        g2d.fill(getBrick());

        g2d.setColor(border);
        g2d.draw(getBrick());
    }

}
