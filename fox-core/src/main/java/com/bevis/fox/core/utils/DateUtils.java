package com.bevis.fox.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * The type Date utils.
 *
 * @author bevis
 * @version DateUtils.java, v 0.1 2018/4/1 下午11:06
 */
public final class DateUtils {

    /**
     * The constant SIMPLE_DATE_FORMAT_POOL.
     */
    private static final ThreadLocal<Map<String, SimpleDateFormat>> SIMPLE_DATE_FORMAT_POOL = new ThreadLocal<>();

    /**
     * Instantiates a new Date utils.
     */
    private DateUtils() {

    }

    /**
     * Format simple date format.
     *
     * @param date   the date
     * @param format the format
     * @return the simple date format
     */
    public static String format(Date date, String format) {
        SimpleDateFormat sdf = getDateFormat(format);
        return sdf.format(date);
    }

    /**
     * Parse date.
     *
     * @param date   the date
     * @param format the format
     * @return the date
     */
    public static Date parse(String date, String format) {
        SimpleDateFormat sdf = getDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * Gets date format.
     *
     * @param format the format
     * @return the date format
     */
    private static SimpleDateFormat getDateFormat(String format) {
        if (null == SIMPLE_DATE_FORMAT_POOL.get()) {
            SIMPLE_DATE_FORMAT_POOL.set(new HashMap<>(16));
        }

        SimpleDateFormat sdf = SIMPLE_DATE_FORMAT_POOL.get().get(format);
        if (null == sdf) {
            sdf = new SimpleDateFormat(format);
            SIMPLE_DATE_FORMAT_POOL.get().put(format, sdf);
        }

        return sdf;
    }
}