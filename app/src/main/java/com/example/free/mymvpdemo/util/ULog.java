package com.example.free.mymvpdemo.util;

import android.util.Log;


public class ULog {
    /**
     * This flag to indicate the log is enabled or disabled.
     */
    private static boolean isLogEnable = true;
    private static int LOG_MAX_LENGTH = 2000;

    /**
     * Debug
     *
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.d(tag, rebuildMsg(stackTraceElement, msg));
        }
    }

    /**
     * Information
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.i(tag, rebuildMsg(stackTraceElement, ""));
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAX_LENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.i(tag, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAX_LENGTH;
                } else {
                    Log.i(tag, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

    /**
     * Verbose
     *
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.v(tag, rebuildMsg(stackTraceElement, msg));
        }
    }

    /**
     * Warning
     *
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.w(tag, rebuildMsg(stackTraceElement, msg));
        }
    }

    /**
     * Error
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.e(tag, rebuildMsg(stackTraceElement, msg));
        }
    }

    /**
     * Rebuild Log Msg
     *
     * @param msg
     * @return
     */
    private static String rebuildMsg(StackTraceElement stackTraceElement, String msg) {
        StringBuffer sb = new StringBuffer();
        sb.append(stackTraceElement.getFileName());
        sb.append(" (");
        sb.append(stackTraceElement.getLineNumber());
        sb.append(") ");
        sb.append(stackTraceElement.getMethodName());
        sb.append(": ");
        sb.append(msg);
        return sb.toString();
    }
}
