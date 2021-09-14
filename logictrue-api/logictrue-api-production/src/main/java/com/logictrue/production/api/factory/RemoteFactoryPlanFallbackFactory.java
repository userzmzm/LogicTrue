package com.logictrue.production.api.factory;

import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.production.api.RemoteFactoryPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 生产订单服务降级处理
 *
 * @author logicTrue
 */
@Component
public class RemoteFactoryPlanFallbackFactory implements FallbackFactory<RemoteFactoryPlanService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteFactoryPlanFallbackFactory.class);


    @Override
    public RemoteFactoryPlanService create(Throwable throwable)
    {
        log.error("生产订单服务调用失败:{}", throwable.getMessage());
        return new RemoteFactoryPlanService()
        {
            @Override
            public AjaxResult getFactoryPlanInfo(Long purchaseId, String source) {
                return AjaxResult.error("获取生产订单信息,操作失败");
            }
        };

    }
}
