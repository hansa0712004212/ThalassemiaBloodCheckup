package uom.research.thalassemia.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import uom.research.thalassemia.logic.BloodCellsManipulation;
import uom.research.thalassemia.logic.BloodCellsManipulationImpl;
import uom.research.thalassemia.util.StretchImage;

/**
 *
 * @author anupama
 */
public class Segments16Viewer extends javax.swing.JDialog {

    /**
     * keep records of image segment files.
     */
    private List<String> segmentFiles;

    /**
     * keep records of labels in the UI.
     */
    private List<JLabel> labelList;

    /**
     * JFrame Resolution info.
     */
    private static final int FRAME_WIDTH = 1200, FRAME_HEIGHT = 720;

    /**
     * Segment Id constants.
     */
    private static final int SEGMENT_0 = 0, SEGMENT_1 = 1, SEGMENT_2 = 2,
            SEGMENT_3 = 3, SEGMENT_4 = 4, SEGMENT_5 = 5, SEGMENT_6 = 6,
            SEGMENT_7 = 7, SEGMENT_8 = 8, SEGMENT_9 = 9, SEGMENT_10 = 10,
            SEGMENT_11 = 11, SEGMENT_12 = 12, SEGMENT_13 = 13, SEGMENT_14 = 14,
            SEGMENT_15 = 15;

    /**
     * Creates new Segments16Viewer.
     *
     * @param parent parent frame
     * @param modal modal true or false
     * @param files segment files
     */
    public Segments16Viewer(java.awt.Frame parent, boolean modal,
            final List<String> files) {
        super(parent, modal);
        initComponents();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Image Segments Viewer ");
        labelList = new ArrayList<>();
        labelList.add(lblSegmant1);
        labelList.add(lblSegmant2);
        labelList.add(lblSegmant3);
        labelList.add(lblSegmant4);
        labelList.add(lblSegmant5);
        labelList.add(lblSegmant6);
        labelList.add(lblSegmant7);
        labelList.add(lblSegmant8);
        labelList.add(lblSegmant9);
        labelList.add(lblSegmant10);
        labelList.add(lblSegmant11);
        labelList.add(lblSegmant12);
        labelList.add(lblSegmant13);
        labelList.add(lblSegmant14);
        labelList.add(lblSegmant15);
        labelList.add(lblSegmant16);
        segmentFiles = files;
        //loadFiles();
        threadScheduler();
    }

