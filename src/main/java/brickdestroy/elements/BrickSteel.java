package brickdestroy.elements;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class BrickSteel extends Brick {

    private static final Color BORDER = Color.BLACK;
    private static final Color INNER = new Color(203, 203, 201);
    private static final int STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Shape steelFace;

    private Random rand = new Random();

    public BrickSteel(Point point, int width, int height) {
        super(point, width, height, STRENGTH);

        this.steelFace = super.getSuperShape();
    }

    @Override
    protected Shape makeBrickFace(Point2D pos, int width, int height) {
        return new Rectangle((Point) pos, new Dimension(width, height));
    }

    @Override
    public Shape getBrick() {
        return steelFace;
    }

    @Override
    protected Color setBorderColour() {
        return BORDER;
    }

    @Override
    protected Color setInnerColour() {
        return INNER;
    }

    @Override
    public void impact() {
        if (rand.nextDouble() < STEEL_PROBABILITY) {
            super.impact();
        }
    }
}
