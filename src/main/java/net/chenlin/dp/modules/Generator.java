package net.chenlin.dp.modules;

import net.chenlin.dp.modules.sys.generator.JdbcGenUtils;

/**
 * 代码生成器
 * @author wang<fangyuan.co@outlook.com>
 */
public class Generator {

    public static void main(String[] args) throws Exception {

        String jdbcDriver = "com.mysql.jdbc.Driver";
        String jdbcUrl = "jdbc:mysql://18.140.249.227:3306/web_domain?useUnicode=true&characterEncoding=utf-8";
        String jdbcUsername = "root";
        String jdbcPassword = "qwer1234";

        String tablePrefix = "gen_";

        String javaModule = "test";
        String webModule = "test";

        JdbcGenUtils.generatorCode(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword, tablePrefix, javaModule, webModule);

    }

}
