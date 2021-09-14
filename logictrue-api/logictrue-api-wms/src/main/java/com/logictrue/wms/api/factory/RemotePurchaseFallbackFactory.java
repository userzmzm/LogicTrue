package com.logictrue.wms.api.factory;

import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.wms.api.RemotePurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 仓储服务降级处理
 *
 * @author logicTrue
 */
@Component
public class RemotePurchaseFallbackFactory implements FallbackFactory<RemotePurchaseService>
{
    private static final Logger log = LoggerFactory.getLogger(RemotePurchaseFallbackFactory.class);


    @Override
    public RemotePurchaseService create(Throwable throwable)
    {
        log.error("仓储服务调用失败:{}", throwable.getMessage());
        return new RemotePurchaseService()
        {
            @Override
            public AjaxResult getPurchaseInfo(Long purchaseId, String source) {
                return AjaxResult.error("获取采购单信息,操作失败");
            }

            @Override
            public AjaxResult remove(Long[] purchaseIds, String source) {
                return AjaxResult.error("删除采购单信息,操作失败");
            }
        };

    }
}
