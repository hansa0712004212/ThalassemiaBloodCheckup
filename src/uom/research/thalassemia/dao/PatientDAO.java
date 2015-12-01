/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.dao;

import uom.research.thalassemia.object.Patient;

/**
 *
 * @author hansa
 */
public interface PatientDAO {

    /**
     * insert patient into database.
     *
     * @param patient patient object
     * @return inserted patient rid
     * @throws Exception exception
     */
    String insertPatient(final Patient patient) throws Exception;

}
