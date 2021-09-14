package com.logictrue.activity.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logictrue.activity.domain.ProcessAuditlog;
import com.logictrue.activity.domain.ProcessBusiness;
import com.logictrue.activity.domain.ProcessConfig;

/**
 * 流程日志Mapper接口
 * 
 * @author zhoumin
 * @date 2021-08-11
 */
public interface ProcessAuditlogMapper  extends BaseMapper<ProcessAuditlog>
{
    /**
     * 查询流程日志
     * 
     * @param id 流程日志主键
     * @return 流程日志
     */
    public ProcessAuditlog selectProcessAuditlogById(Long id);

    /**
     * 查询流程日志列表
     * 
     * @param processAuditlog 流程日志
     * @return 流程日志集合
     */
    public List<ProcessAuditlog> selectProcessAuditlogList(ProcessAuditlog processAuditlog);

    /**
     * 新增流程日志
     * 
     * @param processAuditlog 流程日志
     * @return 结果
     */
    public int insertProcessAuditlog(ProcessAuditlog processAuditlog);

    /**
     * 修改流程日志
     * 
     * @param processAuditlog 流程日志
     * @return 结果
     */
    public int updateProcessAuditlog(ProcessAuditlog processAuditlog);

    /**
     * 删除流程日志
     * 
     * @param id 流程日志主键
     * @return 结果
     */
    public int deleteProcessAuditlogById(Long id);

    /**
     * 批量删除流程日志
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProcessAuditlogByIds(Long[] ids);
}
