package brickdestroy.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;

public class BrickClay extends Brick {

    private static final Color BORDER = Color.GRAY;
    private static final Color INNER = new Color(178, 34, 34).darker();
    private static final int STRENGTH = 1;

    /**
     * The {@code BrickClay} class is a child class of {@code Brick}. It has a
     * {@code strength} of 1.
     * <p>
     * It is only responsible for defining its colours.
     * 
     * @param pos    - The top left corner.
     * @param width  - The width.
     * @param height - The height.
     */
    public BrickClay(Point2D pos, int width, int height) {
        super(pos, width, height, STRENGTH, BORDER, INNER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Shape makeBrickFace(Point2D pos, int width, int height) {
        return new Rectangle((Point) pos, new Dimension(width, height));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Shape getBrick() {
        return super.getSuperShape();
    }

}
