package com.logictrue.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.logictrue.activity.domain.ProcessAuditlog;
import com.logictrue.activity.domain.ProcessBusiness;
import com.logictrue.activity.domain.ProcessConfig;
import com.logictrue.activity.domain.dto.ActivityDto;
import com.logictrue.activity.domain.dto.AuditDto;
import com.logictrue.activity.mapper.ActivityMapper;
import com.logictrue.activity.mapper.ProcessAuditlogMapper;
import com.logictrue.activity.mapper.ProcessBusinessMapper;
import com.logictrue.activity.mapper.ProcessConfigMapper;
import com.logictrue.activity.service.ActivityService;
import com.logictrue.activity.util.BpmnUitl;
import com.logictrue.activity.util.PinyinUtils;
import com.logictrue.common.core.exception.BaseException;
import com.logictrue.common.security.service.TokenService;
import com.logictrue.system.api.model.LoginUser;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.apache.commons.io.IOUtils;


import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 流程Service业务层处理
 * @author zhoumin
 * @date 2021-08-11
 */
@Service(value = "ActivityService")
public class ActivityServiceImpl implements ActivityService {

    /**
     * 项目路径
     */
    String projectPath = System.getProperty("user.dir");

    /**
     * 流程状态比较值
     */
    String noSubmit = "待提交";
    String reject = "驳回";
    String adopt = "流程通过";

    /**
     * 审批动作  Y同意  N拒绝
     */
    String ApproveY = "Y";
    String ApproveN = "N";

    /**
     * 流程通过条件  0一人通过   1全部通过
     */
    String SignY = "0";
    String SignA = "1";

    /**
     * 流程组件service
     */
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    TaskService taskservice;
    @Autowired
    RuntimeService runservice;
    @Autowired
    HistoryService histiryservice;
    @Autowired
    IdentityService identityService;

    /**
     * 流程配置
     */
    @Autowired
    ProcessConfigMapper configMapper;

    /**
     * 流程业务数据
     */
    @Autowired
    ProcessBusinessMapper businessMapper;

    /**
     * 流程日志
     */
    @Autowired
    ProcessAuditlogMapper logMapper;

