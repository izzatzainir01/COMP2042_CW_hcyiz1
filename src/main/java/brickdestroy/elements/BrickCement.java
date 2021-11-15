package brickdestroy.elements;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class BrickCement extends Brick {

    private static final Color BORDER = new Color(217, 199, 175);
    private static final Color INNER = new Color(147, 147, 147);
    private static final int STRENGTH = 2;

    private final int CRACK_DEPTH = 1;
    private final int STEPS = 35;

    private Crack crack;
    private Shape cementFace;

    public BrickCement(Point point, int width, int height) {
        super(point, width, height, STRENGTH);

        this.crack = new Crack(CRACK_DEPTH, STEPS);
        this.cementFace = super.getSuperShape();
    }

    @Override
    protected Shape makeBrickFace(Point2D pos, int width, int height) {
        return new Rectangle((Point) pos, new Dimension(width, height));
    }
    
    @Override
    public Shape getBrick() {
        return cementFace;
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
    public boolean setImpact(Point2D point, int dir) {
        if (isBroken())
            return false;
        impact();
        if (!isBroken()) {
            crack.makeCrack(this, point, dir);
            updateBrick();
            return false;
        }
        return true;
    }

    private void updateBrick() {
        if (!isBroken()) {
            GeneralPath path = crack.draw();
            path.append(super.getSuperShape(), false);
            cementFace = path;
        }
    }

    public void repair() {
        super.repair();
        crack.reset();
        cementFace = super.getSuperShape();
    }
}
