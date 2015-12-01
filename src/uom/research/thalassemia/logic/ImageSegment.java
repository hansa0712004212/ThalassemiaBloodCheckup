package uom.research.thalassemia.logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;
import javaxt.io.Image;
import uom.research.thalassemia.ui.Progress;
import uom.research.thalassemia.ui.Segments16Viewer;
import uom.research.thalassemia.ui.Segments4Viewer;
import uom.research.thalassemia.ui.Segments9ViewerHD;

/**
 * This class is focus on create segments.
 *
 * @author anupama
 */
public final class ImageSegment implements Runnable {

    /**
     * Use to keep all segments.
     */
    private final List<String> segmentFilesList;
    /**
     * No of segments for a image.
     */
    private int noOfSegments;
    /**
     * Track of one segment.
     */
    private Image segment = null;
    /**
     * Segments temporally saving location.
     */
    private final String downloadsFolder = FileSystemView.getFileSystemView()
            .getHomeDirectory().getAbsolutePath() + File.separator
            + "Downloads" + File.separator;

    /**
     * 2 Mega Pixels Image indicator constant.
     */
    private final int mp2 = 2500000;
    /**
     * 5 Mega Pixels Image indicator constant.
     */
    private final int mp5 = 5500000;
    /**
     * segments count constant value for 2 Mega Pixels Image.
     */
    private final int mp2Segments = 2;
    /**
     * segments count constant value for 5 Mega Pixels Image.
     */
    private final int mp5Segments = 3;
    /**
     * segments count constant value for 20 Mega Pixels Image.
     */
    private final int mp20Segments = 4;
    /**
     * Segmentation Position constant values.
     */
    private static final int SEGMENT1 = 1, SEGMENT2 = 2, SEGMENT3 = 3,
            SEGMENT4 = 4, SEGMENT5 = 5, SEGMENT6 = 6, SEGMENT7 = 7,
            SEGMENT8 = 8, SEGMENT9 = 9, SEGMENT10 = 10, SEGMENT11 = 11,
            SEGMENT12 = 12, SEGMENT13 = 13, SEGMENT14 = 14, SEGMENT15 = 15,
            SEGMENT16 = 16;

    /**
     * Keeps working JFrame.
     */
    private final JFrame inputParent;

    /**
     * Keeps Buffered Image to read.
     */
    private BufferedImage inputBufferedImage;

    /**
     * Keeps file to buffer.
     */
    private File imageFile;

    /**
     * constant variable to indicate 3x multiply.
     */
    private final int x3 = 3;

    /**
     * Progress bar to indicate thread is busy.
     */
    private Progress progress;

    /**
     * Constructor will do the segmentation.
     *
     * @param parent parent JFrame
     * @param bufferedImage input as a BufferedImage object
     */
    public ImageSegment(final JFrame parent,
            final BufferedImage bufferedImage) {
        segmentFilesList = new ArrayList<>();
        inputBufferedImage = bufferedImage;
        inputParent = parent;
    }

    /**
     * Constructor will do the segmentation.
     *
     * @param parent parent JFrame
     * @param inputImageFile input file
     * @throws java.io.IOException io exception
     */
    public ImageSegment(final JFrame parent,
            final File inputImageFile) throws IOException {
        segmentFilesList = new ArrayList<>();
        inputParent = parent;
        imageFile = inputImageFile;
    }

    /**
     * perform segmentation for 2MP image.
     *
     * @param position segment position
     * @param bufferedImage image to segment
     * @param wUnit unit width
     * @param hUnit unit height
     */
    void doMP2Segmentation(final int position,
            final BufferedImage bufferedImage,
            final int wUnit, final int hUnit) {

        switch (position) {
            case SEGMENT1:
                segment = new Image(bufferedImage);
                cropImage(0, 0, wUnit, hUnit);
                break;
            case SEGMENT2:
                segment = new Image(bufferedImage);
                cropImage(wUnit, 0, wUnit, hUnit);
                break;
            case SEGMENT3:
                segment = new Image(bufferedImage);
                cropImage(0, hUnit, wUnit, hUnit);
                break;
            case SEGMENT4:
                segment = new Image(bufferedImage);
                cropImage(wUnit, hUnit, wUnit, hUnit);
                break;
            default:
        }
    }

    /**
     * perform segmentation for 5MP image.
     *
     * @param position segment position
     * @param bufferedImage image to segment
     * @param wUnit unit width
     * @param hUnit unit height
     */
    void doMP5Segmentation(final int position,
            final BufferedImage bufferedImage,
            final int wUnit, final int hUnit) {

        switch (position) {
            case SEGMENT1:
                segment = new Image(bufferedImage);
                cropImage(0, 0, wUnit, hUnit);
                break;
            case SEGMENT2:
                segment = new Image(bufferedImage);
                cropImage(wUnit, 0, wUnit, hUnit);
                break;
            case SEGMENT3:
                segment = new Image(bufferedImage);
                cropImage(wUnit * 2, 0, wUnit, hUnit);
                break;
            case SEGMENT4:
                segment = new Image(bufferedImage);
                cropImage(0, hUnit, wUnit, hUnit);
                break;
            case SEGMENT5:
                segment = new Image(bufferedImage);
                cropImage(wUnit, hUnit, wUnit, hUnit);
                break;
            case SEGMENT6:
                segment = new Image(bufferedImage);
                cropImage(wUnit * 2, hUnit, wUnit, hUnit);
                break;
            case SEGMENT7:
                segment = new Image(bufferedImage);
                cropImage(0, hUnit * 2, wUnit, hUnit);
                break;
            case SEGMENT8:
                segment = new Image(bufferedImage);
                cropImage(wUnit, hUnit * 2, wUnit, hUnit);
                break;
            case SEGMENT9:
                segment = new Image(bufferedImage);
                cropImage(wUnit * 2, hUnit * 2, wUnit, hUnit);
                break;
            default:
        }
    }

