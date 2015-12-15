package uom.research.thalassemia.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import uom.research.thalassemia.ui.JDocBar;

/**
 *
 * @author HansA AmarasekarA
 */
public final class ObjectBarSetter {

    /**
     * get Dock Bar object.
     */
    private final JDocBar docBar = new JDocBar();

    /**
     * sets Dock Bar Icons and Actions.
     *
     * @param panel panel
     * @param name name
     * @param icons icons
     * @param action action
     */
    public void setDockBarIcon(final JPanel panel, final String[] name,
            final ImageIcon[] icons, final ActionListener[] action) {

        for (int i = 0; i < icons.length; i++) {
            ImageIcon imageIcon = icons[i];
            Image image = imageIcon.getImage();
            BufferedImage b = new BufferedImage(image.getHeight(null),
                    image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics g = b.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            b = new BufferedImage(image.getHeight(null),
                    image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            g = b.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            docBar.addApplication(name[i], b, action[i]);

        }
        docBar.setFrameParent(docBar, panel);
    }
}
