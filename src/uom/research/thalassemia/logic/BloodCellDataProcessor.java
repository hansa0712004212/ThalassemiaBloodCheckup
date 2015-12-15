/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.logic;

import java.text.DecimalFormat;
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
import uom.research.thalassemia.util.Validator;

/**
 *
 * @author anupama
 */
public final class BloodCellDataProcessor {

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
    private double totalBloodCellArea = 1.0;

    /**
     * keeps total pallor area.
     */
    private double totalPallarArea = 0.0;

    /**
     * ellipse total Ellipse cell area.
     */
    private double totalEllipseArea = 0.0;

    /**
     * keeps all red blood cell data in array.
     */
    private List<Object[]> circularBloodCellsArray;

    /**
     * keeps all pallor cell data in array.
     */
    private List<Object[]> pallorBloodCellsArray;

    /**
     * keeps all ellipse cell data in array.
     */
    private List<Object[]> ellipseBloodCellsArray;

    /**
     * Creates new form CircleData.
     *
     * @param circleSet circularBloodCells
     * @param ellipseSet ellipticalBloodCells
     * @param pallorSet pallorBloodCells
     */
    public BloodCellDataProcessor(final Mat circleSet, final Mat ellipseSet,
            final Mat pallorSet) {
        this.circularBloodCells = circleSet;
        this.ellipticalBloodCells = ellipseSet;
        this.pallorBloodCells = pallorSet;
        this.contours = null;
    }

    /**
     * Creates new form CircleData.
     *
     * @param circleSet circularBloodCells
     * @param ellipseSet contours
     * @param pallorSet pallorBloodCells
     */
    public BloodCellDataProcessor(final Mat circleSet,
            final List<MatOfPoint> ellipseSet, final Mat pallorSet) {
        this.circularBloodCells = circleSet;
        this.contours = ellipseSet;
        this.pallorBloodCells = pallorSet;
        this.ellipticalBloodCells = null;
    }

    /**
     * process circular blood cell data including both pallor and red blood
     * cells.
     *
     * @param currentSelection Mat of selected
     */
    public void circularBloodCellsProcesser(final Mat currentSelection) {

        if (currentSelection != null) {
            if (currentSelection == circularBloodCells) {
                circularBloodCellsArray = new ArrayList<>();
            } else if (currentSelection == pallorBloodCells) {
                pallorBloodCellsArray = new ArrayList<>();
            }
            getMinMaxDiameter(currentSelection);
            double sgf = 0;
            if (minDiameter != 0) {
                //calculate shape geometric factor
                sgf = maxDiameter / minDiameter;
            }
            Map<Point, Double> points = getPallorBloodCellsPointList();
            double[] circles;
            double x, y, r, area, perimeter, diameter, deviationValue,
                    areaPreparation;
            for (int a = 0; a < currentSelection.cols(); a++) {
                areaPreparation = 0;
                circles = currentSelection.get(0, a);
                x = circles[0];
                y = circles[1];
                r = Math.round(circles[2]);

                //get area value
                area = calculateArea(r);
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
                Object[] ob = {(a + 1), x, y, r,
                    Validator.formatDouble(perimeter),
                    Validator.formatDouble(area),
                    Validator.formatDouble(diameter),
                    Validator.formatDouble(deviationValue),
                    Validator.formatDouble(areaPreparation)};

                if (currentSelection == circularBloodCells) {
                    totalBloodCellArea += area;
                    circularBloodCellsArray.add(ob);
                } else if (currentSelection == pallorBloodCells) {
                    totalPallarArea += area;
                    pallorBloodCellsArray.add(ob);
                } else {
                    totalEllipseArea += area;
                }
            }
        }
    }

