package brickdestroy.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * A child class of {@link Brick}. It is a high level brick that potentially has
 * more strength than {@link BrickCement}. It is a rectangular shaped brick with
 * a dark gray border and a light gray inner colour that only breaks based on a
 * probability.
 * <p>
 * It is responsible for defining its colours, its strength, and its impact
 * probability.
 */
public class BrickSteel extends Brick {

    private static final Color BORDER = Color.GRAY.darker();
    private static final Color INNER = new Color(203, 203, 201);
    private static final int STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rand = new Random();

    /**
     * A child class of {@link Brick}. It is a high level brick that potentially has
     * more strength than {@link BrickCement}. It is a rectangular shaped brick with
     * a dark gray border and a light gray inner colour that only breaks based on a
     * probability.
     * <p>
     * It is responsible for defining its colours, its strength, and its impact
     * probability.
     * 
     * @param pos    The top left corner
     * @param width  The width
     * @param height The height
     */
    public BrickSteel(Point2D pos, int width, int height) {
        super(pos, width, height, STRENGTH, BORDER, INNER);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The {@code BrickSteel} class uses a {@link Rectangle} to define its
     * {@code Shape}.
     */
    @Override
    protected Shape makeBrickFace(Point2D pos, int width, int height) {
        return new Rectangle((Point) pos, new Dimension(width, height));
    }

    /**
     * {@inheritDoc}
     * <p>
     * If the brick impacted is a {@code BrickSteel}, then it will also determine
     * the probability of it breaking.
     */
    @Override
    public void impact() {
        if (rand.nextDouble() < STEEL_PROBABILITY) {
            super.impact();
        }
    }
}
