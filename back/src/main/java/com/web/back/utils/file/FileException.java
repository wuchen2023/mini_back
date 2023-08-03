package com.web.back.utils.file;

import com.web.back.exception.base.BaseException;

/**
 * @author by hongdou
 * @date 2023/8/3.
 * @DESC:
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }
}
