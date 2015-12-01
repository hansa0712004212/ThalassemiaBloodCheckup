/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.dao;

import uom.research.thalassemia.object.TestSuite;

/**
 *
 * @author hansa
 */
public interface TestSuiteDAO {

    /**
     * save test suite.
     *
     * @param testSuite test suite
     * @return testSuiteID
     * @throws java.lang.Exception Exception
     */
    String saveTestSuite(final TestSuite testSuite) throws Exception;

}
