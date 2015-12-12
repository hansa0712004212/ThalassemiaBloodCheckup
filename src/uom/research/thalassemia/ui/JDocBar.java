package uom.research.thalassemia.ui;

/**
 *
 * @author hansa
 */
import FrameWorks.RoundShape;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.Animator.RepeatBehavior;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.interpolation.PropertySetter;
import org.jdesktop.swingx.graphics.GraphicsUtilities;
import org.jdesktop.swingx.graphics.ReflectionRenderer;

/**
 * this panel keeps Dock Bar.
 *
 * @author hansa
 */
public final class JDocBar extends JPanel {

    /**
     * dock bar icon size.
     */
    private static final int ICON_SIZE = 32;

    /**
     * dock bar zoom level.
     */
    private static final int ZOOM_LEVEL = 2;

    /**
     * dock bar mouse out zoom level.
     */
    private static final int MOUSE_OUT = 1;

    /**
     * dock bar plate shading color 1.
     */
    private final Color colorShade1 = new Color(255, 0, 0);

    /**
     * dock bar plate shading color 2.
     */
    private final Color colorShade2 = new Color(255, 0, 0);

    /**
     * dock bar plate shading color 3.
     */
    private final Color colorShade3 = new Color(255, 0, 0);

    /**
     * dock bar plate transparent color.
     */
    private final Color transparent = new Color(255, 255, 255, 255);

    /**
     * dock bar plate color palate.
     */
    private final Color[] colors = new Color[]{transparent, transparent,
        transparent};

    /**
     * Buffered Image cacher.
     */
    private BufferedImage cache;

    /**
     * Link List of Icons to be placed on Dock Bar.
     */
    private final LinkedList<IconOnBar> iconsOnBar;

    /**
     * Glass Panel.
     */
    private Glass glass;

    /**
     * Creates new form JDocBar.
     */
    public JDocBar() {
        initComponents();
        iconsOnBar = new LinkedList<>();

        //setOpaque(false);
        addMouseListener(new LauncherMouseListener());
    }

