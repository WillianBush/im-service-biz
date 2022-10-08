package net.chenlin.dp.common.support.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置
 * @author wang<fangyuan.co@outlook.com>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger2.enable:false}")
    private boolean enable;


    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.chenlin.dp.modules"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .enable(enable);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("im-service-biz Rest Api Doc")
                .description("Gp Api 文档")
                .termsOfServiceUrl("https://gitee.com/dp_group")
                .contact(new Contact("Mr.lin", "www.chenlintect.com", "fangyuan.co@outlook.com"))
                .version("1.0.0")
                .build();
    }




    private List<SecurityScheme> securitySchemes() {
        //设置请求头信息
        List<SecurityScheme> result = new ArrayList<>();
        SecurityScheme apiKey = new ApiKey("Authorization", "token", "header");
        result.add(apiKey);
        return result;
    }


}
