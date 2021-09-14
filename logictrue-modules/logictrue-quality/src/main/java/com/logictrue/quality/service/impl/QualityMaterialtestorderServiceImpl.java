package com.logictrue.quality.service.impl;

import java.util.List;

import com.logictrue.quality.mapper.QualityMaterialtestorderMapper;
import com.logictrue.quality.service.IQualityMaterialtestorderService;
import com.logictrue.wms.api.RemotePurchaseService;
import com.logictrue.common.core.constant.SecurityConstants;
import com.logictrue.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.logictrue.quality.domain.QualityMaterialtestorder;

/**
 * 原料质检Service业务层处理
 * 
 * @author zhoumin
 * @date 2021-08-05
 */
@Service
public class QualityMaterialtestorderServiceImpl implements IQualityMaterialtestorderService
{
    @Autowired
    private QualityMaterialtestorderMapper qualityMaterialtestorderMapper;


    @Autowired
    private RemotePurchaseService remotePurchaseService;

    /**
     * 查询原料质检
     * 
     * @param purchaseId 原料质检主键
     * @return 原料质检
     */
    @Override
    public QualityMaterialtestorder selectQualityMaterialtestorderByPurchaseId(Long purchaseId)
    {
        return qualityMaterialtestorderMapper.selectQualityMaterialtestorderByPurchaseId(purchaseId);
    }

    /**
     * 查询原料质检列表
     * 
     * @param qualityMaterialtestorder 原料质检
     * @return 原料质检
     */
    @Override
    public List<QualityMaterialtestorder> selectQualityMaterialtestorderList(QualityMaterialtestorder qualityMaterialtestorder)
    {
        //测试调用仓储服务
        AjaxResult ajaxResult = remotePurchaseService.getPurchaseInfo(new Long(1),  SecurityConstants.INNER);
        return qualityMaterialtestorderMapper.selectQualityMaterialtestorderList(qualityMaterialtestorder);
    }

    /**
     * 新增原料质检
     * 
     * @param qualityMaterialtestorder 原料质检
     * @return 结果
     */
    @Override
    public int insertQualityMaterialtestorder(QualityMaterialtestorder qualityMaterialtestorder)
    {
        return qualityMaterialtestorderMapper.insertQualityMaterialtestorder(qualityMaterialtestorder);
    }

    /**
     * 修改原料质检
     * 
     * @param qualityMaterialtestorder 原料质检
     * @return 结果
     */
    @Override
    public int updateQualityMaterialtestorder(QualityMaterialtestorder qualityMaterialtestorder)
    {
        return qualityMaterialtestorderMapper.updateQualityMaterialtestorder(qualityMaterialtestorder);
    }

    /**
     * 批量删除原料质检
     * 
     * @param purchaseIds 需要删除的原料质检主键
     * @return 结果
     */
    @Override
    public int deleteQualityMaterialtestorderByPurchaseIds(Long[] purchaseIds)
    {
        return qualityMaterialtestorderMapper.deleteQualityMaterialtestorderByPurchaseIds(purchaseIds);
    }

    /**
     * 删除原料质检信息
     * 
     * @param purchaseId 原料质检主键
     * @return 结果
     */
    @Override
    public int deleteQualityMaterialtestorderByPurchaseId(Long purchaseId)
    {
        return qualityMaterialtestorderMapper.deleteQualityMaterialtestorderByPurchaseId(purchaseId);
    }
}
