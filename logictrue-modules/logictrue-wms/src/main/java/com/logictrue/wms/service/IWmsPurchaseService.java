package com.logictrue.wms.service;

import java.util.List;
import com.logictrue.wms.domain.WmsPurchase;

/**
 * 采购入库Service接口
 * 
 * @author zhoumin
 * @date 2021-08-05
 */
public interface IWmsPurchaseService 
{
    /**
     * 查询采购入库
     * 
     * @param purchaseId 采购入库主键
     * @return 采购入库
     */
    public WmsPurchase selectWmsPurchaseByPurchaseId(Long purchaseId);

    /**
     * 查询采购入库列表
     * 
     * @param wmsPurchase 采购入库
     * @return 采购入库集合
     */
    public List<WmsPurchase> selectWmsPurchaseList(WmsPurchase wmsPurchase);

    /**
     * 新增采购入库
     * 
     * @param wmsPurchase 采购入库
     * @return 结果
     */
    public int insertWmsPurchase(WmsPurchase wmsPurchase);

    /**
     * 修改采购入库
     * 
     * @param wmsPurchase 采购入库
     * @return 结果
     */
    public int updateWmsPurchase(WmsPurchase wmsPurchase);

    /**
     * 批量删除采购入库
     * 
     * @param purchaseIds 需要删除的采购入库主键集合
     * @return 结果
     */
    public int deleteWmsPurchaseByPurchaseIds(Long[] purchaseIds);

    /**
     * 删除采购入库信息
     * 
     * @param purchaseId 采购入库主键
     * @return 结果
     */
    public int deleteWmsPurchaseByPurchaseId(Long purchaseId);
}
