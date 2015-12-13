/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.logic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Core;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Scalar;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * This class is to manipulate blood data.
 *
 * @author anupama
 */
public final class BloodCellsManipulationImpl
        implements BloodCellsManipulation {

    /**
     * Use to keep radius values in a array list.
     */
    private static List<Integer> radiusList;

    /**
     * IplImages.
     */
    private Mat original, gray, smooth, canny, threshold,
            red, laplacian, circles, circlesPallor, circularsMat, ellipsesMat;

    /**
     * BufferedImage images.
     */
    private BufferedImage image, grayImage, redImage, cannyImage,
            thresholdImage, laplacianImage, circularsImage, ellipsesImage;

    /**
     * keeps image file.
     */
    private final File imageFile;

    /**
     * keeps circle count.
     */
    private int circleCount;

    /**
     * keep contours details.
     */
    private List<MatOfPoint> contours = null;

    /**
     * Constructor.
     *
     * @param file inputImageFile
     */
    public BloodCellsManipulationImpl(final File file) {
        radiusList = new ArrayList<>();
        this.imageFile = file;
    }

    /**
     * use to add radius value to array list.
     *
     * @param radius radius of a circle
     */
    @Override
    public void addRadius(final int radius) {
        radiusList.add(radius);
    }

    /**
     * use to sort array list, ascending order.
     */
    private void sortRadius() {
        Collections.sort(radiusList);
    }

    /**
     * use to extract minimum radius from the array list.
     *
     * @return minimum radius
     */
    @Override
    public int getMinimumRadius() {
        sortRadius();
        if (radiusList.size() > 0) {
            return radiusList.get(0);
        } else {
            return 0;
        }
    }

    /**
     * use to extract maximum radius from the array list.
     *
     * @return maximum radius
     */
    @Override
    public int getMaximumRadius() {
        sortRadius();
        if (radiusList.size() > 0) {
            return radiusList.get(radiusList.size() - 1);
        } else {
            return 0;
        }
    }

    /**
     * @return the circleCount
     */
    @Override
    public int getCircleCount() {
        return circleCount;
    }

    /**
     * @param count the circleCount to set
     */
    private void setCircleCount(final int count) {
        this.circleCount = count;
    }

    /**
     * get gray image.
     *
     * @return BufferedImage
     */
    @Override
    public BufferedImage getGrayImage() {
        return grayImage;
    }

    /**
     * get red image.
     *
     * @return BufferedImage
     */
    @Override
    public BufferedImage getRedImage() {
        return redImage;
    }

    /**
     * get canny edge detected image.
     *
     * @return BufferedImage
     */
    @Override
    public BufferedImage getCannyImage() {
        return cannyImage;
    }

    /**
     * get threshold image.
     *
     * @return BufferedImage
     */
    @Override
    public BufferedImage getThresholdImage() {
        return thresholdImage;
    }

    /**
     * get Laplacian image.
     *
     * @return BufferedImage
     */
    @Override
    public BufferedImage getLaplacianImage() {
        return laplacianImage;
    }

    /**
     * get Circulars Image.
     *
     * @return BufferedImage
     */
    @Override
    public BufferedImage getCircularsImage() {
        return circularsImage;
    }

    /**
     * get Circulars Image.
     *
     * @return BufferedImage
     */
    @Override
    public BufferedImage getEllipsesImage() {
        return ellipsesImage;
    }

    /**
     * converts map to image.
     *
     * @param mat mat
     * @return bufferedImage
     * @throws IOException IOException
     */
    @Override
    public BufferedImage convertMapToImage(final Mat mat) throws IOException {
        MatOfByte bytemat = new MatOfByte();
        Imgcodecs.imencode(".jpg", mat, bytemat);
        byte[] bytes = bytemat.toArray();
        InputStream in = new ByteArrayInputStream(bytes);
        return ImageIO.read(in);
    }

    /**
     * do Blood Cell Processing.
     *
     * @throws Exception Exception
     */
    @Override
    public void doBloodCellProcessing() throws Exception {
        if (imageFile.isFile()) {
            // Load Native Library
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

            // Load Selected File onto Original Mat
            original = Imgcodecs.imread(imageFile.getAbsolutePath(),
                    Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);

            // Create Mats with Original Mat's Size
            gray = original.clone();
            smooth = original.clone();
            canny = original.clone();
            threshold = original.clone();
            red = original.clone();
            laplacian = original.clone();
            circularsMat = original.clone();
            ellipsesMat = original.clone();

            // Sets Gray Scale to gray Map
            Imgproc.cvtColor(original, gray, Imgproc.COLOR_BGR2HSV);

            grayImage = convertMapToImage(gray);

            // Sest Red Colour Range Min and Max
            Scalar minc = new Scalar(95, 150, 75, 0);
            Scalar maxc = new Scalar(145, 255, 255, 0);

            // Sets Red Colour Mat for Given Range
            Core.inRange(original, maxc, maxc, red);

            redImage = convertMapToImage(red);

            int type = BufferedImage.TYPE_INT_RGB;
            //gray = Imgcodecs.imdecode(original, type);
            image = new BufferedImage(original.cols(), original.rows(), type);

            //Imgproc.GaussianBlur(mGray, mGray, new Size(15,15),50);
            //redImage = new BufferedImage(original.width(), original.height(), BufferedImage.TYPE_INT_RGB);
            //byte[] data2 = ((DataBufferByte) redImage.getRaster().getDataBuffer()).getData();
            Imgproc.threshold(gray, threshold, 155, 255, Imgproc.THRESH_BINARY);

            //Imgproc.Laplacian(gray, laplacian, CvType.CV_8UC3);//CV_8UC1);
            //laplacianImage = convertMapToImage(laplacian);
            thresholdImage = convertMapToImage(threshold);

            Imgproc.Canny(threshold, canny, 50, 100, 3, false);
            cannyImage = convertMapToImage(canny);

            //Imgproc.cvtColor(canny, canny, Imgproc.COLOR_BGR2GRAY);
            circles = new Mat();
            Imgproc.HoughCircles(canny, circles, Imgproc.CV_HOUGH_GRADIENT,
                    8, //Inverse ratio
                    220, //Minimum distance between the centers of the detected circles. default 100
                    100, //Higher threshold for canny edge detector. default100
                    200, //Threshold at the center detection stage. default 200
                    90, //min radius. default 50
                    130 //max radius. default 90
            );

            if (circles.cols() > 0) {
                for (int x = 0; x < circles.cols(); x++) {
                    double vCircle[] = circles.get(0, x);
                    if (vCircle == null) {
                        break;
                    }
                    Point pt = new Point(Math.round(vCircle[0]),
                            Math.round(vCircle[1]));
                    int radius = (int) Math.round(vCircle[2]);
                    radiusList.add(radius);
                    // draw the found circle
                    Imgproc.circle(circularsMat, pt, radius, new Scalar(0, 255, 0), 3);
                }
            }

            // set Pallor circles
            circlesPallor = new Mat();
            Imgproc.HoughCircles(canny, circlesPallor, Imgproc.CV_HOUGH_GRADIENT,
                    8, //Inverse ratio
                    220, //Minimum distance between the centers of the detected circles
                    100, //Higher threshold for canny edge detector
                    150, //Threshold at the center detection stage
                    0, //min radius
                    70 //max radius
            );
            if (circlesPallor.cols() > 0) {
                for (int x = 0; x < circlesPallor.cols(); x++) {
                    double vCircle[] = circlesPallor.get(0, x);
                    if (vCircle == null) {
                        break;
                    }
                    Point pt = new Point(Math.round(vCircle[0]), Math.round(vCircle[1]));
                    int radius = (int) Math.round(vCircle[2]);
                    //radiusList.add(radius);
                    // draw the found circle
                    Imgproc.circle(circularsMat, pt, radius, new Scalar(0, 0, 255), 0);
                }
            }

            // set Ellipses
            contours = new ArrayList<>();
            Imgproc.findContours(canny, contours, new Mat(), Imgproc.RETR_LIST,
                    Imgproc.CHAIN_APPROX_SIMPLE);
            MatOfPoint allcontours = new MatOfPoint();
            for (MatOfPoint mat : contours) {
                mat.copyTo(allcontours);
                RotatedRect boundingEllipse = null;
                if (allcontours.toArray().length > 4) {
                    MatOfPoint newMat1 = new MatOfPoint(allcontours.toArray());
                    MatOfPoint2f newMat2 = new MatOfPoint2f(
                            allcontours.toArray());
                    Rect boundingRect = Imgproc.boundingRect(newMat1);
                    boundingEllipse = Imgproc.fitEllipse(newMat2);
                }

                if (boundingEllipse != null) {
                    Imgproc.ellipse(ellipsesMat, boundingEllipse, new Scalar(255, 0, 0), 2);
                }
            }

            circleCount = circles.cols();
            circularsImage = convertMapToImage(circularsMat);
            ellipsesImage = convertMapToImage(ellipsesMat);
        }
    }

    /**
     * get circles mat.
     *
     * @return
     */
    @Override
    public Mat getCircles() {
        return circles;
    }

    /**
     * get ellipses mat.
     *
     * @return
     */
    @Override
    public Mat getEllipses() {
        return ellipsesMat;
    }

    /**
     * get pallors mat.
     *
     * @return
     */
    @Override
    public Mat getPallors() {
        return circlesPallor;
    }

    @Override
    public List<MatOfPoint> getContours() {
        return contours;
    }
}
