package com.logictrue.equipment.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.logictrue.equipment.mapper.EquipmentDeviseMapper;
import com.logictrue.equipment.domain.EquipmentDevise;
import com.logictrue.equipment.service.IEquipmentDeviseService;

/**
 * 设备台账Service业务层处理
 * 
 * @author zhoumin
 * @date 2021-08-05
 */
@Service
public class EquipmentDeviseServiceImpl implements IEquipmentDeviseService 
{
    @Autowired
    private EquipmentDeviseMapper equipmentDeviseMapper;

    /**
     * 查询设备台账
     * 
     * @param purchaseId 设备台账主键
     * @return 设备台账
     */
    @Override
    public EquipmentDevise selectEquipmentDeviseByPurchaseId(Long purchaseId)
    {
        return equipmentDeviseMapper.selectEquipmentDeviseByPurchaseId(purchaseId);
    }

    /**
     * 查询设备台账列表
     * 
     * @param equipmentDevise 设备台账
     * @return 设备台账
     */
    @Override
    public List<EquipmentDevise> selectEquipmentDeviseList(EquipmentDevise equipmentDevise)
    {
        return equipmentDeviseMapper.selectEquipmentDeviseList(equipmentDevise);
    }

    /**
     * 新增设备台账
     * 
     * @param equipmentDevise 设备台账
     * @return 结果
     */
    @Override
    public int insertEquipmentDevise(EquipmentDevise equipmentDevise)
    {
        return equipmentDeviseMapper.insertEquipmentDevise(equipmentDevise);
    }

    /**
     * 修改设备台账
     * 
     * @param equipmentDevise 设备台账
     * @return 结果
     */
    @Override
    public int updateEquipmentDevise(EquipmentDevise equipmentDevise)
    {
        return equipmentDeviseMapper.updateEquipmentDevise(equipmentDevise);
    }

    /**
     * 批量删除设备台账
     * 
     * @param purchaseIds 需要删除的设备台账主键
     * @return 结果
     */
    @Override
    public int deleteEquipmentDeviseByPurchaseIds(Long[] purchaseIds)
    {
        return equipmentDeviseMapper.deleteEquipmentDeviseByPurchaseIds(purchaseIds);
    }

    /**
     * 删除设备台账信息
     * 
     * @param purchaseId 设备台账主键
     * @return 结果
     */
    @Override
    public int deleteEquipmentDeviseByPurchaseId(Long purchaseId)
    {
        return equipmentDeviseMapper.deleteEquipmentDeviseByPurchaseId(purchaseId);
    }
}
