package com.logictrue.quality.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.logictrue.quality.domain.QualityMaterialtestorder;
import com.logictrue.quality.service.IQualityMaterialtestorderService;
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
import com.logictrue.common.core.web.controller.BaseController;
import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.common.core.utils.poi.ExcelUtil;
import com.logictrue.common.core.web.page.TableDataInfo;

/**
 * 原料质检Controller
 * 
 * @author zhoumin
 * @date 2021-08-05
 */
@RestController
@RequestMapping("/materialtestorder")
public class QualityMaterialtestorderController extends BaseController
{
    @Autowired
    private IQualityMaterialtestorderService qualityMaterialtestorderService;

    /**
     * 查询原料质检列表
     */
    @PreAuthorize(hasPermi = "quality:materialtestorder:list")
    @GetMapping("/list")
    public TableDataInfo list(QualityMaterialtestorder qualityMaterialtestorder)
    {
        startPage();
        List<QualityMaterialtestorder> list = qualityMaterialtestorderService.selectQualityMaterialtestorderList(qualityMaterialtestorder);
        return getDataTable(list);
    }

    /**
     * 导出原料质检列表
     */
    @PreAuthorize(hasPermi = "quality:materialtestorder:export")
    @Log(title = "原料质检", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QualityMaterialtestorder qualityMaterialtestorder) throws IOException
    {
        List<QualityMaterialtestorder> list = qualityMaterialtestorderService.selectQualityMaterialtestorderList(qualityMaterialtestorder);
        ExcelUtil<QualityMaterialtestorder> util = new ExcelUtil<QualityMaterialtestorder>(QualityMaterialtestorder.class);
        util.exportExcel(response, list, "原料质检数据");
    }

    /**
     * 获取原料质检详细信息
     */
    @PreAuthorize(hasPermi = "quality:materialtestorder:query")
    @GetMapping(value = "/{purchaseId}")
    public AjaxResult getInfo(@PathVariable("purchaseId") Long purchaseId)
    {
        return AjaxResult.success(qualityMaterialtestorderService.selectQualityMaterialtestorderByPurchaseId(purchaseId));
    }

    /**
     * 新增原料质检
     */
    @PreAuthorize(hasPermi = "quality:materialtestorder:add")
    @Log(title = "原料质检", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QualityMaterialtestorder qualityMaterialtestorder)
    {
        return toAjax(qualityMaterialtestorderService.insertQualityMaterialtestorder(qualityMaterialtestorder));
    }

    /**
     * 修改原料质检
     */
    @PreAuthorize(hasPermi = "quality:materialtestorder:edit")
    @Log(title = "原料质检", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QualityMaterialtestorder qualityMaterialtestorder)
    {
        return toAjax(qualityMaterialtestorderService.updateQualityMaterialtestorder(qualityMaterialtestorder));
    }

    /**
     * 删除原料质检
     */
    @PreAuthorize(hasPermi = "quality:materialtestorder:remove")
    @Log(title = "原料质检", businessType = BusinessType.DELETE)
	@DeleteMapping("/{purchaseIds}")
    public AjaxResult remove(@PathVariable Long[] purchaseIds)
    {
        return toAjax(qualityMaterialtestorderService.deleteQualityMaterialtestorderByPurchaseIds(purchaseIds));
    }
}
