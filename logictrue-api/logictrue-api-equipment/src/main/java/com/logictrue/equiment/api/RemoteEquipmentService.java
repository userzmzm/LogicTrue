package com.logictrue.equiment.api;

import com.logictrue.common.core.constant.SecurityConstants;
import com.logictrue.common.core.constant.ServiceNameConstants;
import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.equiment.api.factory.RemoteEquipmentFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 采购服务
 * 
 * @author logicTrue
 */
@FeignClient(contextId = "RemoteEquipmentService", value = ServiceNameConstants.Equipment_SERVICE, fallbackFactory = RemoteEquipmentFallbackFactory.class)
public interface RemoteEquipmentService
{
    /**
     * 通过采购单ID查询采购信息
     *
     * @param purchaseId 采购单ID
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/devise/{purchaseId}")
    public  AjaxResult getEquipmentInfo(@PathVariable("purchaseId") Long purchaseId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
