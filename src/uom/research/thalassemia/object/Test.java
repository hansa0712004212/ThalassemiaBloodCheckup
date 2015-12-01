/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.object;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author hansa
 */
public final class Test {

    /**
     * Test Rid.
     */
    private String rid;

    /**
     * Test Type.
     */
    private TestType testType;

    /**
     * Test Image Path.
     */
    private String imagePath;

    /**
     * Test Date.
     */
    private LocalDate testDate;

    /**
     * Test Circles List.
     */
    private List<Circle> circles;

    /**
     * Test Thalassemia isInfected.
     */
    private boolean isInfected;

    /**
     * Default Constructor.
     */
    public Test() {
        setRid(null);
        setTestType(null);
        setImagePath(null);
        setTestDate(null);
        setCircles(null);
        setIsInfected(false);
    }

    /**
     * Parameterized Constructor.
     *
     * @param prid rid
     * @param ptestType testType
     * @param pimagePath imagePath
     * @param ptestDate testDate
     * @param pcircles circles
     * @param pisInfected isInfected
     */
    public Test(final String prid, final TestType ptestType,
            final String pimagePath, final LocalDate ptestDate,
            final List<Circle> pcircles, final boolean pisInfected) {
        setRid(prid);
        setTestType(ptestType);
        setImagePath(pimagePath);
        setTestDate(ptestDate);
        setCircles(pcircles);
        setIsInfected(pisInfected);
    }

    /**
     * Test Rid.
     *
     * @return the TestTypeRid
     */
    public String getRid() {
        return rid;
    }

    /**
     * Test Rid.
     *
     * @param prid the TestTypeRid to set
     */
    public void setRid(final String prid) {
        this.rid = prid;
    }

    /**
     * Test Type.
     *
     * @return the TestTypeRid
     */
    public TestType getTestType() {
        return testType;
    }

    /**
     * Test Type.
     *
     * @param ptestType the TestTypeRid to set
     */
    public void setTestType(final TestType ptestType) {
        this.testType = ptestType;
    }

    /**
     * Test Image Path.
     *
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Test Image Path.
     *
     * @param pimagePath the imagePath to set
     */
    public void setImagePath(final String pimagePath) {
        this.imagePath = pimagePath;
    }

    /**
     * Test Date.
     *
     * @return the testDate
     */
    public LocalDate getTestDate() {
        return testDate;
    }

    /**
     * Test Date.
     *
     * @param ptestDate the testDate to set
     */
    public void setTestDate(final LocalDate ptestDate) {
        this.testDate = ptestDate;
    }

    /**
     * Test Circles List.
     *
     * @return the circles
     */
    public List<Circle> getCircles() {
        return circles;
    }

    /**
     * Test Circles List.
     *
     * @param pcircles the circles to set
     */
    public void setCircles(final List<Circle> pcircles) {
        this.circles = pcircles;
    }

    /**
     * Test Thalassemia isInfected.
     *
     * @return the isInfected
     */
    public boolean isIsInfected() {
        return isInfected;
    }

    /**
     * Test Thalassemia isInfected.
     *
     * @param pisInfected the isInfected to set
     */
    public void setIsInfected(final boolean pisInfected) {
        this.isInfected = pisInfected;
    }
}
