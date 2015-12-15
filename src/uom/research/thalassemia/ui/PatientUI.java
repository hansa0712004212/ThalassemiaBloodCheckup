/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;
import org.apache.commons.io.FileUtils;
import uom.research.thalassemia.dao.PatientDAOImpl;
import uom.research.thalassemia.object.ContactPerson;
import uom.research.thalassemia.object.Patient;
import uom.research.thalassemia.object.User;
import uom.research.thalassemia.util.Constant;
import uom.research.thalassemia.util.FillData;
import uom.research.thalassemia.util.ImageFileChooser;
import uom.research.thalassemia.util.Message;
import uom.research.thalassemia.util.StretchImage;
import uom.research.thalassemia.util.Validator;

/**
 *
 * @author hansa
 */
public final class PatientUI extends javax.swing.JDialog {

    /**
     * JDialog form width.
     */
    private static final int DIALOG_WIDTH = 800;

    /**
     * JDialog form height.
     */
    private static final int DIALOG_HEIGHT = 600;

    /**
     * default profile image file path.
     */
    private static final String DEFAULT_PHOTO = "me.jpg";

    /**
     * Map<String, Map<Integer, String>>.
     */
    private List<List<String>> list;

    /**
     * User object.
     */
    private final User user;

    /**
     * Patient object.
     */
    private Patient patient;

    /**
     * Patient's Contact Person object.
     */
    private ContactPerson contactPerson;

    /**
     * combo box as jtextField.
     */
    private JTextField textField;

    /**
     * Creates new form Patient.
     *
     * @param parent parent
     * @param modal modal
     * @param puser user object
     */
    public PatientUI(final java.awt.Frame parent, final boolean modal,
            final User puser) {
        super(parent, modal);
        initComponents();
        user = puser;
        setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        setLocationRelativeTo(null);
        loadLast20Patients();
        dtBirthDate.setDate(new Date());
        txtPhoto.setText(Constant.PROFILES_DB_FOLDER + DEFAULT_PHOTO);
        setPatientSearchListener();
    }

    /**
     * load patient data onto table.
     */
    private void loadLast20Patients() {
        try {
            list = FillData.fillTable(tblSearch,
                    "SELECT @rid.asString() as rid, title.append(firstName)"
                    + ".append(' ').append(lastName) as name, sex, "
                    + "city, address1.append(', ').append(address2)"
                    + ".append(', ').append(city) as address, mobile, "
                    + "imagePath, contactPerson.name as contactName, "
                    + "contactPerson.mobile as contactMobile "
                    + "FROM Patient WHERE isActive = true "
                    + "ORDER BY @rid desc limit 20");
        } catch (Exception ex) {
            System.out.println("unable to load data");
        }
    }

    /**
     * clear fields.
     */
    private void clearFields() {
        cmbTitle.setSelectedIndex(0);
        txtNIC.setText(null);
        txtFirstName.setText(null);
        txtMiddleName.setText(null);
        txtLastName.setText(null);
        txtAddress1.setText(null);
        txtAddress2.setText(null);
        txtMobile.setText(null);
        txtEmail.setText(null);
        txtCity.setText(null);
        txtContactName.setText(null);
        txtContactEmail.setText(null);
        txtContactMobile.setText(null);
        genderGroup.setSelected(radMale.getModel(), true);
        dtBirthDate.setDate(null);
        txtPhoto.setText(null);
        patient = null;
        contactPerson = null;
    }

