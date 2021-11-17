package brickdestroy.elements;

import java.awt.*;
import java.awt.geom.Point2D;

import brickdestroy.main.GameFrame;

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

    private boolean moveLeft;
    private boolean moveRight;

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

    // Creating the Player's Shape
    private Rectangle makeRectangle(Point2D center, int width, int height) {
        // Getting the top left corner of the Shape
        int tempX = (int) center.getX() - width / 2;
        int tempY = (int) center.getY() - height / 2;

        return new Rectangle(tempX, tempY, width, height);
    }

    public Rectangle getBounds() {
        return playerFace.getBounds();
    }

    public int getWidth() {
        return width;
    }

    // Move the Player. The Player is the only one that checks for frame collisions
    // within itself because checking it within Game did not work as intended.
    public void move() {
        // Check when Player hits the left side of the frame
        if (playerFace.getX() <= 0) {
            moveLeft = false;
        }
        // Check when Player hits the right side of the frame
        if (playerFace.getX() + width >= GameFrame.WIDTH) {
            moveRight = false;
        }

        if (moveLeft)
            centerX -= SPEED;
        if (moveRight)
            centerX += SPEED;

        setLocation(new Point(centerX, centerY));
        // setLocation(new Point((int)Ball.centerX, centerY));
    }

    public void moveLeft(boolean b) {
        moveLeft = b;
    }

    public void moveRight(boolean b) {
        moveRight = b;
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

    // Get the Player's top left corner position
    public Point2D getCornerPosition() {
        return playerFace.getLocation();
    }

    // Render the Player
    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(INNER);
        g2d.fill(playerFace);

        g2d.setColor(BORDER);
        g2d.draw(playerFace);
    }
}