    /**
     * sets frames parent.
     *
     * @param jDocBar jDocBar
     * @param jPanel jPanel
     */
    public void setFrameParent(final JDocBar jDocBar, final JPanel jPanel) {
        JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class,
                jPanel);
        setGlass(new Glass(jDocBar));
        frame.setGlassPane(getGlass());
        jPanel.add(jDocBar);
    }

    @Override
    public Dimension getPreferredSize() {
        int w = 15 + iconsOnBar.size() * (ICON_SIZE + 15);
        return new Dimension(w, 50);
    }

    /**
     * Paint Dock Bar Plate Background.
     *
     * @param width int width
     * @param dockBar JDockBar
     * @param g Graphics
     * @return BufferedImage
     */
    public BufferedImage paintBackground(final int width, final JDocBar dockBar,
            final Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        if (cache == null || cache.getWidth() != width) {
            BufferedImage gradientImage = new BufferedImage(width, 2,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = gradientImage.createGraphics();
            Point2D start = new Point2D.Double(15, 0);
            Point2D end = new Point2D.Double(width - 15, 0);
            float[] fractions = new float[]{0f, 0.5f, 1};
            LinearGradientPaint paint = new LinearGradientPaint(start, end,
                    fractions, colors);
            g2d.setPaint(paint);
            g2d.fillRect(0, 0, width, 2);
            g2d.dispose();
            Polygon polygon = new Polygon(
                    new int[]{15, width - 15, width, 0}, new int[]{
                        (dockBar.getHeight() / 3),
                        (dockBar.getHeight() / 3), dockBar.getHeight(),
                        dockBar.getHeight()}, 4);
            Shape rpolygon = new RoundShape(polygon, 5);
            GraphicsConfiguration gc = g2.getDeviceConfiguration();
            cache = gc.createCompatibleImage(width, dockBar.getHeight(),
                    Transparency.TRANSLUCENT);
            Graphics2D g22 = cache.createGraphics();

            g22.setComposite(AlphaComposite.Clear);
            g22.fillRect(0, 0, width, dockBar.getHeight());
            g22.setComposite(AlphaComposite.Src);
            g22.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g22.setColor(Color.WHITE);
            g22.fill(rpolygon);

            g22.setComposite(AlphaComposite.SrcAtop);
            g22.drawImage(gradientImage, 0, 0, width, dockBar.getHeight(),
                    null);
            gradientImage.flush();
            g22.dispose();
        }
        return cache;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        if (!glass.isVisible()) {
            Graphics2D g2 = (Graphics2D) g;
            BufferedImage background = paintBackground(1, this,
                    g2);
            g2.drawImage(background, 0, 0, null);
            int x = 15;
            int y = 0;

            for (IconOnBar iconOnBar : iconsOnBar) {
                ReflectionRenderer renderer = new ReflectionRenderer();
                int originalH = iconOnBar.getIcon().getHeight();
                BufferedImage mirror = renderer.appendReflection(
                        iconOnBar.getIcon());
                int processedH = mirror.getHeight();
                int newH = (processedH * ICON_SIZE) / originalH;
                g.drawImage(mirror, x, y + getHeight() - ICON_SIZE - 6,
                        ICON_SIZE, newH, null);
                mirror.flush();
                x += ICON_SIZE + 15;
            }
        }
    }

    /**
     * Add Application into Dock Bar Plate.
     *
     * @param label label for application
     * @param icon icon for application
     * @param actionListener action for application
     */
    public void addApplication(final String label, final BufferedImage icon,
            final ActionListener actionListener) {
        iconsOnBar.add(new IconOnBar(label, icon, actionListener));
    }

    /**
     * @return the glass
     */
    public Glass getGlass() {
        return glass;
    }

    /**
     * @param pglass the glass to set
     */
    public void setGlass(final Glass pglass) {
        this.glass = pglass;
    }

    /**
     * private Inner Class for Icons On Dock Bar.
     */
    private final class IconOnBar {

        /**
         * dock item label.
         */
        private final String label;

        /**
         * dock item icon.
         */
        private final BufferedImage icon;

        /**
         * dock item mirror.
         */
        private final BufferedImage mirror;

        /**
         * dock item mouseIs.
         */
        private int mouseIs = MOUSE_OUT;

        /**
         * dock item mouseWas.
         */
        private int mouseWas = MOUSE_OUT;

        /**
         * dock item actionListener.
         */
        private final ActionListener actionListener;

        /**
         * Icon On Bar.
         *
         * @param plabel label
         * @param picon icon
         * @param pactionListener actionListener
         */
        public IconOnBar(final String plabel, final BufferedImage picon,
                final ActionListener pactionListener) {
            this.label = plabel;
            this.icon = picon;
            ReflectionRenderer renderer = new ReflectionRenderer();
            this.mirror = renderer.createReflection(picon);
            this.actionListener = pactionListener;
            setOpaque(true);
        }

        /**
         * get Icon.
         *
         * @return BufferedImage
         */
        public BufferedImage getIcon() {
            return icon;
        }

        /**
         * get Mirror.
         *
         * @return BufferedImage
         */
        public BufferedImage getMirror() {
            return mirror;
        }

        /**
         * lastH.
         */
        private int lastH;

        /**
         * resizedIcon.
         */
        private BufferedImage resizedIcon;

        /**
         * get Resized Icon.
         *
         * @return BufferedImage
         */
        public BufferedImage getResizedIcon() {
            int h = calculateCurrentSize();
            if (lastH != h) {
                resizedIcon = GraphicsUtilities
                        .createCompatibleTranslucentImage(h, (int) (h * 1.3));
                Graphics g = resizedIcon.createGraphics();
                g.drawImage(getIcon(), 0, 0, h, h, null);
                g.drawImage(mirror, 0, h, h, (int) (h * 0.3), null);
                g.dispose();
            }
            lastH = h;
            return resizedIcon;
        }

        /**
         * get Label.
         *
         * @return label
         */
        public String getLabel() {
            return label;
        }

        /**
         * get Action Listener.
         *
         * @return ActionListener
         */
        public ActionListener getActionListener() {
            return actionListener;
        }

        /**
         * set Mouse Location.
         *
         * @param location location
         */
        public void setMouseLocation(final int location) {
            mouseWas = mouseIs;
            mouseIs = location;
        }

        /**
         * get Mouse Location.
         *
         * @return int
         */
        public int getMouseLocation() {
            return mouseIs;
        }

        /**
         * calculate CurrentSize.
         *
         * @return int
         */
        public int calculateCurrentSize() {
            return (int) (ICON_SIZE * ((mouseWas)
                    + ((mouseIs) - (mouseWas)) * getGlass().getProgress()));
        }
    }

    /**
     * Launcher Mouse Listener.
     */
    private final class LauncherMouseListener implements MouseListener {

        /**
         * mouse click event.
         *
         * @param e event
         */
        @Override
        public void mouseClicked(final MouseEvent e) {
            // TODO Auto-generated method stub
        }

        /**
         * mouse Entered.
         *
         * @param e event
         */
        @Override
        public void mouseEntered(final MouseEvent e) {
            if (getGlass() != null) {
                getGlass().setVisible(true);
            }
        }

        /**
         * mouse Exited.
         *
         * @param e event
         */
        @Override
        public void mouseExited(final MouseEvent e) {
        }

        /**
         * mouse Pressed.
         *
         * @param e event
         */
        @Override
        public void mousePressed(final MouseEvent e) {
            // TODO Auto-generated method stub
        }

        /**
         * mouse Released.
         *
         * @param e event
         */
        @Override
        public void mouseReleased(final MouseEvent e) {
            // TODO Auto-generated method stub
        }
    }

    /**
     * Glass JPanel.
     */
    public final class Glass extends JPanel {

        /**
         * dockBar.
         */
        private final JDocBar dockBar;

        /**
         * progress.
         */
        private float progress;

        /**
         * clickProgress.
         */
        private float clickProgress;

        /**
         * Glass.
         *
         * @param pdockBar dockBar
         */
        public Glass(final JDocBar pdockBar) {
            this.dockBar = pdockBar;
            setOpaque(false);
            addMouseMotionListener(new GlassMouseMotionListener(this));
            addMouseListener(new GlassMouseListener(this));
        }

        public JDocBar getDockBar() {
            return dockBar;
        }

        public void setProgress(float progress) {
            this.progress = progress;
            Point p = dockBar.getLocationOnScreen();
            SwingUtilities.convertPointFromScreen(p, getRootPane().getLayeredPane());
            int y = (int) (p.y - 20 * clickProgress);
            repaint(0, y - 200, getSize().width, getSize().height - y + 200);
            //repaint();
        }

        public void setClickProgress(float clickProgress) {
            this.clickProgress = clickProgress;
            Point p = dockBar.getLocationOnScreen();
            SwingUtilities.convertPointFromScreen(p, getRootPane().getLayeredPane());
            int y = (int) (p.y - 20 * clickProgress);

            int currentWidth = 0;

            for (IconOnBar iconOnBar : iconsOnBar) {
                currentWidth += iconOnBar.calculateCurrentSize() + 15;
            }
            int x = (getWidth() - currentWidth) / 2;
            for (IconOnBar iconOnBar : iconsOnBar) {
                int h = iconOnBar.calculateCurrentSize();
                if (iconOnBar.getMouseLocation() == ZOOM_LEVEL) {
                    break;
                }
                x += h + 15;
            }
            int x1 = (int) (x - 5 * clickProgress);
            repaint(x1, y - 400, 220, getSize().height - y + 400);
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            int currentWidth = 0;
            for (IconOnBar iconOnBar : iconsOnBar) {
                currentWidth += iconOnBar.calculateCurrentSize() + 15;
            }
            int x = (getWidth() - currentWidth) / 2;

            BufferedImage background = paintBackground(currentWidth, dockBar, g);
            Point p = dockBar.getLocationOnScreen();
            SwingUtilities.convertPointFromScreen(p, getRootPane().getLayeredPane());

            g.drawImage(background, x, p.y, currentWidth, dockBar.getHeight(),
                    null);

            ReflectionRenderer renderer = new ReflectionRenderer();
            for (IconOnBar iconOnBar : iconsOnBar) {
                int y = p.y;
                int x1 = x;
                int h = iconOnBar.calculateCurrentSize();
                int h1 = h;
                BufferedImage mirror;
                if (iconOnBar.getMouseLocation() == ZOOM_LEVEL) {
                    y = (int) (y - 20 * clickProgress);
                    x1 = (int) (x - 5 * clickProgress);
                    h1 += (int) ((h1 * clickProgress) / 20);
                    int fontHeight = 14; //(h1 * 8 / 25);
                    g.setFont(new Font("Arial", Font.BOLD, fontHeight));
                    int labelWidth = g.getFontMetrics().stringWidth(iconOnBar.getLabel());
                    int labelX = x1 + (h1 - labelWidth) / 2 + 5;
                    labelX = Math.max(15, labelX);
                    labelX = Math.min(labelX, getWidth() - 15 - labelWidth);
                    g.setClip(0, 0, getSize().width, getSize().height);
                    int labelY = p.y + fontHeight + 20;
                    g.setColor(Color.BLACK);
                    g.drawString(iconOnBar.getLabel(), labelX + 1, labelY + 1);
                    g.drawString(iconOnBar.getLabel(), labelX - 1, labelY - 1);
                    g.drawString(iconOnBar.getLabel(), labelX - 1, labelY + 1);
                    g.drawString(iconOnBar.getLabel(), labelX + 1, labelY);
                    g.drawString(iconOnBar.getLabel(), labelX, labelY - 1);
                    g.drawString(iconOnBar.getLabel(), labelX, labelY + 1);
                    g.setColor(Color.white);
                    g.drawString(iconOnBar.getLabel(), labelX, labelY);

                    BufferedImage reflection = iconOnBar.getMirror();
                    BufferedImage buffer = GraphicsUtilities.createCompatibleTranslucentImage(reflection.getWidth(), iconOnBar.getIcon().getHeight() + reflection.getHeight());
                    Graphics2D g2 = buffer.createGraphics();

                    int effectiveRadius = renderer.getEffectiveBlurRadius();

                    g2.drawImage(reflection, 0, (int) (iconOnBar.getIcon().getHeight() - effectiveRadius + 30 * clickProgress), null);
                    g2.drawImage(iconOnBar.getIcon(), effectiveRadius, 0, null);
                    g2.dispose();
                    reflection.flush();
                    mirror = buffer;
                    int originalH = iconOnBar.getIcon().getHeight();

                    int processedH = mirror.getHeight();
                    int newH = (processedH * h1) / originalH;
                    g.setClip(x1, y - h1, h1, dockBar.getHeight() + h1 + 7);
                    g.drawImage(mirror, x1, y + dockBar.getHeight() - h1 - 20, h1,
                            newH, null);
                } else {
                    mirror = iconOnBar.getResizedIcon(); //renderer.appendReflection(iconOnBar.getResizedIcon());
                    g.setClip(x1, y - h1, h1, dockBar.getHeight() + h1 + 7);
                    g.drawImage(mirror, x1, y + dockBar.getHeight() - h1 - 20, null);
                }
                mirror.flush();
                x += h + 15;
            }
        }

        public float getProgress() {
            return progress;
        }
    }

    private class GlassMouseListener implements MouseListener {

        private Glass glass;
        Animator animator;

        public GlassMouseListener(Glass glass) {
            this.glass = glass;

        }

        public void mouseClicked(MouseEvent e) {
            for (IconOnBar iconOnBar : iconsOnBar) {
                if (iconOnBar.getMouseLocation() == ZOOM_LEVEL) {
                    if (animator != null && animator.isRunning()) {
                        animator.stop();
                    }
                    animator = PropertySetter.createAnimator(400, glass,
                            "clickProgress", 0f, 3f);
                    animator.addTarget(new TimingTarget() {

                        public void begin() {
                            // TODO Auto-generated method stub
                        }

                        public void end() {
                            for (int i = 0; i < iconsOnBar.size(); ++i) {
                                IconOnBar iconOnBar = iconsOnBar.get(i);
                                iconOnBar.setMouseLocation(MOUSE_OUT);
                            }
                            glass.setVisible(false);
                        }

                        public void repeat() {
                            // TODO Auto-generated method stub
                        }

                        public void timingEvent(float arg0) {
                            // TODO Auto-generated method stub
                        }
                    });
                    animator.setRepeatBehavior(RepeatBehavior.REVERSE);
                    animator.setRepeatCount(6);
                    animator.setDeceleration(0.98f);
                    animator.start();
                    ActionListener al = iconOnBar.getActionListener();
                    if (al != null) {
                        ActionEvent event = new ActionEvent(iconOnBar, 0, iconOnBar.getLabel());
                        al.actionPerformed(event);
                    }
                    return;
                }
            }
        }

        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
        }
    }

    private class GlassMouseMotionListener implements MouseMotionListener {

        private Glass glass;
        Animator animator;

        public GlassMouseMotionListener(Glass glass) {
            this.glass = glass;

        }

        public void mouseDragged(MouseEvent e) {
            // TODO Auto-generated method stub
        }
        private int lastMouseOver = -1;

        public void mouseMoved(MouseEvent e) {
            int x = 0;
            for (IconOnBar iconOnBar : iconsOnBar) {
                int h = iconOnBar.calculateCurrentSize();
                x += h + 15;
            }

            x = (glass.getWidth() - x) / 2;

            Point p = glass.getDockBar().getLocationOnScreen();
            SwingUtilities.convertPointFromScreen(p, getRootPane().getLayeredPane());

            int y = p.y;

            int i = 0;
            if (y - (ICON_SIZE * ZOOM_LEVEL) > e.getPoint().y) {
                glass.setVisible(false);
                mouseEntered(9999);
                mouseEntered(9999);
            } else {
                for (IconOnBar iconOnBar : iconsOnBar) {
                    int h = iconOnBar.calculateCurrentSize();
                    int mousePos = e.getPoint().x;
                    if ((x < mousePos) && (x + h > mousePos)) {
                        if (lastMouseOver != i) {
                            mouseEntered(i);
                            lastMouseOver = i;
                            return;
                        }
                    }
                    x += h + 15;
                    i++;
                }
            }
        }

        public void mouseEntered(int appId) {
            for (int i = 0; i < iconsOnBar.size(); ++i) {
                IconOnBar iconOnBar = iconsOnBar.get(i);
                int diff = Math.abs(appId - i);
                if (diff < ZOOM_LEVEL - 1) {
                    iconOnBar.setMouseLocation(ZOOM_LEVEL - diff);
                } else {
                    iconOnBar.setMouseLocation(MOUSE_OUT);
                }
            }
            if (animator != null && animator.isRunning()) {
                animator.stop();
            }
            animator = PropertySetter.createAnimator(400, glass, "progress",
                    0f, 1f);
            animator.setAcceleration(0.3f);
            animator.setDeceleration(0.2f);
            animator.start();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 321, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