    /**
     * check for empty fields.
     *
     * @return boolean boolean
     */
    private boolean hasEmptyFields() {
        patient = new Patient();
        if (txtNIC.getText().trim().isEmpty()) {
            if (!Validator.validateNIC(txtNIC.getText().trim())) {
                return true;
            }
            patient.setNic("");
        } else {
            patient.setNic(txtNIC.getText());
        }
        patient.setTitle(cmbTitle.getSelectedItem().toString());
        if (txtFirstName.getText().trim().isEmpty()) {
            return true;
        } else {
            patient.setFirstName(txtFirstName.getText());
        }
        if (txtMiddleName.getText().trim().isEmpty()) {
            return true;
        } else {
            patient.setMiddleName(txtMiddleName.getText());
        }
        if (txtLastName.getText().trim().isEmpty()) {
            return true;
        } else {
            patient.setLastName(txtLastName.getText());
        }
        if (dtBirthDate.getDate().toString().isEmpty()) {
            return true;
        } else {
            patient.setBirthDate(
                    LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd")
                            .format(dtBirthDate.getDate())));
        }
        if (radMale.isSelected()) {
            patient.setSex("Male");
        } else if (radFemale.isSelected()) {
            patient.setSex("Female");
        } else {
            return true;
        }
        if (txtAddress1.getText().isEmpty()) {
            return true;
        } else {
            patient.setAddress1(txtAddress1.getText());
        }
        patient.setAddress2(txtAddress2.getText());
        if (txtCity.getText().isEmpty()) {
            return true;
        } else {
            patient.setCity(txtCity.getText());
        }
        if (txtMobile.getText().isEmpty()) {
            return true;
        } else {
            patient.setMobile(Integer.valueOf(txtMobile.getText()));
        }
        patient.setEmail(txtEmail.getText());
        if (txtPhoto.getText().isEmpty()) {
            patient.setImagePath(DEFAULT_PHOTO);
        } else {
            File file = new File(txtPhoto.getText());
            if (file.exists()) {
                patient.setImagePath(file.getName());
            } else {
                return true;
            }
        }
        if (txtContactName.getText().isEmpty()) {
            return true;
        } else {
            contactPerson = new ContactPerson();
            contactPerson.setTitle("Mr. ");
            contactPerson.setName(txtContactName.getText());
        }
        if (txtContactMobile.getText().isEmpty()) {
            return true;
        } else {
            contactPerson.setMobile(
                    Integer.parseInt(txtContactMobile.getText()));
            contactPerson.setEmail("");
        }
        patient.setContactPerson(contactPerson);
        patient.setIsActive(true);
        return false;
    }

    /**
     * insert new patient to the database.
     */
    private void insertNewPatient() {
        if (!hasEmptyFields()) {
            try {
                PatientDAOImpl patientDAO = new PatientDAOImpl();
                String patientRid = patientDAO.insertPatient(patient);
                patient.setRid(patientRid);
                File src = new File(txtPhoto.getText());
                File dest = new File(Constant.PROFILES_DB_FOLDER
                        + patientRid.substring(4));
                FileUtils.copyFile(src, dest);
                int action = Message.showSuccessYesNoMessage(" New Patient's "
                        + "Data Saved. <br/>Continue With New Patient ? ");
                if (action == Message.YES_OPTION) {
                    dispose();
                    new HomeCellCountAnalyzer(user, patient).setVisible(true);
                } else {
                    clearFields();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            Message.showQuestionMessage("Have You Completed All Required "
                    + "Fields ?");
        }
    }

    /**
     * Patient search listener.
     */
    void setPatientSearchListener() {
        textField = (JTextField) cmbSearch.getEditor().getEditorComponent();
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent evt) {
                try {
                    String s = textField.getText();
                    //List<ODocument> rst = DatabaseAccess.selectData("select from Patient where firstName like '" + s + "%' and isActive=true");
                    FillData.fillCombo(cmbSearch,
                            "select from Patient where firstName like '" + s
                            + "%' and isActive=true", "firstName");
                    int rw = cmbSearch.getMaximumRowCount();
                    if (rw > 5) {
                        cmbSearch.setMaximumRowCount(5);
                    } else {
                        cmbSearch.setMaximumRowCount(rw);
                    }
                    if (s.isEmpty() || evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        cmbSearch.hidePopup();
                    } else {
                        cmbSearch.showPopup();
                    }
                    cmbSearch.setSelectedItem(s);

                } catch (Exception e) {
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderGroup = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        cmbSearch = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSearch = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        lblPhoto = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        ptxtAddress = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        ptxtMobile = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        ptxtContactName = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        ptxtContactMobile = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnContinue = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtNIC = new javax.swing.JTextField();
        cmbTitle = new javax.swing.JComboBox();
        txtFirstName = new javax.swing.JTextField();
        txtMiddleName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        dtBirthDate = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        radMale = new javax.swing.JRadioButton();
        radFemale = new javax.swing.JRadioButton();
        txtAddress1 = new javax.swing.JTextField();
        txtAddress2 = new javax.swing.JTextField();
        txtCity = new javax.swing.JTextField();
        txtMobile = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        txtPhoto = new javax.swing.JTextField();
        btnPhoto = new javax.swing.JButton();
        txtContactName = new javax.swing.JTextField();
        txtContactMobile = new javax.swing.JTextField();
        txtContactEmail = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Patient");
        getContentPane().setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "<html><body bgcolor='#58FAF4'><center><b>Search for Patient</b></center></body></html>", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel20.setText("Search Text");
        jPanel7.add(jLabel20);

        jPanel2.add(jPanel7);

        cmbSearch.setEditable(true);
        jPanel2.add(cmbSearch);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel21.setText("Recent 20 Patients");
        jPanel8.add(jLabel21);

        jPanel2.add(jPanel8);

        tblSearch.setAutoCreateRowSorter(true);
        tblSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rid", "Name", "Gender", "City"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSearchMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSearch);
        tblSearch.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblSearch.getColumnModel().getColumnCount() > 0) {
            tblSearch.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblSearch.getColumnModel().getColumn(2).setPreferredWidth(15);
        }

        jPanel2.add(jScrollPane2);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel18.setText("Selected Patient More Info");
        jPanel9.add(jLabel18);

        jPanel2.add(jPanel9);

        jPanel10.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        jPanel14.setLayout(new java.awt.BorderLayout());
        jPanel14.add(lblPhoto, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel14);

        jPanel11.setLayout(new java.awt.GridLayout(4, 2));

        jLabel19.setText("Address");
        jPanel11.add(jLabel19);

        ptxtAddress.setEditable(false);
        jPanel11.add(ptxtAddress);

        jLabel22.setText("Phone");
        jPanel11.add(jLabel22);

        ptxtMobile.setEditable(false);
        jPanel11.add(ptxtMobile);

        jLabel23.setText("Contact Person");
        jPanel11.add(jLabel23);

        ptxtContactName.setEditable(false);
        jPanel11.add(ptxtContactName);

        jLabel24.setText("Contacter Phone");
        jPanel11.add(jLabel24);

        ptxtContactMobile.setEditable(false);
        jPanel11.add(ptxtContactMobile);

        jPanel10.add(jPanel11);

        jPanel2.add(jPanel10);
        jPanel2.add(jPanel13);

        jPanel12.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        jButton1.setText("Quit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton1);

        btnContinue.setText("Continue");
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });
        jPanel12.add(btnContinue);

        jPanel2.add(jPanel12);

        getContentPane().add(jPanel2);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "<html><body bgcolor='#58FAF4'><center><b>   Create New Patient   </b></center></body></html>", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.TOP));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        jPanel3.setLayout(new java.awt.GridLayout(18, 1));

        jLabel1.setText("NIC");
        jPanel3.add(jLabel1);

        jLabel2.setText("Title");
        jPanel3.add(jLabel2);

        jLabel3.setText("First Name");
        jPanel3.add(jLabel3);

        jLabel4.setText("Middle Name");
        jPanel3.add(jLabel4);

        jLabel5.setText("Last Name");
        jPanel3.add(jLabel5);

        jLabel17.setText("Birth Date");
        jPanel3.add(jLabel17);

        jLabel6.setText("Gender");
        jPanel3.add(jLabel6);

        jLabel7.setText("Address 1");
        jPanel3.add(jLabel7);

        jLabel8.setText("Address 2");
        jPanel3.add(jLabel8);

        jLabel9.setText("City");
        jPanel3.add(jLabel9);

        jLabel10.setText("Phone");
        jPanel3.add(jLabel10);

        jLabel11.setText("Email");
        jPanel3.add(jLabel11);

        jLabel12.setText("Photo");
        jPanel3.add(jLabel12);

        jLabel13.setText("Contact Person Name");
        jPanel3.add(jLabel13);

        jLabel15.setText("Contact Person Mobile");
        jPanel3.add(jLabel15);

        jLabel25.setText("Contact Person Email");
        jPanel3.add(jLabel25);
        jPanel3.add(jLabel14);

        btnCancel.setText("Clear");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancel);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new java.awt.GridLayout(18, 1));

        txtNIC.setText("901563350V");
        jPanel4.add(txtNIC);

        cmbTitle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mr. ", "Mrs. ", "Miss. " }));
        jPanel4.add(cmbTitle);

        txtFirstName.setText("Saman");
        jPanel4.add(txtFirstName);

        txtMiddleName.setText("Nimal");
        jPanel4.add(txtMiddleName);

        txtLastName.setText("Sunil");
        jPanel4.add(txtLastName);

        dtBirthDate.setDateFormatString("yyyy-MM-dd");
        jPanel4.add(dtBirthDate);

        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        genderGroup.add(radMale);
        radMale.setSelected(true);
        radMale.setText("  Male");
        jPanel5.add(radMale);

        genderGroup.add(radFemale);
        radFemale.setText("  Female");
        jPanel5.add(radFemale);

        jPanel4.add(jPanel5);

        txtAddress1.setText("Somewhere");
        jPanel4.add(txtAddress1);

        txtAddress2.setText("Anywhere");
        jPanel4.add(txtAddress2);

        txtCity.setText("Galle");
        jPanel4.add(txtCity);

        txtMobile.setText("0710111000");
        jPanel4.add(txtMobile);

        txtEmail.setText("thmamaraseara@virtusa.com");
        jPanel4.add(txtEmail);

        jPanel6.setLayout(new java.awt.GridLayout(1, 2, 3, 0));

        txtPhoto.setEditable(false);
        jPanel6.add(txtPhoto);

        btnPhoto.setText("..");
        btnPhoto.setMaximumSize(new java.awt.Dimension(20, 15));
        btnPhoto.setMinimumSize(new java.awt.Dimension(20, 15));
        btnPhoto.setPreferredSize(new java.awt.Dimension(20, 15));
        btnPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhotoActionPerformed(evt);
            }
        });
        jPanel6.add(btnPhoto);

        jPanel4.add(jPanel6);

        txtContactName.setText("Hansa");
        jPanel4.add(txtContactName);

        txtContactMobile.setText("0712004212");
        jPanel4.add(txtContactMobile);

        txtContactEmail.setText("hansa0712004212@gmail.com");
        jPanel4.add(txtContactEmail);
        jPanel4.add(jLabel16);

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        jPanel4.add(btnSubmit);

        jPanel1.add(jPanel4);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        int action = Message.showQuestionYesNoCancel(" Discard Changes ? ");
        if (action == Message.YES_OPTION) {
            clearFields();
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSearchMouseClicked
        int index = tblSearch.getSelectedRow();
        ptxtAddress.setText(list.get(index).get(4));
        ptxtMobile.setText(list.get(index).get(5));
        ptxtContactName.setText(list.get(index).get(7));
        StretchImage.setImageStretch(lblPhoto,
                Constant.PROFILES_DB_FOLDER + list.get(index).get(6));
        //lblPhoto.setIcon(new ImageIcon("/home/hansa/Pictures/"
        //      + list.get(index).get(6)));
        ptxtContactMobile.setText(list.get(index).get(8));
    }//GEN-LAST:event_tblSearchMouseClicked

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        if (tblSearch.getSelectedRowCount() > 0) {
            int index = tblSearch.getSelectedRow();
            patient = new Patient();
            patient.setRid(list.get(index).get(0));
            patient.setFirstName(list.get(index).get(1));
            patient.setSex(list.get(index).get(2));
            patient.setCity(list.get(index).get(3));
            patient.setImagePath(list.get(index).get(6));
            patient.setMobile(Integer.valueOf(list.get(index).get(5)));
            patient.setContactPerson(
                    new ContactPerson(
                            "Mr. ", list.get(index).get(7), null,
                            Integer.valueOf(list.get(index).get(8))));
            new HomeCellCountAnalyzer(user, patient).setVisible(true);
            PatientUI.this.dispose();
        } else {
            Message.showQuestionMessage("Have You Selected a Patient ?");
        }
    }//GEN-LAST:event_btnContinueActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        insertNewPatient();
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (Message.showQuestionYesNo(" Quit Now ? ") == Message.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhotoActionPerformed
        File imageFile = ImageFileChooser.openImageFile(PatientUI.this);
        if (imageFile != null) {
            txtPhoto.setText(imageFile.getAbsolutePath());
        }
    }//GEN-LAST:event_btnPhotoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnPhoto;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox cmbSearch;
    private javax.swing.JComboBox cmbTitle;
    private com.toedter.calendar.JDateChooser dtBirthDate;
    private javax.swing.ButtonGroup genderGroup;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JTextField ptxtAddress;
    private javax.swing.JTextField ptxtContactMobile;
    private javax.swing.JTextField ptxtContactName;
    private javax.swing.JTextField ptxtMobile;
    private javax.swing.JRadioButton radFemale;
    private javax.swing.JRadioButton radMale;
    private javax.swing.JTable tblSearch;
    private javax.swing.JTextField txtAddress1;
    private javax.swing.JTextField txtAddress2;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtContactEmail;
    private javax.swing.JTextField txtContactMobile;
    private javax.swing.JTextField txtContactName;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMiddleName;
    private javax.swing.JTextField txtMobile;
    private javax.swing.JTextField txtNIC;
    private javax.swing.JTextField txtPhoto;
    // End of variables declaration//GEN-END:variables
}
