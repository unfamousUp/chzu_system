package com.chzu.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.chzu.entity.User;
import com.chzu.utils.JwtUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVo {

    // 主键-user_id
    private Integer userId;
    // 账号
    private String username;
    // 全名
    private String fullName;
    // 角色
    private String roleName;
    // 是否为管理员用户
    private Boolean isAdmin;
    // 是否为机构用户
    private Boolean isInstitution;
    // token
    private String token;

    public UserLoginVo(JwtUser user,String token) {
        this.setUsername(user.getUserName());
        this.setFullName(user.getFullName());
        this.setUserId(user.getUserId());
        this.setIsAdmin(user.getIsAdmin());
        this.setIsInstitution(user.getIsInstitution());
        this.token = token;
    }

    public UserLoginVo(User user, String token) {
        this.setUsername(user.getUsername());
        this.setFullName(user.getFullName());
        this.setUserId(user.getUserId());
        this.token = token;
    }
}
