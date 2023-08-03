package com.chzu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {

    @TableId("user_id")
    @ApiModelProperty("用户ID")
    private Integer userId;

    @TableField("username")
    @ApiModelProperty("用户名")
    private String username;

    /**
     * @TableField(select = true)
     * 查询时不返回该字段的值，保护数据
     */
    @TableField("password")
    @ApiModelProperty("密码")
    private String password;

    @TableField("full_name")
    @ApiModelProperty("用户姓名")
    private String fullName;

    @TableField("telephone")
    @ApiModelProperty("手机号码")
    private String telephone;

    @TableField("email")
    @ApiModelProperty("电子邮箱")
    private String email;

    @TableField("isAdmin")
    @ApiModelProperty("是否为管理员用户")
    private Boolean isAdmin;

    @TableField("isInstitution")
    @ApiModelProperty("是否为机构用户")
    private Boolean isInstitution;

    @TableField("org_id")
    @ApiModelProperty("外键:机构ID")
    private Integer orgId;

    /**
     * 用户与角色的多对多关联
     */
    @TableField(exist = false)
    @ApiModelProperty("角色列表")
    private List<Role> roles;


}
