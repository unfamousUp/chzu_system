package com.chzu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @TableId("role_id")
    @ApiModelProperty("角色ID")
    private Integer roleId;

    @TableField("role_name")
    @ApiModelProperty("角色名称")
    private String roleName;

    @TableField("desc")
    @ApiModelProperty("权限描述")
    private String desc;

    @TableField("real_name")
    @ApiModelProperty("中文名称")
    private String realName;
}
