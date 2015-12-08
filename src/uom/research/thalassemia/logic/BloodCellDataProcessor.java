/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.imgproc.Imgproc;
import uom.research.thalassemia.util.FillData;

/**
 *
 * @author hansa
 */
public class BloodCellDataProcessor {

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
     * rotatedRect Blood Cells set.
     */
    private final List<MatOfPoint> contours;

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
     * keeps total blood cell area.
     */
    private double totalBloodCellArea = 0.0;

    /**
     * keeps total pallar area.
     */
    private double totalPallarArea = 0.0;

    /**
     * ellipse total Ellipse cell area.
     */
    private double totalEllipseArea = 0.0;

    /**
     * Creates new form CircleData.
     *
     * @param circleSet circularBloodCells
     * @param ellipseSet ellipticalBloodCells
     * @param pallorSet pallorBloodCells
     */
    public BloodCellDataProcessor(final Mat circleSet, final Mat ellipseSet, final Mat pallorSet) {
        this.circularBloodCells = circleSet;
        this.ellipticalBloodCells = ellipseSet;
        this.pallorBloodCells = pallorSet;
        this.contours = null;
    }

    /**
     * fill table.
     *
     * @param jTable1 jTable1
     * @param currentSelection currentSelection
     */
    public void fillTable(final JTable jTable1, final Mat currentSelection) {
        FillData.doEmptyTable(jTable1);

        if (currentSelection != null) {
            getMinMaxDiameter(currentSelection);
            double sgf = 0;
            if (minDiameter != 0) {

                //calculate shape geometric factor
                sgf = maxDiameter / minDiameter;
            }
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

                //get area value
                area = calculateArea(r);

                if (currentSelection == circularBloodCells) {
                    totalBloodCellArea += area;
                } else if (currentSelection == pallorBloodCells) {
                    totalPallarArea += area;
                } else {
                    totalEllipseArea += area;
                }
                //get perimeter value
                perimeter = calculatePerimeter(r);
                //get diameter value
                diameter = calculateDiameter(area, perimeter);
                // calculate deviational value
                deviationValue = sgf / area;

                Point point = new Point(x, y);
                if (points.containsKey(point)) {
                    areaPreparation = calculateArea(points.get(point)) / area;
                }
                Object[] ob = {index, x, y, r, area, perimeter, diameter,
                    deviationValue, areaPreparation};
                dtm.addRow(ob);
            }
        }
    }

    public void fillTable(final JTable jTable1, final List<MatOfPoint> pcontours) {
        FillData.doEmptyTable(jTable1);

        if (pcontours != null) {
            getMinMaxDiameter(pcontours);
            double sgf = 0;
            if (minDiameter != 0) {
                //calculate shape geometric factor
                sgf = maxDiameter / minDiameter;
            }
            Map<Point, Double> points = getPallorBloodCellsPointList();
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            double[] circles;
            int index = 0;
            double x, y, r, area, perimeter, diameter, deviationValue,
                    areaPreparation, height, width;

            MatOfPoint allcontours = new MatOfPoint();
            for (MatOfPoint mat : pcontours) {
                mat.copyTo(allcontours);
                RotatedRect boundingEllipse = null;
                if (allcontours.toArray().length > 4) {
                    MatOfPoint2f newMat2 = new MatOfPoint2f(
                            allcontours.toArray());
                    boundingEllipse = Imgproc.fitEllipse(newMat2);
                    x = boundingEllipse.center.x;
                    y = boundingEllipse.center.y;
                    width = boundingEllipse.size.width;
                    height = boundingEllipse.size.height;
                    r = width;
                    area = calculateArea(width, height);
                    perimeter = calculatePerimeter(width, height);
                    totalEllipseArea += area;

                    Object[] ob = {index, x, y, r, area, perimeter, 0,
                        0, 0};
                    dtm.addRow(ob);

                }

            }
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
     * Calculate Area of a Ellipse.
     *
     * @param width double width value
     * @param height double height value
     * @return area as double
     */
    private double calculateArea(final double width, final double height) {
        return FI * (height / 2) * (width / 2);
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
     * Calculate Perimeter of a Ellipse.
     *
     * @param width double perimeter value
     * @param height double perimeter value
     * @return perimeter as double
     */
    private double calculatePerimeter(final double width, final double height) {
        return 0;//Math.
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
     * Calculate Area Proporation of Circle
     *
     * @param area double area value
     * @param cpArea double centralPallorArea value
     * @return areaProporation as double
     */
    private double calculateAreaProporation(final double area,
            final double cpArea) {
        double ap;
        return ap = cpArea / area;
    }

    /**
     * get min and max diameters.
     *
     * @param pcontours contours
     */
    private void getMinMaxDiameter(final List<MatOfPoint> pcontours) {
        MatOfPoint allcontours = new MatOfPoint();
        double diameter;
        List<Double> diameters = new ArrayList<>();
        for (MatOfPoint mat : pcontours) {
            mat.copyTo(allcontours);
            RotatedRect boundingEllipse = null;
            if (allcontours.toArray().length > 4) {
                MatOfPoint2f newMat2 = new MatOfPoint2f(
                        allcontours.toArray());
                boundingEllipse = Imgproc.fitEllipse(newMat2);
                double x = boundingEllipse.center.x;
                double y = boundingEllipse.center.y;
                double width = boundingEllipse.size.width;
                double height = boundingEllipse.size.height;
                diameters.add(width);

            }
        }
        if (diameters.size() > 1) {
            diameters.sort(null);
        }
        minDiameter = diameters.get(0);
        maxDiameter = diameters.get(diameters.size() - 1);
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
     * keeps total blood cell area.
     * @return the totalBloodCellArea
     */
    public double getTotalBloodCellArea() {
        return totalBloodCellArea;
    }

    /**
     * keeps total pallar area.
     * @return the totalPallarArea
     */
    public double getTotalPallarArea() {
        return totalPallarArea;
    }

    /**
     * ellipse total Ellipse cell area.
     * @return the totalEllipseArea
     */
    public double getTotalEllipseArea() {
        return totalEllipseArea;
    }

}
