package net.chenlin.dp.common.support.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import net.chenlin.dp.common.support.interceptor.RestApiInterceptor;
import net.chenlin.dp.common.support.properties.GlobalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

/**
 * web配置
 * @author wang<fangyuan.co@outlook.com>
 */
@DependsOn("springContextUtils")
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    GlobalProperties globalProperties;

    /**
     * 文件上传路径虚拟映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册rest拦截器
        registry.addInterceptor(new RestApiInterceptor()).addPathPatterns("/**").excludePathPatterns("/login","/health-check","/swagger-ui.html","/error","/logout");
    }

    /**
     * xssFilter注册
     * @return
     */
//    @Bean
//    public FilterRegistrationBean xssFilterRegistration() {
//        XssFilter xssFilter = new XssFilter();
////        xssFilter.setUrlExclusion(Arrays.asList("/rest/testAnon"));
//        FilterRegistrationBean registration = new FilterRegistrationBean(xssFilter);
//        registration.setOrder(Integer.MAX_VALUE);
//        registration.addUrlPatterns("/*");
//        return registration;
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String mapping = "/**";
        String origins = "*"; //写成String origins = "*"; 错误，这时候需要指定明确的域
        String methods = "*";
        Boolean allowCredentials = true; // 表示是否允许携带cookie  //解决Session问题
        registry
                .addMapping(mapping)
                .allowedOrigins(origins)
                .allowedMethods(methods)
                .allowCredentials(allowCredentials);
    }

    /**
     * 验证码生成相关
     */
    @Bean
    public DefaultKaptcha kaptcha() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.image.width", "136");
        properties.put("kaptcha.image.height", "50");
        properties.put("kaptcha.textproducer.char.space", "3");
        properties.put("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
