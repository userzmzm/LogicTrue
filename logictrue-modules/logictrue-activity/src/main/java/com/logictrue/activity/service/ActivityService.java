package com.logictrue.activity.service;

import com.logictrue.activity.domain.ProcessBusiness;
import com.logictrue.activity.domain.ProcessConfig;
import com.logictrue.activity.domain.dto.AuditDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 流程Service接口
 *
 * @author zhoumin
 * @date 2021-08-11
 */
public interface ActivityService {



    /**
     * 创建流程图
     * @param config  流程配置信息
     */
    public void createBpmn(ProcessConfig config) throws IOException;


    /**
     * 发起流程
     * @param configId        流程配置ID
     * @param tableId        业务数据ID
     */
    public void start(Long configId, Long tableId);


    /**
     * 查看流程图
     * @param instanceId 流程实例id
     * @param response
     * @throws Exception
     */
    public void traceprocess(String instanceId, HttpServletResponse response) throws Exception;


    /**
     * 获取我的代办任务集合
     * @return 结果
     */
    public List<Map<String, Object>>  getTaskList();


    /**
     * 处理任务
     * @param auditDto  流程处理dto
     * @throws Exception
     */
    public void complete(AuditDto auditDto) throws Exception;

}
