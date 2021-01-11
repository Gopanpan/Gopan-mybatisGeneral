package com.github.sylphlike.reverse;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>   time  11/09/2020 11:22  星期五 (dd/MM/YYYY HH:mm)
 * <p>  email  15923508369@163.com
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class DateUtils {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");



    public static String getDateString() {
        if(dateFormat != null) {
            return dateFormat.format(new Date());
        } else {
            return new Date().toString();
        }
    }
}
