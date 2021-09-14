package com.logictrue.activity.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logictrue.activity.domain.ProcessConfig;

/**
 * 流程配置Mapper接口
 * 
 * @author zhoumin
 * @date 2021-08-11
 */
public interface ProcessConfigMapper extends BaseMapper<ProcessConfig>
{
    /**
     * 查询流程配置
     * 
     * @param id 流程配置主键
     * @return 流程配置
     */
    public ProcessConfig selectProcessConfigById(Long id);

    /**
     * 查询流程配置列表
     * 
     * @param processConfig 流程配置
     * @return 流程配置集合
     */
    public List<ProcessConfig> selectProcessConfigList(ProcessConfig processConfig);

    /**
     * 新增流程配置
     * 
     * @param processConfig 流程配置
     * @return 结果
     */
    public int insertProcessConfig(ProcessConfig processConfig);

    /**
     * 修改流程配置
     * 
     * @param processConfig 流程配置
     * @return 结果
     */
    public int updateProcessConfig(ProcessConfig processConfig);

    /**
     * 删除流程配置
     * 
     * @param id 流程配置主键
     * @return 结果
     */
    public int deleteProcessConfigById(Long id);

    /**
     * 批量删除流程配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProcessConfigByIds(Long[] ids);
}
