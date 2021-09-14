package com.logictrue.activity.domain.dto;

import lombok.Data;

/**
 * 用于流程配置  修改业务数据 执行sql 参数dto
 * 用户流程状态变动后 修改业务数据
 */
@Data
public class ActivityDto {

    //表名
    private String tableName;

    //id字段名
    private String btableidfield;

    //id字段值
    private Long btableidValue;

    //状态字段名
    private String btablestatefield;

    //状态字段值
    private String btablestateValue;

    //流程实例id字段名
    private String btableinstanceidfield;

    //流程实例id字段值
    private String btableinstanceidValue;

    //流程创建人id字段名
    private String btableapplyuseridfield;

    //流程创建人id字段值
    private String btableapplyuseridValue;


}
