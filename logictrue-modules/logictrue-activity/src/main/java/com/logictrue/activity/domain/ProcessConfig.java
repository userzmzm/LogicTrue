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
 * 流程配置对象 tb_process_config
 * 
 * @author zhoumin
 * @date 2021-08-11
 */
@Data
@TableName("tb_process_config")
public class ProcessConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 流程名称 */
    @Excel(name = "流程名称")
    private String processname;

    /** 新增时间 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Excel(name = "新增时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addtime;

    /** 新增人 */
    @Excel(name = "新增人")
    private Long adduser;

    /** 修改人 */
    @Excel(name = "修改人")
    private Long updateuser;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatetime;

    /** 一  节点名称 */
    @Excel(name = "一  节点名称")
    private String onename;

    /** 一  节点候选人 */
    @Excel(name = "一  节点候选人")
    private String oneusers;

    /** 一  节点会签  0一人通过  1全部通过 */
    @Excel(name = "一  节点会签  0一人通过  1全部通过")
    private String oneisjointlysign;

    /** 二  节点名称 */
    @Excel(name = "二  节点名称")
    private String twoname;

    /** 二  节点候选人 */
    @Excel(name = "二  节点候选人")
    private String twousers;

    /** 二  节点会签 0一人通过  1全部通过 */
    @Excel(name = "二  节点会签 0一人通过  1全部通过")
    private String twoisjointlysign;

    /** 三  节点名称 */
    @Excel(name = "三  节点名称")
    private String threename;

    /** 三  节点候选人 */
    @Excel(name = "三  节点候选人")
    private String threeusers;

    /** 三  节点会签  0一人通过  1全部通过 */
    @Excel(name = "三  节点会签  0一人通过  1全部通过")
    private String threeisjointlysign;

    /** 业务表名 */
    @Excel(name = "业务表名")
    private String btablename;

    /** 业务表主键ID字段 */
    @Excel(name = "业务表主键ID字段")
    private String btableidfield;

    /** 业务表流程状态字段 */
    @Excel(name = "业务表流程状态字段")
    private String btablestatefield;

    /** 业务表流程实例ID字段 */
    @Excel(name = "业务表流程实例ID字段")
    private String btableinstanceidfield;

    @Excel(name = "业务表流程申请人ID字段")
    private String btableapplyuseridfield;

}
