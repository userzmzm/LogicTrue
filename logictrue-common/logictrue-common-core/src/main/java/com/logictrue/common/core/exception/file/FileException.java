package com.logictrue.common.core.exception.file;

import com.logictrue.common.core.exception.BaseException;

/**
 * 文件信息异常类
 * 
 * @author logicTrue
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
