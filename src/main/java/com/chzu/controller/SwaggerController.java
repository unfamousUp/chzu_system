package com.chzu.controller;

import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@RestController
public class SwaggerController {

    @GetMapping("/hello")
    // @ApiOperation("hello控制器方法") // 配置接口显示信息
    public String hello(){
        return "hello";
    }

    /**
     * 只要我们接口的返回值中存在实体类对象
     * 该实体类就会被扫描到Swagger中到页面显示
     * @return
     */
    @PostMapping("/book")
    // @ApiOperation("Book控制器方法") // 配置接口显示信息
    public Book book(@RequestBody Book book){
        return book;
    }

}
