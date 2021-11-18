package brickdestroy.elements;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class BallRubber extends Ball {

    private static final Color BORDER = Color.DARK_GRAY.darker();
    private static final Color INNER = new Color(255, 219, 88);

    /**
     * The {@code BallRubber} class is a child class of {@code Ball}. It is a
     * regular ball with a circular shape, as balls should be.
     * <p>
     * It is only responsible for how its {@code Shape} should be created and
     * defining its colours.
     * 
     * @param center - The center position.
     * @param width  - The diameter.
     */
    public BallRubber(Point2D center, int width) {
        super(center, width, width, BORDER, INNER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Shape makeBall(Point2D center, int width, int height) {
        // Get the top left corner of the Shape
        double x = center.getX() - (width / 2);
        double y = center.getY() - (height / 2);

        return new Ellipse2D.Double(x, y, width, height);
    }
}
