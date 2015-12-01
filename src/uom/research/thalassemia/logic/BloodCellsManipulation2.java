package uom.research.thalassemia.logic;
/*
 import com.googlecode.javacv.cpp.opencv_core;
 import static com.googlecode.javacv.cpp.opencv_core.CV_AA;
 import com.googlecode.javacv.cpp.opencv_core.CvScalar;
 import com.googlecode.javacv.cpp.opencv_core.IplImage;
 import static com.googlecode.javacv.cpp.opencv_core.cvCircle;
 import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
 import static com.googlecode.javacv.cpp.opencv_core.cvGetSeqElem;
 import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
 import static com.googlecode.javacv.cpp.opencv_core.cvPointFrom32f;
 import static com.googlecode.javacv.cpp.opencv_core.cvScalar;
 import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
 import com.googlecode.javacv.cpp.opencv_imgproc;
 import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
 import static com.googlecode.javacv.cpp.opencv_imgproc.CV_GAUSSIAN;
 import static com.googlecode.javacv.cpp.opencv_imgproc.CV_HOUGH_GRADIENT;
 import static com.googlecode.javacv.cpp.opencv_imgproc.CV_THRESH_BINARY;
 import static com.googlecode.javacv.cpp.opencv_imgproc.cvCanny;
 import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
 import static com.googlecode.javacv.cpp.opencv_imgproc.cvHoughCircles;
 import static com.googlecode.javacv.cpp.opencv_imgproc.cvSmooth;
 import static com.googlecode.javacv.cpp.opencv_imgproc.cvThreshold;*/

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * This class is to manipulate blood data.
 *
 * @author anupama
 */
public final class BloodCellsManipulation2 {

    /**
     * Use to keep radius values in a array list.
     */
    private static List<Integer> radiusList;
    /**
     * IplImages.
     */
    private Mat original, gray, smooth, canny, threshold, red;
    /**
     * BufferedImage images.
     */
    private BufferedImage image, grayImage, redImage;
    /**
     * keeps image file.
     */
    private final File imageFile;

    /**
     * keeps circle count.
     */
    private int circleCount;

    /**
     * Constructor.
     *
     * @param file inputImageFile
     */
    public BloodCellsManipulation2(final File file) {
        radiusList = new ArrayList<>();
        this.imageFile = file;
    }

    /**
     * use to add radius value to array list.
     *
     * @param radius radius of a circle
     */
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
    public int getMaximumRadius() {
        sortRadius();
        if (radiusList.size() > 0) {
            return radiusList.get(radiusList.size() - 1);
        } else {
            return 0;
        }
    }

