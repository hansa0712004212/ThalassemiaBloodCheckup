/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.dao;

import uom.research.thalassemia.db.DatabaseAccess;
import uom.research.thalassemia.object.TestSuite;

/**
 *
 * @author hansa
 */
public final class TestSuiteDAOImpl implements TestSuiteDAO {

    /**
     * save test suite.
     *
     * @param testSuite test suite
     * @return testSuiteID
     * @throws java.lang.Exception Exception
     */
    @Override
    public String saveTestSuite(final TestSuite testSuite) throws Exception {
        String rid = DatabaseAccess.insertData("INSERT INTO TestSuite SET "
                + "patient = " + testSuite.getPatient().getRid() + ", "
                + "performedBy= " + testSuite.getPerformedBy().getRid());
        return rid;
    }
}
