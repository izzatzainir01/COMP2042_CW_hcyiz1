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
    private int centerX;
    private int centerY;

    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    public Ball(Point2D center, int width, int height) {

        // Define location
        this.center = center;
        this.centerX = (int) center.getX();
        this.centerY = (int) center.getY();

        // Define points
        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();
        setPoints(this.center);

        // Define size
        this.width = width;
        this.height = height;

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
        checkFrameCollision();
        // Change location by adding with speed
        centerX += speedX;
        centerY += speedY;

        // Set its new location
        setLocation(new Point(centerX, centerY));
    }

    // Set the Ball's location
    public void setLocation(Point2D p) {
        // Set center location
        this.centerX = (int) p.getX();
        this.centerY = (int) p.getY();
        this.center.setLocation(centerX, centerY);

        // Set points
        setPoints(center);

        // Set ballFace location
        RectangularShape tmp = (RectangularShape) ballFace;
        int tempX = (int) (centerX - width/2);
        int tempY = (int) (centerY - height/2);

        tmp.setFrame(tempX, tempY, width, height);
        ballFace = tmp;
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

    public void setSpeed(int x, int y) {
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
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void reverseX() {
        speedX *= -1;
    }

    public void reverseY() {
        speedY *= -1;
    }

    // Private method to set the ball's behaviour if it collides with the frame
    private void checkFrameCollision() {
        // Check for horizontal collision
        if (left.getX() < 0 || right.getX() > 600) {
            // System.out.println(left.getX());
            speedX *= -1;
        }
        // Check for vertical collision
        if (up.getY() < 0) {
            speedY *= -1;
        }
    }

    // Render the ball
    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(border);
        g2d.draw(ballFace);

        g2d.setColor(inner);
        g2d.fill(ballFace);
    }
}
