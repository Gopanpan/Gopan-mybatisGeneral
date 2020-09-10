package com.horse.mybatisGeneral;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @create: Created by intelliJIDEA
 * @author: Gopan
 * @e-mail: 15923508369@163.com
 * @gmdate: 10/09/2020 17:53  星期四 (dd/MM/YYYY HH:mm)
 * @sidesc:
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