    /**
     * Threads maker.
     */
    private void threadScheduler() {
        new Thread(() -> {
            try {
                doImageProcessing(SEGMENT_0);
                doImageProcessing(SEGMENT_1);
                doImageProcessing(SEGMENT_2);
                doImageProcessing(SEGMENT_3);
                doImageProcessing(SEGMENT_4);
                doImageProcessing(SEGMENT_5);
                doImageProcessing(SEGMENT_6);
                doImageProcessing(SEGMENT_7);
                doImageProcessing(SEGMENT_8);
                doImageProcessing(SEGMENT_9);
                doImageProcessing(SEGMENT_10);
                doImageProcessing(SEGMENT_11);
                doImageProcessing(SEGMENT_12);
                doImageProcessing(SEGMENT_13);
                doImageProcessing(SEGMENT_14);
                doImageProcessing(SEGMENT_15);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, " error is " + ex.getMessage());
            }
        }).start();
    }

    /**
     * Load segment files of original image file.
     */
    private void loadFiles() {
        try {
            for (int i = 0; i < labelList.size(); i++) {
                StretchImage.setImageStretch(labelList.get(i),
                        segmentFiles.get(i));
            }

        } catch (Exception e) {
        }
    }

    /**
     * Identify Blood Cells.
     *
     * @param position segment position
     */
    private void doImageProcessing(final int position) throws Exception {
        BloodCellsManipulation bcm = new BloodCellsManipulationImpl(new File(segmentFiles.get(position)));
        bcm.doBloodCellProcessing();
        StretchImage.setImageStretch(labelList.get(position), new ImageIcon(bcm.getRedImage()));
        setLabelTextValues(bcm, position);
    }

    /**
     * set text values for labels.
     *
     * @param bcm currently processing segment BufferedImage
     * @param position segment position
     */
    private void setLabelTextValues(final BloodCellsManipulation bcm,
            final int position) {
        switch (position) {
            case SEGMENT_0:
                lblCountCells1.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius1.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius1.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            case SEGMENT_1:
                lblCountCells2.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius2.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius2.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            case SEGMENT_2:
                lblCountCells3.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius3.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius3.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            case SEGMENT_3:
                lblCountCells4.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius4.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius4.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            case SEGMENT_4:
                lblCountCells5.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius5.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius5.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            case SEGMENT_5:
                lblCountCells6.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius6.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius6.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            case SEGMENT_6:
                lblCountCells7.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius7.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius7.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            case SEGMENT_7:
                lblCountCells8.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius8.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius8.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            case SEGMENT_8:
                lblCountCells9.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius9.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius9.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            case SEGMENT_9:
                lblCountCells10.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius10.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius10.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            case SEGMENT_10:
                lblCountCells11.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius11.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius11.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            case SEGMENT_11:
                lblCountCells12.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius12.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius12.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            case SEGMENT_12:
                lblCountCells13.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius13.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius13.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            case SEGMENT_13:
                lblCountCells14.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius14.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius14.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            case SEGMENT_14:
                lblCountCells15.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius15.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius15.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            case SEGMENT_15:
                lblCountCells16.setText("Cells Count            : " + bcm.getCircleCount());
                lblMaximumRadius16.setText("Maximum Radius    : " + bcm.getMaximumRadius());
                lblMinimumRadius16.setText("Minimum Radius     : " + bcm.getMinimumRadius());
            default:
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
        lblSegmant1 = new javax.swing.JLabel();
        lblCountCells1 = new javax.swing.JLabel();
        lblMaximumRadius1 = new javax.swing.JLabel();
        lblMinimumRadius1 = new javax.swing.JLabel();
        lblCountCells2 = new javax.swing.JLabel();
        lblMaximumRadius2 = new javax.swing.JLabel();
        lblMinimumRadius2 = new javax.swing.JLabel();
        lblSegmant2 = new javax.swing.JLabel();
        lblCountCells3 = new javax.swing.JLabel();
        lblMaximumRadius3 = new javax.swing.JLabel();
        lblMinimumRadius3 = new javax.swing.JLabel();
        lblSegmant3 = new javax.swing.JLabel();
        lblCountCells4 = new javax.swing.JLabel();
        lblMaximumRadius4 = new javax.swing.JLabel();
        lblMinimumRadius4 = new javax.swing.JLabel();
        lblSegmant4 = new javax.swing.JLabel();
        lblSegmant5 = new javax.swing.JLabel();
        lblCountCells5 = new javax.swing.JLabel();
        lblMaximumRadius5 = new javax.swing.JLabel();
        lblMinimumRadius5 = new javax.swing.JLabel();
        lblSegmant6 = new javax.swing.JLabel();
        lblCountCells6 = new javax.swing.JLabel();
        lblMaximumRadius6 = new javax.swing.JLabel();
        lblMinimumRadius6 = new javax.swing.JLabel();
        lblCountCells7 = new javax.swing.JLabel();
        lblMaximumRadius7 = new javax.swing.JLabel();
        lblMinimumRadius7 = new javax.swing.JLabel();
        lblSegmant7 = new javax.swing.JLabel();
        lblCountCells8 = new javax.swing.JLabel();
        lblMaximumRadius8 = new javax.swing.JLabel();
        lblMinimumRadius8 = new javax.swing.JLabel();
        lblCountCells9 = new javax.swing.JLabel();
        lblMaximumRadius9 = new javax.swing.JLabel();
        lblMinimumRadius9 = new javax.swing.JLabel();
        lblSegmant10 = new javax.swing.JLabel();
        lblCountCells10 = new javax.swing.JLabel();
        lblMaximumRadius10 = new javax.swing.JLabel();
        lblMinimumRadius10 = new javax.swing.JLabel();
        lblSegmant11 = new javax.swing.JLabel();
        lblCountCells11 = new javax.swing.JLabel();
        lblMaximumRadius11 = new javax.swing.JLabel();
        lblMinimumRadius11 = new javax.swing.JLabel();
        lblSegmant12 = new javax.swing.JLabel();
        lblSegmant9 = new javax.swing.JLabel();
        lblSegmant8 = new javax.swing.JLabel();
        lblCountCells12 = new javax.swing.JLabel();
        lblMaximumRadius12 = new javax.swing.JLabel();
        lblMinimumRadius12 = new javax.swing.JLabel();
        lblMinimumRadius13 = new javax.swing.JLabel();
        lblMaximumRadius13 = new javax.swing.JLabel();
        lblCountCells13 = new javax.swing.JLabel();
        lblSegmant13 = new javax.swing.JLabel();
        lblMinimumRadius14 = new javax.swing.JLabel();
        lblMaximumRadius14 = new javax.swing.JLabel();
        lblSegmant14 = new javax.swing.JLabel();
        lblCountCells14 = new javax.swing.JLabel();
        lblMaximumRadius15 = new javax.swing.JLabel();
        lblMinimumRadius15 = new javax.swing.JLabel();
        lblCountCells15 = new javax.swing.JLabel();
        lblSegmant15 = new javax.swing.JLabel();
        lblSegmant16 = new javax.swing.JLabel();
        lblCountCells16 = new javax.swing.JLabel();
        lblMaximumRadius16 = new javax.swing.JLabel();
        lblMinimumRadius16 = new javax.swing.JLabel();
        btnClse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSegmant1.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant1.setOpaque(true);
        jPanel1.add(lblSegmant1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 190, 140));

        lblCountCells1.setText("Cells Count            : ");
        jPanel1.add(lblCountCells1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 190, -1));

        lblMaximumRadius1.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 190, -1));

        lblMinimumRadius1.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 190, -1));

        lblCountCells2.setText("Cells Count            : ");
        jPanel1.add(lblCountCells2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 190, -1));

        lblMaximumRadius2.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 190, -1));

        lblMinimumRadius2.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 190, -1));

        lblSegmant2.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant2.setOpaque(true);
        jPanel1.add(lblSegmant2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 190, 140));

        lblCountCells3.setText("Cells Count            : ");
        jPanel1.add(lblCountCells3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 190, -1));

        lblMaximumRadius3.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 190, -1));

        lblMinimumRadius3.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 190, -1));

        lblSegmant3.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant3.setOpaque(true);
        jPanel1.add(lblSegmant3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 190, 140));

        lblCountCells4.setText("Cells Count            : ");
        jPanel1.add(lblCountCells4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 190, -1));

        lblMaximumRadius4.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, 190, -1));

        lblMinimumRadius4.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, 190, -1));

        lblSegmant4.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant4.setOpaque(true);
        jPanel1.add(lblSegmant4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 190, 140));

        lblSegmant5.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant5.setOpaque(true);
        jPanel1.add(lblSegmant5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 190, 140));

        lblCountCells5.setText("Cells Count            : ");
        jPanel1.add(lblCountCells5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 150, 190, -1));

        lblMaximumRadius5.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 190, -1));

        lblMinimumRadius5.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 190, -1));

        lblSegmant6.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant6.setOpaque(true);
        jPanel1.add(lblSegmant6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 190, 140));

        lblCountCells6.setText("Cells Count            : ");
        jPanel1.add(lblCountCells6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 190, -1));

        lblMaximumRadius6.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 190, -1));

        lblMinimumRadius6.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 190, -1));

        lblCountCells7.setText("Cells Count            : ");
        jPanel1.add(lblCountCells7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 190, -1));

        lblMaximumRadius7.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 190, -1));

        lblMinimumRadius7.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 190, -1));

        lblSegmant7.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant7.setOpaque(true);
        jPanel1.add(lblSegmant7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, 190, 140));

        lblCountCells8.setText("Cells Count            : ");
        jPanel1.add(lblCountCells8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 380, 190, -1));

        lblMaximumRadius8.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 400, 190, -1));

        lblMinimumRadius8.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 420, 190, -1));

        lblCountCells9.setText("Cells Count            : ");
        jPanel1.add(lblCountCells9, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 380, 190, -1));

        lblMaximumRadius9.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius9, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 400, 190, -1));

        lblMinimumRadius9.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius9, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 420, 190, -1));

        lblSegmant10.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant10.setOpaque(true);
        jPanel1.add(lblSegmant10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 240, 190, 140));

        lblCountCells10.setText("Cells Count            : ");
        jPanel1.add(lblCountCells10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 380, 190, -1));

        lblMaximumRadius10.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 400, 190, -1));

        lblMinimumRadius10.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 420, 190, -1));

        lblSegmant11.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant11.setOpaque(true);
        jPanel1.add(lblSegmant11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 190, 140));

        lblCountCells11.setText("Cells Count            : ");
        jPanel1.add(lblCountCells11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, 190, -1));

        lblMaximumRadius11.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, 190, -1));

        lblMinimumRadius11.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 190, -1));

        lblSegmant12.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant12.setOpaque(true);
        jPanel1.add(lblSegmant12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 470, 190, 140));

        lblSegmant9.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant9.setOpaque(true);
        jPanel1.add(lblSegmant9, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 190, 140));

        lblSegmant8.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant8.setOpaque(true);
        jPanel1.add(lblSegmant8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 190, 140));

        lblCountCells12.setText("Cells Count            : ");
        jPanel1.add(lblCountCells12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 610, 190, -1));

        lblMaximumRadius12.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 630, 190, -1));

        lblMinimumRadius12.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 650, 190, -1));

        lblMinimumRadius13.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 650, 190, -1));

        lblMaximumRadius13.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 630, 190, -1));

        lblCountCells13.setText("Cells Count            : ");
        jPanel1.add(lblCountCells13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 610, 190, -1));

        lblSegmant13.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant13.setOpaque(true);
        jPanel1.add(lblSegmant13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 470, 190, 140));

        lblMinimumRadius14.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius14, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 650, 190, -1));

        lblMaximumRadius14.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius14, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 630, 190, -1));

        lblSegmant14.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant14.setOpaque(true);
        jPanel1.add(lblSegmant14, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 470, 190, 140));

        lblCountCells14.setText("Cells Count            : ");
        jPanel1.add(lblCountCells14, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 610, 190, -1));

        lblMaximumRadius15.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius15, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 630, 190, -1));

        lblMinimumRadius15.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius15, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 650, 190, -1));

        lblCountCells15.setText("Cells Count            : ");
        jPanel1.add(lblCountCells15, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 610, 190, -1));

        lblSegmant15.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant15.setOpaque(true);
        jPanel1.add(lblSegmant15, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 470, 190, 140));

        lblSegmant16.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant16.setOpaque(true);
        jPanel1.add(lblSegmant16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 470, 190, 140));

        lblCountCells16.setText("Cells Count            : ");
        jPanel1.add(lblCountCells16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 610, 190, -1));

        lblMaximumRadius16.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 630, 190, -1));

        lblMinimumRadius16.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 650, 190, -1));

        btnClse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/exit.png"))); // NOI18N
        btnClse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClseActionPerformed(evt);
            }
        });
        jPanel1.add(btnClse, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 10, -1, -1));

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClseActionPerformed
        dispose();
    }//GEN-LAST:event_btnClseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClse;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCountCells1;
    private javax.swing.JLabel lblCountCells10;
    private javax.swing.JLabel lblCountCells11;
    private javax.swing.JLabel lblCountCells12;
    private javax.swing.JLabel lblCountCells13;
    private javax.swing.JLabel lblCountCells14;
    private javax.swing.JLabel lblCountCells15;
    private javax.swing.JLabel lblCountCells16;
    private javax.swing.JLabel lblCountCells2;
    private javax.swing.JLabel lblCountCells3;
    private javax.swing.JLabel lblCountCells4;
    private javax.swing.JLabel lblCountCells5;
    private javax.swing.JLabel lblCountCells6;
    private javax.swing.JLabel lblCountCells7;
    private javax.swing.JLabel lblCountCells8;
    private javax.swing.JLabel lblCountCells9;
    private javax.swing.JLabel lblMaximumRadius1;
    private javax.swing.JLabel lblMaximumRadius10;
    private javax.swing.JLabel lblMaximumRadius11;
    private javax.swing.JLabel lblMaximumRadius12;
    private javax.swing.JLabel lblMaximumRadius13;
    private javax.swing.JLabel lblMaximumRadius14;
    private javax.swing.JLabel lblMaximumRadius15;
    private javax.swing.JLabel lblMaximumRadius16;
    private javax.swing.JLabel lblMaximumRadius2;
    private javax.swing.JLabel lblMaximumRadius3;
    private javax.swing.JLabel lblMaximumRadius4;
    private javax.swing.JLabel lblMaximumRadius5;
    private javax.swing.JLabel lblMaximumRadius6;
    private javax.swing.JLabel lblMaximumRadius7;
    private javax.swing.JLabel lblMaximumRadius8;
    private javax.swing.JLabel lblMaximumRadius9;
    private javax.swing.JLabel lblMinimumRadius1;
    private javax.swing.JLabel lblMinimumRadius10;
    private javax.swing.JLabel lblMinimumRadius11;
    private javax.swing.JLabel lblMinimumRadius12;
    private javax.swing.JLabel lblMinimumRadius13;
    private javax.swing.JLabel lblMinimumRadius14;
    private javax.swing.JLabel lblMinimumRadius15;
    private javax.swing.JLabel lblMinimumRadius16;
    private javax.swing.JLabel lblMinimumRadius2;
    private javax.swing.JLabel lblMinimumRadius3;
    private javax.swing.JLabel lblMinimumRadius4;
    private javax.swing.JLabel lblMinimumRadius5;
    private javax.swing.JLabel lblMinimumRadius6;
    private javax.swing.JLabel lblMinimumRadius7;
    private javax.swing.JLabel lblMinimumRadius8;
    private javax.swing.JLabel lblMinimumRadius9;
    private javax.swing.JLabel lblSegmant1;
    private javax.swing.JLabel lblSegmant10;
    private javax.swing.JLabel lblSegmant11;
    private javax.swing.JLabel lblSegmant12;
    private javax.swing.JLabel lblSegmant13;
    private javax.swing.JLabel lblSegmant14;
    private javax.swing.JLabel lblSegmant15;
    private javax.swing.JLabel lblSegmant16;
    private javax.swing.JLabel lblSegmant2;
    private javax.swing.JLabel lblSegmant3;
    private javax.swing.JLabel lblSegmant4;
    private javax.swing.JLabel lblSegmant5;
    private javax.swing.JLabel lblSegmant6;
    private javax.swing.JLabel lblSegmant7;
    private javax.swing.JLabel lblSegmant8;
    private javax.swing.JLabel lblSegmant9;
    // End of variables declaration//GEN-END:variables
}
