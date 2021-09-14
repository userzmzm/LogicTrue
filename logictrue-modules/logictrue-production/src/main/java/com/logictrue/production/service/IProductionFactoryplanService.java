package com.logictrue.production.service;

import java.util.List;
import com.logictrue.production.domain.ProductionFactoryplan;

/**
 * 生产订单Service接口
 * 
 * @author zhoumin
 * @date 2021-08-05
 */
public interface IProductionFactoryplanService 
{
    /**
     * 查询生产订单
     * 
     * @param purchaseId 生产订单主键
     * @return 生产订单
     */
    public ProductionFactoryplan selectProductionFactoryplanByPurchaseId(Long purchaseId);

    /**
     * 查询生产订单列表
     * 
     * @param productionFactoryplan 生产订单
     * @return 生产订单集合
     */
    public List<ProductionFactoryplan> selectProductionFactoryplanList(ProductionFactoryplan productionFactoryplan);

    /**
     * 新增生产订单
     * 
     * @param productionFactoryplan 生产订单
     * @return 结果
     */
    public int insertProductionFactoryplan(ProductionFactoryplan productionFactoryplan);

    /**
     * 修改生产订单
     * 
     * @param productionFactoryplan 生产订单
     * @return 结果
     */
    public int updateProductionFactoryplan(ProductionFactoryplan productionFactoryplan);

    /**
     * 批量删除生产订单
     * 
     * @param purchaseIds 需要删除的生产订单主键集合
     * @return 结果
     */
    public int deleteProductionFactoryplanByPurchaseIds(Long[] purchaseIds);

    /**
     * 删除生产订单信息
     * 
     * @param purchaseId 生产订单主键
     * @return 结果
     */
    public int deleteProductionFactoryplanByPurchaseId(Long purchaseId);
}
