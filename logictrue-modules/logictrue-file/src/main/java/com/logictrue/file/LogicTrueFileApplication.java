package com.logictrue.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.logictrue.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 文件服务
 * 
 * @author logicTrue
 */
@EnableCustomSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class LogicTrueFileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(LogicTrueFileApplication.class, args);
        System.out.println("文件服务模块启动成功  \n" +
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
