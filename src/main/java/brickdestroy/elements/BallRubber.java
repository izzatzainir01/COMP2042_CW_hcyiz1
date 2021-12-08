/*
 *  Brick Destroy - A simple arcade video game
 *  Copyright (C) 2021  Izzat Zainir
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package brickdestroy.elements;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * A child class of {@link Ball} that represents a 'normal' ball. It is a
 * circular ball with a constant diameter all around. Basically, it's a normal
 * ball.
 * <p>
 * It is responsible for defining its colours and {@code Shape}, which is a
 * circle, as all balls should be.
 */
public class BallRubber extends Ball {

    private static final Color BORDER = Color.DARK_GRAY.darker();
    private static final Color INNER = new Color(255, 219, 88);

    /**
     * A child class of {@link Ball} that represents a 'normal' ball. It is a
     * circular ball with a constant diameter all around. Basically, it's a normal
     * ball with a dark gray border and a yellow inner colour.
     * <p>
     * It is responsible for defining its colours.
     * 
     * @param center The center location
     * @param width  The width/diameter
     */
    public BallRubber(Point2D center, int width) {
        super(center, width, width, BORDER, INNER);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The {@code BallRubber} class uses an {@link Ellipse2D} to define its shape as
     * a circle.
     */
    @Override
    protected Shape makeBall(Point2D center, int width, int height) {
        // Get the top left corner of the Shape
        double x = center.getX() - (width / 2);
        double y = center.getY() - (height / 2);

        return new Ellipse2D.Double(x, y, width, height);
    }
}
