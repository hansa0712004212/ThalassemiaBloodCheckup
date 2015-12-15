/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.db;

import com.orientechnologies.orient.client.remote.OServerAdmin;
import com.orientechnologies.orient.core.command.OCommandRequest;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import java.io.File;
import java.util.Scanner;
import java.util.Set;

/**
 * orient db connector.
 *
 * @author hansa
 */
public final class DatabaseConnect {

    /**
     * instance.
     */
    private static ODatabaseDocumentTx instance;

    /**
     * user name.
     */
    private static final String USER_NAME = "hansa";

    /**
     * user password.
     */
    private static final String USER_PASSWORD = "4212";

    /**
     * db name.
     */
    private static final String DB_NAME = "Thalassemia";

    /**
     * getInstance.
     *
     * @return Connection
     * @throws Exception exception
     */
    public static synchronized ODatabaseDocumentTx getInstance()
            throws Exception {
        instance = new ODatabaseDocumentTx("remote:localhost/" + DB_NAME)
                .open(USER_NAME, USER_PASSWORD);

        //checkDBExists();
        return instance;
    }

    /**
     * private DatabaseConnect constructor.
     */
    private DatabaseConnect() {
    }

    /**
     * check db exists. if not exists, create db and tables
     *
     * @throws Exception exception
     */
    public static void checkDBExists() throws Exception {
        OServerAdmin admin = new OServerAdmin("remote:localhost");
        ODatabaseDocumentTx db = DatabaseConnect.getInstance();
        OCommandRequest command;
        admin.connect(USER_NAME, USER_PASSWORD);
        Set<String> dbNames = admin.listDatabases().keySet();
        if (dbNames.contains("Thalassemia")) {
            File file = new File("ThalassemiaDB.sql");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String query = scanner.nextLine();
                if (query.length() != 0) {
                    command = db.command(new OCommandSQL(query));
                    command.execute();
                    db.getMetadata().getSchema().reload();
                    System.out.println("Executed --> " + query);
                }
            }
        }
    }
}
