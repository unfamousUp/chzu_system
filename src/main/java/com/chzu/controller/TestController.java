package com.chzu.controller;

import com.chzu.mapper.BookMapper;
import com.chzu.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/myTest")
    private com.chzu.entity.Book myTest(){
        com.chzu.entity.Book book = bookMapper.selectBookById(101);
        return book;
    }

    // 文件上传
    @PostMapping("/upload")
    public String upload(MultipartFile file, HttpSession session) throws Exception{
        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();
        // 构建新的文件名
        String newFileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        // 存储到本地服务器上
        String filePath = "G:\\代码\\IDEA\\workspace\\chzu_system\\src\\main\\resources\\myfile\\";
        // 创建目录
        File myFile = new File(filePath);
        System.out.println(myFile);
        if(!myFile.exists()){
            myFile.mkdir();
        }
        // 上传到指定目录
        file.transferTo(new File(filePath+newFileName));
        return "success";
    }

    // @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    @GetMapping("/books/{id}")
    public String getById(@PathVariable("id") Integer id){
        log.info("book getById...",id);
        return "{'moudle:'book getById'}";
    }

    // @RequestMapping(value = "/books", method = RequestMethod.POST)
    @PostMapping("/books")
    public String save(@RequestBody Book book){
        log.info("book save...",book);
        return "{'moudle:'book save'}";
    }

    // @RequestMapping(value = "/books", method = RequestMethod.PUT)
    @PutMapping("/books")
    public String update(@RequestBody Book book){
        log.info("book update...",book);
        return "{'moudle:'book update'}";
    }

    // @RequestMapping(value = "/books", method = RequestMethod.DELETE)
    @DeleteMapping("/books/{id}")
    public String delete(@PathVariable("id") Integer id){
        log.info("book delete...",id);
        return "{'moudle:'book delete'}";
    }

}
