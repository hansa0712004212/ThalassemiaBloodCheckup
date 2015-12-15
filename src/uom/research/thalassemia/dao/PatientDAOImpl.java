/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.dao;

import com.orientechnologies.orient.core.record.impl.ODocument;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import uom.research.thalassemia.db.DatabaseAccess;
import uom.research.thalassemia.object.ContactPerson;
import uom.research.thalassemia.object.Patient;
import uom.research.thalassemia.util.Message;
import uom.research.thalassemia.util.Validator;

/**
 * Patient DAO Implementation.
 *
 * @author hansa
 */
public final class PatientDAOImpl implements PatientDAO {

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
     * insert patient into database.
     *
     * @param patient patient object
     * @return inserted patient rid
     * @throws Exception exception
     */
    @Override
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

    /**
     * load patient onto application.
     *
     * @param text patient rid
     * @return Patient object
     * @throws Exception exception
     */
    @Override
    public Patient selectPatient(final String text) throws Exception {
        String rid = text.substring(0, text.indexOf(" "));
        if (rid.startsWith("#")) {
            List<ODocument> list
                    = DatabaseAccess.selectData(
                            "SELECT @rid.asString() as rid, title, firstName,"
                            + "middleName, lastName, sex, city, address1, "
                            + "address2, mobile, imagePath, "
                            + "birthDate.format('yyyy-MM-dd'),"
                            + "contactPerson.name as contactName, "
                            + "contactPerson.mobile as contactMobile "
                            + "FROM Patient WHERE @rid=" + rid);
            Patient patient = new Patient();
            patient.setRid(list.get(0).field("rid"));
            patient.setTitle(list.get(0).field("title"));
            patient.setFirstName(list.get(0).field("firstName"));
            patient.setMiddleName(list.get(0).field("middleName"));
            patient.setLastName(list.get(0).field("lastName"));
            patient.setSex(list.get(0).field("sex"));
            patient.setCity(list.get(0).field("city"));
            patient.setBirthDate(Validator.stringDateToLocalDate(
                    list.get(0).field("birthDate")));
            patient.setAddress1(list.get(0).field("address1"));
            patient.setAddress2(list.get(0).field("address2"));
            patient.setMobile(list.get(0).field("mobile"));
            patient.setImagePath(list.get(0).field("imagePath"));
            ContactPerson contactPerson = new ContactPerson();
            contactPerson.setName(list.get(0).field("contactName"));
            contactPerson.setMobile(list.get(0).field("contactMobile"));
            patient.setContactPerson(contactPerson);
            return patient;
        } else {
            Message.showInformationMessage("Please Select A Patient");
        }
        return null;
    }

}