    /**
     * perform segmentation for 20MP image.
     *
     * @param position segment position
     * @param bufferedImage image to segment
     * @param wUnit unit width
     * @param hUnit unit height
     */
    void doMP20Segmentation(final int position,
            final BufferedImage bufferedImage,
            final int wUnit, final int hUnit) {

        switch (position) {
            case SEGMENT1:
                segment = new Image(bufferedImage);
                cropImage(0, 0, wUnit, hUnit);
                break;
            case SEGMENT2:
                segment = new Image(bufferedImage);
                cropImage(wUnit, 0, wUnit, hUnit);
                break;
            case SEGMENT3:
                segment = new Image(bufferedImage);
                cropImage(wUnit * 2, 0, wUnit, hUnit);
                break;
            case SEGMENT4:
                segment = new Image(bufferedImage);
                cropImage(wUnit * x3, 0, wUnit, hUnit);
                break;
            case SEGMENT5:
                segment = new Image(bufferedImage);
                cropImage(0, hUnit, wUnit, hUnit);
                break;
            case SEGMENT6:
                segment = new Image(bufferedImage);
                cropImage(wUnit, hUnit, wUnit, hUnit);
                break;
            case SEGMENT7:
                segment = new Image(bufferedImage);
                cropImage(wUnit * 2, hUnit, wUnit, hUnit);
                break;
            case SEGMENT8:
                segment = new Image(bufferedImage);
                cropImage(wUnit * x3, hUnit, wUnit, hUnit);
                break;
            case SEGMENT9:
                segment = new Image(bufferedImage);
                cropImage(0, hUnit * 2, wUnit, hUnit);
                break;
            case SEGMENT10:
                segment = new Image(bufferedImage);
                cropImage(wUnit, hUnit * 2, wUnit, hUnit);
                break;
            case SEGMENT11:
                segment = new Image(bufferedImage);
                cropImage(wUnit * 2, hUnit * 2, wUnit, hUnit);
                break;
            case SEGMENT12:
                segment = new Image(bufferedImage);
                cropImage(wUnit * x3, hUnit * 2, wUnit, hUnit);
                break;
            case SEGMENT13:
                segment = new Image(bufferedImage);
                cropImage(0, hUnit * x3, wUnit, hUnit);
                break;
            case SEGMENT14:
                segment = new Image(bufferedImage);
                cropImage(wUnit, hUnit * x3, wUnit, hUnit);
                break;
            case SEGMENT15:
                segment = new Image(bufferedImage);
                cropImage(wUnit * 2, hUnit * x3, wUnit, hUnit);
                break;
            case SEGMENT16:
                segment = new Image(bufferedImage);
                cropImage(wUnit * x3, hUnit * x3, wUnit, hUnit);
                break;
            default:
        }
    }

    /**
     * save image segment in local drive.
     *
     * @param position segment position of the original image
     */
    private void saveTemporaryImage(final int position) {
        segmentFilesList.add(downloadsFolder + "temp" + position + ".jpg");
        segment.saveAs(downloadsFolder + "temp" + position + ".jpg");
    }

    /**
     * crop the image and create segment.
     *
     * @param x cropping start x coordinate
     * @param y cropping start y coordinate
     * @param width width for segment
     * @param height height for segment
     */
    private void cropImage(final int x, final int y,
            final int width, final int height) {
        segment.crop(x, y, width, height);
    }

    /**
     * get saved segment one by one.
     *
     * @param position segment position
     * @return image segment
     */
    public String getSegmentFileFromList(final int position) {
        return segmentFilesList.get(position);
    }

    /**
     * get all saved segments list.
     *
     * @return the partialImagesList
     */
    public List<String> getSegmentFilesList() {
        return segmentFilesList;
    }

    /**
     * runnable implementation.
     */
    @Override
    public void run() {
        try {
            SwingUtilities.invokeLater(() -> {
                progress = new Progress(inputParent, true);
                progress.setVisible(true);
            });
            inputBufferedImage = ImageIO.read(imageFile);
            int width = inputBufferedImage.getWidth();
            int height = inputBufferedImage.getHeight();
            int pixels = width * height;

            if (pixels < mp2) {
                noOfSegments = mp2Segments;
            } else if (pixels < mp5) {
                noOfSegments = mp5Segments;
            } else {
                noOfSegments = mp20Segments;
            }

            int wUnit = width / noOfSegments;
            int hUnit = height / noOfSegments;
            new Thread(() -> {
                for (int i = 1; i <= (noOfSegments * noOfSegments); i++) {
                    //final int iterator = i;

                    switch (noOfSegments) {
                        case mp2Segments:
                            doMP2Segmentation(i, inputBufferedImage,
                                    wUnit, hUnit);
                            break;
                        case mp5Segments:
                            doMP5Segmentation(i, inputBufferedImage,
                                    wUnit, hUnit);
                            break;
                        case mp20Segments:
                            doMP20Segmentation(i, inputBufferedImage,
                                    wUnit, hUnit);
                            break;
                        default:
                    }
                    saveTemporaryImage(i);
                }
                progress.dispose();

                switch (noOfSegments) {
                    case mp2Segments:
                        new Segments4Viewer(inputParent, true,
                                segmentFilesList).setVisible(true);
                        break;
                    case mp5Segments:
                        new Segments9ViewerHD(inputParent, true,
                                segmentFilesList).setVisible(true);
                        break;
                    case mp20Segments:
                        new Segments16Viewer(inputParent, true,
                                segmentFilesList).setVisible(true);
                        break;
                    default:
                }
            }).start();
        } catch (Exception ex) {

        }

    }
}
