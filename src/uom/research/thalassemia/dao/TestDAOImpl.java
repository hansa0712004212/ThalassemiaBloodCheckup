/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.dao;

import java.text.SimpleDateFormat;
import java.util.List;
import uom.research.thalassemia.db.DatabaseAccess;
import uom.research.thalassemia.object.Circle;
import uom.research.thalassemia.object.Test;
import uom.research.thalassemia.object.TestSuite;

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
     * save Test data.
     *
     * @param test test rid
     * @return rid string
     * @throws java.lang.Exception Exception
     */
    @Override
    public String saveTest(final TestSuite testSuite, final Test test)
            throws Exception {

        String rid = DatabaseAccess.insertData("INSERT INTO Test SET "
                + "testType=" + test.getTestType() + ", "
                + "imagePath=" + test.getImagePath() + ", "
                + "testDate=" + test.getTestDate() + ", "
                + "isInfected=" + test.isIsInfected());

        List<Circle> circles = test.getCircles();
        circles.stream().forEach((circle) -> {
            String circleDocument = CIRCLE_DOCUMENT_PREFIX
                    + " \"xAxis\":\"" + circle.getxPoint() + "\","
                    + " \"yAxis\":\"" + circle.getyPoint() + "\","
                    + " \"radius\":\"" + circle.getRadius() + "\","
                    + " \"perimeter\":\"" + circle.getPerimeter() + "\","
                    + " \"area\":\"" + circle.getArea() + "\","
                    + CIRCLE_DOCUMENT_POSTFIX;
        });

        return null;
    }
}
