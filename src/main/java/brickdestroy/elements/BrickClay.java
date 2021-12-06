package brickdestroy.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * A child class of {@link Brick}. It is the lowest level brick of the game and
 * has the lowest strength. Its {@link Shape} is that of a {@link Rectangle}.
 * <p>
 * It is responsible for defining its colour and strength.
 */
public class BrickClay extends Brick {

    private static final Color BORDER = Color.GRAY;
    private static final Color INNER = new Color(178, 34, 34).darker();
    private static final int STRENGTH = 1;

    /**
     * A child class of {@link Brick}. It is the lowest level brick of the game and
     * has the lowest strength. It is a rectangular shaped brick with a gray border
     * and a dark red inner colour that is immediately broken upon impact.
     * <p>
     * It is responsible for defining its colour and its strength.
     * 
     * @param pos    The top left corner
     * @param width  The width
     * @param height The height
     */
    public BrickClay(Point2D pos, int width, int height) {
        super(pos, width, height, STRENGTH, BORDER, INNER);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The {@code BrickClay} class uses a {@link Rectangle} to define its
     * {@code Shape}.
     */
    @Override
    protected Shape makeBrickFace(Point2D pos, int width, int height) {
        return new Rectangle((Point) pos, new Dimension(width, height));
    }

}
