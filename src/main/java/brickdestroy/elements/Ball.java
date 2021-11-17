package brickdestroy.elements;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 * The abstract class Ball is the 'template' for the Balls in the game. It is
 * reponsible for defining its shape, looks and location.
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

    private double speedX;
    private double speedY;

    public Ball(Point2D center, int width, int height) {

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

        // Initialise the speed
        speedX = 0;
        speedY = 0;

        // Define the Ball's colours
        this.border = setBorderColour();
        this.inner = setInnerColour();

        // Create the Ball's shape
        ballFace = makeBall(center, width, height);
    }

    // Abstract method for making the ball
    protected abstract Shape makeBall(Point2D center, int width, int height);

    // Abstract method for setting the border colour of the ball
    protected abstract Color setBorderColour();

    // Abstract method for setting the inner colour of the ball
    protected abstract Color setInnerColour();

    // Move the ball every time this method is called
    public void move() {

        // Change location by adding with speed
        centerX += speedX;
        centerY += speedY;

        // Set its new location
        setLocation(centerX, centerY);
    }

    // Set the Ball's location
    public void setLocation(double x, double y) {
        // Set center location
        this.centerX = x;
        this.centerY = y;
        this.center.setLocation(centerX, centerY);

        // Set points
        setPoints(center);

        // Set ballFace location
        RectangularShape tmp = (RectangularShape) ballFace;
        double tempX = centerX - width / 2;
        double tempY = centerY - height / 2;

        tmp.setFrame(tempX, tempY, width, height);
        ballFace = tmp;
    }

    public void setLocation(Point2D p) {
        this.setLocation(p.getX(), p.getY());
    }

    // Set the Ball's points
    private void setPoints(Point2D center) {
        int tempX = (int) center.getX();
        int tempY = (int) center.getY();

        up.setLocation(tempX, tempY - (height / 2));
        down.setLocation(tempX, tempY + (height / 2));
        left.setLocation(tempX - (width / 2), tempY);
        right.setLocation(tempX + (width / 2), tempY);
    }

    public Rectangle2D getBounds() {
        return ballFace.getBounds2D();
    }

    public int getWidth() {
        return width;
    }

    public Point2D getPosition() {
        return center;
    }

    public Point2D getUp() {
        return up;
    }

    public Point2D getDown() {
        return down;
    }

    public Point2D getLeft() {
        return left;
    }

    public Point2D getRight() {
        return right;
    }

    public void setSpeed(double x, double y) {
        speedX = x;
        speedY = y;
    }

    public void setXSpeed(int s) {
        speedX = s;
    }

    public void setYSpeed(int s) {
        speedY = s;
    }

    public int getSpeedX() {
        return (int) speedX;
    }

    public int getSpeedY() {
        return (int) speedY;
    }

    public void reverseX() {
        speedX *= -1.0;
    }

    public void reverseY() {
        speedY *= -1.0;
    }

    // Render the ball
    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(inner);
        g2d.fill(ballFace);

        g2d.setColor(border);
        g2d.draw(ballFace);
    }
}
