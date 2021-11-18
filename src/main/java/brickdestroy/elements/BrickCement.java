package brickdestroy.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
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

    /**
     * The {@code BrickCement} class is a child class of {@code Brick}. It has a
     * {@code strength} of 2 and is able to create cracks upon impact.
     * <p>
     * It is responsible for defining its colours, strength and {@code Crack}
     * properties.
     * 
     * @param pos    - The top left corner.
     * @param width  - The width.
     * @param height - The height.
     */
    public BrickCement(Point2D pos, int width, int height) {
        super(pos, width, height, STRENGTH, BORDER, INNER);

        this.crack = new Crack(CRACK_DEPTH, STEPS);
        this.cementFace = super.getSuperShape();
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
        return cementFace;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setImpact(Point2D point, String dir) {
        impact();

        if (isBroken())
            return false;

        if (!isBroken()) {
            // Crack direction = up
            if (dir.equalsIgnoreCase("up"))
                crack.makeCrack(this, point, crack.getUp());
            // Crack direction = down
            if (dir.equalsIgnoreCase("down"))
                crack.makeCrack(this, point, crack.getDown());
            // Crack direction = left
            if (dir.equalsIgnoreCase("left"))
                crack.makeCrack(this, point, crack.getLeft());
            // Crack direction = right
            if (dir.equalsIgnoreCase("right"))
                crack.makeCrack(this, point, crack.getRight());

            updateBrick();
            return false;
        }
        return true;
    }

    /**
     * Calls the parent's {@code repair()} method and resets the cracks.
     */
    @Override
    public void repair() {
        super.repair();
        crack.reset();
        cementFace = super.getSuperShape();
    }

    /**
     * Draw cracks on the Brick if it's not broken.
     */
    private void updateBrick() {
        if (!isBroken()) {
            GeneralPath path = crack.draw();
            path.append(super.getSuperShape(), false);
            cementFace = path;
        }
    }
}
