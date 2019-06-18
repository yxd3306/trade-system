package com.sun.trade_system.util;

import java.text.DecimalFormat;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-18 10:10:36
 * @Description:
 */
public class DoubleUtil {

    public static String formatDouble(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(d);
    }

}
