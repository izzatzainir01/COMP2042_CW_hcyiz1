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

    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    private Shape brickFace;

    private Point2D p;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;

    public Brick(Point2D pos, int width, int height, int strength) {

        // Define location (top left corner)
        this.p = pos;

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

    // Abstract method for getting the children bricks' Shape
    public abstract Shape getBrick();

    // Abstract method for setting the border colour
    protected abstract Color setBorderColour();

    // Abstract method for setting the inner colour
    protected abstract Color setInnerColour();

    // Get super Shape
    public Shape getSuperShape() {
        return brickFace;
    }

    // Get the brick's bounds
    public Rectangle2D getBounds() {
        return brickFace.getBounds2D();
    }

    public Point2D getPoint() {
        return p;
    }

    // Determine the point of impact between the Brick and the Ball
    public final int findImpact(Ball ball) {
        if (broken)
            return 0;

        int out = 0;
        
        // If the right side of the ball impacts the left side of the brick
        if (brickFace.contains(ball.getRight()))
            out = LEFT;
        // If the left side of the ball impacts the right side of the brick
        else if (brickFace.contains(ball.getLeft()))
            out = RIGHT;
        // If the top side of the ball impacts the bottom side of the brick
        else if (brickFace.contains(ball.getUp()))
            out = DOWN;
        // If the bottom side of the ball impacts the top side of the brick
        else if (brickFace.contains(ball.getDown()))
            out = UP;

        return out;
    }

    public boolean setImpact(Point2D point, int dir) {
        if (broken)
            return false;
        impact();
        return broken;
    }

    public void impact() {
        strength--;
        broken = (strength == 0);
    }

    public int getUpImpact() {
        return UP;
    }

    public int getDownImpact() {
        return DOWN;
    }

    public int getLeftImpact() {
        return LEFT;
    }

    public int getRightImpact() {
        return RIGHT;
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
