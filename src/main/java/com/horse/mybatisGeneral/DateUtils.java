package com.horse.mybatisGeneral;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p> create  Pan Pan worked hard to achieve </p>
 * <p>   time  10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm) </p>
 * <p>  email  15923508369@163.com </p>
 *
 * @author Gopan
 * @version 1.0.0
 */
public class DateUtils {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");



    public static String getDateString() {
        if(dateFormat != null) {
            return dateFormat.format(new Date());
        } else {
            return new Date().toString();
        }
    }
}
