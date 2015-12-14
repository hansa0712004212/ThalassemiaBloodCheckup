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
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author nadeera
 */
public class BloodCellData {

    /**
     * Frame height.
     */
    private static final double FI = 22.0 / 7.0;

    /**
     * FOUR (4) as constant.
     */
    private static final int FOUR = 4;

    /**
     * min diameter.
     */
    private double minDiameter;

    /**
     * max diameter.
     */
    private double maxDiameter;

    /**
     * pallor Blood Cells set.
     */
    private final Mat pallorBloodCells;

    /**
     * circular Blood Cells set.
     */
    private final Mat circularBloodCells;

    /**
     * keeps list of blood related classes.
     */
    private final Map<String, Integer> classes;

    public Map<String, Integer> getClasses() {
        return classes;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getDeviationValue() {
        return deviationValue;
    }

    public void setDeviationValue(double deviationValue) {
        this.deviationValue = deviationValue;
    }

    public double getAreaPreparation() {
        return areaPreparation;
    }

    public void setAreaPreparation(double areaPreparation) {
        this.areaPreparation = areaPreparation;
    }

    public double getSGF() {
        return sgf;
    }

    public void setSGF(double psgf) {
        this.sgf = psgf;
    }

    private double x, y, r, area, perimeter, diameter, deviationValue,
            areaPreparation, sgf;
    /**
     *
     */
    private final boolean tf;

    public BloodCellData(final Mat circleSet, final Mat ellipseSet,
            final Mat pallorSet) {
        pallorBloodCells = pallorSet;
        circularBloodCells = circleSet;
        tf = true;
        classes = new HashMap<>();
        process(circleSet);
    }

    private void process(final Mat currentSelection) {
        sgf = 0;
        getMinMaxDiameter(currentSelection);
        if (minDiameter != 0) {
            //calculate shape geometric factor
            sgf = maxDiameter / minDiameter;
        }
        double[] circles;

        for (int a = 0; a < currentSelection.cols(); a++) {

            Map<Point, Double> points = getPallorBloodCellsPointList();
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
            getAbnormalCellTypes();
        }
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
        return 0; //Math.
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
     * @param parea double area value
     * @param cpArea double centralPallorArea value
     * @return areaProporation as double
     */
    private double calculateAreaProporation(final double parea,
            final double cpArea) {
        return cpArea / parea;
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
                double xx = boundingEllipse.center.x;
                //ellipse centre y cordination
                double yy = boundingEllipse.center.y;
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
     * get Pallor Blood cells point list.
     *
     * @return List<Point> List
     */
    private Map<Point, Double> getPallorBloodCellsPointList() {
        double[] circles;
        Map<Point, Double> points = new HashMap<>();
        double xx, yy, rr;
        for (int a = 0; a < pallorBloodCells.cols(); a++) {
            circles = pallorBloodCells.get(0, a);
            xx = circles[0];
            yy = circles[1];
            rr = Math.round(circles[2]);
            points.put(new Point(xx, yy), rr);
        }
        return points;
    }

    /**
     * identify abnormal cell types.
     *
     */
    private void getAbnormalCellTypes() {
        //System.out.println("sgf " + sgf + " | deviationVal " + deviationValue + " | diameter " + diameter + " | area " + area + " | AP " + areaPreparation);
        if (sgf > 1.2) {
            //sgf true
            if (deviationValue > 0.2) {
                //dv true
                fillClasses("Class 12");
            } else {
                //dv false
                fillClasses("Class 5");
            }
        } else {
            System.out.println();
            if ((6.5 < diameter) && (8.5 > diameter)) {
                if (area > 0) {
                    if (tf) {
                        fillClasses("Class 6");
                    } else if (areaPreparation > 0.2) {
                        fillClasses("Class 9");
                    } else {
                        fillClasses("Class 1");
                    }
                } else {
                    fillClasses("Class 2");
                }
            } else {
                System.out.println();
                if (deviationValue > 8.5) {
                    if (area > 0) {
                        //cpArea true
                        if (areaPreparation > 0.2) {
                            //ap true
                            fillClasses("Class 8");
                        } else {
                            //ap false
                            fillClasses("Class 9");
                        }
                    } else {
                        fillClasses("Class 3");
                    }
                } else {
                    System.out.println();
                    if (area > 0) {
                        //cpArea true
                        if (areaPreparation > 0.2) {
                            fillClasses("Class 7");
                        } else {
                            //cpArea false
                            fillClasses("Class 10");
                        }
                    } else {
                        //cpArea false
                        fillClasses("Class 4");
                    }
                }
            }
        }
    }

    /**
     * get cell type count.
     *
     * @param key key
     */
    private void fillClasses(final String key) {
        if (classes.containsKey(key)) {
            int value = classes.get(key);
            classes.put(key, ++value);
        } else {
            classes.put(key, 1);
        }
    }

}
