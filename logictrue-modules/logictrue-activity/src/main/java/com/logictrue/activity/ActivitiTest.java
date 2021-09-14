package com.logictrue.activity;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

/**
 * 生成流程表数据
 *
 * @author logicTrue
 */
public class ActivitiTest {


    /**
     * 生成表结构
     */
    public static  void testGenTable(){
        // 1: 通过资源文件创建 ProcessEngineConfiguration
        ProcessEngineConfiguration processEngineConfiguration =
                ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 2: 通过ProcessEngineConfiguration 构建 ProcessEngine
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println(processEngine);
    }

    public static void main(String[] args) {
        testGenTable();
    }

}
