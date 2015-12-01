/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import uom.research.thalassemia.db.DatabaseAccess;
import uom.research.thalassemia.object.Patient;

/**
 * Patient DAO Implementation.
 *
 * @author hansa
 */
public final class PatientDAOImpl {

    /**
     * Simple date formatter.
     */
    private static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Contact person document prefix.
     */
    private static final String CONTACT_PERSON_DOCUMENT_PREFIX
            = "{\"@type\":\"d\",\"@version\":0,\"@class\":\"ContactPerson\","
            + "\"title\":\"Mr. \",";

    /**
     * Contact person document postfix.
     */
    private static final String CONTACT_PERSON_DOCUMENT_POSTFIX
            = "}";

    /**
     * v
     * insert patient into database.
     *
     * @param patient patient object
     * @return inserted patient rid
     * @throws Exception exception
     */
    public String insertPatient(final Patient patient)
            throws Exception {
        String contactPerson = CONTACT_PERSON_DOCUMENT_PREFIX
                + " \"name\":\"" + patient.getContactPerson().getName() + "\","
                + " \"mobile\":\"" + patient.getContactPerson().getMobile()
                + "\","
                + " \"email\":\"" + patient.getContactPerson().getEmail()
                + "\""
                + CONTACT_PERSON_DOCUMENT_POSTFIX;
        String patientQuery = "INSERT INTO Patient SET "
                + "title = '" + patient.getTitle() + "', "
                + "nic = '" + patient.getNic() + "', "
                + "firstName = '" + patient.getFirstName() + "', "
                + "middleName = '" + patient.getMiddleName() + "', "
                + "lastName = '" + patient.getLastName() + "', "
                + "birthDate = '" + patient.getBirthDate() + "', "
                + "sex = '" + patient.getSex() + "', "
                + "address1 = '" + patient.getAddress1() + "', "
                + "address2 = '" + patient.getAddress2() + "', "
                + "city = '" + patient.getCity() + "', "
                + "email = '" + patient.getEmail() + "', "
                + "mobile = '" + patient.getMobile() + "', "
                + "imagePath = '" + patient.getImagePath() + "', "
                + "registeredDate = '" + DATE_FORMAT.format(new Date()) + "', "
                + "contactPerson = " + contactPerson + ", "
                + "isActive = '" + patient.getIsActive() + "', "
                + "password = '" + patient.getBirthDate().toString()
                .replaceAll("-", "") + "'";

        return DatabaseAccess.insertData(patientQuery);
    }

}
