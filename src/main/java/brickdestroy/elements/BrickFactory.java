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

import java.awt.Dimension;
import java.awt.Point;

/**
 * A factory for making bricks.
 */
public class BrickFactory {

    public static final int CLAY = 1;
    public static final int STEEL = 2;
    public static final int CEMENT = 3;

    /**
     * Makes a brick of the specified type. Idk what else to say
     * 
     * @param point The top-left point
     * @param size  The size
     * @param type  The type of brick
     * @return
     */
    public static Brick makeBrick(Point point, Dimension size, int type) {
        switch (type) {
            case CLAY:
                return new BrickClay(point, size.width, size.height);
            case STEEL:
                return new BrickSteel(point, size.width, size.height);
            case CEMENT:
                return new BrickCement(point, size.width, size.height);
            default:
                throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        }
    }

}
