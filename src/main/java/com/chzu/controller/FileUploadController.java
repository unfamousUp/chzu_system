package com.chzu.controller;

import com.alibaba.excel.EasyExcel;
import com.chzu.entity.Events;
import com.chzu.entity.Organizations;
import com.chzu.entity.SensitiveWord;
import com.chzu.mapper.EventsMapper;
import com.chzu.mapper.OrganizationsMapper;
import com.chzu.utils.ExcelListener;
import com.chzu.utils.OrgExcelListener;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@Api(tags="文件上传")
public class FileUploadController {
    @Autowired
    EventsMapper eventsMapper;

    @Autowired
    OrganizationsMapper organizationsMapper;
    // @PostMapping("/api/uploadSensitiveWord")
    // @ApiOperation("导入敏感词数据")
    // @RequiresRoles("admin")
    // public ResponseEntity<String> uploadSensitiveWord(MultipartFile file) throws IOException {
    //     if (file == null) {
    //         System.out.println("Received file is null.");
    //         return ResponseEntity.badRequest().body("上传的文件为空.");
    //     }
    //
    //     System.out.println("Received file: " + file.getOriginalFilename());
    //      System.out.println("File size: " + file.getSize());
    //
    //     InputStream inputStream = file.getInputStream();
    //
    //     // 解析Listener
    //     ExcelListener excelListener = new ExcelListener();
    //
    //     // 使用EasyExcel读取Excel
    //     EasyExcel.read(inputStream, SensitiveWord.class, excelListener).sheet().doRead();
    //
    //     // 获取数据
    //     List<SensitiveWord> list = excelListener.getDatas();
    //
    //     // DemoData为实体类,用来映射Excel行数据
    //     for (SensitiveWord data : list) {
    //         // 插入数据库等操作
    //         System.out.println(data);
    //     }
    //
    //     return ResponseEntity.ok("上传成功");
    // }

    @PostMapping("/api/uploadEvents")
    @ApiOperation("导入事件信息")
    // @RequiresRoles("admin")
    public ResponseEntity<String> uploadEvents(MultipartFile file) throws IOException {
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
        EasyExcel.read(inputStream, Events.class, excelListener).sheet().doRead();

        // 获取数据
        List<Events> list = excelListener.getDatas();

        // DemoData为实体类,用来映射Excel行数据
        for (Events data : list) {
            // 插入数据库等操作
            System.out.println(data);
            eventsMapper.addEventsInfo(data);
        }

        return ResponseEntity.ok("上传成功");
    }

    @PostMapping("/api/uploadOrgInfo")
    @ApiOperation("上传机构信息")
    // @RequiresRoles("admin")
    public ResponseEntity<String> uploadOrgInfo(MultipartFile file) throws IOException {
        if (file == null) {
            System.out.println("Received file is null.");
            return ResponseEntity.badRequest().body("上传的文件为空.");
        }

        System.out.println("Received file: " + file.getOriginalFilename());
        System.out.println("File size: " + file.getSize());

        InputStream inputStream = file.getInputStream();

        // 解析Listener
        OrgExcelListener orgExcelListener = new OrgExcelListener();

        // 使用EasyExcel读取Excel
        EasyExcel.read(inputStream, Organizations.class, orgExcelListener).sheet().doRead();

        // 获取数据
        List<Organizations> list = orgExcelListener.getDatas();

        // DemoData为实体类,用来映射Excel行数据
        for (Organizations data : list) {
            // 插入数据库等操作
            System.out.println(data);
            organizationsMapper.insertOrgInfo(data);
        }

        return ResponseEntity.ok("上传成功");
    }

}
