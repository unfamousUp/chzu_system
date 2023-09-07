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

    // 修改建议
    @ApiModelProperty("修改建议")
    private String suggestion;

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
        setProcessByUser(getUserId());
        setEventType("在办"); // 事件类型：在办
        setEventsStatus("在办"); // 待办 -> 在办
        setEventStatusInstitution("待办"); // 待通告 -> 待办
        setProcessStatus("待处理"); // 处理状态：待通告 -> 待处理
    }

    /**
     * 机构用户处理待办事件
     */
    public void updateWaitToEventsInfoForInsUser() {
        setProcessByUser(getUserId());
        setEventType("在办"); // 事件类型：在办
        setEventsStatus("待办"); // 网信办事件状态：在办 -> 待办
        setEventStatusInstitution("在办"); // 机构用户事件状态：待办 -> 在办
        setProcessStatus("审核中"); // 处理状态：待处理 -> 审核中
    }


    /**
     * 网信办审核通过事件
     */
    public void audit(){
        setEventsStatus("办结"); // 网信办事件状态：待办 -> 办结
        setEventStatusInstitution("办结"); // 机构事件状态： 在办 -> 办结
        setProcessStatus("审核通过"); // 事件处理状态：待审核 -> 审核通过
        setEventType("办结"); // 事件类型：办结
    }

    /**
     * 网信办审核不通过事件
     */
    public void rejectAudit(){
        setEventsStatus("在办"); // 网信办事件状态：待办 -> 在办
        setEventStatusInstitution("待办"); // 机构事件状态： 在办 -> 待办
        setProcessStatus("修改中"); // 事件处理状态：待审核 -> 修改中
        setEventType("在办"); // 事件类型：在办
    }

}
