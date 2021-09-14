package com.logictrue.activity.controller;

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
import com.logictrue.activity.domain.ProcessBusiness;
import com.logictrue.activity.service.IProcessBusinessService;
import com.logictrue.common.core.web.controller.BaseController;
import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.common.core.utils.poi.ExcelUtil;
import com.logictrue.common.core.web.page.TableDataInfo;

/**
 * 流程业务数据Controller
 * 
 * @author zhoumin
 * @date 2021-08-11
 */
@RestController
@RequestMapping("/business")
public class ProcessBusinessController extends BaseController
{
    @Autowired
    private IProcessBusinessService processBusinessService;

    /**
     * 查询流程业务数据列表
     */
    @PreAuthorize(hasPermi = "activity:business:list")
    @GetMapping("/list")
    public TableDataInfo list(ProcessBusiness processBusiness)
    {
        startPage();
        List<ProcessBusiness> list = processBusinessService.selectProcessBusinessList(processBusiness);
        return getDataTable(list);
    }

    /**
     * 导出流程业务数据列表
     */
    @PreAuthorize(hasPermi = "activity:business:export")
    @Log(title = "流程业务数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessBusiness processBusiness) throws IOException
    {
        List<ProcessBusiness> list = processBusinessService.selectProcessBusinessList(processBusiness);
        ExcelUtil<ProcessBusiness> util = new ExcelUtil<ProcessBusiness>(ProcessBusiness.class);
        util.exportExcel(response, list, "流程业务数据数据");
    }

    /**
     * 获取流程业务数据详细信息
     */
    @PreAuthorize(hasPermi = "activity:business:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(processBusinessService.selectProcessBusinessById(id));
    }

    /**
     * 新增流程业务数据
     */
    @PreAuthorize(hasPermi = "activity:business:add")
    @Log(title = "流程业务数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessBusiness processBusiness)
    {
        return toAjax(processBusinessService.insertProcessBusiness(processBusiness));
    }

    /**
     * 修改流程业务数据
     */
    @PreAuthorize(hasPermi = "activity:business:edit")
    @Log(title = "流程业务数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessBusiness processBusiness)
    {
        return toAjax(processBusinessService.updateProcessBusiness(processBusiness));
    }

    /**
     * 删除流程业务数据
     */
    @PreAuthorize(hasPermi = "activity:business:remove")
    @Log(title = "流程业务数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processBusinessService.deleteProcessBusinessByIds(ids));
    }
}