    /**
     * process circular blood cell data including both pallor and red blood
     * cells.
     *
     */
    public void ellipseBloodCellsProcesser() {
        ellipseBloodCellsArray = new ArrayList<>();
        int index = 0;
        MatOfPoint allcontours = new MatOfPoint();
        for (MatOfPoint mat : contours) {
            mat.copyTo(allcontours);
            RotatedRect boundingEllipse;
            if (allcontours.toArray().length > FOUR) {
                MatOfPoint2f newMat2 = new MatOfPoint2f(
                        allcontours.toArray());
                boundingEllipse = Imgproc.fitEllipse(newMat2);
                //ellipse centre x cordination
                double xx, yy, rr, width, height, area, perimeter, diameter,
                        deviationValue, areaPreparation, sgf;

                xx = boundingEllipse.center.x;
                //ellipse centre y cordination
                yy = boundingEllipse.center.y;
                //ellipse width
                width = boundingEllipse.size.width;
                //ellipse height
                height = boundingEllipse.size.height;
                // assume radius is width. width is the hightest length.
                rr = width;
                sgf = width / height;
                //get area value
                area = calculateArea(width, height);
                //get perimeter value
                perimeter = calculatePerimeter(width, height);
                //get diameter value
                diameter = calculateDiameter(area, perimeter);
                // calculate deviational value
                if (rr > 0) {
                    deviationValue = sgf / area;

                    Map<Point, Double> points = getPallorBloodCellsPointList();
                    areaPreparation = 0;

                    Point point = new Point(xx, yy);
                    if (points.containsKey(point)) {
                        areaPreparation = calculateArea(points.get(point))
                                / area;
                    }

                    Object[] ob = {(++index), xx, yy, rr,
                        Validator.formatDouble(perimeter),
                        Validator.formatDouble(area),
                        Validator.formatDouble(diameter),
                        Validator.formatDouble(deviationValue),
                        Validator.formatDouble(areaPreparation)};

                    totalEllipseArea += area;
                    ellipseBloodCellsArray.add(ob);
                }
            }
        }
    }

    /**
     * fill table.
     *
     * @param jTable1 jTable1
     * @param currentSelection currentSelection
     */
    public void fillTable(final JTable jTable1, final Mat currentSelection) {
        FillData.doEmptyTable(jTable1);
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        if (currentSelection == circularBloodCells) {
            circularBloodCellsArray.stream().forEach((circularBloodCell) -> {
                dtm.addRow(circularBloodCell);
            });
        } else if (currentSelection == pallorBloodCells) {
            pallorBloodCellsArray.stream().forEach((circularBloodCell) -> {
                dtm.addRow(circularBloodCell);
            });
        }
    }

    /**
     * fill Table.
     *
     * @param jTable1 jTable1
     * @param pcontours contours
     */
    public void fillTable(final JTable jTable1,
            final List<MatOfPoint> pcontours) {
        FillData.doEmptyTable(jTable1);
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        ellipseBloodCellsArray.stream().forEach((ellipseBloodCell) -> {
            dtm.addRow(ellipseBloodCell);
        });
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
        return FI * ((3 * (width + height)) - Math.sqrt(((3 * width)
                + height) + (width + (3 * height))));
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
     * Calculate Area Proportion of Circle.
     *
     * @param area double area value
     * @param cpArea double centralPallorArea value
     * @return areaProporation as double
     */
    private double calculateAreaProporation(final double area,
            final double cpArea) {
        return cpArea / area;
    }

    /**
     * get min and max diameters.
     *
     * @param pcontours contours
     */
    private void getMinMaxDiameter(final List<MatOfPoint> pcontours) {
        MatOfPoint allcontours = new MatOfPoint();
        List<Double> diameters = new ArrayList<>();
        for (MatOfPoint mat : pcontours) {
            mat.copyTo(allcontours);
            RotatedRect boundingEllipse;
            if (allcontours.toArray().length > 4) {
                MatOfPoint2f newMat2 = new MatOfPoint2f(
                        allcontours.toArray());
                boundingEllipse = Imgproc.fitEllipse(newMat2);
                //ellipse centre x cordination
                double x = boundingEllipse.center.x;
                //ellipse centre y cordination
                double y = boundingEllipse.center.y;
                //ellipse width
                double width = boundingEllipse.size.width;
                //ellipse height
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
     *
     * @return the totalBloodCellArea
     */
    public double getTotalBloodCellArea() {
        return totalBloodCellArea;
    }

    /**
     * keeps total pallor area.
     *
     * @return the totalPallarArea
     */
    public double getTotalPallarArea() {
        return totalPallarArea;
    }

    /**
     * ellipse total Ellipse cell area.
     *
     * @return the totalEllipseArea
     */
    public double getTotalEllipseArea() {
        return totalEllipseArea;
    }
}
