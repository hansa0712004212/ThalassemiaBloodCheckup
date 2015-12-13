/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.util;

import java.time.LocalDateTime;

/**
 * Validations.
 *
 * @author hansa
 */
public final class Validator {

    /**
     * NIC length.
     */
    private static final int NIC_LENGTH = 10;

    /**
     * NIC int substring max index.
     */
    private static final int NIC_INDEX = 8;

    /**
     * Validate National Identity Card.
     *
     * @param nic nic
     * @return boolean
     */
    public static boolean validateNIC(final String nic) {
        return nic.length() == NIC_LENGTH
                && isNumber(nic.substring(0, NIC_INDEX))
                && (nic.endsWith("V") || nic.endsWith("X"));
    }

    /**
     * Check whether value is a number.
     *
     * @param value value
     * @return boolean
     */
    public static boolean isNumber(final String value) {
        char[] array = value.toCharArray();
        for (char ch : array) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Local Date To yyyy-MM-dd HH:mm:ss format.
     *
     * @param ldt local date time
     * @return string
     */
    public static String localDateToFormattedDate(final LocalDateTime ldt) {
        String dateString = ldt.getYear() + "-" + ldt.getMonthValue() + "-"
                + ldt.getDayOfMonth() + " "
                + ldt.getHour() + ":" + ldt.getMinute() + ":" + ldt.getSecond();
        return dateString;
    }

    /**
     * private constructor.
     */
    private Validator() {

    }
}
