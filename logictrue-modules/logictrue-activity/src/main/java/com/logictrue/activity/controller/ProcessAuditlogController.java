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
import com.logictrue.activity.domain.ProcessAuditlog;
import com.logictrue.activity.service.IProcessAuditlogService;
import com.logictrue.common.core.web.controller.BaseController;
import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.common.core.utils.poi.ExcelUtil;
import com.logictrue.common.core.web.page.TableDataInfo;

/**
 * 流程日志Controller
 * 
 * @author zhoumin
 * @date 2021-08-11
 */
@RestController
@RequestMapping("/auditlog")
public class ProcessAuditlogController extends BaseController
{
    @Autowired
    private IProcessAuditlogService processAuditlogService;

    /**
     * 查询流程日志列表
     */
    @PreAuthorize(hasPermi = "activity:auditlog:list")
    @GetMapping("/list")
    public TableDataInfo list(ProcessAuditlog processAuditlog)
    {
        startPage();
        List<ProcessAuditlog> list = processAuditlogService.selectProcessAuditlogList(processAuditlog);
        return getDataTable(list);
    }

    /**
     * 导出流程日志列表
     */
    @PreAuthorize(hasPermi = "activity:auditlog:export")
    @Log(title = "流程日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessAuditlog processAuditlog) throws IOException
    {
        List<ProcessAuditlog> list = processAuditlogService.selectProcessAuditlogList(processAuditlog);
        ExcelUtil<ProcessAuditlog> util = new ExcelUtil<ProcessAuditlog>(ProcessAuditlog.class);
        util.exportExcel(response, list, "流程日志数据");
    }

    /**
     * 获取流程日志详细信息
     */
    @PreAuthorize(hasPermi = "activity:auditlog:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(processAuditlogService.selectProcessAuditlogById(id));
    }

    /**
     * 新增流程日志
     */
    @PreAuthorize(hasPermi = "activity:auditlog:add")
    @Log(title = "流程日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessAuditlog processAuditlog)
    {
        return toAjax(processAuditlogService.insertProcessAuditlog(processAuditlog));
    }

    /**
     * 修改流程日志
     */
    @PreAuthorize(hasPermi = "activity:auditlog:edit")
    @Log(title = "流程日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessAuditlog processAuditlog)
    {
        return toAjax(processAuditlogService.updateProcessAuditlog(processAuditlog));
    }

    /**
     * 删除流程日志
     */
    @PreAuthorize(hasPermi = "activity:auditlog:remove")
    @Log(title = "流程日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processAuditlogService.deleteProcessAuditlogByIds(ids));
    }
}
