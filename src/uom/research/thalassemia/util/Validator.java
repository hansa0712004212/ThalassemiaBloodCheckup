/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.util;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

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
     * Formatting for double value to Exact Decimal Places.
     */
    private static final DecimalFormat DECIMAL_FORMAT
            = new DecimalFormat("#.00");

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
     * string date value to a LocalDate object.
     *
     * @param date string date time
     * @return localDate
     */
    public static LocalDate stringDateToLocalDate(final String date) {
        int year = Integer.valueOf(date.substring(0, 4));
        int month = Integer.valueOf(date.substring(5, 7));
        int day = Integer.valueOf(date.substring(8, 10));
        LocalDate localDate = LocalDate.of(year, month, day);
        return localDate;
    }

    /**
     * string date value to a LocalDate object.
     *
     * @param date string date time
     * @return localDate
     */
    public static LocalDateTime stringDateToLocalDateTime(final String date) {
        int year = Integer.valueOf(date.substring(0, 4));
        int month = Integer.valueOf(date.substring(5, 7));
        int day = Integer.valueOf(date.substring(8, 10));

        int hour = Integer.valueOf(date.substring(11, 13));
        int minute = Integer.valueOf(date.substring(14, 16));
        int second = Integer.valueOf(date.substring(17, 19));

        LocalDateTime localDateTime = LocalDateTime.of(year, month, day,
                hour, minute, second);
        return localDateTime;
    }

    /**
     * string date value to a LocalDate object.
     *
     * @param from string date time
     * @return int
     */
    public static int calculateAge(final LocalDate from) {
        LocalDate today = LocalDate.now();
        return today.getYear() - from.getYear();
    }

    /**
     * format to decimal number and returns double.
     *
     * @param value a double value
     * @return formatted double
     */
    public static double formatDouble(final double value) {
        return Double.valueOf(DECIMAL_FORMAT.format(value));
    }

    /**
     * private constructor.
     */
    private Validator() {

    }
}
