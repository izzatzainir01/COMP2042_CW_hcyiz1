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

    public MyImage(String fileName) {
        // Format the path to the image file
        String path = String.format("img/%s", fileName);
        image = new ImageIcon(path).getImage();
    }

    // Rescale while keeping the original image's ratio
    public void rescale(double newWidth) {
        double ratio = newWidth / image.getWidth(null);
        image = image.getScaledInstance((int) newWidth, (int) (image.getHeight(null) * ratio), Image.SCALE_DEFAULT);
        image = new ImageIcon(image).getImage();
    }

    // Keep track of the image's location since I can't keep track of painted
    // images' locations.
    public void setLocation(int x, int y) {
        location = new Point(x, y);
    }

    public void setLocation(Point2D p) {
        setLocation((int) p.getX(), (int) p.getY());
    }

    public int getX() {
        return (int) location.getX();
    }

    public int getY() {
        return (int) location.getY();
    }

    public int getWidth() {
        return image.getWidth(null);
    }

    public int getHeight() {
        return image.getHeight(null);
    }

    public ImageIcon getImageIcon() {
        return new ImageIcon(image);
    }

    public Image getImage() {
        return new ImageIcon(image).getImage();
    }

}
