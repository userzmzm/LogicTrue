package com.logictrue.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.logictrue.common.security.annotation.EnableRyFeignClients;

/**
 * 认证授权中心
 * 
 * @author logicTrue
 */
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class LogicTrueAuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(LogicTrueAuthApplication.class, args);
        System.out.println("认证授权中心启动成功 \n" +
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
