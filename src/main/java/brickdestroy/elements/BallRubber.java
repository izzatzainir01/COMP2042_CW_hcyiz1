package brickdestroy.elements;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class BallRubber extends Ball {

    private static final int WIDTH = 10;
    private static final Color BORDER = Color.DARK_GRAY.darker();
    private static final Color INNER = new Color(255, 219, 88);

    public BallRubber(Point2D center) {
        super(center, WIDTH, WIDTH);
    }

    @Override
    protected Shape makeBall(Point2D center, int width, int height) {

        double x = center.getX() - (width / 2);
        double y = center.getY() - (height / 2);

        return new Ellipse2D.Double(x, y, width, height);
    }

    @Override
    protected Color setBorderColour() {
        return BORDER;
    }

    @Override
    protected Color setInnerColour() {
        return INNER;
    }
}
