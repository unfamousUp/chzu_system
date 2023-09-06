package com.chzu.dto;

import com.chzu.entity.Role;
import com.chzu.vo.RoleVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventsInfoDTO {

    // 被通知的用户id
    @ApiModelProperty("处理事件的用户id")
    private Integer userId;

    // 事件Id
    @ApiModelProperty("事件Id")
    private Integer eventId;

    // 事件类型
    @ApiModelProperty("事件类型")
    private String eventType;

    // 机构用户的事件状态
    @ApiModelProperty("机构用户的事件状态")
    private String eventStatusInstitution;

    // 网信办用户的事件状态
    @ApiModelProperty("网信办用户的事件状态")
    private String eventsStatus;

    // 事件处理的状态
    @ApiModelProperty("事件处理的状态")
    private String processStatus;

    @ApiModelProperty("处理事件的机构用户id")
    private Integer processByUser;

    // 附件文本信息
    @ApiModelProperty("附件文本信息")
    private String textarea;

    /**
     * 在办 -> 待办
     */
    public void rollback() {
        setEventType("待通告");
        setEventsStatus("待办");
        setEventStatusInstitution("待通告");
        setProcessStatus("待通告");
        setProcessByUser(null); // 重置
    }

    /**
     * 网信办用户处理待办事件
     */
    public void updateWaitToEventsInfoForAdminUser() {
        setEventType("在办"); // 事件类型：在办
        setEventsStatus("在办"); // 待办 -> 在办
        setEventStatusInstitution("待办"); // 待通告 -> 待办
        setProcessStatus("待处理"); // 处理状态：待通告 -> 待处理
    }

    /**
     * 机构用户处理待办事件
     */
    public void updateWaitToEventsInfoForInsUser() {
        setEventType("在办"); // 事件类型：在办
        setEventsStatus("待办"); // 网信办事件状态：在办 -> 待办
        setEventStatusInstitution("在办"); // 机构用户事件状态：待办 -> 在办
        setProcessStatus("审核中"); // 处理状态：待处理 -> 审核中
    }

}
