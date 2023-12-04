package com.chzu.controller;

import com.chzu.service.impl.RedisService;
import com.chzu.utils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisService redisService;

    @GetMapping("/getTextareaByUserId")
    @ApiOperation("根据用户id获取文本框内容信息")
    R<String> getTextareaByUserId(@RequestParam("userId")Integer userId){
        return redisService.getUserContent(userId);
    }

    @GetMapping("/getSuggestionByUserId")
    @ApiOperation("根据用户id获取修改信息")
    R<String> getSuggestionByUserId(@RequestParam("userId")Integer userId){
        return redisService.getSuggestion(userId);
    }

}
