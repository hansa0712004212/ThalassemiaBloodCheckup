/**
 * This class use for resizing image.
 */
package uom.research.thalassemia.util;

import javax.swing.ImageIcon;
import javaxt.io.Image;

/**
 *
 * @author anupama
 */
public final class ResizeImage {

    /**
     * Width for Icon.
     */
    private static final int ICON_WIDTH = 24;

    /**
     * method to stretch image and set on a label when image path is given.
     *
     * @param imageName resource file name
     * @return ImageIcon
     */
    public static ImageIcon resizeImage(final String imageName) {
        Image i = new Image(imageName);
        //i.resize(ICON_WIDTH, ICON_WIDTH);
        return new ImageIcon(i.getImage());
    }

    /**
     * private default constructor to avoid creating objects.
     */
    private ResizeImage() {

    }
}
