package com.chzu.service.impl;

import com.chzu.dto.RoleDTO;
import com.chzu.dto.UpdateEventsInfoDTO;
import com.chzu.entity.Events;
import com.chzu.mapper.EventsMapper;
import com.chzu.mapper.OrganizationsMapper;
import com.chzu.service.EventsService;
import com.chzu.utils.JwtUser;
import com.chzu.utils.R;
import com.chzu.utils.Status;
import com.chzu.vo.EventsWithOrgVo;
import com.chzu.vo.RoleVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EventsServiceImpl implements EventsService {

    private JwtUser jwtUser;

    @Autowired
    private EventsMapper eventsMapper;

    @Autowired
    private OrganizationsMapper organizationsMapper;

    /**
     * 更新事件信息
     *
     * @param updateEventsInfoDTO
     * @return
     */
    @Override
    public R updateEventsInfo(UpdateEventsInfoDTO updateEventsInfoDTO) {
        // 判断是网信办还是机构用户
        jwtUser = (JwtUser) SecurityUtils.getSubject().getPrincipal();
        // 网信办用户
        if(jwtUser.getIsAdmin()){
            updateEventsInfoDTO.setEventType("在办"); // 事件类型：在办
            updateEventsInfoDTO.setEventsStatus("在办"); // 待办 -> 在办
            updateEventsInfoDTO.setEventStatusInstitution("待办"); // 待通告 -> 待办
            updateEventsInfoDTO.setProcessStatus("待处理"); // 机构待处理
            log.info("{}",updateEventsInfoDTO);
            Integer result = eventsMapper.updateEventsInfoForAdminUser(updateEventsInfoDTO);
            if (result!=0) return R.buildR(Status.OK,"更新事件成功");
        }
        // 机构用户
        if(jwtUser.getIsInstitution()){
            eventsMapper.updateEventsInfoForAdminUser(updateEventsInfoDTO);
        }
        return null;
    }

    /**
     * 获取用户待办事件信息
     *
     * @param userId
     * @return
     */
    @Override
    public R<List<EventsWithOrgVo>> getWaitToEventsInfo(Integer userId) {
        String status = "待办";
        // 判断是否为网信办用户
         jwtUser = (JwtUser) SecurityUtils.getSubject().getPrincipal();
        if (jwtUser.getIsAdmin()) {
            List<Events> events = eventsMapper.getToDoEventsForAdminUser(userId, status);
            if (events.size() != 0) return R.buildR(Status.OK, "success", EventsWithOrgVo.convertToEventsWithOrgVoList(events));
        }
        // 判断是否为机构用户
        if (jwtUser.getIsInstitution()) {
            List<Events> events = eventsMapper.getToDoEventsForInstitutionUser(userId, status);
            if (events.size() != 0) return R.buildR(Status.OK, "success", EventsWithOrgVo.convertToEventsWithOrgVoList(events));
        }
        return R.buildR(Status.SYSTEM_ERROR, "查询失败");
    }

    @Override
    public R<List<EventsWithOrgVo>> getAtToEventsInfo(Integer userId) {
        String status = "在办";
        // 判断是否为网信办用户
        jwtUser = (JwtUser) SecurityUtils.getSubject().getPrincipal();
        if (jwtUser.getIsAdmin()) {
            List<Events> events = eventsMapper.getAtToDoEventsForAdminUser(userId, status);
            if (events.size() != 0) return R.buildR(Status.OK, "success", EventsWithOrgVo.convertToEventsWithOrgVoList(events));
        }
        // 判断是否为机构用户
        if (jwtUser.getIsInstitution()) {
            List<Events> events = eventsMapper.getToDoEventsForInstitutionUser(userId, status);
            if (events.size() != 0) return R.buildR(Status.OK, "success", EventsWithOrgVo.convertToEventsWithOrgVoList(events));
        }
        return R.buildR(Status.SYSTEM_ERROR, "查询失败");
    }

    @Override
    public R<List<Events>> getEventsByAssignedUserId(Integer userId) {
        return null;
    }

    @Override
    public R<List<Events>> getEventsByProcessUserId(Integer userId) {
        return null;
    }

    public R resetToDoEventsInfo(UpdateEventsInfoDTO updateEventsInfoDTO){
        Integer result = eventsMapper.resetToDoEventsInfo(updateEventsInfoDTO);
        if (result!=0) return R.buildR(Status.OK,"重置成功");
        return R.buildR(Status.SYSTEM_ERROR,"重置失败");
    }

}
