package com.chzu.service;

import com.chzu.dto.AddEventsInfoDTO;
import com.chzu.dto.UpdateEventsInfoDTO;
import com.chzu.entity.Events;
import com.chzu.utils.R;
import com.chzu.vo.EventsWithOrgVo;

import java.util.List;

public interface EventsService {

    R<List<EventsWithOrgVo>> getWaitToEventsInfo(Integer userId);

    R<List<EventsWithOrgVo>> getAtToEventsInfo(Integer userId);

    R updateEventsInfo(UpdateEventsInfoDTO updateEventsInfoDTO);

    R<List<Events>> getEventsByAssignedUserId(Integer userId);

    R<List<Events>> getEventsByProcessUserId(Integer userId);

    R<List<EventsWithOrgVo>> getWaitToEventsInfoByDynamicQuery(Integer userId, String orgName, String illegalContent, String processStatus);

    R<Boolean> resetToDoEventsInfo(UpdateEventsInfoDTO updateEventsInfoDTO);

    R<Boolean> rollBackEventsInfo(UpdateEventsInfoDTO updateEventsInfoDTO);

    R<Boolean> rejectAuditEventsInfo(UpdateEventsInfoDTO updateEventsInfoDTO);

    R<Boolean> auditEventsInfo(UpdateEventsInfoDTO updateEventsInfoDTO);

    R<Boolean> addEventsInfo(AddEventsInfoDTO addEventsInfoDTO);

    R<List<EventsWithOrgVo>> getDoneToEventsInfo(Integer userId);
}
