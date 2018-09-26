package com.qcap.core.config;


import com.qcap.core.properties.FameProperties;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

///import com.whxx.core.properties.V4wardProperties;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
	private FameProperties espProperties;

    @Bean
    public Docket createRestApi() {
        //根据配置esp.swaggerEnable 决定是否开启swagger
        if ("true".equalsIgnoreCase(espProperties.getSwaggerEnable())) {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                    .paths(PathSelectors.any())
                    .build();
        }else {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(disInfo())
                    .select()
                    .apis(RequestHandlerSelectors.none())
                    .paths(PathSelectors.any())
                    .build();
        }
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    private ApiInfo disInfo() {
        return new ApiInfoBuilder()
                .title("未开启Swagger-ui")
                .description("查看相关配置是否正确")
                .build();
    }
}
