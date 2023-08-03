package com.web.back.utils.file;

/**
 * @author by hongdou
 * @date 2023/8/3.
 * @DESC:
 */
public class FileNameLengthLimitExceededException extends FileException{
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength)
    {
        super("upload.filename.exceed.length", new Object[] { defaultFileNameLength });
    }
}
