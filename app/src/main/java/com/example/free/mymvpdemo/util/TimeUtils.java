package com.example.free.mymvpdemo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by free on 2017/9/11.
 */

public class TimeUtils {

    /**
     * 转换新浪微博中的created_at字段的时间，例子：  Fri Sep 11 17:19:59 +0800 2017
     * @param microBlogTime, 例子：Fri Sep 11 17:19:59 +0800 2017
     * @return 根据当前时间不同，返回不同的时间描述
     */
    public static String getMicroBlogShowTime(String microBlogTime) {
        try {
            Date jobTime = new Date(microBlogTime);
            Date noeTIme = new Date();
            if (jobTime.getYear() != noeTIme.getYear()) {
                return transferLongToDate("yyyy-MM-dd", jobTime.getTime());
            } else {
                if (jobTime.getMonth() != noeTIme.getMonth()) {
                    return transferLongToDate("MM-dd", jobTime.getTime());
                } else {
                    if (jobTime.getDate() == noeTIme.getDate()) {
                        System.out.println(noeTIme.getTime());
                        System.out.println(jobTime.getTime());
                        System.out.println((noeTIme.getTime() - jobTime.getTime()) / 3600000);
                        if ((noeTIme.getTime() - jobTime.getTime()) / 3600000 > 0) {
                            return (noeTIme.getTime() - jobTime.getTime()) / 3600000 + "小时之前";
                        } else {
                            if (3600000 - (noeTIme.getTime() - jobTime.getTime()) > 1800000) {
                                return "刚刚";
                            } else {
                                return "半小时之前";
                            }
                        }
                    } else if (jobTime.getDate() + 1 == noeTIme.getDate()) {
                        return transferLongToDate("MM-dd HH:mm", jobTime.getTime());
                    } else {
                        return transferLongToDate("MM-dd", jobTime.getTime());
                    }
                }
            }
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * 把毫秒转化成日期
     *
     * @param dateFormat
     * @param l
     * @return
     */
    public static String transferLongToDate(String dateFormat, long l) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(l);
        return sdf.format(date);
    }
}
