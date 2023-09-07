package com.chzu.controller;

import com.chzu.dto.AddEventsInfoDTO;
import com.chzu.dto.RoleDTO;
import com.chzu.dto.UpdateEventsInfoDTO;
import com.chzu.entity.Events;
import com.chzu.entity.Role;
import com.chzu.entity.User;
import com.chzu.mapper.EventsMapper;
import com.chzu.service.EventsService;
import com.chzu.utils.R;
import com.chzu.utils.Status;
import com.chzu.vo.EventsWithOrgVo;
import com.chzu.vo.RoleVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@Slf4j
public class EventsController {

    @Autowired
    private EventsService eventsService;


    /**
     * 获取待办事件信息
     */
    @GetMapping("/events/getWaitToEventsInfo")
    @ApiOperation("获取待办事件信息")
    public R<List<EventsWithOrgVo>> getWaitToEventsInfo(@RequestParam("userId")Integer userId){
        log.info("getWaitToEventsInfo().userId={}",userId);

        return eventsService.getWaitToEventsInfo(userId);
    }

    /**
     * 获取待办事件信息
     */
    @GetMapping("/events/getWaitToEventsInfoByOrgName")
    @ApiOperation("获取待办事件信息")
    public R<List<EventsWithOrgVo>> getWaitToEventsInfoByInstitutionName(@RequestParam("userId")Integer userId,@RequestParam("orgName")String orgName){
        log.info("getWaitToEventsInfoByInstitutionName().userId={},orgName={}",userId,orgName);
        return eventsService.getWaitToEventsInfoByOrgName(userId,orgName);
    }

    /**
     * 获取在办事件信息
     */
    @GetMapping("/events/getAtToEventsInfo")
    @ApiOperation("获取在办事件信息")
    public R<List<EventsWithOrgVo>> getAtToEventsInfo(@RequestParam("userId")Integer userId){
        log.info("getAtToEventsInfo().userId = {}",userId);
        return eventsService.getAtToEventsInfo(userId);
    }

    @GetMapping("/events/getDoneToEventsInfo")
    @ApiOperation("获取办结事件信息")
    public R<List<EventsWithOrgVo>> getDoneToEventsInfo(@RequestParam("userId")Integer userId){
        log.info("getDoneToEventsInfo().userId = {}",userId);
        return eventsService.getDoneToEventsInfo(userId);
    }

    /**
     * 修改事件信息
     * @param updateEventsInfoDTO
     * @return
     */
    @PutMapping("/events/updateEventsInfo")
    @ApiOperation("修改事件信息")
    public R updateEventsInfo(@RequestBody UpdateEventsInfoDTO updateEventsInfoDTO){
        log.info("updateEventsInfo().updateEventsInfoDTO:{}",updateEventsInfoDTO);
        return eventsService.updateEventsInfo(updateEventsInfoDTO);
    }

    @PostMapping("/events/addEventsInfo")
    @ApiOperation("添加事件信息")
    public R addEventsInfo(@RequestBody AddEventsInfoDTO addEventsInfoDTO){
        log.info("addEventsInfo().addEventsInfoDTO:{}",addEventsInfoDTO);
        return eventsService.addEventsInfo(addEventsInfoDTO);
    }


    @GetMapping("/events/testGet")
    @ApiOperation("获取待办事件信息")
    public String testGet(){

        return "testGet";
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/events/testPost")
    public String testPost(User user){
        log.info("user:{}",user);
        return "testPost";
    }

    @PutMapping("/events/resetToDoEventsInfo")
    @ApiOperation("重置待办事件")
    public R resetToDoEventsInfo(@RequestBody UpdateEventsInfoDTO updateEventsInfoDTO){
        log.info("resetToDoEventsInfo().updateEventsInfoDTO:{}",updateEventsInfoDTO);
        return eventsService.resetToDoEventsInfo(updateEventsInfoDTO);
    }

    @PutMapping("/events/rollBackEventsInfo")
    @ApiOperation("回滚事件信息")
    public R<Boolean> rollBackEventsInfo(@RequestBody UpdateEventsInfoDTO updateEventsInfoDTO){
        log.info("rollBackEventsInfo().updateEventsInfoDTO:{}",updateEventsInfoDTO);
        return eventsService.rollBackEventsInfo(updateEventsInfoDTO);
    }

    @PutMapping("/events/auditEventsInfo")
    @ApiOperation("审核事件信息")
    public R<Boolean> auditEventsInfo(@RequestBody UpdateEventsInfoDTO updateEventsInfoDTO){
        log.info("auditEventsInfo().updateEventsInfoDTO:{}",updateEventsInfoDTO);
        return eventsService.auditEventsInfo(updateEventsInfoDTO);
    }

    @PutMapping("/events/rejectAuditEventsInfo")
    @ApiOperation("审核事件信息")
    public R<Boolean> rejectAuditEventsInfo(@RequestBody UpdateEventsInfoDTO updateEventsInfoDTO){
        log.info("rejectAuditEventsInfo().updateEventsInfoDTO:{}",updateEventsInfoDTO);
        return eventsService.rejectAuditEventsInfo(updateEventsInfoDTO);
    }


}
