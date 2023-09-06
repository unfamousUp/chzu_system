package com.chzu.service.impl;

import com.chzu.dto.AddEventsInfoDTO;
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

    @Autowired
    private RedisService redisService;

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
            updateEventsInfoDTO.updateWaitToEventsInfoForAdminUser();

            Integer result = eventsMapper.updateEventsInfoForAdminUser(updateEventsInfoDTO);
            if (result!=0) return R.buildR(Status.OK,"更新事件成功");
        }
        // 机构用户
        if(jwtUser.getIsInstitution()){
            updateEventsInfoDTO.updateWaitToEventsInfoForInsUser();
            redisService.saveUserContent(updateEventsInfoDTO.getUserId(),updateEventsInfoDTO.getTextarea());
            log.info("{}",updateEventsInfoDTO);
            Integer result = eventsMapper.updateEventsInfoForInstitutionUser(updateEventsInfoDTO);
            if (result!=0) return R.buildR(Status.OK,"更新事件成功",updateEventsInfoDTO.getTextarea());
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
        // String processStatus = "审核中";
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

    /**
     * 根据机构名称查询待办事件信息
     * @param userId
     * @param orgName
     * @return
     */
    @Override
    public R<List<EventsWithOrgVo>> getWaitToEventsInfoByOrgName(Integer userId, String orgName) {
        String status = "待办";
        // QueryWrapper<Events> eventsQueryWrapper = new QueryWrapper<>();
        // QueryWrapper<Organizations> organizationsQueryWrapper = new QueryWrapper<>();
        // 判断是否为网信办用户
        jwtUser = (JwtUser) SecurityUtils.getSubject().getPrincipal();
        if (jwtUser.getIsAdmin()) {
            // List<Events> events = eventsMapper.getToDoEventsForAdminUser(userId, status);
            List<Events> events = eventsMapper.getToDoEventsByOrgNameForAdminUser(status,userId,orgName);
            if (events.size() != 0) return R.buildR(Status.OK, "success", EventsWithOrgVo.convertToEventsWithOrgVoList(events));
        }
        // 判断是否为机构用户
        if (jwtUser.getIsInstitution()) {
            List<Events> events = eventsMapper.getToDoEventsForInstitutionUser(userId, status);
            if (events.size() != 0) return R.buildR(Status.OK, "success", EventsWithOrgVo.convertToEventsWithOrgVoList(events));
        }
        return R.buildR(Status.SYSTEM_ERROR, "查询失败");
    }

    /**
     * 查询在办事件信息
     * @param userId
     * @return
     */
    @Override
    public R<List<EventsWithOrgVo>> getAtToEventsInfo(Integer userId) {
        String status = "在办";
        String processStatus = "待处理";
        // 判断是否为网信办用户
        jwtUser = (JwtUser) SecurityUtils.getSubject().getPrincipal();
        if (jwtUser.getIsAdmin()) {
            List<Events> events = eventsMapper.getAtToDoEventsForAdminUser(userId, status, processStatus);
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

    @Override
    public R<Boolean> rollBackEventsInfo(UpdateEventsInfoDTO updateEventsInfoDTO) {
        jwtUser = (JwtUser) SecurityUtils.getSubject().getPrincipal();
        if (jwtUser.getIsAdmin()) {
            updateEventsInfoDTO.rollback();
            Integer result = eventsMapper.rollBackEventsInfo(updateEventsInfoDTO);
            return R.buildR(Status.OK, "success", !(result == 0));
        }
        return R.buildR(Status.SYSTEM_ERROR,"重置失败");
    }

    @Override
    public R<Boolean> addEventsInfo(AddEventsInfoDTO addEventsInfoDTO) {
        jwtUser = (JwtUser) SecurityUtils.getSubject().getPrincipal();
        if (jwtUser.getIsAdmin()) {
            addEventsInfoDTO.setEventsInfo();
            Integer result = eventsMapper.addEventsInfo(addEventsInfoDTO);
            return R.buildR(Status.OK, "success", !(result == 0));
        }
        return R.buildR(Status.SYSTEM_ERROR,"重置失败");
    }
}
