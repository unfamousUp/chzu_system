package com.chzu.utils;

import com.chzu.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser {

    private Integer userId;

    private String fullName;

    private String userName;

    private Boolean isAdmin;

    private Boolean isInstitution;

    public JwtUser(User user){
        this.setUserId(user.getUserId());
        this.setFullName(user.getFullName());
        this.setUserName(user.getUsername());
        this.setIsAdmin(user.getIsAdmin());
        this.setIsInstitution(user.getIsInstitution());
    }

    // private String roles;

}
