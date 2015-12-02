/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.ui;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import uom.research.thalassemia.util.FillData;
import uom.research.thalassemia.util.Message;

/**
 *
 * @author anupama
 */
public final class CircleData extends javax.swing.JDialog {

    /**
     * Frame width.
     */
    private static final int FRAME_WIDTH = 800;

    /**
     * Frame height.
     */
    private static final int FRAME_HEIGHT = 500;

    /**
     * Frame height.
     */
    private static final double FI = 22.0 / 7.0;

    /**
     * FOUR (4) as constant.
     */
    private static final int FOUR = 4;

    /**
     * circular Blood Cells set.
     */
    private final Mat circularBloodCells;

    /**
     * elliptical Blood Cells set.
     */
    private final Mat ellipticalBloodCells;

    /**
     * pallor Blood Cells set.
     */
    private final Mat pallorBloodCells;

    /**
     * min diameter.
     */
    private double minDiameter;

    /**
     * max diameter.
     */
    private double maxDiameter;

    /**
     * Creates new form CircleData.
     *
     * @param parent parent frame
     * @param modal is modal or not
     * @param circleSet circularBloodCells
     * @param ellipseSet ellipticalBloodCells
     * @param pallorSet pallorBloodCells
     */
    public CircleData(final Frame parent, final boolean modal,
            final Mat circleSet, final Mat ellipseSet, final Mat pallorSet) {
        initComponents();
        this.circularBloodCells = circleSet;
        this.ellipticalBloodCells = ellipseSet;
        this.pallorBloodCells = pallorSet;
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        fillTable(circularBloodCells);
    }

    /**
     * fill table.
     *
     * @param currentSelection currentSelection
     */
    private void fillTable(final Mat currentSelection) {
        FillData.doEmptyTable(jTable1);
        if (currentSelection != null) {
            getMinMaxDiameter(currentSelection);
            double sgf = 0;
            if (minDiameter != 0) {
                sgf = maxDiameter / minDiameter;
            }
            lblSGFValue.setText(lblSGFValue.getText()
                    .concat(String.valueOf(sgf)));
            Map<Point, Double> points = getPallorBloodCellsPointList();
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            double[] circles;
            int index = 0;
            double x, y, r, area, perimeter, diameter, deviationValue,
                    areaPreparation;
            for (int a = 0; a < currentSelection.cols(); a++) {
                areaPreparation = 0;
                index = a + 1;
                circles = currentSelection.get(0, a);
                x = circles[0];
                y = circles[1];
                r = Math.round(circles[2]);
                area = calculateArea(r);
                perimeter = calculatePerimeter(r);
                diameter = calculateDiameter(area, perimeter);
                deviationValue = sgf / area;
                Point point = new Point(x, y);
                if (points.containsKey(point)) {
                    areaPreparation = calculateArea(points.get(point)) / area;
                }
                Object[] ob = {index, x, y, r, area, perimeter, diameter,
                    deviationValue, areaPreparation};
                dtm.addRow(ob);
            }
            lblSumBloodCells.setText(lblSumBloodCells.getText()
                    .concat(String.valueOf(circularBloodCells.cols())));
            lblSumPallar.setText(lblSumPallar.getText()
                    .concat(String.valueOf(pallorBloodCells.cols())));
        }
    }

    /**
     * Calculate Area of a Circle.
     *
     * @param radius double radius value
     * @return area as double
     */
    private double calculateArea(final double radius) {
        return FI * radius * radius;
    }

    /**
     * Calculate Perimeter of a Circle.
     *
     * @param radius double perimeter value
     * @return perimeter as double
     */
    private double calculatePerimeter(final double radius) {
        return 2 * FI * radius;
    }

    /**
     * Calculate Diameter of a Circle.
     *
     * @param area double area value
     * @param perimeter double perimeter value
     * @return diameter as double
     */
    private double calculateDiameter(final double area,
            final double perimeter) {
        return (FOUR * area) / perimeter;
    }

    /**
     * Calculate Diameter of a Circle.
     *
     * @param radius double diameter value
     * @return diameter as double
     */
    private double calculateDiameter(final double radius) {
        return FOUR * (FI * radius * radius) / (2 * FI * radius);
    }

    /**
     * get min and max diameters.
     *
     * @param currentSelection currentSelection of Mat
     */
    private void getMinMaxDiameter(final Mat currentSelection) {
        double[] circles;
        List<Double> diameters = new ArrayList<>();
        double r, diameter;
        for (int a = 0; a < currentSelection.cols(); a++) {
            circles = currentSelection.get(0, a);
            r = Math.round(circles[2]);
            diameter = calculateDiameter(r);
            diameters.add(diameter);
        }
        if (diameters.size() > 1) {
            diameters.sort(null);
        }
        minDiameter = diameters.get(0);
        maxDiameter = diameters.get(diameters.size() - 1);
    }

