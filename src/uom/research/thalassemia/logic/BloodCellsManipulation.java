/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.logic;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

/**
 *
 * @author anupama
 */
public interface BloodCellsManipulation {

    /**
     * use to add radius value to array list.
     *
     * @param radius radius of a circle
     */
    void addRadius(final int radius);

    /**
     * converts map to image.
     *
     * @param mat mat
     * @return bufferedImage
     * @throws IOException IOException
     */
    BufferedImage convertMapToImage(final Mat mat) throws IOException;

    /**
     * do Blood Cell Processing.
     *
     * @throws Exception Exception
     */
    void doBloodCellProcessing() throws Exception;

    /**
     * get canny edge detected image.
     *
     * @return BufferedImage
     */
    BufferedImage getCannyImage();

    /**
     * @return the circleCount
     */
    int getCircleCount();

    /**
     * get circles mat.
     *
     * @return mat
     */
    Mat getCircles();

    /**
     * get gray image.
     *
     * @return BufferedImage
     */
    BufferedImage getGrayImage();

    /**
     * use to extract maximum radius from the array list.
     *
     * @return maximum radius
     */
    int getMaximumRadius();

    /**
     * use to extract minimum radius from the array list.
     *
     * @return minimum radius
     */
    int getMinimumRadius();

    /**
     * get red image.
     *
     * @return BufferedImage
     */
    BufferedImage getRedImage();

    /**
     * get threshold image.
     *
     * @return BufferedImage
     */
    BufferedImage getThresholdImage();

    /**
     * get lalacian image.
     *
     * @return BufferedImage
     */
    BufferedImage getLaplacianImage();

    /**
     * get ellipses mat.
     *
     * @return mat
     */
    Mat getEllipses();

    /**
     * get pallors mat.
     *
     * @return mat
     */
    Mat getPallors();

    /**
     * get Circulars Image.
     *
     * @return BufferedImage
     */
    BufferedImage getCircularsImage();

    /**
     * get Ellipses image.
     *
     * @return BufferedImage
     */
    BufferedImage getEllipsesImage();

    /**
     * keep ellipses details.
     *
     * @return the getContours
     */
    List<MatOfPoint> getContours();

}
