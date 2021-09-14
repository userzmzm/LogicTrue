package com.logictrue.activity.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logictrue.activity.domain.ProcessBusiness;

/**
 * 流程业务数据Mapper接口
 * 
 * @author zhoumin
 * @date 2021-08-11
 */
public interface ProcessBusinessMapper  extends BaseMapper<ProcessBusiness>
{
    /**
     * 查询流程业务数据
     * 
     * @param id 流程业务数据主键
     * @return 流程业务数据
     */
    public ProcessBusiness selectProcessBusinessById(Long id);

    /**
     * 查询流程业务数据列表
     * 
     * @param processBusiness 流程业务数据
     * @return 流程业务数据集合
     */
    public List<ProcessBusiness> selectProcessBusinessList(ProcessBusiness processBusiness);

    /**
     * 新增流程业务数据
     * 
     * @param processBusiness 流程业务数据
     * @return 结果
     */
    public int insertProcessBusiness(ProcessBusiness processBusiness);

    /**
     * 修改流程业务数据
     * 
     * @param processBusiness 流程业务数据
     * @return 结果
     */
    public int updateProcessBusiness(ProcessBusiness processBusiness);

    /**
     * 删除流程业务数据
     * 
     * @param id 流程业务数据主键
     * @return 结果
     */
    public int deleteProcessBusinessById(Long id);

    /**
     * 批量删除流程业务数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProcessBusinessByIds(Long[] ids);
}
