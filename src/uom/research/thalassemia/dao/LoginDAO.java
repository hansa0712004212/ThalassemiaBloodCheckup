/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.dao;

import uom.research.thalassemia.object.User;

/**
 *
 * @author hansa
 */
public interface LoginDAO {

    /**
     * authenticate user.
     *
     * @param name name
     * @param password password
     *
     * @return boolean
     * @throws Exception Exception
     */
    User authenticate(final String name, final String password)
            throws Exception;
}
