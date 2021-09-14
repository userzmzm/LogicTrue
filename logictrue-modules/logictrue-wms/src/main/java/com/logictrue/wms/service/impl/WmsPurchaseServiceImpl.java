package com.logictrue.wms.service.impl;

import java.util.List;
import com.logictrue.common.core.constant.SecurityConstants;
import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.common.redis.service.RedisService;
import com.logictrue.equiment.api.RemoteEquipmentService;
import com.logictrue.production.api.RemoteFactoryPlanService;
import com.logictrue.quality.api.RemoteMaterialtestorderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.logictrue.wms.mapper.WmsPurchaseMapper;
import com.logictrue.wms.domain.WmsPurchase;
import com.logictrue.wms.service.IWmsPurchaseService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 采购入库Service业务层处理
 * 
 * @author zhoumin
 * @date 2021-08-05
 */
@Service
public class WmsPurchaseServiceImpl implements IWmsPurchaseService 
{
    @Autowired
    private WmsPurchaseMapper wmsPurchaseMapper;

    @Autowired
    private RemoteMaterialtestorderService remoteMaterialtestorderService;

    @Autowired
    private RemoteFactoryPlanService remoteFactoryPlanService;

    @Autowired
    private RemoteEquipmentService remoteEquipmentService;

    @Autowired
    private RedisService redisService;

    /**
     * 查询采购入库
     * 
     * @param purchaseId 采购入库主键
     * @return 采购入库
     */
    @Override
    public WmsPurchase selectWmsPurchaseByPurchaseId(Long purchaseId)
    {
        return wmsPurchaseMapper.selectWmsPurchaseByPurchaseId(purchaseId);
    }

    /**
     * 查询采购入库列表
     * 
     * @param wmsPurchase 采购入库
     * @return 采购入库
     */
    @Override
    public List<WmsPurchase> selectWmsPurchaseList(WmsPurchase wmsPurchase)
    {
//        AjaxResult qualityResult = new AjaxResult();
//        AjaxResult productionResult = new AjaxResult();
//        AjaxResult equipmentResult = new AjaxResult();
//
//        if(redisService.hasKey("qualityResult")){
//            qualityResult = redisService.getCacheObject("qualityResult");
//        }else {
//            //测试调用质检服务
//            qualityResult = remoteMaterialtestorderService.getMaterialtestorderInfo(new Long(1), SecurityConstants.INNER);
//            //测试缓存
//            redisService.setCacheObject("qualityResult", qualityResult);
//        }
//
//        if(redisService.hasKey("productionResult")){
//            productionResult = redisService.getCacheObject("productionResult");
//        }else {
//            //测试调用生产服务
//            productionResult = remoteFactoryPlanService.getFactoryPlanInfo(new Long(1), SecurityConstants.INNER);
//            //测试缓存
//            redisService.setCacheObject("productionResult", productionResult);
//        }
//
//        if(redisService.hasKey("equipmentResult")){
//            equipmentResult = redisService.getCacheObject("equipmentResult");
//        }else {
//            //测试调用设备服务
//            equipmentResult = remoteEquipmentService.getEquipmentInfo(new Long(1), SecurityConstants.INNER);
//            //测试缓存
//            redisService.setCacheObject("equipmentResult", equipmentResult);
//        }

        return wmsPurchaseMapper.selectWmsPurchaseList(wmsPurchase);
    }

    /**
     * 新增采购入库
     * 
     * @param wmsPurchase 采购入库
     * @return 结果
     */
    @Override
    public int insertWmsPurchase(WmsPurchase wmsPurchase)
    {
        return wmsPurchaseMapper.insertWmsPurchase(wmsPurchase);
    }

    /**
     * 修改采购入库
     * 
     * @param wmsPurchase 采购入库
     * @return 结果
     */
    @Override
    public int updateWmsPurchase(WmsPurchase wmsPurchase)
    {
        return wmsPurchaseMapper.updateWmsPurchase(wmsPurchase);
    }

    /**
     * 批量删除采购入库
     * 
     * @param purchaseIds 需要删除的采购入库主键
     * @return 结果
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
    public int deleteWmsPurchaseByPurchaseIds(Long[] purchaseIds)
    {
        return wmsPurchaseMapper.deleteWmsPurchaseByPurchaseIds(purchaseIds);
    }

    /**
     * 删除采购入库信
     * 
     * @param purchaseId 采购入库主键
     * @return 结果
     */
    @Override
    public int deleteWmsPurchaseByPurchaseId(Long purchaseId)
    {
        return wmsPurchaseMapper.deleteWmsPurchaseByPurchaseId(purchaseId);
    }
}
