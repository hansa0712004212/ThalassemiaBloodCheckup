/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.util;

import javax.swing.JOptionPane;

/**
 * Message.
 *
 * @author hansa
 */
public final class Message {

    /**
     * Message Yes Option.
     */
    public static final int YES_OPTION = JOptionPane.YES_OPTION;

    /**
     * Message No Option.
     */
    public static final int NO_OPTION = JOptionPane.NO_OPTION;

    /**
     * Message Cancel Option.
     */
    public static final int CANCEL_OPTION = JOptionPane.CANCEL_OPTION;

    /**
     * showInformationMessage.
     *
     * @param message message
     */
    public static void showInformationMessage(final String message) {
        String msg = message;
        StringBuilder preFix = new StringBuilder(
                "<html><body bgcolor='#58FAF4'><center><b>  ");
        String postFix = "  </b></center></body></html>";
        if (message == null) {
            msg = "Information";
        }
        preFix.append(msg).append(postFix);
        JOptionPane.showMessageDialog(null, preFix, "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * show Success Message.
     *
     * @param message message
     */
    public static void showSuccessMessage(final String message) {
        String msg = message;
        StringBuilder preFix = new StringBuilder(
                "<html><body bgcolor='#00FF00'><center><b>  ");
        String postFix = "  </b></center></body></html>";
        if (message == null) {
            msg = "Successful";
        }
        preFix.append(msg).append(postFix);
        JOptionPane.showMessageDialog(null, preFix, "Successful",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * show Success Yes No Message.
     *
     * @param message message
     * @return int action
     */
    public static int showSuccessYesNoMessage(final String message) {
        String msg = message;
        StringBuilder preFix = new StringBuilder(
                "<html><body bgcolor='#00FF00'><center><b>  ");
        String postFix = "  </b></center></body></html>";
        if (message == null) {
            msg = "Successful";
        }
        preFix.append(msg).append(postFix);
        return JOptionPane.showConfirmDialog(null, preFix, "Successful",
                JOptionPane.YES_NO_OPTION);
    }

    /**
     * show Warning Message.
     *
     * @param message message
     */
    public static void showWarningMessage(final String message) {
        String msg = message;
        StringBuilder preFix = new StringBuilder(
                "<html><body bgcolor='#FE9A2E'><center><b>  ");
        String postFix = "  </b></center></body></html>";
        if (message == null) {
            msg = "Warning";
        }
        preFix.append(msg).append(postFix);
        JOptionPane.showMessageDialog(null, preFix, "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    /**
     * show Error Message.
     *
     * @param message message
     */
    public static void showErrorMessage(final String message) {
        String msg = message;
        StringBuilder preFix = new StringBuilder(
                "<html><body bgcolor='#FA5858'><center><b>  ");
        String postFix = "  </b></center></body></html>";
        if (message == null) {
            msg = "Error";
        }
        preFix.append(msg).append(postFix);
        JOptionPane.showMessageDialog(null, preFix, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * show Question Message.
     *
     * @param message message
     */
    public static void showQuestionMessage(final String message) {
        String msg = message;
        StringBuilder preFix = new StringBuilder(
                "<html><body bgcolor='#ffff00'><center><b>  ");
        String postFix = "  </b></center></body></html>";
        if (message == null) {
            msg = "Question";
        }
        preFix.append(msg).append(postFix);
        JOptionPane.showMessageDialog(null, preFix, "Question",
                JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * show Question Yes No Message.
     *
     * @param message message
     * @return integer action id
     */
    public static int showQuestionYesNo(final String message) {
        String msg = message;
        StringBuilder preFix = new StringBuilder(
                "<html><body bgcolor='#ff9900'><center><b>  ");
        String postFix = "  </b></center></body></html>";
        if (message == null) {
            msg = "Question";
        }
        preFix.append(msg).append(postFix);
        return JOptionPane.showConfirmDialog(null, preFix, "Question",
                JOptionPane.YES_NO_OPTION);
    }

    /**
     * show Question Yes No Message.
     *
     * @param message message
     * @return integer action id
     */
    public static int showQuestionYesNoCancel(final String message) {
        String msg = message;
        StringBuilder preFix = new StringBuilder(
                "<html><body bgcolor='#ff9900'><center><b>  ");
        String postFix = "  </b></center></body></html>";
        if (message == null) {
            msg = "Question";
        }
        preFix.append(msg).append(postFix);
        return JOptionPane.showConfirmDialog(null, preFix, "Question",
                JOptionPane.YES_NO_CANCEL_OPTION);
    }

    /**
     * private constructor.
     */
    private Message() {

    }
}
