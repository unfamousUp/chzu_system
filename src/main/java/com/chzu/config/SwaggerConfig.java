package com.chzu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // 配置类
@EnableSwagger2 // 开启Swagger2
public class SwaggerConfig {

    /**
     * 创建API应用
     *
     * @return
     */
    @Bean
    public Docket restApi(Environment environment) {

        // 设置要监视的Swagger环境
        Profiles profiles = Profiles.of("dev","test");
        // 通过该方法判断是否处在设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                // 定义一些我们项目的描述信息☆
                .apiInfo(apiInfo("互联网执法系统API接口文档", "1.0"))
                // 配置分组信息
                .groupName("标准接口")
                // 根据判断当前是否在设置的环境中来决定是否启动swagger
                .enable(flag)
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                // 返回一个ApiSelectorBuilder实例对象,用来控制哪些接口暴露给Swagger来展现
                .select()
                // 扫描接口层路径,指定扫描的包路径来定义指定要建立API的目录
                .apis(RequestHandlerSelectors.basePackage("com.chzu.controller"))
                // 过滤指定路径
                // .paths()
                // 返回docket对象
                .build();
    }

    /**
     * 创建该API的默认文档配置信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://ip:port/swagger-ui.html
     *
     * @param title
     * @param version
     * @return
     */
    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfoBuilder()
                // 标题
                .title(title)
                // 版本号
                .version(version)
                // 描述☆
                .description("互联网执法系统后端接口")
                // 所属组织
                .termsOfServiceUrl("")
                // 作者信息
                .contact(new Contact("陈久佳", "techotaku.top", "510618293@qq.com"))
                // 开源版本号
                .license("Apache 2.0")
                //  官网
                .licenseUrl("http://www.apache.org/licemses/LICENSE-2.0")
                // 返回ApiInfo对象
                .build();
    }

}
