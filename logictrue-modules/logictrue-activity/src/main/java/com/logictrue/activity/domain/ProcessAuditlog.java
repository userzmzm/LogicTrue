package com.logictrue.activity.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.logictrue.common.core.annotation.Excel;
import com.logictrue.common.core.web.domain.BaseEntity;

/**
 * 流程日志对象 tb_process_auditlog
 * 
 * @author zhoumin
 * @date 2021-08-11
 */
@Data
@TableName("tb_process_auditlog")
public class ProcessAuditlog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 流程实例id */
    @Excel(name = "流程实例id")
    private String instanceid;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskname;

    /** 审批动作 */
    @Excel(name = "审批动作")
    private String approve;

    /** 新增人 */
    @Excel(name = "新增人")
    private Long adduser;

    /** 新增时间 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Excel(name = "新增时间", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date addtime;


}
