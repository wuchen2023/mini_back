package com.web.back.exception.file;

import com.web.back.utils.file.FileException;

/**
 * @author by hongdou
 * @date 2023/8/3.
 * @DESC:
 */
public class FileSizeLimitExceededException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize)
    {
        super("upload.exceed.maxSize", new Object[] { defaultMaxSize });
    }
}
