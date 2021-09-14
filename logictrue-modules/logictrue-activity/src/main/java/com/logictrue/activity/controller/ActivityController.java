package com.logictrue.activity.controller;


import com.logictrue.activity.domain.ProcessAuditlog;
import com.logictrue.activity.domain.ProcessBusiness;
import com.logictrue.activity.domain.dto.AuditDto;
import com.logictrue.activity.domain.dto.ConfigDto;
import com.logictrue.activity.service.ActivityService;
import com.logictrue.activity.service.IProcessAuditlogService;
import com.logictrue.activity.service.IProcessBusinessService;
import com.logictrue.common.core.web.controller.BaseController;
import com.logictrue.common.core.web.domain.AjaxResult;
import com.logictrue.common.core.web.page.TableDataInfo;
import com.logictrue.common.security.annotation.PreAuthorize;
import com.logictrue.common.security.service.TokenService;
import com.logictrue.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 高空抛物
 */
@RestController
public class ActivityController extends BaseController {

    @Autowired
    private IProcessBusinessService processBusinessService;
    @Autowired
    private IProcessAuditlogService processAuditlogService;

    /**
     * 流程
     */
    @Autowired
    private ActivityService activityService;

    /**
     * 用户信息
     */
    @Autowired
    private TokenService tokenService;


    /**
     * 提交流程
     */
    @PreAuthorize(hasPermi = "activity:config:submit")
    @PostMapping("/submit")
    public AjaxResult submit(@RequestBody ConfigDto dto) throws IOException
    {
        //业务调用请使用此方法
         activityService.start(dto.getConfigId(), dto.getTableId());
        //把启动流程返回的 流程id和状态字段写入自己的业务表
        return AjaxResult.success();
    }


    /**
     * 我发起的流程
     */
    @PreAuthorize(hasPermi = "activity:myProcess:list")
    @GetMapping("/myProcess")
    public TableDataInfo myProcess()
    {
        ProcessBusiness processBusiness = new ProcessBusiness();
        //获取当前登录人
        LoginUser loginUser = tokenService.getLoginUser();
        Long userId = loginUser.getUserid();
        processBusiness.setApplyuser(userId);
        startPage();
        List<ProcessBusiness> list = processBusinessService.selectProcessBusinessList(processBusiness);
        return getDataTable(list);
    }


    /**
     * 查看流程图
     * @param instanceId 流程实例id
     * @return
     */
    @PreAuthorize(hasPermi = "activity:traceProcess:img")
    @GetMapping("/traceProcess/{instanceId}")
    public void traceProcess(@PathVariable("instanceId") String instanceId, HttpServletResponse response) throws Exception
    {
        activityService.traceprocess(instanceId, response);
    }



    /**
     * 查看流程流转详情
     * @param instanceId 流程实例id
     * @return
     */
    @PreAuthorize(hasPermi = "activity:infoCirculation:list")
    @GetMapping("/infoCirculation/{instanceId}")
    public TableDataInfo infoCirculation(@PathVariable("instanceId") String instanceId) throws Exception
    {
        ProcessAuditlog auditlog = new ProcessAuditlog();
        auditlog.setInstanceid(instanceId);
        startPage();
        List<ProcessAuditlog> list = processAuditlogService.selectProcessAuditlogList(auditlog);
        return getDataTable(list);
    }


    /**
     * 查看我的代办任务
     * @return
     */
    @PreAuthorize(hasPermi = "activity:myTask:list")
    @GetMapping("/myTask")
    public TableDataInfo myTask() throws Exception
    {
        startPage();
        List<Map<String, Object>> list = activityService.getTaskList();
        return getDataTable(list);
    }


    /**
     * 审核流程
     * @param dto 流程处理dto
     * @return
     * @throws Exception
     */
    @PreAuthorize(hasPermi = "activity:myTask:complete")
    @PostMapping("/complete")
    public AjaxResult complete(@RequestBody AuditDto dto) throws Exception
    {
        //业务调用请使用此方法
        activityService.complete(dto);
        //把启动流程返回的 流程id和状态字段写入自己的业务表
        return AjaxResult.success();
    }


}
