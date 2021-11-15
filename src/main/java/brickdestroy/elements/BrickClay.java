package brickdestroy.elements;

import java.awt.*;
import java.awt.geom.Point2D;

public class BrickClay extends Brick {

    private static final Color BORDER = Color.GRAY;
    private static final Color INNER = new Color(178, 34, 34).darker();
    private static final int STRENGTH = 1;

    public BrickClay(Point point, int width, int height) {
        super(point, width, height, STRENGTH);
    }

    @Override
    protected Shape makeBrickFace(Point2D pos, int width, int height) {
        return new Rectangle((Point) pos, new Dimension(width, height));
    }

    @Override
    public Shape getBrick() {
        return super.getSuperShape();
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
