/**
 * Custom JFileChooser use to select and open image files only.
 */
package uom.research.thalassemia.util;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * Custom File Chooser.
 *
 * @author anupama
 */
public final class ImageFileChooser {

    /**
     * keeps track of last opened folder location.
     */
    private static String lastOpenedLocation
            = FileSystemView.getFileSystemView().getHomeDirectory()
            .getAbsolutePath() + File.separator + "Pictures";

    /**
     * JPEG Image Filter.
     */
    private static final FileFilter JPG_FILTER
            = new FileNameExtensionFilter("JPEG Image Files", "jpg", "jpeg");

    /**
     * JPEG Image Filter.
     */
    private static final FileFilter PNG_FILTER
            = new FileNameExtensionFilter("PNG Image Files", "png");

    /**
     * JPEG Image Filter.
     */
    private static final FileFilter GIF_FILTER
            = new FileNameExtensionFilter("GIF Image Files", "gif");

    /**
     * JPEG Image Filter.
     */
    private static final FileFilter IMAGES_FILTER
            = new FileNameExtensionFilter("All Image Files", "jpg", "jpeg",
                    "png", "gif");

    /**
     * Swing JFileChooser to select an image file.
     *
     * @param frame the parent frame for ImageFileChooser
     * @return selected image file
     */
    public static File openImageFile(final Component frame) {
        JFileChooser imageChooser = new JFileChooser(lastOpenedLocation);
        imageChooser.setFileFilter(JPG_FILTER);
        imageChooser.setFileFilter(PNG_FILTER);
        imageChooser.setFileFilter(GIF_FILTER);
        imageChooser.setFileFilter(IMAGES_FILTER);
        imageChooser.setAcceptAllFileFilterUsed(false);
        imageChooser.setMultiSelectionEnabled(false);
        imageChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int option = imageChooser.showDialog(frame, "Select Image");
        if (option == JFileChooser.APPROVE_OPTION) {
            synchronized (ImageFileChooser.class) {
                lastOpenedLocation = imageChooser
                        .getCurrentDirectory().getAbsolutePath();
            }
            return imageChooser.getSelectedFile();
        } else {
            return null;
        }
    }

    /**
     * private constructor to avoid creating objects.
     */
    private ImageFileChooser() {

    }
}