    /**
     * 用户信息
     */
    @Autowired
    private TokenService tokenService;

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public void createBpmn(ProcessConfig config) throws IOException {
        System.out.println(projectPath);

        String beenName = PinyinUtils.getPingYin(config.getProcessname());
        int i = 1;
        BpmnModel bpmnModel = new BpmnModel();
        Process process = new Process();
        process.setId(beenName);
        process.setName(config.getProcessname());
        // 开始节点
        process.addFlowElement(BpmnUitl.createStartEvent());
        // 开始节点 ------》 发起申请
        process.addFlowElement(BpmnUitl.creatSequenceFlow("sl" + i, null, "start", "apply", null));
        i = i + 1;
        // 发起申请
        process.addFlowElement(BpmnUitl.createUserTask("${applyuserid}", "apply", "发起申请", null, null, null,
                "com.logictrue.activity.listener.OneNodeListener"));
        // 发起申请------》审核节点1
        process.addFlowElement(BpmnUitl.creatSequenceFlow("sl" + i, "", "apply", "audit_one", ""));
        i = i + 1;
        // 审核节点1
        process.addFlowElement(BpmnUitl.createUserTask("${one}", "audit_one", config.getOnename(), "OneList", "one",
                "${nrOfCompletedInstances/nrOfInstances  >= OneIs}", "com.logictrue.activity.listener.TwoNodeListener"));
        // 审核节点1------》排他网关1
        process.addFlowElement(BpmnUitl.creatSequenceFlow("sl" + i, "", "audit_one", "judgeTwo-1", ""));
        i = i + 1;
        // 节点1-网关
        process.addFlowElement(BpmnUitl.creatExclusiveGateway("judgeTwo-1", "节点1-网关"));

        /**
         * 网关出来一般两条线 且带条件 审批动作 Y/N
         */
        // 节点1-网关------》调整申请
        process.addFlowElement(
                BpmnUitl.creatSequenceFlow("sl" + i, "拒绝", "judgeTwo-1", "modifyApply", "${result == 'N'}"));
        i = i + 1;
        // 调整申请
        process.addFlowElement(
                BpmnUitl.createUserTask("${applyuserid}", "modifyApply", "调整申请", null, null, null, null));
        /**
         * 调整申请 默认两条线
         */
        // 调整申请------》流程结束
        process.addFlowElement(BpmnUitl.creatSequenceFlow("sl" + i, "流程作废", "modifyApply", "end", "${ modify == 'N'}"));
        i = i + 1;
        // 调整申请------》审核节点1
        process.addFlowElement(
                BpmnUitl.creatSequenceFlow("sl" + i, "重新申请", "modifyApply", "audit_one", "${ modify == 'Y'}"));
        i = i + 1;

        /**
         * 两个审核节点的处理情况
         */
        if (config.getTwoname() != null && !config.getTwoname().equals("")) {
            // 节点1-网关------》审核节点2
            process.addFlowElement(
                    BpmnUitl.creatSequenceFlow("sl" + i, "同意", "judgeTwo-1", "audit_two", "${result == 'Y'}"));
            i = i + 1;
            // 审核节点2
            process.addFlowElement(BpmnUitl.createUserTask("${two}", "audit_two", config.getTwoname(), "TwoList", "two",
                    "${nrOfCompletedInstances/nrOfInstances  >= TwoIs}", "com.logictrue.activity.listener.ThreeNodeListener"));
            // 审核节点2------》排他网关2
            process.addFlowElement(BpmnUitl.creatSequenceFlow("sl" + i, "", "audit_two", "judgeTwo-2", ""));
            i = i + 1;
            // 节点2-网关
            process.addFlowElement(BpmnUitl.creatExclusiveGateway("judgeTwo-2", "节点2-网关"));
            /**
             * 网关出来一般两条线 且带条件 审批动作 Y/N
             */
            // 节点2-网关------》调整申请
            process.addFlowElement(
                    BpmnUitl.creatSequenceFlow("sl" + i, "拒绝", "judgeTwo-2", "modifyApply", "${result == 'N'}"));
            i = i + 1;
            /**
             * 三个审核节点的处理情况
             */
            if (config.getThreename() != null && !config.getThreename().equals("")) {
                // 节点2-网关------》审核节点3
                process.addFlowElement(
                        BpmnUitl.creatSequenceFlow("sl" + i, "同意", "judgeTwo-2", "audit_three", "${result == 'Y'}"));
                i = i + 1;
                // 审核节点3
                process.addFlowElement(BpmnUitl.createUserTask("${three}", "audit_three", config.getThreename(),
                        "ThreeList", "three", "${nrOfCompletedInstances/nrOfInstances  >= ThreeIs}", null));
                // 审核节点3------》排他网关3
                process.addFlowElement(BpmnUitl.creatSequenceFlow("sl" + i, "", "audit_three", "judgeTwo-3", ""));
                i = i + 1;
                // 节点3-网关
                process.addFlowElement(BpmnUitl.creatExclusiveGateway("judgeTwo-3", "节点3-网关"));
                /**
                 * 网关出来一般两条线 且带条件 审批动作 Y/N
                 */
                // 节点3-网关------》调整申请
                process.addFlowElement(
                        BpmnUitl.creatSequenceFlow("sl" + i, "拒绝", "judgeTwo-3", "modifyApply", "${result == 'N'}"));
                i = i + 1;
                // 节点3-网关------》流程结束
                process.addFlowElement(
                        BpmnUitl.creatSequenceFlow("sl" + i, "同意", "judgeTwo-3", "end", "${result == 'Y'}"));
                i = i + 1;
            } else {
                // 节点2-网关------》流程结束
                process.addFlowElement(
                        BpmnUitl.creatSequenceFlow("sl" + i, "同意", "judgeTwo-2", "end", "${result == 'Y'}"));
                i = i + 1;
            }
        } else {
            // 节点1-网关------》流程结束
            process.addFlowElement(BpmnUitl.creatSequenceFlow("sl" + i, "同意", "judgeTwo-1", "end", "${result == 'Y'}"));
            i = i + 1;
        }
        process.addFlowElement(BpmnUitl.createEndEvent());
        bpmnModel.addProcess(process);

        // 生成图形信息
        new BpmnAutoLayout(bpmnModel).execute();

        // 部署流程图
        Deployment deployment = repositoryService.createDeployment().addBpmnModel(beenName + ".bpmn", bpmnModel).deploy();

        // 保存图片及bpmn
        List<String> names = repositoryService.getDeploymentResourceNames(deployment.getId());
        for (String name : names) {
            System.out.print(name);
            InputStream in = repositoryService.getResourceAsStream(deployment.getId(), name);
            if (name.contains("bpmn")) {
                name = beenName + ".bpmn";
            }
            if (name.contains("png")) {
                name = beenName + ".png";
            }
            OutputStream out = new FileOutputStream(new File(projectPath+"/logictrue-modules/logictrue-activity/src/main/java/processes/" + name));
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            out.close();
            in.close();
        }
    }

