/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.util;

import com.orientechnologies.orient.core.record.impl.ODocument;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import uom.research.thalassemia.db.DatabaseAccess;

/**
 *
 * @author HansA AmarasekarA
 */
public final class FillData {

    /**
     * rid.
     */
    private static List<List<String>> rid;

    /**
     * data.
     */
    private static List<String> data;

    /**
     * private constructor.
     */
    private FillData() {

    }

    /**
     * empty table.
     *
     * @param table jTable
     */
    public static void doEmptyTable(final JTable table) {
        DefaultTableModel defaultTableModel
                = (DefaultTableModel) table.getModel();
        int row = defaultTableModel.getRowCount();
        for (int i = 0; i < row; i++) {
            defaultTableModel.removeRow(0);
        }
    }

    /**
     * fill combo boxes.
     *
     * @param combo jComboBox
     * @param query query string
     * @param columnName column name
     * @throws Exception exception
     */
    public static void fillCombo(final JComboBox combo, final String query,
            final String columnName) throws Exception {
        List<ODocument> results = DatabaseAccess.selectData(query);
        combo.removeAllItems();
        results.stream().forEach((result) -> {
            combo.addItem(result.field(columnName));
        });
    }

    /**
     * fill Lists.
     *
     * @param jList jListBox
     * @param query query string
     * @param columnName columnName
     * @throws Exception exception
     */
    public static void fillList(final JList jList, final String query,
            final String columnName) throws Exception {
        jList.removeAll();
        List<ODocument> results = DatabaseAccess.selectData(query);
        Vector<String> vector = new Vector<>(results.size());
        results.stream().forEach((result) -> {
            vector.add(result.field(columnName));
        });
        jList.setListData(vector);
    }

    /**
     * Fill jTable.
     *
     * @param table jTable
     * @param query query
     * @return List
     * @throws Exception Exception
     */
    public static List<List<String>> fillTable(final JTable table,
            final String query) throws Exception {
        doEmptyTable(table);
        DefaultTableModel defaultTableModel
                = (DefaultTableModel) table.getModel();
        int row = defaultTableModel.getRowCount();

        for (int i = 0; i < row; i++) {
            defaultTableModel.removeRow(0);
        }

        List<ODocument> results = DatabaseAccess.selectData(query);

        String[] columnNames = results.get(0).fieldNames();
        int noOfColumns = columnNames.length;
        Object[] ob = new Object[noOfColumns];
        rid = new ArrayList<>();
        int index;
        for (ODocument result : results) {
            data = new ArrayList<>();
            index = 0;
            for (String columnName : columnNames) {
                try {
                    ob[index] = result.field(columnName).toString();
                    data.add(ob[index].toString());
                } catch (Exception ex) {
                    ob[index] = null;
                    data.add(null);
                }
                index++;
            }
            rid.add(data);
            defaultTableModel.addRow(ob);
        }
        return rid;
    }
}
