/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.dao;

import com.orientechnologies.orient.core.record.impl.ODocument;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import uom.research.thalassemia.db.DatabaseAccess;
import uom.research.thalassemia.object.Circle;
import uom.research.thalassemia.object.Test;
import uom.research.thalassemia.object.TestSuite;
import uom.research.thalassemia.util.Constant;
import uom.research.thalassemia.util.Validator;

/**
 *
 * @author hansa
 */
public final class TestDAOImpl implements TestDAO {

    /**
     * Simple date formatter.
     */
    private static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Circle document prefix.
     */
    private static final String CIRCLE_DOCUMENT_PREFIX
            = "{\"@type\":\"d\",\"@version\":0,\"@class\":\"Circle\", ";

    /**
     * Circle document postfix.
     */
    private static final String CIRCLE_DOCUMENT_POSTFIX = "}";

    /**
     * test suite.
     */
    private TestSuite testSuite;

    /**
     * save Test data.
     *
     * @param pTestSuite test rid
     * @param test test rid
     * @return rid string
     * @throws java.lang.Exception Exception
     */
    @Override
    public String saveTest(final TestSuite pTestSuite, final Test test)
            throws Exception {
        testSuite = pTestSuite;
        String rid = DatabaseAccess.insertData("INSERT INTO Test SET "
                + "testType=(select * from TestType where testType = "
                + "\"Blood Cell Image Analysis\"), "
                + "imagePath='" + copyToBloodDBImages(test.getImagePath())
                + "', "
                + "testDate='" + Validator.localDateToFormattedDate(
                        test.getTestDate()) + "', "
                + "isInfected=" + test.isIsInfected() + ", "
                + "actualRBC=" + test.getActualRBC() + ", "
                + "mcvActual=" + test.getMcvActual() + ", "
                + "rdwActual=" + test.getRdwActual());

        List<Circle> circles = test.getCircles();
        circles.stream().forEach((circle) -> {
            String circleDocument = CIRCLE_DOCUMENT_PREFIX
                    + " \"xAxis\":\"" + circle.getxPoint() + "\","
                    + " \"yAxis\":\"" + circle.getyPoint() + "\","
                    + " \"radius\":\"" + circle.getRadius() + "\","
                    + " \"perimeter\":\"" + circle.getPerimeter() + "\","
                    + " \"area\":\"" + circle.getArea() + "\""
                    + CIRCLE_DOCUMENT_POSTFIX;

            try {
                DatabaseAccess.updateData("UPDATE " + rid + " ADD circles = "
                        + circleDocument);
            } catch (Exception ex) {
                System.out.println("error at saving circle " + ex.getMessage());
            }
        });

        return rid;
    }

    /**
     * copy blood cell data to blood cell images db folder.
     *
     * @param file image file
     * @return completion status
     * @exception IOException exception
     */
    private String copyToBloodDBImages(final File file) throws IOException {
        File destFile = null;
        if (file.exists() && new File(Constant.IMAGES_DB_FOLDER).exists()) {
            destFile = new File(Constant.IMAGES_DB_FOLDER
                    + new Date().getTime()
                    + file.getAbsolutePath().substring(
                            file.getAbsolutePath().lastIndexOf(".")));
            FileUtils.copyFile(file, destFile);
        } else {
            new File(Constant.IMAGES_DB_FOLDER).mkdir();
        }
        return destFile.getName();
    }

    /**
     * load Circles.
     *
     * @param rid test rid
     * @return Circle List
     * @throws Exception Exception
     */
    public List<Circle> loadCircles(final String rid) throws Exception {
        List<ODocument> circleDocuments = DatabaseAccess.selectData(
                "SELECT EXPAND(circles) FROM " + rid);
        List<Circle> circlesList = new ArrayList<>();
        Circle circle;
        for (ODocument circleDocument : circleDocuments) {
            circle = new Circle(
                    circleDocument.field("xAxis"),
                    circleDocument.field("yAxis"),
                    circleDocument.field("radius"),
                    circleDocument.field("perimeter"),
                    circleDocument.field("area"));
            circlesList.add(circle);
        }
        return circlesList;
    }
}
