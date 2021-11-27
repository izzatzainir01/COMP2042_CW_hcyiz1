package brickdestroy.elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

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

    private double speedX = 0;
    private double speedY = 0;

    /**
     * The {@code Ball} abstract class provides a template that allows different
     * types of Balls to be created. It is reponsible for defining its shape and
     * location. The colour of the Ball is left to its children to define.
     * <p>
     * It is not responsible for defining its behaviour in the game within itself. It does,
     * however, provide methods that allow the Game class to define its behaviour
     * in the game.
     * 
     * @param center - The center position.
     * @param width  - The horizontal diameter.
     * @param height - The vertical diameter.
     * @param border - The border colour.
     * @param inner  - The interior colour.
     */
    public Ball(Point2D center, int width, int height, Color border, Color inner) {

        // Define size
        this.width = width;
        this.height = height;

        // Define location
        this.centerX = center.getX();
        this.centerY = center.getY();
        this.center = (Point2D) center.clone();

        // Define points
        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();
        setPoints(this.center);

        // Define the Ball's colours
        this.border = border;
        this.inner = inner;

        // Create the Ball's shape
        ballFace = makeBall(center, width, height);
    }

    /**
     * An abstract method for creating the Ball's Shape.
     * 
     * @param center - The center position.
     * @param width  - The horizontal diameter.
     * @param height - The vertical diameter.
     * @return The {@code Shape} of the ball.
     */
    protected abstract Shape makeBall(Point2D center, int width, int height);

    /**
     * Increment the Ball's location by {@code speedX} and {@code speedY}.
     */
    public void move() {
        // Change location by adding with speed
        centerX += speedX;
        centerY += speedY;

        // Set its new location
        setLocation(centerX, centerY);
    }

    /**
     * Set a new center location.
     * 
     * @param x - The new center X coordinate.
     * @param y - The new center Y coordinate.
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
     * Set all 4 of the Ball's points.
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

    /**
     * Get the bounds of the Ball.
     * 
     * @return A {@code Rectangle2D} of the Ball's bounds.
     */
    public Rectangle2D getBounds() {
        return ballFace.getBounds2D();
    }

    /**
     * Get the up-most point of the Ball.
     * 
     * @return A {@code Point2D} of the Ball's up-most point.
     */
    public Point2D getUp() {
        return up;
    }

    /**
     * Get the down-most point of the Ball.
     * 
     * @return A {@code Point2D} of the Ball's bottom-most point.
     */
    public Point2D getDown() {
        return down;
    }

    /**
     * Get the left-most point of the Ball.
     * 
     * @return A {@code Point2D} of the Ball's left-most point.
     */
    public Point2D getLeft() {
        return left;
    }

    /**
     * Get the right-most point of the Ball.
     * 
     * @return A {@code Point2D} of the Ball's right-most point.
     */
    public Point2D getRight() {
        return right;
    }

    /**
     * Set the new speed in the X and Y axis.
     * 
     * @param x - The new speed in the X axis.
     * @param y - The new speed in the Y axis.
     */
    public void setSpeed(double x, double y) {
        speedX = x;
        speedY = y;
    }

    /**
     * Get the current speed of the Ball in the X axis.
     * 
     * @return A {@code double} of the current speed in the X axis.
     */
    public double getSpeedX() {
        return speedX;
    }

    /**
     * Get the current speed in the Y axis.
     * 
     * @return A {@code double} of the current speed in the Y axis.
     */
    public double getSpeedY() {
        return speedY;
    }

    /**
     * Reverse the X direction.
     */
    public void reverseX() {
        speedX *= -1;
    }

    /**
     * Reverses the Y direction.
     */
    public void reverseY() {
        speedY *= -1;
    }

    /**
     * Render the Ball's inner and border colours.
     * 
     * @param g - A {@code Graphics2D} object.
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
}
