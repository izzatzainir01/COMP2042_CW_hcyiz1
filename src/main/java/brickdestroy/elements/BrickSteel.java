package brickdestroy.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.Random;

public class BrickSteel extends Brick {

    private static final Color BORDER = Color.BLACK;
    private static final Color INNER = new Color(203, 203, 201);
    private static final int STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Shape steelFace;

    private Random rand = new Random();

    /**
     * The {@code BrickSteel} class is a child class of {@code Brick}. Although its
     * {@code strength} is only 1, it only loses its strength based on a
     * probability.
     * <p>
     * It is responsible for defining its colours and the probability of losing its
     * {@code strength}.
     * 
     * @param pos    - The top left corner.
     * @param width  - The width.
     * @param height - The height.
     */
    public BrickSteel(Point2D pos, int width, int height) {
        super(pos, width, height, STRENGTH, BORDER, INNER);

        this.steelFace = super.getSuperShape();
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
        return steelFace;
    }

    /**
     * Calls the parent's {@code impact()} method based on the probability.
     */
    @Override
    public void impact() {
        if (rand.nextDouble() < STEEL_PROBABILITY) {
            super.impact();
        }
    }
}
