package com.logictrue.production.controller;

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
import com.logictrue.production.domain.ProductionFactoryplan;
import com.logictrue.production.service.IProductionFactoryplanService;
import com.logictrue.common.core.web.controller.BaseController;
import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.common.core.utils.poi.ExcelUtil;
import com.logictrue.common.core.web.page.TableDataInfo;

/**
 * 生产订单Controller
 * 
 * @author zhoumin
 * @date 2021-08-05
 */
@RestController
@RequestMapping("/factoryplan")
public class ProductionFactoryplanController extends BaseController
{
    @Autowired
    private IProductionFactoryplanService productionFactoryplanService;

    /**
     * 查询生产订单列表
     */
    @PreAuthorize(hasPermi = "production:factoryplan:list")
    @GetMapping("/list")
    public TableDataInfo list(ProductionFactoryplan productionFactoryplan)
    {
        startPage();
        List<ProductionFactoryplan> list = productionFactoryplanService.selectProductionFactoryplanList(productionFactoryplan);
        return getDataTable(list);
    }

    /**
     * 导出生产订单列表
     */
    @PreAuthorize(hasPermi = "production:factoryplan:export")
    @Log(title = "生产订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductionFactoryplan productionFactoryplan) throws IOException
    {
        List<ProductionFactoryplan> list = productionFactoryplanService.selectProductionFactoryplanList(productionFactoryplan);
        ExcelUtil<ProductionFactoryplan> util = new ExcelUtil<ProductionFactoryplan>(ProductionFactoryplan.class);
        util.exportExcel(response, list, "生产订单数据");
    }

    /**
     * 获取生产订单详细信息
     */
    @PreAuthorize(hasPermi = "production:factoryplan:query")
    @GetMapping(value = "/{purchaseId}")
    public AjaxResult getInfo(@PathVariable("purchaseId") Long purchaseId)
    {
        return AjaxResult.success(productionFactoryplanService.selectProductionFactoryplanByPurchaseId(purchaseId));
    }

    /**
     * 新增生产订单
     */
    @PreAuthorize(hasPermi = "production:factoryplan:add")
    @Log(title = "生产订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductionFactoryplan productionFactoryplan)
    {
        return toAjax(productionFactoryplanService.insertProductionFactoryplan(productionFactoryplan));
    }

    /**
     * 修改生产订单
     */
    @PreAuthorize(hasPermi = "production:factoryplan:edit")
    @Log(title = "生产订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductionFactoryplan productionFactoryplan)
    {
        return toAjax(productionFactoryplanService.updateProductionFactoryplan(productionFactoryplan));
    }

    /**
     * 删除生产订单
     */
    @PreAuthorize(hasPermi = "production:factoryplan:remove")
    @Log(title = "生产订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{purchaseIds}")
    public AjaxResult remove(@PathVariable Long[] purchaseIds)
    {
        return toAjax(productionFactoryplanService.deleteProductionFactoryplanByPurchaseIds(purchaseIds));
    }
}
