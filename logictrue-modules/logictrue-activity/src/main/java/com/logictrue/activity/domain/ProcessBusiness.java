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
 * 流程业务数据对象 tb_process_business
 * 
 * @author zhoumin
 * @date 2021-08-11
 */
@Data
@TableName("tb_process_business")
public class ProcessBusiness extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    /** $column.columnComment */
    private Long id;

    /** 流程配置id */
    @Excel(name = "流程配置id")
    private Long configid;

    /** 流程实例id */
    @Excel(name = "流程实例id")
    private String instanceid;

    /** 申请人 */
    @Excel(name = "申请人")
    private Long applyuser;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date applytime;

    /** 状态 */
    @Excel(name = "状态")
    private String state;
}
