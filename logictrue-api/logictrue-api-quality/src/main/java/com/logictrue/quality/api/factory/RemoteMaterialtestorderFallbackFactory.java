package com.logictrue.quality.api.factory;

import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.quality.api.RemoteMaterialtestorderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 原料质检服务降级处理
 *
 * @author logicTrue
 */
@Component
public class RemoteMaterialtestorderFallbackFactory implements FallbackFactory<RemoteMaterialtestorderService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteMaterialtestorderFallbackFactory.class);


    @Override
    public RemoteMaterialtestorderService create(Throwable throwable)
    {
        log.error("原料质检服务调用失败:{}", throwable.getMessage());
        return new RemoteMaterialtestorderService()
        {
            @Override
            public AjaxResult getMaterialtestorderInfo(Long purchaseId, String source) {
                return AjaxResult.error("获取原料质检单信息,操作失败");
            }
        };

    }
}
