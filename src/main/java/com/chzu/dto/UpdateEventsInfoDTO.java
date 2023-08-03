package com.chzu.dto;

import com.chzu.entity.Role;
import com.chzu.vo.RoleVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventsInfoDTO {

    // 被通知的用户id
    private Integer userId;

    // 事件Id
    private Integer eventId;

    // 事件类型
    private String eventType;

    // 机构用户的事件状态
    private String eventStatusInstitution;

    // 网信办用户的事件状态
    private String eventsStatus;

    // 事件处理的状态
    private String processStatus;


}
