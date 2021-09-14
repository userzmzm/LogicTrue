package com.logictrue.quality.api;

import com.logictrue.common.core.constant.SecurityConstants;
import com.logictrue.common.core.constant.ServiceNameConstants;
import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.quality.api.factory.RemoteMaterialtestorderFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 原料质检服务
 * 
 * @author logicTrue
 */
@FeignClient(contextId = "RemoteMaterialtestorderService", value = ServiceNameConstants.Quality_SERVICE, fallbackFactory = RemoteMaterialtestorderFallbackFactory.class)
public interface RemoteMaterialtestorderService
{
    /**
     * 通过原料质检单ID查询采购信息
     *
     * @param purchaseId 原料质检单ID
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/materialtestorder/{purchaseId}")
    public  AjaxResult getMaterialtestorderInfo(@PathVariable("purchaseId") Long purchaseId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
