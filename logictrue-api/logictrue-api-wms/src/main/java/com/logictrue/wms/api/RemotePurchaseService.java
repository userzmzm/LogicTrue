package com.logictrue.wms.api;

import com.logictrue.common.core.constant.SecurityConstants;
import com.logictrue.common.core.constant.ServiceNameConstants;
import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.wms.api.factory.RemotePurchaseFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 采购服务
 * 
 * @author logicTrue
 */
@FeignClient(contextId = "RemotePurchaseService", value = ServiceNameConstants.WMS_SERVICE, fallbackFactory = RemotePurchaseFallbackFactory.class)
public interface RemotePurchaseService
{
    /**
     * 通过采购单ID查询采购信息
     *
     * @param purchaseId 采购单ID
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/purchase/{purchaseId}")
    public  AjaxResult getPurchaseInfo(@PathVariable("purchaseId") Long purchaseId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);


    /**
     * 新增采购入库
     */
    @DeleteMapping("/purchase/{purchaseIds}")
    public AjaxResult remove(@PathVariable("purchaseIds") Long[] purchaseIds,  @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
