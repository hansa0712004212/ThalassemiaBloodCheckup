/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import uom.research.thalassemia.logic.BloodCellsManipulation;
import uom.research.thalassemia.logic.BloodCellsManipulationImpl;
import uom.research.thalassemia.util.ResizeImage;
import uom.research.thalassemia.util.StretchImage;

/**
 *
 * @author anupama
 */
public class Segments9ViewerHD extends javax.swing.JDialog {

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
    private static final int FRAME_WIDTH = 800, FRAME_HEIGHT = 700;

    /**
     * Segment Id constants.
     */
    private static final int SEGMENT_0 = 0, SEGMENT_1 = 1, SEGMENT_2 = 2,
            SEGMENT_3 = 3, SEGMENT_4 = 4, SEGMENT_5 = 5, SEGMENT_6 = 6,
            SEGMENT_7 = 7, SEGMENT_8 = 8;

    /**
     * Creates new Segments16Viewer.
     *
     * @param parent parent frame
     * @param modal modal true or false
     * @param files segment files
     */
    public Segments9ViewerHD(final java.awt.Frame parent, final boolean modal,
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
        segmentFiles = files;
        loadFiles();
        threadScheduler();
        loadIcons();
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
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, " error is "
                        + ex.getMessage());
            }
        }).start();
    }

    /**
     * Load segment files of original image file.
     */
    private void loadFiles() {
        for (int i = 0; i < labelList.size(); i++) {
            StretchImage.setImageStretch(labelList.get(i), segmentFiles.get(i));
        }
    }

    /**
     * Identify Blood Cells.
     *
     * @param position segment position
     * @throws Exception any exception
     */
    private void doImageProcessing(final int position) throws Exception {

        BloodCellsManipulation bcm = new BloodCellsManipulationImpl(
                new File(segmentFiles.get(position)));
        bcm.doBloodCellProcessing();
        StretchImage.setImageStretch(labelList.get(position),
                new ImageIcon(bcm.getRedImage()));
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
            default:
        }
    }

    /**
     * set icons on tabs.
     */
    private void loadIcons() {
        /*this.setIconImage(ResizeImage.resizeImage("/uom/research/thalassemia/images/git.png").getImage());
        for (int i = 0; i < jTabbedPane1.getTabCount(); i++) {
            jTabbedPane1.setIconAt(i, ResizeImage.resizeImage("/uom/research/thalassemia/images/segment.png"));
        }*/

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        lblCountCells1 = new javax.swing.JLabel();
        lblMaximumRadius1 = new javax.swing.JLabel();
        lblMinimumRadius1 = new javax.swing.JLabel();
        lblSegmant1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        lblCountCells2 = new javax.swing.JLabel();
        lblMaximumRadius2 = new javax.swing.JLabel();
        lblMinimumRadius2 = new javax.swing.JLabel();
        lblSegmant2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lblCountCells3 = new javax.swing.JLabel();
        lblMaximumRadius3 = new javax.swing.JLabel();
        lblMinimumRadius3 = new javax.swing.JLabel();
        lblSegmant3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        lblCountCells4 = new javax.swing.JLabel();
        lblMaximumRadius4 = new javax.swing.JLabel();
        lblMinimumRadius4 = new javax.swing.JLabel();
        lblSegmant4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        lblCountCells5 = new javax.swing.JLabel();
        lblMaximumRadius5 = new javax.swing.JLabel();
        lblMinimumRadius5 = new javax.swing.JLabel();
        lblSegmant5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        lblCountCells6 = new javax.swing.JLabel();
        lblMaximumRadius6 = new javax.swing.JLabel();
        lblMinimumRadius6 = new javax.swing.JLabel();
        lblSegmant6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        lblCountCells7 = new javax.swing.JLabel();
        lblMaximumRadius7 = new javax.swing.JLabel();
        lblMinimumRadius7 = new javax.swing.JLabel();
        lblSegmant7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        lblCountCells8 = new javax.swing.JLabel();
        lblMaximumRadius8 = new javax.swing.JLabel();
        lblMinimumRadius8 = new javax.swing.JLabel();
        lblSegmant8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        lblCountCells9 = new javax.swing.JLabel();
        lblMaximumRadius9 = new javax.swing.JLabel();
        lblMinimumRadius9 = new javax.swing.JLabel();
        lblSegmant9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel10.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel10.setPreferredSize(new java.awt.Dimension(728, 100));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCountCells1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells1.setText("Cells Count            : ");
        jPanel10.add(lblCountCells1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, -1));

        lblMaximumRadius1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius1.setText("Maximum Radius    : ");
        jPanel10.add(lblMaximumRadius1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, -1));

        lblMinimumRadius1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius1.setText("Minimum Radius     : ");
        jPanel10.add(lblMinimumRadius1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, -1));

        jPanel1.add(jPanel10, java.awt.BorderLayout.PAGE_END);
        jPanel1.add(lblSegmant1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Segment 1", jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel11.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel11.setPreferredSize(new java.awt.Dimension(728, 100));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCountCells2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells2.setText("Cells Count            : ");
        jPanel11.add(lblCountCells2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, -1));

        lblMaximumRadius2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius2.setText("Maximum Radius    : ");
        jPanel11.add(lblMaximumRadius2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, -1));

        lblMinimumRadius2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius2.setText("Minimum Radius     : ");
        jPanel11.add(lblMinimumRadius2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, -1));

        jPanel2.add(jPanel11, java.awt.BorderLayout.PAGE_END);
        jPanel2.add(lblSegmant2, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Segment 2", jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel12.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel12.setPreferredSize(new java.awt.Dimension(728, 100));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCountCells3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells3.setText("Cells Count            : ");
        jPanel12.add(lblCountCells3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, -1));

        lblMaximumRadius3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius3.setText("Maximum Radius    : ");
        jPanel12.add(lblMaximumRadius3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, -1));

        lblMinimumRadius3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius3.setText("Minimum Radius     : ");
        jPanel12.add(lblMinimumRadius3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, -1));

        jPanel3.add(jPanel12, java.awt.BorderLayout.PAGE_END);
        jPanel3.add(lblSegmant3, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Segment 3", jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel13.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel13.setPreferredSize(new java.awt.Dimension(728, 100));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCountCells4.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells4.setText("Cells Count            : ");
        jPanel13.add(lblCountCells4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, -1));

        lblMaximumRadius4.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius4.setText("Maximum Radius    : ");
        jPanel13.add(lblMaximumRadius4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, -1));

        lblMinimumRadius4.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius4.setText("Minimum Radius     : ");
        jPanel13.add(lblMinimumRadius4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, -1));

        jPanel4.add(jPanel13, java.awt.BorderLayout.PAGE_END);
        jPanel4.add(lblSegmant4, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Segment 4", jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel14.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel14.setPreferredSize(new java.awt.Dimension(728, 100));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCountCells5.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells5.setText("Cells Count            : ");
        jPanel14.add(lblCountCells5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, -1));

        lblMaximumRadius5.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius5.setText("Maximum Radius    : ");
        jPanel14.add(lblMaximumRadius5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, -1));

        lblMinimumRadius5.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius5.setText("Minimum Radius     : ");
        jPanel14.add(lblMinimumRadius5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, -1));

        jPanel5.add(jPanel14, java.awt.BorderLayout.PAGE_END);
        jPanel5.add(lblSegmant5, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Segment 5", jPanel5);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel15.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel15.setPreferredSize(new java.awt.Dimension(728, 100));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCountCells6.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells6.setText("Cells Count            : ");
        jPanel15.add(lblCountCells6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, -1));

        lblMaximumRadius6.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius6.setText("Maximum Radius    : ");
        jPanel15.add(lblMaximumRadius6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, -1));

        lblMinimumRadius6.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius6.setText("Minimum Radius     : ");
        jPanel15.add(lblMinimumRadius6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, -1));

        jPanel6.add(jPanel15, java.awt.BorderLayout.PAGE_END);
        jPanel6.add(lblSegmant6, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Segment 6", jPanel6);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel16.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel16.setPreferredSize(new java.awt.Dimension(728, 100));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCountCells7.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells7.setText("Cells Count            : ");
        jPanel16.add(lblCountCells7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, -1));

        lblMaximumRadius7.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius7.setText("Maximum Radius    : ");
        jPanel16.add(lblMaximumRadius7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, -1));

        lblMinimumRadius7.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius7.setText("Minimum Radius     : ");
        jPanel16.add(lblMinimumRadius7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, -1));

        jPanel7.add(jPanel16, java.awt.BorderLayout.PAGE_END);
        jPanel7.add(lblSegmant7, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Segment 7", jPanel7);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel17.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel17.setPreferredSize(new java.awt.Dimension(728, 100));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCountCells8.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells8.setText("Cells Count            : ");
        jPanel17.add(lblCountCells8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, -1));

        lblMaximumRadius8.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius8.setText("Maximum Radius    : ");
        jPanel17.add(lblMaximumRadius8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, -1));

        lblMinimumRadius8.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius8.setText("Minimum Radius     : ");
        jPanel17.add(lblMinimumRadius8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, -1));

        jPanel8.add(jPanel17, java.awt.BorderLayout.PAGE_END);
        jPanel8.add(lblSegmant8, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Segment 8", jPanel8);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel18.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel18.setPreferredSize(new java.awt.Dimension(728, 100));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCountCells9.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells9.setText("Cells Count            : ");
        jPanel18.add(lblCountCells9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, -1));

        lblMaximumRadius9.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius9.setText("Maximum Radius    : ");
        jPanel18.add(lblMaximumRadius9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, -1));

        lblMinimumRadius9.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius9.setText("Minimum Radius     : ");
        jPanel18.add(lblMinimumRadius9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, -1));

        jPanel9.add(jPanel18, java.awt.BorderLayout.PAGE_END);
        jPanel9.add(lblSegmant9, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Segment 9", jPanel9);

        getContentPane().add(jTabbedPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCountCells1;
    private javax.swing.JLabel lblCountCells2;
    private javax.swing.JLabel lblCountCells3;
    private javax.swing.JLabel lblCountCells4;
    private javax.swing.JLabel lblCountCells5;
    private javax.swing.JLabel lblCountCells6;
    private javax.swing.JLabel lblCountCells7;
    private javax.swing.JLabel lblCountCells8;
    private javax.swing.JLabel lblCountCells9;
    private javax.swing.JLabel lblMaximumRadius1;
    private javax.swing.JLabel lblMaximumRadius2;
    private javax.swing.JLabel lblMaximumRadius3;
    private javax.swing.JLabel lblMaximumRadius4;
    private javax.swing.JLabel lblMaximumRadius5;
    private javax.swing.JLabel lblMaximumRadius6;
    private javax.swing.JLabel lblMaximumRadius7;
    private javax.swing.JLabel lblMaximumRadius8;
    private javax.swing.JLabel lblMaximumRadius9;
    private javax.swing.JLabel lblMinimumRadius1;
    private javax.swing.JLabel lblMinimumRadius2;
    private javax.swing.JLabel lblMinimumRadius3;
    private javax.swing.JLabel lblMinimumRadius4;
    private javax.swing.JLabel lblMinimumRadius5;
    private javax.swing.JLabel lblMinimumRadius6;
    private javax.swing.JLabel lblMinimumRadius7;
    private javax.swing.JLabel lblMinimumRadius8;
    private javax.swing.JLabel lblMinimumRadius9;
    private javax.swing.JLabel lblSegmant1;
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
