package com.logictrue.activity.mapper;

import com.logictrue.activity.domain.dto.ActivityDto;

import java.util.Map;

public interface ActivityMapper {

    /**
     * 到业务数据表 根据主键 得到业务数据的状态及创建人id和流程实例id
     * @param
     * @return 结果
     */
    public Map<String, Object> getBusinessInfo(ActivityDto activityDto);

    /**
     * 到业务数据表 根据主键 得到业务数据的状态及流程实例id
     * @param
     * @return 结果
     */
    public int updateByIdBusiness(ActivityDto activityDto);


    /**
     * 到业务数据表 根据流程实例id 得到业务数据的状态
     * @param
     * @return 结果
     */
    public int updateByInsIdBusiness(ActivityDto activityDto);
}
