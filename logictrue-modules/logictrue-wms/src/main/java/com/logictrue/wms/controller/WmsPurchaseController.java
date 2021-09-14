package com.logictrue.wms.controller;

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
import com.logictrue.wms.domain.WmsPurchase;
import com.logictrue.wms.service.IWmsPurchaseService;
import com.logictrue.common.core.web.controller.BaseController;
import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.common.core.utils.poi.ExcelUtil;
import com.logictrue.common.core.web.page.TableDataInfo;

/**
 * 采购入库Controller
 * 
 * @author zhoumin
 * @date 2021-08-05
 */
@RestController
@RequestMapping("/purchase")
public class WmsPurchaseController extends BaseController
{
    @Autowired
    private IWmsPurchaseService wmsPurchaseService;

    /**
     * 查询采购入库列表
     */
    @PreAuthorize(hasPermi = "wms:purchase:list")
    @GetMapping("/list")
    public TableDataInfo list(WmsPurchase wmsPurchase)
    {
        startPage();
        List<WmsPurchase> list = wmsPurchaseService.selectWmsPurchaseList(wmsPurchase);
        return getDataTable(list);
    }

    /**
     * 导出采购入库列表
     */
    @PreAuthorize(hasPermi = "wms:purchase:export")
    @Log(title = "采购入库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmsPurchase wmsPurchase) throws IOException
    {
        List<WmsPurchase> list = wmsPurchaseService.selectWmsPurchaseList(wmsPurchase);
        ExcelUtil<WmsPurchase> util = new ExcelUtil<WmsPurchase>(WmsPurchase.class);
        util.exportExcel(response, list, "采购入库数据");
    }

    /**
     * 获取采购入库详细信息
     */
    @PreAuthorize(hasPermi = "wms:purchase:query")
    @GetMapping(value = "/{purchaseId}")
    public AjaxResult getInfo(@PathVariable("purchaseId") Long purchaseId)
    {
        return AjaxResult.success(wmsPurchaseService.selectWmsPurchaseByPurchaseId(purchaseId));
    }

    /**
     * 新增采购入库
     */
    @PreAuthorize(hasPermi = "wms:purchase:add")
    @Log(title = "采购入库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmsPurchase wmsPurchase)
    {
        return toAjax(wmsPurchaseService.insertWmsPurchase(wmsPurchase));
    }

    /**
     * 修改采购入库
     */
    @PreAuthorize(hasPermi = "wms:purchase:edit")
    @Log(title = "采购入库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmsPurchase wmsPurchase)
    {
        return toAjax(wmsPurchaseService.updateWmsPurchase(wmsPurchase));
    }

    /**
     * 删除采购入库
     */
    @PreAuthorize(hasPermi = "wms:purchase:remove")
    @Log(title = "采购入库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{purchaseIds}")
    public AjaxResult remove(@PathVariable Long[] purchaseIds)
    {
        return toAjax(wmsPurchaseService.deleteWmsPurchaseByPurchaseIds(purchaseIds));
    }
}
