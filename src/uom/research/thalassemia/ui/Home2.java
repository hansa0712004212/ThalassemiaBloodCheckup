/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.ui;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import uom.research.thalassemia.dao.TestDAO;
import uom.research.thalassemia.dao.TestDAOImpl;
import uom.research.thalassemia.dao.TestSuiteDAO;
import uom.research.thalassemia.dao.TestSuiteDAOImpl;
import uom.research.thalassemia.logic.BloodCellsManipulation;
import uom.research.thalassemia.logic.BloodCellsManipulationImpl;
import uom.research.thalassemia.logic.ImageSegment;
import uom.research.thalassemia.object.Circle;
import uom.research.thalassemia.object.Patient;
import uom.research.thalassemia.object.Test;
import uom.research.thalassemia.object.TestSuite;
import uom.research.thalassemia.object.User;
import uom.research.thalassemia.util.ImageFileChooser;
import uom.research.thalassemia.util.Message;
import uom.research.thalassemia.util.StretchImage;

/**
 *
 * @author hansa
 */
public final class Home2 extends javax.swing.JFrame {

    /**
     * Selected Image File .
     */
    private File imageFile;

    /**
     * Thread to be executed.
     */
    private Thread thread;

    /**
     * Progress bar to indicate thread is busy.
     */
    private Progress progress;

    /**
     * BloodCellsManipulation.
     */
    private BloodCellsManipulation bcm = null;

    /**
     * User object.
     */
    private final User user;

    /**
     * Patient object.
     */
    private final Patient patient;

    /**
     * Keeps current working JFrame.
     */
    private final JFrame jFrame;

    /**
     * simple date time formatter. yyyy-MM-dd HH:mm:ss
     */
    private static final SimpleDateFormat DATE_TIME_FORMAT
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * DateTime keeper.
     */
    private Date date;

    /**
     * One Second In MiliSeconds.
     */
    private static final int ONE_S_IN_MS = 1000;

    /**
     * Test Suite.
     */
    private TestSuite testSuite = null;

    /**
     * Test Circles.
     */
    private List<Circle> circlesList;

    /**
     * pi constant.
     */
    private final double fi = 22.0 / 7.0;

    /**
     * Creates new form Home.
     *
     * @param puser user
     * @param ppatient patient
     */
    public Home2(final User puser, final Patient ppatient) {
        initComponents();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Thalassemia Affected Blood Sample Analyzer v1.0");
        setLocationRelativeTo(null);
        setResizable(false);
        btnProcess.setEnabled(false);
        btnSegmentImage.setEnabled(false);
        mnuProcess.setEnabled(false);
        mnuSegment.setEnabled(false);
        btnCellData.setEnabled(false);
        jButton1.setEnabled(false);
        user = puser;
        patient = ppatient;
        jFrame = Home2.this;
        setToolBarData();
    }

