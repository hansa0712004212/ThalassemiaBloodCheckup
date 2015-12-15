/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.object;

import java.io.File;
import java.time.LocalDateTime;
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
    private File imagePath;

    /**
     * Test Date.
     */
    private LocalDateTime testDate;

    /**
     * Test Circles List.
     */
    private List<Circle> circles;

    /**
     * Test Thalassemia isInfected.
     */
    private boolean isInfected;

    /**
     * actual RBC.
     */
    private double actualRBC;

    /**
     * mcv Actual.
     */
    private double mcvActual;

    /**
     * rdw Actual.
     */
    private double rdwActual;

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
            final File pimagePath, final LocalDateTime ptestDate,
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
    public File getImagePath() {
        return imagePath;
    }

    /**
     * Test Image Path.
     *
     * @param pimagePath the imagePath to set
     */
    public void setImagePath(final File pimagePath) {
        this.imagePath = pimagePath;
    }

    /**
     * Test Date.
     *
     * @return the testDate
     */
    public LocalDateTime getTestDate() {
        return testDate;
    }

    /**
     * Test Date.
     *
     * @param ptestDate the testDate to set
     */
    public void setTestDate(final LocalDateTime ptestDate) {
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

    /**
     * @return the actualRBC
     */
    public double getActualRBC() {
        return actualRBC;
    }

    /**
     * @param pactualRBC the actualRBC to set
     */
    public void setActualRBC(final double pactualRBC) {
        this.actualRBC = pactualRBC;
    }

    /**
     * @return the mcvActual
     */
    public double getMcvActual() {
        return mcvActual;
    }

    /**
     * @param pmcvActual the mcvActual to set
     */
    public void setMcvActual(final double pmcvActual) {
        this.mcvActual = pmcvActual;
    }

    /**
     * @return the rdwActual
     */
    public double getRdwActual() {
        return rdwActual;
    }

    /**
     * @param prdwActual the rdwActual to set
     */
    public void setRdwActual(final double prdwActual) {
        this.rdwActual = prdwActual;
    }

}
