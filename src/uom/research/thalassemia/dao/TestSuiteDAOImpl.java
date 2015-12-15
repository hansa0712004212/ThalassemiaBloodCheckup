/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.dao;

import com.orientechnologies.orient.core.record.impl.ODocument;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import uom.research.thalassemia.db.DatabaseAccess;
import uom.research.thalassemia.object.Circle;
import uom.research.thalassemia.object.Patient;
import uom.research.thalassemia.object.Test;
import uom.research.thalassemia.object.TestSuite;
import uom.research.thalassemia.object.TestType;
import uom.research.thalassemia.object.User;
import uom.research.thalassemia.util.Constant;
import uom.research.thalassemia.util.Validator;

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
                + "testSuiteDate = '" + Validator.localDateToFormattedDate(
                        testSuite.getTestSuiteDate()) + "', "
                + "performedBy= " + testSuite.getPerformedBy().getRid());
        return rid;
    } 
	
	public String abc1(final TestSuite testSuite) throws Exception {
        String rid = DatabaseAccess.insertData("INSERT INTO TestSuite SET "
                + "patient=" + testSuite.getPatient().getRid() + ", "
                + "performedBy=" + testSuite.getPerformedBy().getRid());
        return rid;
    }
	
	public String abc2(final TestSuite testSuite) throws Exception {
        String rid = DatabaseAccess.insertData("INSERT INTO TestSuite SET "
                + "patient=" + testSuite.getPatient().getRid() + ", "
                + "performedBy=" + testSuite.getPerformedBy().getRid());
        return rid;
    }

    /**
     * load Test Suite Of Patients.
     *
     * @param patient patient
     * @return testSuiteID
     * @throws java.lang.Exception Exception
     */
    @Override
    public List<TestSuite> loadAllTestSuitesOfPatient(final Patient patient)
            throws Exception {
        List<ODocument> testSuiteDocuments = DatabaseAccess.selectData(
                "SELECT @rid.asString() as rid, patient, performedBy, "
                + "testSuiteDate.format('yyyy-MM-dd HH:mm:ss'), "
                + "test as test FROM TestSuite WHERE patient="
                + patient.getRid());
        List<TestSuite> testSuites = new ArrayList<>();
        TestSuite testSuite;
        for (ODocument testSuiteDocument : testSuiteDocuments) {
            testSuite = new TestSuite();
            testSuite.setPatient(patient);

            ODocument userDoc = testSuiteDocument.field("performedBy");
            User user = new User();
            user.setRid(userDoc.field("rid"));
            user.setUserName(userDoc.field("username"));
            user.setFirstName(userDoc.field("firstName"));
            user.setLastName(userDoc.field("lastName"));

            testSuite.setPerformedBy(user);
            testSuite.setRid(testSuiteDocument.field("rid").toString());
            testSuite.setTestSuiteDate(Validator.stringDateToLocalDateTime(
                    testSuiteDocument.field("testSuiteDate")));

            List<ODocument> testDocuments = testSuiteDocument.field("test");
            List<Test> tests = new ArrayList<>();
            Test test;
            for (ODocument testDocument : testDocuments) {
                test = new Test();
                test.setRid(testDocument.field("@rid").toString());
                test.setActualRBC(testDocument.field("actualRBC"));

                test.setImagePath(new File(Constant.IMAGES_DB_FOLDER
                        + testDocument.field("imagePath").toString()));
                test.setIsInfected(testDocument.field("isInfected"));
                test.setMcvActual(testDocument.field("mcvActual"));
                test.setRdwActual(testDocument.field("rdwActual"));

                test.setTestDate(Validator.stringDateToLocalDateTime(
                        testDocument.field(
                                "testDate.format('yyyy-MM-dd HH:mm:ss')")));

                ODocument testTypeDocument = testDocument.field("testType");
                TestType testType = new TestType(
                        testTypeDocument.field("@rid").toString(),
                        testTypeDocument.field("testType"));
                test.setTestType(testType);
                tests.add(test);
            }
            testSuite.setTest(tests);
            testSuites.add(testSuite);
        }
        return testSuites;
    }
}
