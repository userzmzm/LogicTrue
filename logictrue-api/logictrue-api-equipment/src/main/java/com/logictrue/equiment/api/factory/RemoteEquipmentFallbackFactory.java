package com.logictrue.equiment.api.factory;

import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.equiment.api.RemoteEquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 设备服务降级处理
 *
 * @author logicTrue
 */
@Component
public class RemoteEquipmentFallbackFactory implements FallbackFactory<RemoteEquipmentService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteEquipmentService.class);


    @Override
    public RemoteEquipmentService create(Throwable throwable)
    {
        log.error("设备服务调用失败:{}", throwable.getMessage());
        return new RemoteEquipmentService()
        {
            @Override
            public AjaxResult getEquipmentInfo(Long purchaseId, String source) {
                return AjaxResult.error("获取设备服务信息,操作失败");
            }
        };

    }
}