    @Override
    public void  start(Long configId, Long tableId) {
        // 取得流程配置信息
        ProcessConfig config = configMapper.selectProcessConfigById(configId);

        //取得业务数据
        //select state,applyuser,instanceid from tableName where id = ?
        ActivityDto dto = new ActivityDto();
        dto.setBtablestatefield(config.getBtablestatefield());
        dto.setBtableapplyuseridfield(config.getBtableapplyuseridfield());
        dto.setBtableinstanceidfield(config.getBtableinstanceidfield());
        dto.setTableName(config.getBtablename());
        dto.setBtableidfield(config.getBtableidfield());
        dto.setBtableidValue(tableId);
        Map<String, Object> Info = activityMapper.getBusinessInfo(dto);

        String state = Info.get(config.getBtablestatefield())+"";
        Long createUser = Long.valueOf(Info.get(config.getBtableapplyuseridfield()).toString());

        //获取当前登录人
        LoginUser loginUser = tokenService.getLoginUser();
        Long userId = loginUser.getUserid();

        //判断流程状态
        if (!state.contains(noSubmit)  &&  !state.contains(reject)) {
            throw new BaseException("此流程，不支持提交！");
        }
        //比对流程创建人
        if( createUser.longValue() != userId.longValue() ) {
            throw new BaseException("此流程，您没有权限操作!");
        }

        Map<String, Object> result = new HashMap<>();
        if(state.contains(noSubmit)){
            //流程状态为待提交时 调用此方法提交 发起流程
            result = Nsubmit(config, tableId, userId);
        }else if(state.contains(reject)){
            String instanceId = Info.get(config.getBtableinstanceidfield())+"";
            //流程状态包含驳回时 调用此方法提交 发起流程
            result = Rsubmit(config, instanceId, userId);
        }
        //回写业务数据
        //update tableName set state = ? and InstanceId = ? where id = ?
        String instanceId = result.get("instanceid")+"";
        String struts = result.get("struts")+"";
        //重新定义请求参数
        dto = new ActivityDto();
        dto.setTableName(config.getBtablename());
        dto.setBtableinstanceidfield(config.getBtableinstanceidfield());
        dto.setBtableinstanceidValue(instanceId);
        dto.setBtablestatefield(config.getBtablestatefield());
        dto.setBtablestateValue(struts);
        dto.setBtableidfield(config.getBtableidfield());
        dto.setBtableidValue(tableId);
        activityMapper.updateByIdBusiness(dto);
    }

