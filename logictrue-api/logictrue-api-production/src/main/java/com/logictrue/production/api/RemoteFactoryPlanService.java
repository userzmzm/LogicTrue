package com.logictrue.production.api;

import com.logictrue.common.core.constant.SecurityConstants;
import com.logictrue.common.core.constant.ServiceNameConstants;
import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.production.api.factory.RemoteFactoryPlanFallbackFactory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 生产订单服务
 * 
 * @author logicTrue
 */
@FeignClient(contextId = "RemoteFactoryPlanService", value = ServiceNameConstants.Production_SERVICE, fallbackFactory = RemoteFactoryPlanFallbackFactory.class)
public interface RemoteFactoryPlanService
{
    /**
     * 通过生产订单ID查询生产订单信息
     *
     * @param purchaseId 生产订单ID
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/factoryplan/{purchaseId}")
    public  AjaxResult getFactoryPlanInfo(@PathVariable("purchaseId") Long purchaseId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
