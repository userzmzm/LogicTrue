package com.logictrue.production.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.logictrue.production.mapper.ProductionFactoryplanMapper;
import com.logictrue.production.domain.ProductionFactoryplan;
import com.logictrue.production.service.IProductionFactoryplanService;

/**
 * 生产订单Service业务层处理
 * 
 * @author zhoumin
 * @date 2021-08-05
 */
@Service
public class ProductionFactoryplanServiceImpl implements IProductionFactoryplanService 
{
    @Autowired
    private ProductionFactoryplanMapper productionFactoryplanMapper;

    /**
     * 查询生产订单
     * 
     * @param purchaseId 生产订单主键
     * @return 生产订单
     */
    @Override
    public ProductionFactoryplan selectProductionFactoryplanByPurchaseId(Long purchaseId)
    {
        return productionFactoryplanMapper.selectProductionFactoryplanByPurchaseId(purchaseId);
    }

    /**
     * 查询生产订单列表
     * 
     * @param productionFactoryplan 生产订单
     * @return 生产订单
     */
    @Override
    public List<ProductionFactoryplan> selectProductionFactoryplanList(ProductionFactoryplan productionFactoryplan)
    {
        return productionFactoryplanMapper.selectProductionFactoryplanList(productionFactoryplan);
    }

    /**
     * 新增生产订单
     * 
     * @param productionFactoryplan 生产订单
     * @return 结果
     */
    @Override
    public int insertProductionFactoryplan(ProductionFactoryplan productionFactoryplan)
    {
        return productionFactoryplanMapper.insertProductionFactoryplan(productionFactoryplan);
    }

    /**
     * 修改生产订单
     * 
     * @param productionFactoryplan 生产订单
     * @return 结果
     */
    @Override
    public int updateProductionFactoryplan(ProductionFactoryplan productionFactoryplan)
    {
        return productionFactoryplanMapper.updateProductionFactoryplan(productionFactoryplan);
    }

    /**
     * 批量删除生产订单
     * 
     * @param purchaseIds 需要删除的生产订单主键
     * @return 结果
     */
    @Override
    public int deleteProductionFactoryplanByPurchaseIds(Long[] purchaseIds)
    {
        return productionFactoryplanMapper.deleteProductionFactoryplanByPurchaseIds(purchaseIds);
    }

    /**
     * 删除生产订单信息
     * 
     * @param purchaseId 生产订单主键
     * @return 结果
     */
    @Override
    public int deleteProductionFactoryplanByPurchaseId(Long purchaseId)
    {
        return productionFactoryplanMapper.deleteProductionFactoryplanByPurchaseId(purchaseId);
    }
}
