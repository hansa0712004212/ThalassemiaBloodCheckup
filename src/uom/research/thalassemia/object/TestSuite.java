/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.object;

import java.util.List;

/**
 *
 * @author hansa
 */
public final class TestSuite {

    /**
     * Test Rid.
     */
    private String rid;

    /**
     * Test Suite related Patient.
     */
    private Patient patient;

    /**
     * List of Tests of Test Suite.
     */
    private List<Test> test;

    /**
     * Test Suite Created By.
     */
    private User performedBy;

    /**
     * Default Constructor.
     */
    public TestSuite() {
        setPatient(null);
        setTest(null);
        setPerformedBy(null);
    }

    /**
     * Parameterized Constructor.
     *
     * @param prid rid
     * @param ppatient patient
     * @param ptest test
     * @param pperformedBy user
     */
    public TestSuite(final String prid, final Patient ppatient,
            final List<Test> ptest, final User pperformedBy) {
        setPatient(ppatient);
        setTest(ptest);
        setPerformedBy(pperformedBy);
    }

    /**
     * Test Suite rid.
     *
     * @return the rid
     */
    public String getRid() {
        return rid;
    }

    /**
     * Test Suite rid.
     *
     * @param prid the patient to set
     */
    public void setRid(final String prid) {
        this.rid = prid;
    }

    /**
     * Test Suite related Patient.
     *
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Test Suite related Patient.
     *
     * @param ppatient the patient to set
     */
    public void setPatient(final Patient ppatient) {
        this.patient = ppatient;
    }

    /**
     * List of Tests of Test Suite.
     *
     * @return the test
     */
    public List<Test> getTest() {
        return test;
    }

    /**
     * List of Tests of Test Suite.
     *
     * @param ptest the test to set
     */
    public void setTest(final List<Test> ptest) {
        this.test = ptest;
    }

    /**
     * Test Suite Created By.
     *
     * @return the performedBy
     */
    public User getPerformedBy() {
        return performedBy;
    }

    /**
     * Test Suite Created By.
     *
     * @param pperformedBy the performedBy to set
     */
    public void setPerformedBy(final User pperformedBy) {
        this.performedBy = pperformedBy;
    }
}
