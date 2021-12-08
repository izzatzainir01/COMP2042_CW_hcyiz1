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
