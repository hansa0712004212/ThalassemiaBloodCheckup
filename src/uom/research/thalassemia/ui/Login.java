/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.ui;

import uom.research.thalassemia.dao.LoginDAO;
import uom.research.thalassemia.dao.LoginDAOImpl;
import uom.research.thalassemia.util.Message;
import uom.research.thalassemia.object.User;
import uom.research.thalassemia.util.FillData;

/**
 *
 * @author hansa
 */
public final class Login extends javax.swing.JDialog {

    /**
     * Frame width.
     */
    private static final int FRAME_WIDTH = 450;
    /**
     * Frame height.
     */
    private static final int FRAME_HEIGHT = 200;

    /**
     * Creates new form Login.
     *
     * @param parent parent
     * @param modal modal
     */
    public Login(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        loadTypes();
    }

    /**
     * loadTypes.
     */
    private void loadTypes() {
        try {
            FillData.fillCombo(cmbTest, "SELECT FROM TestType ORDER BY "
                    + " @rid DESC", "testType");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        cmbTest = new javax.swing.JComboBox();
        btnExit = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        getContentPane().setLayout(new java.awt.GridLayout(4, 2, 10, 15));

        jLabel1.setText("     User Name");
        getContentPane().add(jLabel1);

        txtUserName.setText("hansa");
        getContentPane().add(txtUserName);

        jLabel2.setText("     Password");
        getContentPane().add(jLabel2);

        txtPassword.setText("4212");
        getContentPane().add(txtPassword);

        jLabel3.setText("     Action Type");
        getContentPane().add(jLabel3);
        getContentPane().add(cmbTest);

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/exit.png"))); // NOI18N
        btnExit.setText("  Exit");
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit);

        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/segment.png"))); // NOI18N
        btnLogin.setText("  Login");
        btnLogin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            LoginDAO login = new LoginDAOImpl();
            User user = login.authenticate(txtUserName.getText(),
                    new String(txtPassword.getPassword()));
            if (user != null) {
                this.dispose();
                switch (cmbTest.getSelectedItem().toString()) {
                    case "Home":
                        new HomeMain(user).setVisible(true);
                        break;
                    case "Blood Cell Image Analysis":
                        new PatientUI(null, true, user).setVisible(true);
                        break;
                    default:
                        Message.showErrorMessage("No Action Defined To Take");
                }
            } else {
                Message.showInformationMessage("Invalid User.");
            }
        } catch (Exception ex) {
            System.out.println("Login.java" + ex.getMessage());
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JComboBox cmbTest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables

}
