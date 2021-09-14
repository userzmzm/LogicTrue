package com.logictrue.quality;

import com.logictrue.common.security.annotation.EnableCustomConfig;
import com.logictrue.common.security.annotation.EnableRyFeignClients;
import com.logictrue.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 仓储模块
 * 
 * @author logicTrue
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class LogicTrueQualityApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(LogicTrueQualityApplication.class, args);
        System.out.println("质检模块启动成功 \n" +
                "                           _ooOoo_\n" +
                "                          o8888888o\n" +
                "                          88\" . \"88\n" +
                "                          (| -_- |)\n" +
                "                          O\\  =  /O\n" +
                "                       ____/`---'\\____\n" +
                "                     .'  \\\\|     |//  `.\n" +
                "                    /  \\\\|||  :  |||//  \\\n" +
                "                   /  _||||| -:- |||||-  \\\n" +
                "                   |   | \\\\\\  -  /// |   |\n" +
                "                   | \\_|  ''\\---/''  |   |\n" +
                "                   \\  .-\\__  `-`  ___/-. /\n" +
                "                 ___`. .'  /--.--\\  `. . __\n" +
                "              .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n" +
                "             | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
                "             \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n" +
                "        ======`-.____`-.___\\_____/___.-`____.-'======\n" +
                "                           `=---='\n" +
                "        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" +
                "               // 佛祖保佑 永不宕机 永无BUG //");
    }
}
