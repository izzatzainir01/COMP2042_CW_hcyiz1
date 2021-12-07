package brickdestroy.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * A child class of {@link Brick}. It is mid level brick that has slightly more
 * strength than {@link BrickClay}. It is a rectangular shaped brick with a dark
 * gray border and a gray inner colour that has the ability to crack upon
 * impact.
 * <p>
 * It is responsible for defining its colours and its strength.
 */
public class BrickCement extends Brick {

    private static final Color BORDER = Color.GRAY.darker();
    private static final Color INNER = new Color(147, 147, 147);
    private static final int STRENGTH = 2;

    private final int CRACK_DEPTH = 2;
    private final int STEPS = 35;

    private Crack crack;

    private Point2D pos;
    private int width;
    private int height;

    /**
     * A child class of {@link Brick}. It is mid level brick that has slightly more
     * strength than {@link BrickClay}. It is a rectangular shaped brick with a dark
     * gray border and a gray inner colour that has the ability to crack upon
     * impact.
     * <p>
     * It is responsible for defining its colours and its strength.
     * 
     * @param pos    The top left corner
     * @param width  The width
     * @param height The height
     */
    public BrickCement(Point2D pos, int width, int height) {
        super(pos, width, height, STRENGTH, BORDER, INNER);

        this.pos = pos;
        this.width = width;
        this.height = height;

        this.crack = new Crack(CRACK_DEPTH, STEPS);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The {@code BrickCement} class uses a {@link Rectangle} to define its
     * {@code Shape}.
     */
    @Override
    protected Shape makeBrickFace(Point2D pos, int width, int height) {
        return new Rectangle((Point) pos, new Dimension(width, height));
    }

    /**
     * {@inheritDoc}
     * <p>
     * If the brick impacted is a {@code BrickCement}, then it will also determine
     * how to draw cracks on the brick.
     */
    @Override
    public boolean setImpact(Point2D point, String dir) {
        if (isBroken())
            return false;
        impact();

        if (!isBroken()) {
            // Crack direction = up
            if (dir.equalsIgnoreCase("up"))
                crack.makeCrack(this, point, Crack.UP);
            // Crack direction = down
            if (dir.equalsIgnoreCase("down"))
                crack.makeCrack(this, point, Crack.DOWN);
            // Crack direction = left
            if (dir.equalsIgnoreCase("left"))
                crack.makeCrack(this, point, Crack.LEFT);
            // Crack direction = right
            if (dir.equalsIgnoreCase("right"))
                crack.makeCrack(this, point, Crack.RIGHT);

            updateBrick();
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * If the brick impacted is a {@code BrickCement}, then it will also reset the
     * cracks.
     */
    @Override
    public void repair() {
        super.repair();
        crack.reset();
        brickFace = makeBrickFace(pos, width, height);
    }

    /**
     * Draw cracks on the Brick if it's not broken.
     */
    private void updateBrick() {
        if (!isBroken()) {
            GeneralPath path = crack.draw();
            path.append(brickFace, false);
            brickFace = path;
        }
    }
}
