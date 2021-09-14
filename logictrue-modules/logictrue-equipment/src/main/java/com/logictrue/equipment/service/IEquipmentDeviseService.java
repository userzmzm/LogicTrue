package com.logictrue.equipment.service;

import java.util.List;
import com.logictrue.equipment.domain.EquipmentDevise;

/**
 * 设备台账Service接口
 * 
 * @author zhoumin
 * @date 2021-08-05
 */
public interface IEquipmentDeviseService 
{
    /**
     * 查询设备台账
     * 
     * @param purchaseId 设备台账主键
     * @return 设备台账
     */
    public EquipmentDevise selectEquipmentDeviseByPurchaseId(Long purchaseId);

    /**
     * 查询设备台账列表
     * 
     * @param equipmentDevise 设备台账
     * @return 设备台账集合
     */
    public List<EquipmentDevise> selectEquipmentDeviseList(EquipmentDevise equipmentDevise);

    /**
     * 新增设备台账
     * 
     * @param equipmentDevise 设备台账
     * @return 结果
     */
    public int insertEquipmentDevise(EquipmentDevise equipmentDevise);

    /**
     * 修改设备台账
     * 
     * @param equipmentDevise 设备台账
     * @return 结果
     */
    public int updateEquipmentDevise(EquipmentDevise equipmentDevise);

    /**
     * 批量删除设备台账
     * 
     * @param purchaseIds 需要删除的设备台账主键集合
     * @return 结果
     */
    public int deleteEquipmentDeviseByPurchaseIds(Long[] purchaseIds);

    /**
     * 删除设备台账信息
     * 
     * @param purchaseId 设备台账主键
     * @return 结果
     */
    public int deleteEquipmentDeviseByPurchaseId(Long purchaseId);
}
