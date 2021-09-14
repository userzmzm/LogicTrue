package com.logictrue.activity.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.logictrue.activity.mapper.ProcessAuditlogMapper;
import com.logictrue.activity.domain.ProcessAuditlog;
import com.logictrue.activity.service.IProcessAuditlogService;

/**
 * 流程日志Service业务层处理
 * 
 * @author zhoumin
 * @date 2021-08-11
 */
@Service
public class ProcessAuditlogServiceImpl implements IProcessAuditlogService 
{
    @Autowired
    private ProcessAuditlogMapper processAuditlogMapper;

    /**
     * 查询流程日志
     * 
     * @param id 流程日志主键
     * @return 流程日志
     */
    @Override
    public ProcessAuditlog selectProcessAuditlogById(Long id)
    {
        return processAuditlogMapper.selectProcessAuditlogById(id);
    }

    /**
     * 查询流程日志列表
     * 
     * @param processAuditlog 流程日志
     * @return 流程日志
     */
    @Override
    public List<ProcessAuditlog> selectProcessAuditlogList(ProcessAuditlog processAuditlog)
    {
        return processAuditlogMapper.selectProcessAuditlogList(processAuditlog);
    }

    /**
     * 新增流程日志
     * 
     * @param processAuditlog 流程日志
     * @return 结果
     */
    @Override
    public int insertProcessAuditlog(ProcessAuditlog processAuditlog)
    {
        return processAuditlogMapper.insertProcessAuditlog(processAuditlog);
    }

    /**
     * 修改流程日志
     * 
     * @param processAuditlog 流程日志
     * @return 结果
     */
    @Override
    public int updateProcessAuditlog(ProcessAuditlog processAuditlog)
    {
        return processAuditlogMapper.updateProcessAuditlog(processAuditlog);
    }

    /**
     * 批量删除流程日志
     * 
     * @param ids 需要删除的流程日志主键
     * @return 结果
     */
    @Override
    public int deleteProcessAuditlogByIds(Long[] ids)
    {
        return processAuditlogMapper.deleteProcessAuditlogByIds(ids);
    }

    /**
     * 删除流程日志信息
     * 
     * @param id 流程日志主键
     * @return 结果
     */
    @Override
    public int deleteProcessAuditlogById(Long id)
    {
        return processAuditlogMapper.deleteProcessAuditlogById(id);
    }
}