    /**
     * get Pallor Blood cells point list.
     *
     * @return List<Point> List
     */
    private Map<Point, Double> getPallorBloodCellsPointList() {
        double[] circles;
        Map<Point, Double> points = new HashMap<>();
        double x, y, r;
        for (int a = 0; a < pallorBloodCells.cols(); a++) {
            circles = pallorBloodCells.get(0, a);
            x = circles[0];
            y = circles[1];
            r = Math.round(circles[2]);
            points.put(new Point(x, y), r);
        }
        return points;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        circlesType = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        radCircularBloodCells = new javax.swing.JRadioButton();
        radPallorBloodCells = new javax.swing.JRadioButton();
        radEllipticalBloodCells = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        lblSumBloodCells = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        lblSumPallar = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblSGFValue = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Circle Data");
        getContentPane().setLayout(new java.awt.BorderLayout(0, 5));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 11)); // NOI18N
        jLabel3.setText("  Blood Cells Data");
        jPanel3.add(jLabel3, java.awt.BorderLayout.CENTER);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/exit.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, java.awt.BorderLayout.LINE_END);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        circlesType.add(radCircularBloodCells);
        radCircularBloodCells.setSelected(true);
        radCircularBloodCells.setText("  Red Blood Cell Circles  ");
        radCircularBloodCells.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCircularBloodCellsActionPerformed(evt);
            }
        });
        jPanel2.add(radCircularBloodCells);

        circlesType.add(radPallorBloodCells);
        radPallorBloodCells.setText("  Detected Pallar Circles  ");
        radPallorBloodCells.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radPallorBloodCellsActionPerformed(evt);
            }
        });
        jPanel2.add(radPallorBloodCells);

        circlesType.add(radEllipticalBloodCells);
        radEllipticalBloodCells.setText("  Ellipse Shaped Blood Cells  ");
        radEllipticalBloodCells.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radEllipticalBloodCellsActionPerformed(evt);
            }
        });
        jPanel2.add(radEllipticalBloodCells);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "x Point", "y Point", "r Radius", "Perimeter", "Area", "Diameter", "Deviation Value", "AP (beta)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jToolBar2.setRollover(true);

        lblSumBloodCells.setText("  Total No Of Blood Cells Detected : ");
        jToolBar2.add(lblSumBloodCells);
        jToolBar2.add(jSeparator1);

        lblSumPallar.setText("  Total No of Detected Pallar Circles : ");
        jToolBar2.add(lblSumPallar);

        jPanel4.add(jToolBar2, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.GridLayout(5, 2));

        jLabel4.setText("  π  =  22.0 / 7  ");
        jPanel5.add(jLabel4);

        jLabel6.setText("  deviation value  =  sgf / area");
        jPanel5.add(jLabel6);

        jLabel8.setText("  perimeter  =  2 . π . radius ");
        jPanel5.add(jLabel8);

        jLabel5.setText("  area propotion (ap)  =  central pallor area / cell area");
        jPanel5.add(jLabel5);

        jLabel9.setText("  area = π . radius ^ 2");
        jPanel5.add(jLabel9);

        jLabel10.setText("                                                  ");
        jPanel5.add(jLabel10);

        jLabel7.setText("  diameter  =  4 . area / perimeter");
        jPanel5.add(jLabel7);

        lblSGFValue.setText("  SGF Value = ");
        jPanel5.add(lblSGFValue);

        jLabel12.setText("  shape geometric factor (sgf) = large / small diameter");
        jPanel5.add(jLabel12);

        jLabel13.setText("                                                  ");
        jPanel5.add(jLabel13);

        jPanel4.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void radCircularBloodCellsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCircularBloodCellsActionPerformed
       fillTable(circularBloodCells);
    }//GEN-LAST:event_radCircularBloodCellsActionPerformed

    private void radPallorBloodCellsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radPallorBloodCellsActionPerformed
        fillTable(pallorBloodCells);
    }//GEN-LAST:event_radPallorBloodCellsActionPerformed

    private void radEllipticalBloodCellsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radEllipticalBloodCellsActionPerformed
        fillTable(ellipticalBloodCells);
    }//GEN-LAST:event_radEllipticalBloodCellsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup circlesType;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel lblSGFValue;
    private javax.swing.JLabel lblSumBloodCells;
    private javax.swing.JLabel lblSumPallar;
    private javax.swing.JRadioButton radCircularBloodCells;
    private javax.swing.JRadioButton radEllipticalBloodCells;
    private javax.swing.JRadioButton radPallorBloodCells;
    // End of variables declaration//GEN-END:variables
}
