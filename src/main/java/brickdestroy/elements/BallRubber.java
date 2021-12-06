package brickdestroy.elements;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * A child class of {@link Ball} that represents a 'normal' ball. It is a
 * circular ball with a constant diameter all around. Basically, it's a normal
 * ball.
 * <p>
 * It is responsible for defining its colours and {@code Shape}, which is a
 * circle, as all balls should be.
 */
public class BallRubber extends Ball {

    private static final Color BORDER = Color.DARK_GRAY.darker();
    private static final Color INNER = new Color(255, 219, 88);

    /**
     * A child class of {@link Ball} that represents a 'normal' ball. It is a
     * circular ball with a constant diameter all around. Basically, it's a normal
     * ball.
     * <p>
     * It is responsible for defining its colours and {@code Shape}, which is a
     * circle, as all balls should be.
     * 
     * @param center The center location
     * @param width  The width/diameter
     */
    public BallRubber(Point2D center, int width) {
        super(center, width, width, BORDER, INNER);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The {@code BallRubber} class uses an {@link Ellipse2D} to define its shape as
     * a circle.
     */
    @Override
    protected Shape makeBall(Point2D center, int width, int height) {
        // Get the top left corner of the Shape
        double x = center.getX() - (width / 2);
        double y = center.getY() - (height / 2);

        return new Ellipse2D.Double(x, y, width, height);
    }
}
