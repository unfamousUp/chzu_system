package com.chzu.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.chzu.entity.Events;
import com.chzu.utils.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventsWithOrgVo {

    @ApiModelProperty("事件ID")
    private Integer eventId;

    @ApiModelProperty("事件名称")
    private String eventName;

    @ApiModelProperty("事件类型：通告")
    private String eventType;

    @ApiModelProperty("事件在网信办的状态:待办|在办|办结")
    private String eventStatus;

    @ApiModelProperty("事件在机构的状态:待办|在办|办结")
    private String eventStatusInstitution;

    @ApiModelProperty("设置处理该事件的机构用户ID")
    private Integer processByUser; // 网信办用户手动选择用户id

    @ApiModelProperty("事件在机构的处理状态：")
    private String processStatus;

    @ApiModelProperty("事件分配给的机构ID")
    private Integer assignedToOrganization;

    @ApiModelProperty("事件分配给的机构名称")
    private String assignedToOrganizationName;

    @ApiModelProperty("事件创建时间")
    private String createTime;

    @ApiModelProperty("事件更新时间")
    private String updateTime;

    @ApiModelProperty("发布该事件的网信办用户ID")
    private Integer assignedByUser = 1; // 默认为wxb用户

    @ApiModelProperty("审核通过事件的网信办用户ID")
    private Integer approvedByUser = 1; // 默认为wxb用户

    @ApiModelProperty("审核状态：审核中|审核通过|审核不通过")
    private String approvalStatus;

    @ApiModelProperty("事件紧急程度")
    private String emergencyDegree;

    @ApiModelProperty("数据来源")
    private String datasource;

    @ApiModelProperty("违规内容")
    private String illegalContent;

    @TableField("org_id")
    @ApiModelProperty("机构ID")
    private Integer orgId;

    @TableField("org_name")
    @ApiModelProperty("机构名称")
    private String orgName;

    @TableField("org_domain_name")
    @ApiModelProperty("机构网站域名")
    private String orgDomainName;

    @TableField("org_type")
    @ApiModelProperty("机构类型")
    private String orgType;

    public EventsWithOrgVo(Events events){
        this.eventId = events.getEventId();
        this.eventName = events.getEventName();
        this.eventType = events.getEventType();
        this.eventStatus = events.getEventStatus();
        this.eventStatusInstitution = events.getEventStatusInstitution();
        this.processByUser = events.getProcessByUser();
        this.processStatus = events.getProcessStatus();
        this.assignedToOrganization = events.getAssignedToOrganization();
        // this.createTime = DateUtil.getFormattedDateTime(events.getCreateTime());
        // this.updateTime = DateUtil.getFormattedDateTime(events.getUpdateTime());
        this.assignedByUser = events.getAssignedByUser();
        this.approvedByUser = events.getApprovedByUser();
        this.approvalStatus = events.getApprovalStatus();
        this.emergencyDegree = events.getEmergencyDegree();
        this.datasource = events.getDatasource();
        this.illegalContent = events.getIllegalContent();
        // Org
        this.orgId = events.getOrganizations().getOrgId();
        this.orgName = events.getOrganizations().getOrgName();
        this.orgType = events.getOrganizations().getOrgType();
        this.orgDomainName = events.getOrganizations().getOrgDomainName();
    }

    // 将 List<Events> 转换为 List<EventsWithOrgVo> 的静态方法
    public static List<EventsWithOrgVo> convertToEventsWithOrgVoList(List<Events> eventsList) {
        List<EventsWithOrgVo> eventsWithOrgVoList  = new ArrayList<>();

        for (Events events : eventsList) {
            eventsWithOrgVoList.add(new EventsWithOrgVo(events));
        }
        return eventsWithOrgVoList;
    }
}
