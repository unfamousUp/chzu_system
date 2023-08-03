package com.chzu.controller;

import com.alibaba.excel.EasyExcel;
import com.chzu.entity.SensitiveWord;
import com.chzu.utils.ExcelListener;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class FileUploadController {

    @PostMapping("/api/upload")
    @ApiOperation("导入敏感词数据")
    @RequiresRoles("admin")
    public ResponseEntity<String> upload(MultipartFile file) throws IOException {
        if (file == null) {
            System.out.println("Received file is null.");
            return ResponseEntity.badRequest().body("上传的文件为空.");
        }

        System.out.println("Received file: " + file.getOriginalFilename());
         System.out.println("File size: " + file.getSize());

        InputStream inputStream = file.getInputStream();

        // 解析Listener
        ExcelListener excelListener = new ExcelListener();

        // 使用EasyExcel读取Excel
        EasyExcel.read(inputStream, SensitiveWord.class, excelListener).sheet().doRead();

        // 获取数据
        List<SensitiveWord> list = excelListener.getDatas();

        // DemoData为实体类,用来映射Excel行数据
        for (SensitiveWord data : list) {
            // 插入数据库等操作
            System.out.println(data);
        }

        return ResponseEntity.ok("上传成功");
    }

}
