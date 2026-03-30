package com.feng.companyframe.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * description: SwaggerConfig
 * author: 冯凡利
 * create:  2020/2/1 14:37
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    /**
     * 引入 yml 配置文件中的自定义属性
     * 用来控制 swagger2 接口文档的开关，因为在生产环境中，是要关闭掉 swagger2 接口文档的
     */
    @Value("${swagger2.enable}")
    private boolean enable;

    @Bean
    public Docket createRestApi() {
        /*
         * 这是为了我们在用 swagger 测试接口的时候添加头部信息
         * 模拟使用 header 参数，非必填
         * */
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder refreshTokenPar = new ParameterBuilder();

        tokenPar.name("authorization").description("swagger测试用(模拟authorization传入)非必填 header").modelRef(new ModelRef("string")).parameterType("header").required(false);
        refreshTokenPar.name("refresh_token").description("swagger测试用(模拟刷新token传入)非必填 header").modelRef(new ModelRef("string")).parameterType("header").required(false);
        /**
         * 多个的时候 就直接添加到 pars 就可以了
         */
        pars.add(tokenPar.build());
        pars.add(refreshTokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                // 自定义的 描述表头信息
                .apiInfo(apiInfo())
                // 函数返回一个ApiSerlectorBuilder 实例来控制哪些接口暴露给 Swagger ui 来展示
                .select()
                // 指定需要扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("com.feng.companyframe.controller"))
                .paths(PathSelectors.any())
                .build()
                // 添加请求头等信息
                .globalOperationParameters(pars)
                // 设置swagger文档的开关
                .enable(enable);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("智慧社区")
                .description("智慧社区-spring boot 实战系列")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
