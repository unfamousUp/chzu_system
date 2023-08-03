package com.chzu.config;

import com.chzu.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// 定制web功能要实现WebMvcConfigurer接口
public class AdminWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加注册拦截器
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**") // 拦截所有请求包括静态资源
                .excludePathPatterns("/webjars/**","/swagger-resources","/doc.html")
                .excludePathPatterns("/", "/login", "/css/**", "/js/**"); // 放行
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        // 放行swagger
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
