package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/17.
 */
public class TimeUtils {
    public static String getTime(String temp) {

        Date mDate;
        SimpleDateFormat mSimpleDateFormat;

        mDate = new Date();
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String localTime = mSimpleDateFormat.format(mDate);

        String[] strs = localTime.split(" ");
        String[] strs1 = strs[0].split("-");
        String[] strs2 = strs[1].split(":");

        String[] strDate1 = temp.split(" ");
        String[] strDate2 = strDate1[0].split("-");
        String[] strDate3 = strDate1[1].split(":");

        int[] strsInt1 = new int[strs1.length];
        int[] strsInt2 = new int[strs1.length];
        int[] strDateInt1 = new int[strs1.length];
        int[] strDateInt2 = new int[strs1.length];

        for (int i = 0; i < strsInt1.length; i++) {
            strsInt1[i] = Integer.valueOf(strs1[i]);
            strsInt2[i] = Integer.valueOf(strs2[i]);
            strDateInt1[i] = Integer.valueOf(strDate2[i]);
            strDateInt2[i] = Integer.valueOf(strDate3[i]);
        }

        int year = strsInt1[0] - strDateInt1[0];

        if (year > 0) {
            return year + "年前";
        }

        int month = strsInt1[1] - strDateInt1[1];

        if (month > 0) {
            return month + "月前";
        }

        int day = strsInt1[2] - strDateInt1[2];

        if (day > 0) {
            return day + "天前";
        }


        int hour = strsInt2[0] - strDateInt2[0];
        int min = strsInt2[1] - strDateInt2[1];
        int intTemp = hour * 60 + min;

        if (intTemp > 60) {
            intTemp = intTemp / 60;
            return intTemp + "小时前";
        } else {
            return intTemp + "分钟前";
        }
    }
}
