package com.chzu.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 事件表
public class Events {

    @TableId(value = "event_id", type = IdType.AUTO)
    @ApiModelProperty("事件ID")
    private Integer eventId;

    @TableField("event_name")
    @ApiModelProperty("事件名称")
    private String eventName;

    @TableField("event_type")
    @ApiModelProperty("事件类型：通告")
    private String eventType;

    @TableField("event_status")
    @ApiModelProperty("事件在网信办的状态:待办|在办|办结")
    private String eventStatus;

    @TableField("event_status_institution")
    @ApiModelProperty("事件在机构的状态:待办|在办|办结")
    private String eventStatusInstitution;

    @TableField("process_by_user")
    @ApiModelProperty("设置处理该事件的机构用户ID")
    private Integer processByUser; // 网信办用户手动选择用户id

    @TableField("process_status")
    @ApiModelProperty("事件在机构的处理状态：")
    private String processStatus;

    @TableField("assigned_to_organization")
    @ApiModelProperty("事件分配给的机构ID")
    private Integer assignedToOrganization;

    @TableField("create_time")
    @ApiModelProperty("事件创建时间")
    private Date createTime;

    @TableField("create_time")
    @ApiModelProperty("事件更新时间")
    private Date updateTime;

    @TableField("assigned_by_user")
    @ApiModelProperty("发布该事件的网信办用户ID")
    private Integer assignedByUser = 1; // 默认为wxb用户

    @TableField("approved_by_user")
    @ApiModelProperty("审核通过事件的网信办用户ID")
    private Integer approvedByUser = 1; // 默认为wxb用户

    @TableField("approval_status")
    @ApiModelProperty("审核状态：审核中|审核通过|审核不通过")
    private String approvalStatus;

    @TableField("emergency_degree")
    @ApiModelProperty("事件紧急程度")
    private String emergencyDegree;

    @TableField("datasource")
    @ApiModelProperty("数据来源")
    private String datasource;

    @TableField("illegal_content")
    @ApiModelProperty("违规内容")
    private String illegalContent;

    // 数据库中不存在的字段可以使用该注解
    @TableField(exist = false)
    private List<WorkflowSteps> workflowSteps;

    // 数据库中不存在的字段可以使用该注解
    @TableField(exist = false)
    private Organizations organizations;
}
