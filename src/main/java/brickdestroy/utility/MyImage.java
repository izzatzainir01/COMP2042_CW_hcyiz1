package brickdestroy.utility;

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

/**
 * My own image class with some functions that make my life easier.
 */
public class MyImage {

    private Image image;
    private Point2D location;

    /**
     * The {@code MyImage} class is a class I made to also make my life easier. It's
     * mainly for defining methods that I find tedious to write all the time and use
     * somewhat frequently.
     * 
     * @param fileName - The name of the image file.
     */
    public MyImage(String fileName) {
        // Format the path to the image file
        String path = String.format("/img/%s", fileName);
        image = new ImageIcon(getClass().getResource(path)).getImage();
    }

    /**
     * Rescale the image while preserving the original aspect ratio.
     * 
     * @param width - The new width of the image.
     */
    public void rescale(double width) {
        double ratio = width / image.getWidth(null);
        image = image.getScaledInstance((int) width, (int) (image.getHeight(null) * ratio), Image.SCALE_DEFAULT);
        image = new ImageIcon(image).getImage();
    }

    /**
     * Resize the image without perserving the original aspect ratio.
     * 
     * @param width  - The new width of the image.
     * @param height - The new height of the image.
     */
    public void resize(int width, int height) {
        image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        image = new ImageIcon(image).getImage();
    }

    /**
     * Set the location of the image. While this doesn't technically do anything
     * functionally, it stores the location that I want to put the image at.
     * 
     * @param x - The X coordinate.
     * @param y - The Y coordinate.
     */
    public void setLocation(int x, int y) {
        location = new Point(x, y);
    }

    /**
     * Get the X coordinate of the image.
     * 
     * @return An {@code int} of the X coordinate.
     */
    public int getX() {
        return (int) location.getX();
    }

    /**
     * Get the Y coordinate of the image.
     * 
     * @return An {@code int} of the Y coordinate.
     */
    public int getY() {
        return (int) location.getY();
    }

    /**
     * Get the width of the image.
     * 
     * @return An {@code int} of the image's width.
     */
    public int getWidth() {
        return image.getWidth(null);
    }

    /**
     * Get the height of the image.
     * 
     * @return An {@code int} of the image's height.
     */
    public int getHeight() {
        return image.getHeight(null);
    }

    /**
     * Get an {@code ImageIcon} object of the image.
     * 
     * @return An {@code ImageIcon} object of the image.
     */
    public ImageIcon getImageIcon() {
        return new ImageIcon(image);
    }

    /**
     * Get an {@code Image} object of the image.
     * 
     * @return An {@code Image} object of the image.
     */
    public Image getImage() {
        return new ImageIcon(image).getImage();
    }

}
