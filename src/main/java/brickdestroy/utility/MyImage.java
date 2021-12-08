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
package brickdestroy.utility;

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

/**
 * A utility class that contains methods for images that I use somewhat
 * oftenly and didn't want to write it over and over again throughout the
 * program.
 * <p>
 * Basically, it's just a compilation of methods for images.
 * 
 * @see ImageIcon
 * @see Image
 */
public class MyImage {

    private Image image;
    private Point2D location;

    /**
     * A utility class that contains methods for images that I use somewhat
     * oftenly and didn't want to write it over and over again throughout the
     * program.
     * <p>
     * Basically, it's just a compilation of methods for images.
     * 
     * @param fileName The name of the image file
     * 
     * @see ImageIcon
     * @see Image
     */
    public MyImage(String fileName) {
        // Format the path to the image file
        String path = String.format("/img/%s", fileName);
        image = new ImageIcon(getClass().getResource(path)).getImage();
    }

    /**
     * Rescales the image while preserving the original aspect ratio.
     * 
     * @param width - The new width
     */
    public void rescale(double width) {
        double ratio = width / image.getWidth(null);
        image = image.getScaledInstance((int) width, (int) (image.getHeight(null) * ratio), Image.SCALE_DEFAULT);
        image = new ImageIcon(image).getImage();
    }

    /**
     * Resizes the image without perserving the original aspect ratio.
     * 
     * @param width  - The new width
     * @param height - The new height
     */
    public void resize(int width, int height) {
        image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        image = new ImageIcon(image).getImage();
    }

    /**
     * Sets the location of the image. While this doesn't technically do anything
     * functionally, it stores the location that I want to put the image at.
     * 
     * @param x - The X coordinate
     * @param y - The Y coordinate
     */
    public void setLocation(int x, int y) {
        location = new Point(x, y);
    }

    /**
     * Gets the X coordinate of the image.
     * 
     * @return An {@code int} of the X coordinate
     */
    public int getX() {
        return (int) location.getX();
    }

    /**
     * Gets the Y coordinate of the image.
     * 
     * @return An {@code int} of the Y coordinate
     */
    public int getY() {
        return (int) location.getY();
    }

    /**
     * Gets the width of the image.
     * 
     * @return An {@code int} of the image's width
     */
    public int getWidth() {
        return image.getWidth(null);
    }

    /**
     * Gets the height of the image.
     * 
     * @return An {@code int} of the image's height
     */
    public int getHeight() {
        return image.getHeight(null);
    }

    /**
     * Gets an {@code ImageIcon} object of the image.
     * 
     * @return An {@code ImageIcon} object of the image
     */
    public ImageIcon getImageIcon() {
        return new ImageIcon(image);
    }

    /**
     * Gets an {@code Image} object of the image.
     * 
     * @return An {@code Image} object of the image
     */
    public Image getImage() {
        return new ImageIcon(image).getImage();
    }

}
