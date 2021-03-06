/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.dao;

import java.util.List;
import uom.research.thalassemia.object.Circle;
import uom.research.thalassemia.object.Test;
import uom.research.thalassemia.object.TestSuite;

/**
 *
 * @author hansa
 */
public interface TestDAO {

    /**
     * save Test data.
     *
     * @param testSuite test suite
     * @param test test
     * @return rid string
     * @throws java.lang.Exception Exception
     */
    String saveTest(final TestSuite testSuite, final Test test)
            throws Exception;

    /**
     * load Circles.
     *
     * @param rid test rid
     * @return Circle List
     * @throws Exception Exception
     */
    public List<Circle> loadCircles(final String rid) throws Exception;
}
