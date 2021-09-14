package com.logictrue.common.log.service;

import com.logictrue.system.api.RemoteLogService;
import com.logictrue.system.api.domain.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.logictrue.common.core.constant.SecurityConstants;

/**
 * 异步调用日志服务
 * 
 * @author logicTrue
 */
@Service
public class AsyncLogService
{
    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLog sysOperLog)
    {
        remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER);
    }
}
