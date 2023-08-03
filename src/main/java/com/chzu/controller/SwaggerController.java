package com.chzu.controller;

import com.chzu.entity.Book;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
public class SwaggerController {

    @GetMapping("/hello")
    @ApiOperation("hello控制器方法") // 配置接口显示信息
    public String hello(){
        return "hello";
    }

    @PostMapping("/testPost")
    @ApiOperation("testPost控制器方法") // 配置接口显示信息
    // 如要使用form表单提交测试Post请求，则不需使用@RequestBody注解
    public Book testPost(Book book){
        return book;
    }

    /**
     * 只要我们接口的返回值中存在实体类对象
     * 该实体类就会被扫描到Swagger中到页面显示
     * @return
     */
    @PostMapping("/book")
    @ApiOperation("Book控制器方法") // 配置接口显示信息
    public Book book(@RequestBody Book book){
        return book;
    }

}
