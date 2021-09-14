package com.logictrue.equipment.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logictrue.common.log.annotation.Log;
import com.logictrue.common.log.enums.BusinessType;
import com.logictrue.common.security.annotation.PreAuthorize;
import com.logictrue.equipment.domain.EquipmentDevise;
import com.logictrue.equipment.service.IEquipmentDeviseService;
import com.logictrue.common.core.web.controller.BaseController;
import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.common.core.utils.poi.ExcelUtil;
import com.logictrue.common.core.web.page.TableDataInfo;

/**
 * 设备台账Controller
 * 
 * @author zhoumin
 * @date 2021-08-05
 */
@RestController
@RequestMapping("/devise")
public class EquipmentDeviseController extends BaseController
{
    @Autowired
    private IEquipmentDeviseService equipmentDeviseService;

    /**
     * 查询设备台账列表
     */
    @PreAuthorize(hasPermi = "equipment:devise:list")
    @GetMapping("/list")
    public TableDataInfo list(EquipmentDevise equipmentDevise)
    {
        startPage();
        List<EquipmentDevise> list = equipmentDeviseService.selectEquipmentDeviseList(equipmentDevise);
        return getDataTable(list);
    }

    /**
     * 导出设备台账列表
     */
    @PreAuthorize(hasPermi = "equipment:devise:export")
    @Log(title = "设备台账", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EquipmentDevise equipmentDevise) throws IOException
    {
        List<EquipmentDevise> list = equipmentDeviseService.selectEquipmentDeviseList(equipmentDevise);
        ExcelUtil<EquipmentDevise> util = new ExcelUtil<EquipmentDevise>(EquipmentDevise.class);
        util.exportExcel(response, list, "设备台账数据");
    }

    /**
     * 获取设备台账详细信息
     */
    @PreAuthorize(hasPermi = "equipment:devise:query")
    @GetMapping(value = "/{purchaseId}")
    public AjaxResult getInfo(@PathVariable("purchaseId") Long purchaseId)
    {
        return AjaxResult.success(equipmentDeviseService.selectEquipmentDeviseByPurchaseId(purchaseId));
    }

    /**
     * 新增设备台账
     */
    @PreAuthorize(hasPermi = "equipment:devise:add")
    @Log(title = "设备台账", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EquipmentDevise equipmentDevise)
    {
        return toAjax(equipmentDeviseService.insertEquipmentDevise(equipmentDevise));
    }

    /**
     * 修改设备台账
     */
    @PreAuthorize(hasPermi = "equipment:devise:edit")
    @Log(title = "设备台账", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EquipmentDevise equipmentDevise)
    {
        return toAjax(equipmentDeviseService.updateEquipmentDevise(equipmentDevise));
    }

    /**
     * 删除设备台账
     */
    @PreAuthorize(hasPermi = "equipment:devise:remove")
    @Log(title = "设备台账", businessType = BusinessType.DELETE)
	@DeleteMapping("/{purchaseIds}")
    public AjaxResult remove(@PathVariable Long[] purchaseIds)
    {
        return toAjax(equipmentDeviseService.deleteEquipmentDeviseByPurchaseIds(purchaseIds));
    }
}
