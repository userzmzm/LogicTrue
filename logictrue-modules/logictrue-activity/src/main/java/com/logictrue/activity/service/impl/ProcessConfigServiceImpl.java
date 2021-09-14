package com.logictrue.activity.service.impl;

import java.io.IOException;
import java.util.List;

import com.logictrue.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.logictrue.activity.mapper.ProcessConfigMapper;
import com.logictrue.activity.domain.ProcessConfig;
import com.logictrue.activity.service.IProcessConfigService;

/**
 * 流程配置Service业务层处理
 * 
 * @author zhoumin
 * @date 2021-08-11
 */
@Service
public class ProcessConfigServiceImpl implements IProcessConfigService 
{
    @Autowired
    private ProcessConfigMapper processConfigMapper;

    @Autowired
    private ActivityService activityService;
    /**
     * 查询流程配置
     * 
     * @param id 流程配置主键
     * @return 流程配置
     */
    @Override
    public ProcessConfig selectProcessConfigById(Long id)
    {
        return processConfigMapper.selectProcessConfigById(id);
    }

    /**
     * 查询流程配置列表
     * 
     * @param processConfig 流程配置
     * @return 流程配置
     */
    @Override
    public List<ProcessConfig> selectProcessConfigList(ProcessConfig processConfig)
    {
        return processConfigMapper.selectProcessConfigList(processConfig);
    }

    /**
     * 新增流程配置
     * 
     * @param processConfig 流程配置
     * @return 结果
     */
    @Override
    public int insertProcessConfig(ProcessConfig processConfig) throws IOException {
        activityService.createBpmn(processConfig);
        return processConfigMapper.insertProcessConfig(processConfig);
    }

    /**
     * 修改流程配置
     * 
     * @param processConfig 流程配置
     * @return 结果
     */
    @Override
    public int updateProcessConfig(ProcessConfig processConfig)
    {
        return processConfigMapper.updateProcessConfig(processConfig);
    }

    /**
     * 批量删除流程配置
     * 
     * @param ids 需要删除的流程配置主键
     * @return 结果
     */
    @Override
    public int deleteProcessConfigByIds(Long[] ids)
    {
        return processConfigMapper.deleteProcessConfigByIds(ids);
    }

    /**
     * 删除流程配置信息
     * 
     * @param id 流程配置主键
     * @return 结果
     */
    @Override
    public int deleteProcessConfigById(Long id)
    {
        return processConfigMapper.deleteProcessConfigById(id);
    }
}
