package com.logictrue.quality.service;

import java.util.List;
import com.logictrue.quality.domain.QualityMaterialtestorder;

/**
 * 原料质检Service接口
 * 
 * @author zhoumin
 * @date 2021-08-05
 */
public interface IQualityMaterialtestorderService 
{
    /**
     * 查询原料质检
     * 
     * @param purchaseId 原料质检主键
     * @return 原料质检
     */
    public QualityMaterialtestorder selectQualityMaterialtestorderByPurchaseId(Long purchaseId);

    /**
     * 查询原料质检列表
     * 
     * @param qualityMaterialtestorder 原料质检
     * @return 原料质检集合
     */
    public List<QualityMaterialtestorder> selectQualityMaterialtestorderList(QualityMaterialtestorder qualityMaterialtestorder);

    /**
     * 新增原料质检
     * 
     * @param qualityMaterialtestorder 原料质检
     * @return 结果
     */
    public int insertQualityMaterialtestorder(QualityMaterialtestorder qualityMaterialtestorder);

    /**
     * 修改原料质检
     * 
     * @param qualityMaterialtestorder 原料质检
     * @return 结果
     */
    public int updateQualityMaterialtestorder(QualityMaterialtestorder qualityMaterialtestorder);

    /**
     * 批量删除原料质检
     * 
     * @param purchaseIds 需要删除的原料质检主键集合
     * @return 结果
     */
    public int deleteQualityMaterialtestorderByPurchaseIds(Long[] purchaseIds);

    /**
     * 删除原料质检信息
     * 
     * @param purchaseId 原料质检主键
     * @return 结果
     */
    public int deleteQualityMaterialtestorderByPurchaseId(Long purchaseId);
}