    /**
     * Image Processing.
     *
     * @throws Exception any exception
     */
    public void doBloodCellProcessing() throws Exception {
        if (imageFile.isFile()) {
            // Load Native Library
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

            // Load Selected File onto Original Mat
            original = Imgcodecs.imread(imageFile.getAbsolutePath(),
                    Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);

            System.out.println("1");
            // Create Mats with Original Mat's Size
            gray = original.clone();
            smooth = original.clone();
            canny = original.clone();
            threshold = original.clone();
            red = original.clone();

            //original.convertTo(original, -1, 1, 5);
            System.out.println("2");
            // Sets Gray Scale to gray Map
            Imgproc.cvtColor(original, gray, Imgproc.COLOR_BGR2GRAY);
//Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

            System.out.println("3");

            // Sest Red Colour Range Min and Max
            Scalar minc = new Scalar(95, 150, 75, 0);
            Scalar maxc = new Scalar(145, 255, 255, 0);

            // Sets Red Colour Mat for Given Range
            Core.inRange(original, maxc, maxc, red);
            System.out.println("4");

            int type = BufferedImage.TYPE_INT_RGB;
            //gray = Imgcodecs.imdecode(original, type);
            image = new BufferedImage(original.cols(), original.rows(), type);

            System.out.println("5");

            //Imgproc.GaussianBlur(mGray, mGray, new Size(15,15),50);
            //redImage = new BufferedImage(original.width(), original.height(), BufferedImage.TYPE_INT_RGB);
            //byte[] data2 = ((DataBufferByte) redImage.getRaster().getDataBuffer()).getData();
            Imgproc.threshold(gray, gray, 155, 255, Imgproc.THRESH_BINARY);
            Imgproc.Canny(gray, gray, 20, 40, 3, false);

            Imgproc.cvtColor(canny, canny, Imgproc.COLOR_BGR2GRAY);
            Mat circles = new Mat();
            Imgproc.HoughCircles(canny, circles, Imgproc.CV_HOUGH_GRADIENT, 8, 100);

            if (circles.cols() > 0) {
                for (int x = 0; x < circles.cols(); x++) {
                    System.out.println(x);
                    double vCircle[] = circles.get(0, x);
                    if (vCircle == null) {
                        break;
                    }
                    Point pt = new Point(Math.round(vCircle[0]), Math.round(vCircle[1]));
                    int radius = (int) Math.round(vCircle[2]);
                    // draw the found circle
                    Imgproc.circle(gray, pt, radius, new Scalar(0, 255, 0), 2);
                    System.out.println(x);
                    //Imgproc.circle(gray, pt, 3, new Scalar(0, 0, 255), 2);
                }
            }
            System.out.println("circles " + circles.cols());
            /*
             CV_HOUGH_GRADIENT, //Detection method
             8, //Inverse ratio
             100, //Minimum distance between the centers of the detected circles
             100, //Higher threshold for canny edge detector
             100, //Threshold at the center detection stage
             0, //min radius
             90 //max radius
             for (int i = 0; i < circles.total(); i++) {
             opencv_core.CvPoint3D32f circle = new opencv_core.CvPoint3D32f(cvGetSeqElem(circles, i));
             opencv_core.CvPoint center = cvPointFrom32f(new opencv_core.CvPoint2D32f(circle.x(), circle.y()));
             int radius = Math.round(circle.z());
             addRadius(radius);
             cvCircle(original, center, radius, opencv_core.CvScalar.GREEN, 3, CV_AA, 0);
             }*/
            System.out.println("6");
            grayImage = convertMapToImage(gray);
            redImage = convertMapToImage(red);

            /*
             original = cvLoadImage();
             red = cvCreateImage(cvGetSize(original), 8, 3);
             gray = cvCreateImage(cvGetSize(original), 8, 1);
             smooth = cvCreateImage(cvGetSize(original), 8, 1);
             threshold = cvCreateImage(cvGetSize(original), 8, 1);
             canny = cvCreateImage(cvGetSize(original), 8, 1);

             //cvCvtColor(original, red, opencv_core.CV_8U);
             cvCvtColor(original,red,opencv_imgproc.CV_BGR2HSV);

             // make loaded image to grayscale
             cvCvtColor(original, gray, CV_BGR2GRAY);

             // make grayscaled image more smooth
             cvSmooth(gray, smooth, CV_GAUSSIAN, 3);
             cvThreshold(smooth, threshold, 155, 255, CV_THRESH_BINARY);

             opencv_core.CvMemStorage mem = opencv_core.CvMemStorage.create();

             // perform Canny edge detection
             cvCanny(threshold, canny, 20, 40, 3);

             // perform Hough circle detection 
             opencv_core.CvSeq circles = cvHoughCircles(
             threshold, //Input image
             mem, //Memory Storage
             CV_HOUGH_GRADIENT, //Detection method
             8, //Inverse ratio
             100, //Minimum distance between the centers of the detected circles
             100, //Higher threshold for canny edge detector
             100, //Threshold at the center detection stage
             0, //min radius
             90 //max radius
             );
             setCircleCount(circles.total());
             // draw circles
             for (int i = 0; i < circles.total(); i++) {
             opencv_core.CvPoint3D32f circle = new opencv_core.CvPoint3D32f(cvGetSeqElem(circles, i));
             opencv_core.CvPoint center = cvPointFrom32f(new opencv_core.CvPoint2D32f(circle.x(), circle.y()));
             int radius = Math.round(circle.z());
             addRadius(radius);
             cvCircle(original, center, radius, opencv_core.CvScalar.GREEN, 3, CV_AA, 0);
             }*/
        }
    }

    public BufferedImage getGrayImage() {
        System.out.println("7");
        return grayImage;
    }

    public BufferedImage getRedImage() {

        System.out.println("8");
        return redImage;
    }

    public BufferedImage convertMapToImage(Mat mat) throws IOException {
        MatOfByte bytemat = new MatOfByte();
        Imgcodecs.imencode(".jpg", mat, bytemat);
        byte[] bytes = bytemat.toArray();
        InputStream in = new ByteArrayInputStream(bytes);
        return ImageIO.read(in);
    }

    /*public BufferedImage getSmoothImage() {
        return smooth.;
    }

    public BufferedImage getCannyImage() {
        return canny.getBufferedImage();
    }

    public BufferedImage getThresholdImage() {
        return threshold.getBufferedImage();
    }

    public BufferedImage getProcessedImage() {
        return original.getBufferedImage();
    }*/

    /**
     * @return the circleCount
     */
    public int getCircleCount() {
        return circleCount;
    }

    /**
     * @param count the circleCount to set
     */
    private void setCircleCount(final int count) {
        this.circleCount = count;
    }
}
