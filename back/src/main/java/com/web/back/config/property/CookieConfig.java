package com.web.back.config.property;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:
 */
public class CookieConfig {
    /**
     * Gets name.
     *
     * @return the name
     */
    public static String getName() {
        return "喜尔课堂";
    }

    /**
     * Gets interval.
     *
     * @return the interval
     */
    public static Integer getInterval() {
        return 30 * 24 * 60 * 60;
    }
}
