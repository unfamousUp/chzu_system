package com.chzu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("permissions")
public class Permissions {

    @TableId("permission_id")
    @ApiModelProperty("权限ID")
    private Integer permissionId;

    @TableField("permission_name")
    @ApiModelProperty("权限名称")
    private String permissionName;

    @TableField("permission_info")
    @ApiModelProperty("权限信息")
    private String permissionInfo;

    @TableField("permission_desc")
    @ApiModelProperty("权限描述")
    private String permissionDesc;
}
