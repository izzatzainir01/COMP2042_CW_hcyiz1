package brickdestroy.elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.Random;

/**
 * An abstract class that represents a ball in the game. It provides a template
 * that child classes can extend to easily create different types of balls. It
 * is responsible for storing data that allows the {@link Game} to make
 * decisions during gameplay.
 * <p>
 * The location of the {@code Ball} is defined by its center, as opposed to the
 * top-left corner of its {@link Shape}. This is mainly for the sake of ease of
 * visualisation.
 * <p>
 * The {@code Ball} also uses a 4-point system that represents the up-most,
 * bottom-most, left-most and right-most points of the Ball. This is done to
 * ease the process of finding impacts during collision checking in the game.
 */
abstract public class Ball {

    private Shape ballFace;
    private int width;
    private int height;

    private Point2D center;
    private double centerX;
    private double centerY;

    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    private Color border;
    private Color inner;

    private double speed = 0;
    private double speedX = 0;
    private double speedY = 0;

    /**
     * An abstract class that represents a ball in the game. It provides a template
     * that child classes can extend to easily create different types of balls. It
     * is responsible for storing data that allows the {@link Game} to make
     * decisions during gameplay.
     * <p>
     * The location of the {@code Ball} is defined by its center, as opposed to the
     * top-left corner of its {@link Shape}. This is mainly for the sake of ease of
     * visualisation.
     * <p>
     * The {@code Ball} also uses a 4-point system that represents the up-most,
     * bottom-most, left-most and right-most points of the {@code Ball}. This is
     * done to ease the process of finding impacts during collision checking in the
     * game.
     * 
     * @param center The center position
     * @param width  The horizontal diameter
     * @param height The vertical diameter
     * @param border The border colour
     * @param inner  The interior colour
     */
    protected Ball(Point2D center, int width, int height, Color border, Color inner) {

        // Define size
        this.width = width;
        this.height = height;

        // Define location
        this.centerX = center.getX();
        this.centerY = center.getY();
        this.center = (Point2D) center.clone(); // Copy the point

        // Define points
        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        // Set the points' locations
        setPoints(this.center);

        // Define the Ball's colours
        this.border = border;
        this.inner = inner;

        // Create the Ball's shape
        ballFace = makeBall(center, width, height);
    }

    /**
     * Creates the {@code Shape} of this {@code Ball}. As the return value is of a
     * {@code Shape} object, child classes are technically allowed to create balls
     * of various shapes, including of a triangle (for example) if they wish to do
     * so.
     * 
     * @param center The center position
     * @param width  The horizontal diameter
     * @param height The vertical diameter
     * @return The {@code Shape} of the {@code Ball}
     */
    protected abstract Shape makeBall(Point2D center, int width, int height);

    /**
     * Increments this {@code Ball's} location by {@code speedX} and {@code speedY}.
     * This method calls the {@code setLocation} method to move the {@code Ball}.
     * 
     * @see #setLocation
     */
    public void move() {
        // Change location by adding with speed
        centerX += speedX;
        centerY += speedY;

        // Set its new location
        setLocation(centerX, centerY);
    }

    /**
     * Sets a new center location of this {@code Ball}. This method also
     * automatically sets the 4 impact points of this {@code Ball}.
     * 
     * @param x The new center X coordinate
     * @param y The new center Y coordinate
     */
    public void setLocation(double x, double y) {
        // Set center location
        this.centerX = x;
        this.centerY = y;
        this.center.setLocation(centerX, centerY);

        // Set points
        setPoints(center);

        // Set ballFace location (using top left corner)
        RectangularShape tmp = (RectangularShape) ballFace;
        double tempX = centerX - width / 2;
        double tempY = centerY - height / 2;
        tmp.setFrame(tempX, tempY, width, height);
        ballFace = tmp;
    }

    /**
     * Gets the bounds of this {@code Ball}.
     * 
     * @return A {@code Rectangle2D} of the {@code Ball's} bounds
     */
    public Rectangle2D getBounds2D() {
        return ballFace.getBounds2D();
    }

    /**
     * Gets the up-most point of this {@code Ball}.
     * 
     * @return A {@code Point2D} of this {@code Ball's} up-most point
     */
    public Point2D getUp() {
        return up;
    }

    /**
     * Gets the down-most point of this {@code Ball}.
     * 
     * @return A {@code Point2D} of this {@code Ball's} bottom-most point
     */
    public Point2D getDown() {
        return down;
    }

    /**
     * Gets the left-most point of this {@code Ball}.
     * 
     * @return A {@code Point2D} of this {@code Ball's} left-most point
     */
    public Point2D getLeft() {
        return left;
    }

    /**
     * Gets the right-most point of this {@code Ball}.
     * 
     * @return A {@code Point2D} of this {@code Ball's} right-most point
     */
    public Point2D getRight() {
        return right;
    }

    /**
     * Sets the new speed of this {@code Ball} in the X and Y axis.
     * 
     * @param x The new speed in the X axis
     * @param y The new speed in the Y axis
     */
    public void setSpeed(double speed) {
        double ratio = speed / this.speed;
        speedX *= ratio;
        speedY *= ratio;
        this.speed = speed;
    }

    /**
     * Gets the current speed of this {@code Ball} in the X axis.
     * 
     * @return A {@code double} of the current X speed
     */
    public double getSpeedX() {
        return speedX;
    }

    /**
     * Gets the current speed of this {@code Ball} in the Y axis.
     * 
     * @return A {@code double} of the current Y speed
     */
    public double getSpeedY() {
        return speedY;
    }

    /**
     * Randomises this {@code Ball's} angle of travel using the hypothenuse formula
     * to keep the speed consistent in all directions.
     * <p>
     * This method takes in a {@code boolean} that determines whether to reverse the
     * Y direction of this {@code Ball} or not.
     * 
     * @param reverseY A {@code boolean}
     */
    public void randomBallAngle(boolean reverseY) {
        Random rand = new Random();

        speedX = speed * rand.nextDouble(0.2, 0.8); // X speed = random ratio of speed
        speedY = Math.sqrt((speed * speed) - (speedX * speedX));// y = sqrt(z^2 - x^2)

        speedX *= rand.nextBoolean() ? 1 : -1; // randomly determine left or right

        if (reverseY)
            speedY *= -1;
    }

    /**
     * Reverses the X direction of this {@code Ball}.
     */
    public void reverseX() {
        speedX *= -1;
    }

    /**
     * Reverses the Y direction of this {@code Ball}.
     */
    public void reverseY() {
        speedY *= -1;
    }

    /**
     * Renders this {@code Ball's} inner and border colours.
     * <p>
     * While this method doesn't draw the {@code Ball} by itself, it is intended to
     * be used inside a {@link JComponent} within the {@code paint} method in order
     * to be drawn onto the GUI. This method is defined within the {@code Ball}
     * class to ensure that other classes have limited access to the fields
     * required to draw the {@code Ball}. Thus, minimising the need for getters.
     * 
     * @param g A {@code Graphics2D} object
     */
    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the interior colour
        g2d.setColor(inner);
        g2d.fill(ballFace);

        // Set the border colour
        g2d.setColor(border);
        g2d.draw(ballFace);
    }

    /**
     * Set all 4 of the {@code Ball's} points.
     * 
     * @param center - The new center position.
     */
    private void setPoints(Point2D center) {
        int tempX = (int) center.getX();
        int tempY = (int) center.getY();

        up.setLocation(tempX, tempY - (height / 2));
        down.setLocation(tempX, tempY + (height / 2));
        left.setLocation(tempX - (width / 2), tempY);
        right.setLocation(tempX + (width / 2), tempY);
    }
}
