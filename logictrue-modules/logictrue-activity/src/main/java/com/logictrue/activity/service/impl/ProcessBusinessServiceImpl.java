package com.logictrue.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.logictrue.activity.mapper.ProcessBusinessMapper;
import com.logictrue.activity.domain.ProcessBusiness;
import com.logictrue.activity.service.IProcessBusinessService;

/**
 * 流程业务数据Service业务层处理
 * 
 * @author zhoumin
 * @date 2021-08-11
 */
@Service
public class ProcessBusinessServiceImpl implements IProcessBusinessService 
{
    @Autowired
    private ProcessBusinessMapper processBusinessMapper;

    /**
     * 查询流程业务数据
     * 
     * @param id 流程业务数据主键
     * @return 流程业务数据
     */
    @Override
    public ProcessBusiness selectProcessBusinessById(Long id)
    {
        return processBusinessMapper.selectProcessBusinessById(id);
    }

    /**
     * 查询流程业务数据列表
     * 
     * @param processBusiness 流程业务数据
     * @return 流程业务数据
     */
    @Override
    public List<ProcessBusiness> selectProcessBusinessList(ProcessBusiness processBusiness)
    {
        return processBusinessMapper.selectProcessBusinessList(processBusiness);
    }

    /**
     * 新增流程业务数据
     * 
     * @param processBusiness 流程业务数据
     * @return 结果
     */
    @Override
    public int insertProcessBusiness(ProcessBusiness processBusiness)
    {
        return processBusinessMapper.insertProcessBusiness(processBusiness);
    }

    /**
     * 修改流程业务数据
     * 
     * @param processBusiness 流程业务数据
     * @return 结果
     */
    @Override
    public int updateProcessBusiness(ProcessBusiness processBusiness)
    {
        return processBusinessMapper.updateProcessBusiness(processBusiness);
    }

    /**
     * 批量删除流程业务数据
     * 
     * @param ids 需要删除的流程业务数据主键
     * @return 结果
     */
    @Override
    public int deleteProcessBusinessByIds(Long[] ids)
    {
        return processBusinessMapper.deleteProcessBusinessByIds(ids);
    }

    /**
     * 删除流程业务数据信息
     * 
     * @param id 流程业务数据主键
     * @return 结果
     */
    @Override
    public int deleteProcessBusinessById(Long id)
    {
        return processBusinessMapper.deleteProcessBusinessById(id);
    }
}
