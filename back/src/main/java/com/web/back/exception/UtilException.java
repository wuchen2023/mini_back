package com.web.back.exception;

/**
 * @author by hongdou
 * @date 2023/8/3.
 * @DESC:
 */
public class UtilException extends RuntimeException{
    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable e)
    {
        super(e.getMessage(), e);
    }

    public UtilException(String message)
    {
        super(message);
    }

    public UtilException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
