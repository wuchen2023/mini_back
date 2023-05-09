package com.web.back.utils;

/**
 * @author by hongdou
 * @date 2023/5/8.
 * @DESC:
 */
public class ErrorUtil {

    public static String parameterErrorFormat(String field, String msg) {
        return "【" + field + " : " + msg + "】";
    }
}
