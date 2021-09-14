package com.logictrue.common.core.exception.user;

import com.logictrue.common.core.exception.BaseException;

/**
 * 用户信息异常类
 * 
 * @author logicTrue
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
