package com.chzu.service;

import com.chzu.dto.AddEventsInfoDTO;
import com.chzu.dto.RoleDTO;
import com.chzu.dto.UpdateEventsInfoDTO;
import com.chzu.entity.Events;
import com.chzu.utils.R;
import com.chzu.vo.EventsWithOrgVo;
import com.chzu.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EventsService {

    R<List<EventsWithOrgVo>> getWaitToEventsInfo(Integer userId);

    R<List<EventsWithOrgVo>> getAtToEventsInfo(Integer userId);

    R updateEventsInfo(UpdateEventsInfoDTO updateEventsInfoDTO);

    R<List<Events>> getEventsByAssignedUserId(Integer userId);

    R<List<Events>> getEventsByProcessUserId(Integer userId);

    R<List<EventsWithOrgVo>> getWaitToEventsInfoByOrgName(Integer userId, String orgName);

    R resetToDoEventsInfo(UpdateEventsInfoDTO updateEventsInfoDTO);

    R<Boolean> addEventsInfo(AddEventsInfoDTO addEventsInfoDTO);
}