    /**
     * Select and Open Image File.
     */
    private void selectImageFile() {
        File prevFile = imageFile;
        imageFile = ImageFileChooser.openImageFile(this);
        if (imageFile != null) {
            new Thread(() -> {
                try {
                    SwingUtilities.invokeLater(() -> {
                        progress = new Progress(jFrame, true);
                        progress.setVisible(true);
                    });
                    if (imageFile.isFile()) {
                        lblImagePath.setText("Path :- "
                                + imageFile.getAbsolutePath());
                        StretchImage.setImageStretch(lblDisplayImage,
                                imageFile.getAbsolutePath());
                        BufferedImage bufferedImage
                                = ImageIO.read(imageFile);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Original Image Resolution "
                                + "(Width x Height) : ")
                                .append(bufferedImage.getWidth())
                                .append(" x ")
                                .append(bufferedImage.getHeight());
                        lblResolution.setText(stringBuilder.toString());
                        btnProcess.setEnabled(true);
                        btnSegmentImage.setEnabled(true);
                        mnuProcess.setEnabled(true);
                        mnuSegment.setEnabled(true);
                    }
                } catch (Exception e) {
                    System.out.println("btnOpenImage " + e.getMessage()
                            + " | " + imageFile.getAbsolutePath());
                }
                progress.dispose();
            }).start();
        } else {
            if (prevFile != null) {
                imageFile = prevFile;
            }
        }
    }

    /**
     * Do Image Segmentation and Open Up Appropriate Window.
     */
    private void createSegmentsOfImageFile() {
        try {
            if (imageFile.isFile()) {
                new Thread(new ImageSegment(this, imageFile)).start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Do Image Processing and Find Blood Cells.
     */
    private void processImageFile() {
        /**
         * process running on another thread to avoid UI getting stuck
         */
        thread = new Thread(() -> {
            try {
                SwingUtilities.invokeLater(() -> {
                    progress = new Progress(jFrame, true);
                    progress.setVisible(true);
                });
                Long start = new Date().getTime();
                bcm = new BloodCellsManipulationImpl(imageFile);
                bcm.doBloodCellProcessing();
                StretchImage.setImageStretch(lblDisplayImageGray,
                        new ImageIcon(bcm.getGrayImage()));
                StretchImage.setImageStretch(lblDisplayImageSmooth,
                        new ImageIcon(bcm.getThresholdImage()));
                StretchImage.setImageStretch(lblDisplayImageCanny,
                        new ImageIcon(bcm.getCannyImage()));
                StretchImage.setImageStretch(lblDisplayImageProcessed,
                        new ImageIcon(bcm.getRedImage()));
                lblCountCells.setText(String.valueOf(bcm.getCircleCount()));
                lblMaximumRadius.setText(
                        String.valueOf(bcm.getMaximumRadius()));
                lblMinimumRadius.setText(
                        String.valueOf(bcm.getMinimumRadius()));
                Long end = new Date().getTime();
                lblTimeElapsed.setText(
                        String.valueOf(new Date(end - start).getTime()));
                progress.dispose();
                btnCellData.setEnabled(true);
                Thread.currentThread().interrupt();
            } catch (Exception ex) {
                progress.dispose();
                Message.showErrorMessage(" error is " + ex);
            }
        });
        if (!thread.isAlive()) {
            thread.start();
        }
    }

    /**
     * view user's and patient's details in tool bar.
     */
    private void setToolBarData() {
        if (user != null) {
            lblUser.setText(user.getFirstName() + " " + user.getLastName()
                    + "  ");
        }
        if (patient != null) {
            lblPatient.setText(patient.getFirstName() + "  |  "
                    + patient.getSex() + "  |  "
                    + patient.getMobile());
        }
        setClock();
    }

    /**
     * run clock.
     */
    private void setClock() {
        Timer t = new Timer(ONE_S_IN_MS, (ActionEvent evt) -> {
            date = new Date();
            lblDateAndTime.setText("  " + DATE_TIME_FORMAT.format(date) + "  ");
        });
        t.start();
    }

    /**
     * save Test.
     */
    private void saveTest() {

        if (testSuite == null) {
            try {
                testSuite = new TestSuite(null, patient, null, user);
                TestSuiteDAO testSuiteDAO = new TestSuiteDAOImpl();
                testSuite.setRid(testSuiteDAO.saveTestSuite(testSuite));
            } catch (Exception ex) {
                Message.showErrorMessage("Error on Saving Test Suite."
                        + ex.getMessage());
                return;
            }
        }
        try {
            Test test = new Test();
            test.setImagePath("somewhere");
            test.setIsInfected(false);
            test.setTestDate(LocalDate.now());
            test.setTestType(null);
            convertCirclesMapToCirclesList();
            test.setCircles(circlesList);

            TestDAO testDAO = new TestDAOImpl();
            testDAO.saveTest(testSuite, test);
        } catch (Exception ex) {
            Message.showErrorMessage("Error on Saveing Test Data "
                    + ex.getMessage());
        }
    }

    /**
     * convert Circles Map To CirclesList.
     */
    private void convertCirclesMapToCirclesList() {
        Mat circles = bcm.getCircles();
        double[] circle;
        double x, y, r, area, perimeter;
        for (int a = 0; a < circles.cols(); a++) {
            circle = circles.get(0, a);
            x = circle[0];
            y = circle[1];
            r = Math.round(circle[2]);
            area = fi * r * r;
            perimeter = 2 * fi * r;
            circlesList.add(new Circle((int) x, (int) y, (int) r,
                    perimeter, area));
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

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        lblImagePath = new javax.swing.JLabel();
        lblResolution = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        lblDisplayImage = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lblDisplayImageGray = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lblDisplayImageSmooth = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lblDisplayImageCanny = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btnOpenImage = new javax.swing.JButton();
        btnSegmentImage = new javax.swing.JButton();
        btnProcess = new javax.swing.JButton();
        btnCellData = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblCountCells = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMaximumRadius = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblMinimumRadius = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTimeElapsed = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnSaveTest = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lblDisplayImageProcessed = new javax.swing.JLabel();
        jtoolBar = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jLabel7 = new javax.swing.JLabel();
        lblPatient = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        chkAlwaysOnTop = new javax.swing.JCheckBox();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        lblDateAndTime = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuOpen = new javax.swing.JMenuItem();
        mnuReset = new javax.swing.JMenuItem();
        mnuExit = new javax.swing.JMenuItem();
        mnuEdit = new javax.swing.JMenu();
        mnuSegment = new javax.swing.JMenuItem();
        mnuProcess = new javax.swing.JMenuItem();
        mnuHelp = new javax.swing.JMenu();
        mnuHelpContent = new javax.swing.JMenuItem();
        mnuGit = new javax.swing.JMenuItem();
        mnuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 720));

        mainPanel.setLayout(new java.awt.GridLayout(2, 4, 5, 5));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 11)); // NOI18N
        jLabel6.setText(" Original Image Stretched Preview");
        jPanel1.add(jLabel6, java.awt.BorderLayout.PAGE_START);

        jPanel15.setLayout(new java.awt.GridLayout(2, 1));

        lblImagePath.setText("Path : ");
        jPanel15.add(lblImagePath);

        lblResolution.setText("Resolution : ");
        jPanel15.add(lblResolution);

        jPanel1.add(jPanel15, java.awt.BorderLayout.PAGE_END);

        jPanel16.setLayout(new java.awt.BorderLayout());
        jPanel16.add(lblDisplayImage, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel16, java.awt.BorderLayout.CENTER);

        mainPanel.add(jPanel1);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 1, 11)); // NOI18N
        jLabel8.setText("Gray Scaled Preview");
        jPanel2.add(jLabel8);

        jLabel9.setText("                              ");
        jPanel2.add(jLabel9);

        jPanel11.setLayout(new java.awt.GridLayout(1, 1));

        lblDisplayImageGray.setBackground(java.awt.SystemColor.controlLtHighlight);
        lblDisplayImageGray.setText(" ");
        lblDisplayImageGray.setOpaque(true);
        jPanel11.add(lblDisplayImageGray);

        jPanel2.add(jPanel11);

        mainPanel.add(jPanel2);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 11)); // NOI18N
        jLabel10.setText("Threshold Preview");
        jPanel3.add(jLabel10);

        jLabel11.setText("                              ");
        jPanel3.add(jLabel11);

        jPanel12.setLayout(new java.awt.GridLayout(1, 1));

        lblDisplayImageSmooth.setBackground(java.awt.SystemColor.controlLtHighlight);
        lblDisplayImageSmooth.setText(" ");
        lblDisplayImageSmooth.setOpaque(true);
        jPanel12.add(lblDisplayImageSmooth);

        jPanel3.add(jPanel12);

        mainPanel.add(jPanel3);

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 11)); // NOI18N
        jLabel12.setText("Edge Detection Preview");
        jPanel4.add(jLabel12);

        jLabel13.setText("                              ");
        jPanel4.add(jLabel13);

        jPanel13.setLayout(new java.awt.GridLayout(1, 1));

        lblDisplayImageCanny.setBackground(java.awt.SystemColor.controlLtHighlight);
        lblDisplayImageCanny.setText(" ");
        lblDisplayImageCanny.setOpaque(true);
        jPanel13.add(lblDisplayImageCanny);

        jPanel4.add(jPanel13);

        mainPanel.add(jPanel4);

        jPanel5.setLayout(new java.awt.GridLayout(2, 1, 5, 5));

        jPanel9.setLayout(new java.awt.GridLayout(4, 1));

        btnOpenImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/open.png"))); // NOI18N
        btnOpenImage.setText("Open Image");
        btnOpenImage.setIconTextGap(15);
        btnOpenImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenImageActionPerformed(evt);
            }
        });
        jPanel9.add(btnOpenImage);

        btnSegmentImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/segment.png"))); // NOI18N
        btnSegmentImage.setText("Segment Image");
        btnSegmentImage.setIconTextGap(10);
        btnSegmentImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSegmentImageActionPerformed(evt);
            }
        });
        jPanel9.add(btnSegmentImage);

        btnProcess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/process.png"))); // NOI18N
        btnProcess.setText("Process");
        btnProcess.setIconTextGap(20);
        btnProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessActionPerformed(evt);
            }
        });
        jPanel9.add(btnProcess);

        btnCellData.setText("Blood Cell Data");
        btnCellData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCellDataActionPerformed(evt);
            }
        });
        jPanel9.add(btnCellData);

        jPanel5.add(jPanel9);

        jPanel10.setLayout(new java.awt.GridLayout(5, 2, 2, 5));

        jLabel2.setText("  Cells Count  ");
        jPanel10.add(jLabel2);
        jPanel10.add(lblCountCells);

        jLabel3.setText("  Maximum Radius");
        jPanel10.add(jLabel3);
        jPanel10.add(lblMaximumRadius);

        jLabel4.setText("  Minimum Radius  ");
        jPanel10.add(jLabel4);
        jPanel10.add(lblMinimumRadius);

        jLabel5.setText("  Time Elapsed  ");
        jPanel10.add(jLabel5);
        jPanel10.add(lblTimeElapsed);

        jButton1.setText("<html><center>Load Previous<br/>Test Suite</center></html>");
        jPanel10.add(jButton1);

        btnSaveTest.setText("Save Test");
        btnSaveTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveTestActionPerformed(evt);
            }
        });
        jPanel10.add(btnSaveTest);

        jPanel5.add(jPanel10);

        mainPanel.add(jPanel5);

        jPanel6.setBackground(java.awt.SystemColor.activeCaptionBorder);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 331, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 445, Short.MAX_VALUE)
        );

        mainPanel.add(jPanel6);

        jPanel7.setBackground(java.awt.SystemColor.activeCaptionBorder);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 331, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 445, Short.MAX_VALUE)
        );

        mainPanel.add(jPanel7);

        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 1, 11)); // NOI18N
        jLabel14.setText("Identified Blood Cells Preview");
        jPanel8.add(jLabel14);

        jLabel15.setText("                              ");
        jPanel8.add(jLabel15);

        jPanel14.setLayout(new java.awt.GridLayout(1, 1));

        lblDisplayImageProcessed.setBackground(java.awt.SystemColor.controlLtHighlight);
        lblDisplayImageProcessed.setText(" ");
        lblDisplayImageProcessed.setOpaque(true);
        jPanel14.add(lblDisplayImageProcessed);

        jPanel8.add(jPanel14);

        mainPanel.add(jPanel8);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        jtoolBar.setRollover(true);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 11)); // NOI18N
        jLabel1.setText("  User - ");
        jtoolBar.add(jLabel1);

        lblUser.setText("User  ");
        jtoolBar.add(lblUser);
        jtoolBar.add(jSeparator1);

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 11)); // NOI18N
        jLabel7.setText("  Patient - ");
        jtoolBar.add(jLabel7);

        lblPatient.setText("Patient  ");
        jtoolBar.add(lblPatient);
        jtoolBar.add(jSeparator2);

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 1, 11)); // NOI18N
        jLabel16.setText("  Test Suite ID - ");
        jtoolBar.add(jLabel16);

        jLabel17.setText("unsaved  ");
        jtoolBar.add(jLabel17);
        jtoolBar.add(jSeparator4);

        chkAlwaysOnTop.setText("  Always On Top  ");
        chkAlwaysOnTop.setFocusable(false);
        chkAlwaysOnTop.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAlwaysOnTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAlwaysOnTopActionPerformed(evt);
            }
        });
        jtoolBar.add(chkAlwaysOnTop);
        jtoolBar.add(jSeparator3);

        lblDateAndTime.setFont(new java.awt.Font("Helvetica Neue", 1, 11)); // NOI18N
        lblDateAndTime.setText("System Date And Time");
        jtoolBar.add(lblDateAndTime);

        getContentPane().add(jtoolBar, java.awt.BorderLayout.SOUTH);

        mnuFile.setText("  File        ");

        mnuOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mnuOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/open.png"))); // NOI18N
        mnuOpen.setText("  Open ...     ");
        mnuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuOpenActionPerformed(evt);
            }
        });
        mnuFile.add(mnuOpen);

        mnuReset.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        mnuReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/reset.png"))); // NOI18N
        mnuReset.setText("  Reset     ");
        mnuReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuResetActionPerformed(evt);
            }
        });
        mnuFile.add(mnuReset);

        mnuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        mnuExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/exit.png"))); // NOI18N
        mnuExit.setText("  Exit     ");
        mnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });
        mnuFile.add(mnuExit);

        menuBar.add(mnuFile);

        mnuEdit.setText("  Edit        ");

        mnuSegment.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        mnuSegment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/segment.png"))); // NOI18N
        mnuSegment.setText("  Segment Image     ");
        mnuSegment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSegmentActionPerformed(evt);
            }
        });
        mnuEdit.add(mnuSegment);

        mnuProcess.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        mnuProcess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/process.png"))); // NOI18N
        mnuProcess.setText("  Process     ");
        mnuProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuProcessActionPerformed(evt);
            }
        });
        mnuEdit.add(mnuProcess);

        menuBar.add(mnuEdit);

        mnuHelp.setText("  Help        ");

        mnuHelpContent.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        mnuHelpContent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/help.png"))); // NOI18N
        mnuHelpContent.setText("  Help Content     ");
        mnuHelpContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHelpContentActionPerformed(evt);
            }
        });
        mnuHelp.add(mnuHelpContent);

        mnuGit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        mnuGit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/git.png"))); // NOI18N
        mnuGit.setText("  Git Repository     ");
        mnuGit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGitActionPerformed(evt);
            }
        });
        mnuHelp.add(mnuGit);

        mnuAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        mnuAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uom/research/thalassemia/images/info.png"))); // NOI18N
        mnuAbout.setText("  About     ");
        mnuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAboutActionPerformed(evt);
            }
        });
        mnuHelp.add(mnuAbout);

        menuBar.add(mnuHelp);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuHelpContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuHelpContentActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(new File(
                        Home.class.getResource("/index.html").getFile()));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_mnuHelpContentActionPerformed

    private void mnuGitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGitActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop d = Desktop.getDesktop();
                d.browse(new URI(
                        "https://github.com/hansa0712004212/thalassemia"));
            } catch (IOException | URISyntaxException ex) {
                System.out.println("web");
            }
        }
    }//GEN-LAST:event_mnuGitActionPerformed

    private void mnuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAboutActionPerformed
        // opens About window.
        new About(Home2.this, true).setVisible(true);
    }//GEN-LAST:event_mnuAboutActionPerformed

    private void mnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExitActionPerformed
        // system exit.
        System.exit(0);
    }//GEN-LAST:event_mnuExitActionPerformed

    private void mnuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuOpenActionPerformed
        // open File Chooser and load image.
        selectImageFile();
    }//GEN-LAST:event_mnuOpenActionPerformed

    private void mnuResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuResetActionPerformed
        lblDisplayImage.setIcon(null);
        lblDisplayImageSmooth.setIcon(null);
        lblDisplayImageGray.setIcon(null);
        lblDisplayImageProcessed.setIcon(null);
        lblDisplayImageCanny.setIcon(null);
        lblCountCells.setText(null);
        lblImagePath.setText(null);
        lblMaximumRadius.setText(null);
        lblMinimumRadius.setText(null);
        lblTimeElapsed.setText(null);
        lblResolution.setText(null);
        btnProcess.setEnabled(false);
        btnSegmentImage.setEnabled(false);
        btnCellData.setEnabled(false);
        mnuProcess.setEnabled(false);
        mnuSegment.setEnabled(false);
    }//GEN-LAST:event_mnuResetActionPerformed

    private void mnuSegmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSegmentActionPerformed
        // open image as segments.
        createSegmentsOfImageFile();
    }//GEN-LAST:event_mnuSegmentActionPerformed

    private void mnuProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProcessActionPerformed
        // do thalassemia image analysis for loaded image.
        processImageFile();
    }//GEN-LAST:event_mnuProcessActionPerformed

    private void btnOpenImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenImageActionPerformed
        selectImageFile();
    }//GEN-LAST:event_btnOpenImageActionPerformed

    private void btnSegmentImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSegmentImageActionPerformed
        createSegmentsOfImageFile();
    }//GEN-LAST:event_btnSegmentImageActionPerformed

    private void btnProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessActionPerformed
        processImageFile();
    }//GEN-LAST:event_btnProcessActionPerformed

    private void btnCellDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCellDataActionPerformed
        if (bcm != null) {
            new CircleData(bcm.getCircles()).setVisible(true);
        }
    }//GEN-LAST:event_btnCellDataActionPerformed

    private void chkAlwaysOnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAlwaysOnTopActionPerformed
        Font font = chkAlwaysOnTop.getFont();
        TextAttribute attribute = TextAttribute.WEIGHT;
        if (chkAlwaysOnTop.isSelected()) {
            setAlwaysOnTop(true);
            font = font.deriveFont(Collections.singletonMap(attribute,
                    TextAttribute.WEIGHT_BOLD));
            chkAlwaysOnTop.setFont(font);
        } else {
            setAlwaysOnTop(false);
            font = font.deriveFont(Collections.singletonMap(attribute,
                    TextAttribute.WEIGHT_REGULAR));
            chkAlwaysOnTop.setFont(font);
        }
    }//GEN-LAST:event_chkAlwaysOnTopActionPerformed

    private void btnSaveTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveTestActionPerformed
        saveTest();
    }//GEN-LAST:event_btnSaveTestActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCellData;
    private javax.swing.JButton btnOpenImage;
    private javax.swing.JButton btnProcess;
    private javax.swing.JButton btnSaveTest;
    private javax.swing.JButton btnSegmentImage;
    private javax.swing.JCheckBox chkAlwaysOnTop;
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
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar jtoolBar;
    private javax.swing.JLabel lblCountCells;
    private javax.swing.JLabel lblDateAndTime;
    private javax.swing.JLabel lblDisplayImage;
    private javax.swing.JLabel lblDisplayImageCanny;
    private javax.swing.JLabel lblDisplayImageGray;
    private javax.swing.JLabel lblDisplayImageProcessed;
    private javax.swing.JLabel lblDisplayImageSmooth;
    private javax.swing.JLabel lblImagePath;
    private javax.swing.JLabel lblMaximumRadius;
    private javax.swing.JLabel lblMinimumRadius;
    private javax.swing.JLabel lblPatient;
    private javax.swing.JLabel lblResolution;
    private javax.swing.JLabel lblTimeElapsed;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mnuAbout;
    private javax.swing.JMenu mnuEdit;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuGit;
    private javax.swing.JMenu mnuHelp;
    private javax.swing.JMenuItem mnuHelpContent;
    private javax.swing.JMenuItem mnuOpen;
    private javax.swing.JMenuItem mnuProcess;
    private javax.swing.JMenuItem mnuReset;
    private javax.swing.JMenuItem mnuSegment;
    // End of variables declaration//GEN-END:variables
}
