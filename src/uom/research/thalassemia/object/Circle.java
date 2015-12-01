/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.object;

/**
 *
 * @author hansa
 */
public final class Circle {

    /**
     * Circle Center x Point.
     */
    private int xPoint;

    /**
     * Circle Center y Point.
     */
    private int yPoint;

    /**
     * Circle Radius.
     */
    private int radius;

    /**
     * Circle Perimeter.
     */
    private double perimeter;

    /**
     * Circle Area.
     */
    private double area;

    /**
     * Default constructor.
     */
    public Circle() {
        setxPoint(0);
        setyPoint(0);
        setRadius(0);
        setPerimeter(0);
        setArea(0);
    }

    /**
     * parameterized constructor.
     *
     * @param pxPoint xPoint
     * @param pyPoint yPoint
     * @param pradius radius
     * @param pperimeter perimeter
     * @param parea area
     */
    public Circle(final int pxPoint, final int pyPoint, final int pradius,
            final double pperimeter, final double parea) {
        setxPoint(pxPoint);
        setyPoint(pyPoint);
        setRadius(pradius);
        setPerimeter(pperimeter);
        setArea(parea);
    }

    /**
     * Circle Center x Point.
     *
     * @return the xPoint
     */
    public int getxPoint() {
        return xPoint;
    }

    /**
     * Circle Center x Point.
     *
     * @param pxPoint the xPoint to set
     */
    public void setxPoint(final int pxPoint) {
        this.xPoint = pxPoint;
    }

    /**
     * Circle Center y Point.
     *
     * @return the yPoint
     */
    public int getyPoint() {
        return yPoint;
    }

    /**
     * Circle Center y Point.
     *
     * @param pyPoint the yPoint to set
     */
    public void setyPoint(final int pyPoint) {
        this.yPoint = pyPoint;
    }

    /**
     * Circle Radius.
     *
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Circle Radius.
     *
     * @param pradius the radius to set
     */
    public void setRadius(final int pradius) {
        this.radius = pradius;
    }

    /**
     * Circle Perimeter.
     *
     * @return the perimeter
     */
    public double getPerimeter() {
        return perimeter;
    }

    /**
     * Circle Perimeter.
     *
     * @param pperimeter the perimeter to set
     */
    public void setPerimeter(final double pperimeter) {
        this.perimeter = pperimeter;
    }

    /**
     * Circle Area.
     *
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * Circle Area.
     *
     * @param parea the area to set
     */
    public void setArea(final double parea) {
        this.area = parea;
    }
}
