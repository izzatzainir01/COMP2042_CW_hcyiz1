package brickdestroy.elements;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * The Player class is responsible for defining the Player's shape, looks,
 * location and behaviour.
 */
public class Player {

    private final Color BORDER = Color.GREEN.darker().darker();
    private final Color INNER = Color.GREEN;

    private static final int SPEED = 5;

    private Rectangle playerFace;
    private int width;
    private int height;

    private Point2D center;
    private int centerX;
    private int centerY;

    private int moveAmount;
    private int min;
    private int max;

    public Player(Point2D center, int width, int height) {

        // Define size
        this.width = width;
        this.height = height;

        // Define location (center)
        this.centerX = (int) center.getX();
        this.centerY = (int) center.getY();
        this.center = new Point(centerX, centerY);

        // Define movement and limits
        this.moveAmount = 0;
        this.min = width / 2;
        this.max = 600 - width / 2;

        // Create the Player's Shape
        playerFace = makeRectangle(center, width, height);
    }

    // Creating the Player's Shape
    private Rectangle makeRectangle(Point2D center, int width, int height) {
        // Getting the top left corner of the Shape
        int tempX = (int) center.getX() - width / 2;
        int tempY = (int) center.getY() - height / 2;

        return new Rectangle(tempX, tempY, width, height);
    }

    // Move the Player
    public void move() {

        if (centerX + moveAmount < min || centerX + moveAmount > max) {
            // Do nothing lmao
        }
        else {
            centerX += moveAmount;
        }

        setLocation(new Point(centerX, centerY));
    }

    // Set the location of the Player's center and Shape
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

    // Check for the Player's impact with the Ball
    public boolean checkBallImpact(Ball b) {
        return playerFace.intersects(b.getBounds()) && playerFace.contains(b.getDown());
    }

    public void moveLeft() {
        moveAmount = -SPEED;
    }

    public void moveRight() {
        moveAmount = SPEED;
    }

    public void stop() {
        moveAmount = 0;
    }

    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(INNER);
        g2d.fill(playerFace);

        g2d.setColor(BORDER);
        g2d.draw(playerFace);
    }
}
