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
public class Segments9Viewer extends javax.swing.JDialog {

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
    private static final int FRAME_WIDTH = 1200, FRAME_HEIGHT = 660;

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
    public Segments9Viewer(java.awt.Frame parent, boolean modal, final List<String> files) {
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
        //loadFiles();
        threadScheduler();
        this.setIconImage(ResizeImage.resizeImage("/resources/icon.jpg").getImage());
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
                JOptionPane.showMessageDialog(null, " error is " + ex.getMessage());
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
        lblMinimumRadius2 = new javax.swing.JLabel();
        lblMaximumRadius2 = new javax.swing.JLabel();
        lblCountCells2 = new javax.swing.JLabel();
        lblSegmant2 = new javax.swing.JLabel();
        lblMinimumRadius3 = new javax.swing.JLabel();
        lblMaximumRadius3 = new javax.swing.JLabel();
        lblCountCells3 = new javax.swing.JLabel();
        lblSegmant3 = new javax.swing.JLabel();
        lblSegmant4 = new javax.swing.JLabel();
        lblCountCells4 = new javax.swing.JLabel();
        lblMaximumRadius4 = new javax.swing.JLabel();
        lblMinimumRadius4 = new javax.swing.JLabel();
        lblMinimumRadius5 = new javax.swing.JLabel();
        lblMaximumRadius5 = new javax.swing.JLabel();
        lblCountCells5 = new javax.swing.JLabel();
        lblSegmant5 = new javax.swing.JLabel();
        lblMinimumRadius6 = new javax.swing.JLabel();
        lblMaximumRadius6 = new javax.swing.JLabel();
        lblCountCells6 = new javax.swing.JLabel();
        lblSegmant6 = new javax.swing.JLabel();
        lblSegmant7 = new javax.swing.JLabel();
        lblCountCells7 = new javax.swing.JLabel();
        lblMaximumRadius7 = new javax.swing.JLabel();
        lblMinimumRadius7 = new javax.swing.JLabel();
        lblMinimumRadius8 = new javax.swing.JLabel();
        lblMaximumRadius8 = new javax.swing.JLabel();
        lblCountCells8 = new javax.swing.JLabel();
        lblSegmant8 = new javax.swing.JLabel();
        lblMinimumRadius9 = new javax.swing.JLabel();
        lblMaximumRadius9 = new javax.swing.JLabel();
        lblCountCells9 = new javax.swing.JLabel();
        lblSegmant9 = new javax.swing.JLabel();
        btnClse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSegmant1.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant1.setOpaque(true);
        jPanel1.add(lblSegmant1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 170));

        lblCountCells1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells1.setText("Cells Count            : ");
        jPanel1.add(lblCountCells1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 130, -1));

        lblMaximumRadius1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius1.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 130, -1));

        lblMinimumRadius1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius1.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 130, -1));

        lblMinimumRadius2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius2.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 130, -1));

        lblMaximumRadius2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius2.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 130, -1));

        lblCountCells2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells2.setText("Cells Count            : ");
        jPanel1.add(lblCountCells2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 130, -1));

        lblSegmant2.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant2.setOpaque(true);
        jPanel1.add(lblSegmant2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 250, 170));

        lblMinimumRadius3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius3.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 50, 130, -1));

        lblMaximumRadius3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius3.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 30, 130, -1));

        lblCountCells3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells3.setText("Cells Count            : ");
        jPanel1.add(lblCountCells3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 10, 130, -1));

        lblSegmant3.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant3.setOpaque(true);
        jPanel1.add(lblSegmant3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 250, 170));

        lblSegmant4.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant4.setOpaque(true);
        jPanel1.add(lblSegmant4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 250, 170));

        lblCountCells4.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells4.setText("Cells Count            : ");
        jPanel1.add(lblCountCells4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 130, -1));

        lblMaximumRadius4.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius4.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 130, -1));

        lblMinimumRadius4.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius4.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 130, -1));

        lblMinimumRadius5.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius5.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 130, -1));

        lblMaximumRadius5.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius5.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, 130, -1));

        lblCountCells5.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells5.setText("Cells Count            : ");
        jPanel1.add(lblCountCells5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 130, -1));

        lblSegmant5.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant5.setOpaque(true);
        jPanel1.add(lblSegmant5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 250, 170));

        lblMinimumRadius6.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius6.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 250, 130, -1));

        lblMaximumRadius6.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius6.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 230, 130, -1));

        lblCountCells6.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells6.setText("Cells Count            : ");
        jPanel1.add(lblCountCells6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 210, 130, -1));

        lblSegmant6.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant6.setOpaque(true);
        jPanel1.add(lblSegmant6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 210, 250, 170));

        lblSegmant7.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant7.setOpaque(true);
        jPanel1.add(lblSegmant7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 250, 170));

        lblCountCells7.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells7.setText("Cells Count            : ");
        jPanel1.add(lblCountCells7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, 130, -1));

        lblMaximumRadius7.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius7.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 430, 130, -1));

        lblMinimumRadius7.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius7.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 450, 130, -1));

        lblMinimumRadius8.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius8.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 450, 130, -1));

        lblMaximumRadius8.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius8.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 430, 130, -1));

        lblCountCells8.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells8.setText("Cells Count            : ");
        jPanel1.add(lblCountCells8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 410, 130, -1));

        lblSegmant8.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant8.setOpaque(true);
        jPanel1.add(lblSegmant8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 410, 250, 170));

        lblMinimumRadius9.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMinimumRadius9.setText("Minimum Radius     : ");
        jPanel1.add(lblMinimumRadius9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 450, 130, -1));

        lblMaximumRadius9.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblMaximumRadius9.setText("Maximum Radius    : ");
        jPanel1.add(lblMaximumRadius9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 430, 130, -1));

        lblCountCells9.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCountCells9.setText("Cells Count            : ");
        jPanel1.add(lblCountCells9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 410, 130, -1));

        lblSegmant9.setBackground(java.awt.SystemColor.controlShadow);
        lblSegmant9.setOpaque(true);
        jPanel1.add(lblSegmant9, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 410, 250, 170));

        btnClse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/exit.png"))); // NOI18N
        btnClse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClseActionPerformed(evt);
            }
        });
        jPanel1.add(btnClse, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 140, -1, -1));

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
