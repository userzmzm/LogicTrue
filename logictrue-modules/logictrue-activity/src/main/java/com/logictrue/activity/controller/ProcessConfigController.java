package com.logictrue.activity.controller;

import java.util.List;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.logictrue.activity.domain.dto.ConfigDto;
import com.logictrue.activity.eums.ProcessEums;
import com.logictrue.activity.mapper.ProcessConfigMapper;
import com.logictrue.activity.service.ActivityService;
import com.logictrue.common.security.service.TokenService;
import com.logictrue.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.logictrue.common.log.annotation.Log;
import com.logictrue.common.log.enums.BusinessType;
import com.logictrue.common.security.annotation.PreAuthorize;
import com.logictrue.activity.domain.ProcessConfig;
import com.logictrue.activity.service.IProcessConfigService;
import com.logictrue.common.core.web.controller.BaseController;
import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.common.core.utils.poi.ExcelUtil;
import com.logictrue.common.core.web.page.TableDataInfo;

/**
 * 流程配置Controller
 * 
 * @author zhoumin
 * @date 2021-08-11
 */
@RestController
@RequestMapping("/config")
public class ProcessConfigController extends BaseController
{
    @Autowired
    private IProcessConfigService processConfigService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private TokenService tokenService;


    /**
     * 查询流程配置列表
     */
    @PreAuthorize(hasPermi = "activity:config:list")
    @GetMapping("/list")
    public TableDataInfo list(ProcessConfig processConfig)
    {
        startPage();
        List<ProcessConfig> list = processConfigService.selectProcessConfigList(processConfig);
        return getDataTable(list);
    }

    /**
     * 导出流程配置列表
     */
    @PreAuthorize(hasPermi = "activity:config:export")
    @Log(title = "流程配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessConfig processConfig) throws IOException
    {
        List<ProcessConfig> list = processConfigService.selectProcessConfigList(processConfig);
        ExcelUtil<ProcessConfig> util = new ExcelUtil<ProcessConfig>(ProcessConfig.class);
        util.exportExcel(response, list, "流程配置数据");
    }

    /**
     * 获取流程配置详细信息
     */
    @PreAuthorize(hasPermi = "activity:config:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(processConfigService.selectProcessConfigById(id));
    }

    /**
     * 新增流程配置
     */
    @PreAuthorize(hasPermi = "activity:config:add")
    @Log(title = "流程配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessConfig processConfig) throws IOException {
        return toAjax(processConfigService.insertProcessConfig(processConfig));
    }

    /**
     * 修改流程配置
     */
    @PreAuthorize(hasPermi = "activity:config:edit")
    @Log(title = "流程配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessConfig processConfig)
    {
        return toAjax(processConfigService.updateProcessConfig(processConfig));
    }

    /**
     * 删除流程配置
     */
    @PreAuthorize(hasPermi = "activity:config:remove")
    @Log(title = "流程配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processConfigService.deleteProcessConfigByIds(ids));
    }






    /**
     * 提交流程
     */
    @PreAuthorize(hasPermi = "activity:config:submit")
    @Log(title = "流程配置", businessType = BusinessType.SUBMIT)
    @PostMapping("/submit")
    public AjaxResult submit(@RequestBody ConfigDto dto) throws IOException
    {
        //启动流程
        //这是通用方法
        activityService.start(dto.getConfigId(), dto.getTableId());
        return toAjax("成功！");
    }










}