    @Override
    public void traceprocess(String instanceId, HttpServletResponse response) throws Exception {
        ProcessInstance process = runservice.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
        BpmnModel bpmnmodel = repositoryService.getBpmnModel(process.getProcessDefinitionId());
        List<String> activeActivityIds = runservice.getActiveActivityIds(instanceId);
        DefaultProcessDiagramGenerator gen = new DefaultProcessDiagramGenerator();
        // 获得历史活动记录实体（通过启动时间正序排序，不然有的线可以绘制不出来）
        List<HistoricActivityInstance> historicActivityInstances = histiryservice.createHistoricActivityInstanceQuery()
                .executionId(instanceId).orderByHistoricActivityInstanceStartTime().asc().list();
        // 计算活动线
        List<String> highLightedFlows = getHighLightedFlows(
                        (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                                .getDeployedProcessDefinition(process.getProcessDefinitionId()),
                        historicActivityInstances);

        InputStream in = gen.generateDiagram(bpmnmodel, "png", activeActivityIds, highLightedFlows, "宋体", "宋体", null,
                null, 1.0);
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(in, output);
    }

    @Override
    public List<Map<String, Object>> getTaskList() {
        LoginUser loginUser = tokenService.getLoginUser();
        Long userId = loginUser.getUserid();

         // 取得所有待处理任务数据
        List<Task> taskList = taskservice.createNativeTaskQuery().sql(
                "SELECT DISTINCT RES.* FROM ACT_RU_TASK RES WHERE RES.ASSIGNEE_ = #{ASSIGNEE_} AND RES.TASK_DEF_KEY_ LIKE '%audit%'")
                .parameter("ASSIGNEE_", userId).list();

        List<String> instanceIdList = new ArrayList<>();
        Map<String, Object> taskInfo = new HashMap<String, Object>();

        for (Task task : taskList) {
            String instanceId = task.getProcessInstanceId();
            // 得到业务数据 流程实例id
            instanceIdList.add(instanceId);
            taskInfo.put(instanceId, task.getId());
            taskInfo.put(instanceId+"Name", task.getName());
        }

        QueryWrapper<ProcessBusiness> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(" * ");
        queryWrapper.in("InstanceId",instanceIdList);
        List<Map<String, Object>> list = businessMapper.selectMaps(queryWrapper);

        for (Map<String, Object> map : list) {
            map.put("taskId", taskInfo.get(map.get("InstanceId").toString()));
            map.put("taskName", taskInfo.get(map.get("InstanceId") + "Name"));
        }

        return list;
    }

    @Override
    public void complete(AuditDto auditDto) throws Exception {
        //取得用户数据
        LoginUser loginUser = tokenService.getLoginUser();
        Long userId = loginUser.getUserid();

        //取得流程业务数据
        QueryWrapper<ProcessBusiness> bQuery = new QueryWrapper<>();
        bQuery.select(" * ");
        bQuery.eq("InstanceId",auditDto.getInstanceid());
        ProcessBusiness newBusiness = businessMapper.selectOne(bQuery);
        // 取得流程配置数据
        QueryWrapper<ProcessConfig> pQuery = new QueryWrapper<>();
        pQuery.select(" * ");
        pQuery.eq("Id", newBusiness.getConfigid());
        ProcessConfig config = configMapper.selectOne(pQuery);

        //定义流程状态
        String state;
        //判断状态
        if (newBusiness==null) {
            throw new BaseException("此流程，不支持提交！");
        }else if (newBusiness.getState().contains(reject)){
            throw new BaseException("此流程，不支持提交！");
        }else if (newBusiness.getState().contains(adopt)){
            throw new BaseException("此流程，不支持提交！");
        }else {
            state = newBusiness.getState();
        }

        // 取得任务数据
        Task task = taskservice.createTaskQuery().taskId(auditDto.getTaskid()).singleResult();

        // 取得当前流程所有任务
        List<Task> list = taskservice.createTaskQuery()
                .processInstanceId(String.valueOf(newBusiness.getInstanceid())).list();

        // 存储审批动作
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("result", auditDto.getApprove());

        /**
         * 一人审批 且同意
         */
        if (list != null && list.size() == 1 && ApproveY.equals(auditDto.getApprove())) {
            // 第一节点审核
            if (task.getName().equals(config.getOnename())) {
                if (config.getTwoname() != null && !"".equals(config.getTwoname())) {
                    state = "待" + config.getTwoname();
                } else {
                    state = adopt;
                }

                //判断权限
                if (!config.getOneusers().contains(userId.toString())) {
                    throw new BaseException("此申请单，你没有权限审核!");
                }

            }

            // 第二节点审核
            if (task.getName().equals(config.getTwoname())) {
                if (config.getThreename() != null && !"".equals(config.getThreename())) {
                    state = "待" + config.getThreename();
                } else {
                    state = adopt;
                }

                //判断权限
                if (!config.getTwousers().contains(userId.toString())) {
                    throw new BaseException("此申请单，你没有权限审核!");
                }

            }

            // 第三节点审核
            if (task.getName().equals(config.getThreename())) {
                state = adopt;

                //判断权限
                if (!config.getThreeusers().contains(userId.toString())) {
                    throw new BaseException("此申请单，你没有权限审核!");
                }
            }

            //处理流程数据
            taskservice.addComment(auditDto.getTaskid(), String.valueOf(newBusiness.getInstanceid()),
                    auditDto.getRemark());
            taskservice.complete(auditDto.getTaskid(), variables);

            /**
             * 多人审批 且同意
             */
        } else if (list != null && list.size() > 1 && ApproveY.equals(auditDto.getApprove())) {
            // 第一节点审核
            if (task.getName().equals(config.getOnename())) {
                if (SignY.equals(config.getOneisjointlysign())) {
                    if (config.getTwoname() != null && !"".equals(config.getTwoname())) {
                        state = "待" + config.getTwoname();
                    } else {
                        state = adopt ;
                    }
                }

                //判断权限
                if (!config.getOneusers().contains(userId.toString())) {
                    throw new BaseException("此申请单，你没有权限审核!");
                }
            }

            // 第二节点审核
            if (task.getName().equals(config.getTwoname())) {
                if (SignY.equals(config.getThreeisjointlysign())) {
                    if (config.getThreename() != null && !"".equals(config.getThreename())) {
                        state = "待" + config.getThreename();
                    } else {
                        state = adopt;
                    }
                }


                //判断权限
                if ( !config.getTwousers().contains(userId.toString()) ) {
                    throw new BaseException("此申请单，你没有权限审核!");
                }
            }

            // 第三节点审核
            if (task.getName().equals(config.getThreename())) {
                if (SignY.equals(config.getTwoisjointlysign())) {
                    state = adopt;
                }

                //判断权限
                if ( !config.getThreeusers().contains(userId.toString())) {
                    throw new BaseException("此申请单，你没有权限审核!");
                }
            }

            //处理流程数据
            taskservice.addComment(auditDto.getTaskid(), String.valueOf(newBusiness.getInstanceid()),
                    auditDto.getRemark());
            taskservice.complete(auditDto.getTaskid(), variables);

        } else if (ApproveN.equals(auditDto.getApprove())) {
            state = task.getName() + "驳回";
                 //一节点
            if (task.getName().equals(config.getOnename()) ) {

                //判断权限
                if ( !config.getOneusers().contains(userId.toString()) ) {
                    throw new BaseException("此申请单，你没有权限审核!");
                }

                if (SignA.equals(config.getOneisjointlysign())) {
                    for (Task t : list) {
                        taskservice.addComment(t.getId(), String.valueOf(newBusiness.getInstanceid()),
                                auditDto.getRemark());
                        taskservice.complete(t.getId(), variables);
                    }
                } else {
                    taskservice.addComment(task.getId(), String.valueOf(newBusiness.getInstanceid()),
                            auditDto.getRemark());
                    taskservice.complete(task.getId(), variables);
                }
                //二节点
            } else if ( task.getName().equals(config.getTwoname() )) {

                //判断权限
                if (!config.getTwousers().contains(userId.toString())) {
                    throw new BaseException("此申请单，你没有权限审核!");
                }

                if (SignA.equals(config.getTwoisjointlysign())) {
                    for (Task t : list) {
                        taskservice.addComment(t.getId(), String.valueOf(newBusiness.getInstanceid()),
                                auditDto.getRemark());
                        taskservice.complete(t.getId(), variables);
                    }
                } else {
                    taskservice.addComment(task.getId(), String.valueOf(newBusiness.getInstanceid()),
                            auditDto.getRemark());
                    taskservice.complete(task.getId(), variables);
                }
                //三节点
            } else if (task.getName().equals(config.getThreename())) {


                //判断权限
                if ( !config.getThreeusers().contains(userId.toString()) ) {
                    throw new BaseException("此申请单，你没有权限审核!");
                }

                if (SignA.equals(config.getThreeisjointlysign())) {
                    for (Task t : list) {
                        taskservice.addComment(t.getId(), String.valueOf(newBusiness.getInstanceid()),
                                auditDto.getRemark());
                        taskservice.complete(t.getId(), variables);
                    }
                } else {
                    taskservice.addComment(task.getId(), String.valueOf(newBusiness.getInstanceid()),
                            auditDto.getRemark());
                    taskservice.complete(task.getId(), variables);
                }
            }
        }

        // 修改流程业务数据
        newBusiness.setState(state);
        businessMapper.updateProcessBusiness(newBusiness);


        /**
         * 生成处理记录
         */
        ProcessAuditlog log = new ProcessAuditlog();
        log.setAddtime(new Date());
        log.setAdduser(userId);
        log.setInstanceid(newBusiness.getInstanceid());
        if (ApproveY.equals(auditDto.getApprove())) {
            log.setApprove("Y");
        } else {
            log.setApprove("N");
        }
        log.setTaskname(task.getName());
        log.setRemark(auditDto.getRemark());
        logMapper.insertProcessAuditlog(log);


        //更新业务表状态
        //update tableName set state = ? where InstanceId = ?
        ActivityDto dto = new ActivityDto();
        dto.setTableName(config.getBtablename());
        dto.setBtablestatefield(config.getBtablestatefield());
        dto.setBtablestateValue(state);
        dto.setBtableinstanceidfield(config.getBtableinstanceidfield());
        dto.setBtableinstanceidValue(newBusiness.getInstanceid());
        activityMapper.updateByInsIdBusiness(dto);

    }


    /**
     * 流程状态为待提交时 调用此方法提交 发起流程
     * @param config   流程配置
     * @param tableId  业务表ID
     * @param userId   用户ID
     */
    public Map<String, Object> Nsubmit(ProcessConfig config, Long tableId, Long userId){
        // 得到流程名称
        String bmpnName = PinyinUtils.getPingYin(config.getProcessname());
        // 保存业务数据
        ProcessBusiness business = new ProcessBusiness();
        business.setApplytime(new Date());
        business.setApplyuser(userId);
        business.setConfigid(config.getId());
        business.setState("待" + config.getOnename());
        businessMapper.insertProcessBusiness(business);
        // 保存业务id
        String businessKey = String.valueOf(business.getId());
        System.out.println(businessKey);
        // 通过流程组件保存用户信息
        identityService.setAuthenticatedUserId(String.valueOf(userId));
        // 流程开始 得到流程实例
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("applyuserid", userId);
        // 节点定义处理人员
        // 第一审批节点
        if (config.getOneusers() != null && !config.getOneusers().equals("")) {
            variables.put("OneUsers", config.getOneusers());
            variables.put("OneIs", config.getOneisjointlysign());
        }
        // 第二审批节点
        if (config.getTwousers() != null && !config.getTwousers().equals("")) {
            variables.put("TwoUsers", config.getTwousers());
            variables.put("TwoIs", config.getTwoisjointlysign());
        }
        // 第三审批节点
        if (config.getThreeusers() != null && !config.getThreeusers().equals("")) {
            variables.put("ThreeUsers", config.getThreeusers());
            variables.put("ThreeIs", config.getThreeisjointlysign());
        }
        // leave bpmn名称 businesskey连接数据 variables {userid:xx}
        ProcessInstance instance = runservice.startProcessInstanceByKey(bmpnName, businessKey, variables);
        // 得到流程实例id
        String instanceId = instance.getId();
        System.out.println(instanceId);
        System.out.println("流程已启动,id:" + instance.getId());
        // 处理业务数据
        business.setInstanceid(instance.getId());
        businessMapper.updateProcessBusiness(business);
        // 根据任务名称查询
        List<Task> taskList = taskservice.createNativeTaskQuery().sql(
                "SELECT DISTINCT RES.* FROM ACT_RU_TASK RES WHERE RES.ASSIGNEE_ = #{ASSIGNEE_} AND RES.PROC_INST_ID_ = #{PROC_INST_ID_} ")
                .parameter("ASSIGNEE_", userId).parameter("PROC_INST_ID_", instance.getId()).list();
        // 处理申请流程
        for (Task task : taskList) {
            taskservice.addComment(task.getId(), task.getProcessInstanceId(), "发起申请");
            taskservice.complete(task.getId(), variables);
        }

        //写入日志
        ProcessAuditlog log = new ProcessAuditlog();
        log.setApprove(ApproveY);
        log.setAddtime(new Date());
        log.setAdduser(Long.valueOf(userId));
        log.setInstanceid(instance.getId());
        log.setRemark("流程启动");
        log.setTaskname("发起申请");
        logMapper.insertProcessAuditlog(log);

        Map<String, Object> result = new HashMap<>();
        result.put("instanceid", instanceId);
        result.put("struts", "待" + config.getOnename());
        return result;
    }


    /**
     * 当流程状态为被驳回的情况下使用此方法提交
     * @param config   流程配置
     * @param instanceId   业务表流程实例id
     * @param userId     用户id
     * @return
     */
    public  Map<String, Object> Rsubmit(ProcessConfig config, String instanceId  ,Long userId){
        // 得到已有流程数据
        QueryWrapper<ProcessBusiness> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(" * ");
        queryWrapper.eq("instanceid",instanceId);
        ProcessBusiness newBusiness = businessMapper.selectOne(queryWrapper);
        // 查询当前流程所有任务
        List<Task> list = taskservice.createTaskQuery()
                .processInstanceId(String.valueOf(newBusiness.getInstanceid())).list();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("modify", "Y");

        ProcessAuditlog log = new ProcessAuditlog();
        // 处理任务
        for (Task task : list) {
            // 修改业务数据
            newBusiness.setState("待" + config.getOnename());
            businessMapper.updateProcessBusiness(newBusiness);
            // 备份处理日志
            log.setApprove(ApproveY);
            log.setAddtime(new Date());
            log.setAdduser(userId);
            log.setInstanceid(instanceId);
            log.setRemark("流程继续");
            log.setTaskname(task.getName());
            logMapper.insertProcessAuditlog(log);
            // 处理流程数据
            taskservice.complete(task.getId(), variables);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("instanceid", newBusiness.getInstanceid());
        result.put("struts", "待" + config.getOnename());
        return result;
    }


    /**
     * 根据流程实时状态 得到实时流程图
     * @return
     */
    public List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinitionEntity,  List<HistoricActivityInstance> historicActivityInstances) {
        // 用以保存高亮的线flowId
        List<String> highFlows = new ArrayList<String>();
        // 对历史流程节点进行遍历
        for (int i = 0; i < historicActivityInstances.size(); i++) {
            // 得到节点定义的详细信息
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i).getActivityId());
            // 用以保存后需开始时间相同的节点
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();
            if ((i + 1) >= historicActivityInstances.size()) {
                break;
            }
            // 将后面第一个节点放在时间相同节点的集合里
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1).getActivityId());
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                // 后续第一个节点
                HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);
                // 后续第二个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);
                // 如果第一个节点和第二个节点开始时间相同保存
                if (activityImpl1.getStartTime().equals(activityImpl2.getStartTime())) {
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            // 取出节点的所有出去的线
            List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
            // 对所有的线进行遍历
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }

}
