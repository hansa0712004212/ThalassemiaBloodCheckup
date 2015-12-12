/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.dao;

import com.orientechnologies.orient.core.record.impl.ODocument;
import java.text.SimpleDateFormat;
import uom.research.thalassemia.object.User;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import uom.research.thalassemia.db.DatabaseAccess;

/**
 * LoginDAOImpl.
 *
 * @author hansa
 */
public final class LoginDAOImpl implements LoginDAO {

    /**
     * Simple Date Formatter.
     */
    private static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * authenticate user.
     *
     * @param name name
     * @param password password
     *
     * @return boolean
     * @throws Exception Exception
     */
    @Override
    public User authenticate(final String name, final String password)
            throws Exception {
        List<ODocument> result = DatabaseAccess.selectData(
                "SELECT @rid.asString() as rid, username, userRole.role as role, "
                + "firstName, lastName, email, mobile, assignedDate.asLong() "
                + "FROM User WHERE username='" + name + "' AND password='"
                + password + "'");
        if (result.size() > 0) {
            ODocument document = result.get(0);
            String[] columnNames = document.fieldNames();
            long longDate = Long.valueOf(document.field(
                    columnNames[7]).toString());
            User user = new User(document.field(columnNames[0]).toString(),
                    document.field(columnNames[1]).toString(),
                    document.field(columnNames[2]).toString(),
                    document.field(columnNames[3]).toString(),
                    document.field(columnNames[4]).toString(),
                    document.field(columnNames[5]).toString(),
                    Integer.valueOf(document.field(columnNames[6]).toString()),
                    LocalDate.parse(DATE_FORMAT.format(new Date(longDate))),
                    null, null);
            return user;
        }
        return null;
    }
}
