/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.db;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.ORecord;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import java.sql.SQLException;
import java.util.List;

/**
 * DatabaseAccess.
 *
 * @author hansa
 */
public final class DatabaseAccess {

    /**
     * DatabaseAccess.
     *
     * @throws SQLException SQLException
     */
    private DatabaseAccess() throws SQLException {

    }

    /**
     * insert Data.
     *
     * @param query sql query
     * @return String
     * @throws Exception exception
     */
    public static String insertData(final String query)
            throws Exception {
        ODatabaseDocumentTx db = DatabaseConnect.getInstance();
        ODocument execute = db.command(new OCommandSQL(query)).execute();
        ORecord or = execute.getRecord();
        return or.getIdentity().toString();
    }

    /**
     * select Data.
     *
     * @param query sql query
     * @return ResultSet
     * @throws Exception exception
     */
    public static List selectData(final String query)
            throws Exception {
        ODatabaseDocumentTx db = DatabaseConnect.getInstance();
        List<ODocument> result = db.query(new OSQLSynchQuery<>(query));
        return result;
    }

    /**
     * update Data.
     *
     * @param query sql query
     * @return String
     * @throws Exception exception
     */
    public static boolean updateData(final String query)
            throws Exception {
        ODatabaseDocumentTx db = DatabaseConnect.getInstance();
        db.command(new OCommandSQL(query)).execute();
        return true;
    }
}
