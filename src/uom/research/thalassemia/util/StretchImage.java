/**
 * This class is used for StretchImage for a given JLabel height and width.
 */
package uom.research.thalassemia.util;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Custom Image Utility.
 *
 * @author anupama
 */
public final class StretchImage {

    /**
     * method to stretch image and set on a label when image path is given.
     *
     * @param imageLabel destination image label
     * @param imagePath source image file path
     */
    public static void setImageStretch(final JLabel imageLabel,
            final String imagePath) {
        ImageIcon ic = new ImageIcon(imagePath);
        Image im = ic.getImage();
        im = im.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),
                Image.SCALE_AREA_AVERAGING);
        ic.setImage(im);
        imageLabel.setIcon(ic);
    }

    /**
     * method to stretch image and set on a label when buffered image is given.
     *
     * @param imageLabel destination image label
     * @param ic source buffered image
     */
    public static void setImageStretch(final JLabel imageLabel,
            final ImageIcon ic) {
        /*Image im = ic.getImage();
        im = im.getScaledInstance(imageLabel.getWidth(),
                imageLabel.getHeight(), Image.SCALE_SMOOTH);
        ic.setImage(im);
        imageLabel.setIcon(ic);*/
        
        Image im = ic.getImage();
        im = im.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),
                Image.SCALE_SMOOTH);
        ic.setImage(im);
        imageLabel.setIcon(ic);
    }

    /**
     * private default constructor to avoid creating objects.
     */
    private StretchImage() {

    }
}
