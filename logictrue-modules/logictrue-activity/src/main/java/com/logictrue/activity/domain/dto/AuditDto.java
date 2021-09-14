package com.logictrue.activity.domain.dto;

import lombok.Data;

/**
 * 接收流程审核参数dto
 * 用户流程审核
 */
@Data
public class AuditDto {

    //流程实例id
    private String instanceid;

    //任务id
    private String taskid;

    //审批动作
    private String approve;

    //审批备注
    private String remark;
}

