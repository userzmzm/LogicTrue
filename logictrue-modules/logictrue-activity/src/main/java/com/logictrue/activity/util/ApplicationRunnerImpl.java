package com.logictrue.activity.util;

import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@EnableScheduling
public class ApplicationRunnerImpl  implements ApplicationRunner {

    @Autowired
    RepositoryService repositoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("通过实现ApplicationRunner接口，在spring boot项目启动后打印参数");
        //部署流程图
        System.out.println("Activiti部署流程图");
        Resource[] resources = null;
        try {
            resources = new PathMatchingResourcePatternResolver().getResources("classpath*:processes/*.bpmn");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Resource r : resources) {
            String addr = "processes/" + r.getFilename();
            repositoryService.createDeployment().addClasspathResource(addr).deploy();
        }

    }

}
