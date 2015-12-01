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
public final class TestType {

    /**
     * Test Rid.
     */
    private String rid;

    /**
     * Test Type Name.
     */
    private String testType;

    /**
     * Private Default Constructor.
     */
    private TestType() {
    }

    /**
     * Parameterized Constructor.
     *
     * @param prid rid
     * @param ptestType test type
     */
    public TestType(final String prid, final String ptestType) {
        this.rid = prid;
        this.testType = ptestType;
    }

    /**
     * Test Rid.
     *
     * @return the rid
     */
    public String getRid() {
        return rid;
    }

    /**
     * Test Rid.
     *
     * @param prid the rid to set
     */
    public void setRid(final String prid) {
        this.rid = prid;
    }

    /**
     * Test Type Name.
     *
     * @return the testType
     */
    public String getTestType() {
        return testType;
    }

    /**
     * Test Type Name.
     *
     * @param ptestType the testType to set
     */
    public void setTestType(final String ptestType) {
        this.testType = ptestType;
    }
}
