package com.logictrue.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 网关启动程序
 * 
 * @author LogicTrue
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class LogicTrueGatewayApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(LogicTrueGatewayApplication.class, args);
        System.out.println("网关启动成功 \n" +
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