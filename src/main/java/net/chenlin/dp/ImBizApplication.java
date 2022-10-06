package net.chenlin.dp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 应用启动器
 * @author wang<fangyuan.co@outlook.com>
 */
@SpringBootApplication
public class ImBizApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImBizApplication.class);


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ImBizApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
        LOGGER.info("The fang-yuan application has been started successfully!");
    }


}
