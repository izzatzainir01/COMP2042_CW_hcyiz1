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

    /**
     * The {@code Player} class creates the model that the player will be
     * controlling. It is responsible for defining its shape, colours and location.
     * <p>
     * It is not responsible for defining its behaviour in the game within itself,
     * with the exception of stopping its movements upon colliding with the game's
     * frame.
     * 
     * @param center - The center position.
     * @param width  - The width.
     * @param height - The height.
     */
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

    /**
     * Create the Player's model, which is a Rectangle.
     * 
     * @param center - The center position.
     * @param width  - The width.
     * @param height - The height.
     * @return A {@code Rectangle} object.
     */
    private Rectangle makeRectangle(Point2D center, int width, int height) {
        // Getting the top left corner of the Shape
        int tempX = (int) center.getX() - width / 2;
        int tempY = (int) center.getY() - height / 2;

        return new Rectangle(tempX, tempY, width, height);
    }

    /**
     * Get the bounds of the Player.
     * 
     * @return A {@code Rectangle} of the Player's bounds.
     */
    public Rectangle getBounds() {
        return playerFace.getBounds();
    }

    /**
     * Increment the Player's position by {@code SPEED} only if called to do so by
     * the Game.
     * <p>
     * Ideally, it is not supposed to be responsible for checking its collision with
     * the game's frame borders. However, I could not figure out a way to make it
     * work as I intend it to from the {@code GameController}. Thus, the collision
     * check is done here.
     */
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

    /**
     * Set the {@code moveLeft} variable to whatever is given in the argument.
     * 
     * @param bool - A {@code boolean} value.
     */
    public void moveLeft(boolean bool) {
        moveLeft = bool;
    }

    /**
     * Set the {@code moveRight} variable to whatever is give in the argument.
     * 
     * @param bool - A {@code boolean} value.
     */
    public void moveRight(boolean bool) {
        moveRight = bool;
    }

    /**
     * Set a new center location.
     * 
     * @param p - The new center location.
     */
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

    /**
     * Render the Player's inner and border colours.
     * 
     * @param g - A {@code Graphics2D} object.
     */
    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the interior colour
        g2d.setColor(INNER);
        g2d.fill(playerFace);

        // Set the border colour
        g2d.setColor(BORDER);
        g2d.draw(playerFace);
    }
}
