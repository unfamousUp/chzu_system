package com.chzu.vo;

import com.chzu.entity.Role;
import com.chzu.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.role;

@Data
// @AllArgsConstructor
@NoArgsConstructor
public class RoleVo {

    private List<Role> roles;

    private Boolean isAdminUser = false;

    private Boolean isInstitutionUser = false;

    public RoleVo(List<Role> roles,Boolean isAdminUser, Boolean isInstitutionUser){
        this.roles = roles;
        this.isAdminUser = isAdminUser;
        this.isInstitutionUser = isInstitutionUser;
    }

    // 将 List<User> 转换为 List<RoleVoList> 的静态方法
    // public static List<RoleVo> convertToRoleVoList(List<Role> roleList) {
    //     List<RoleVo> roleVoList = new ArrayList<>();
    //
    //     for (Role role : roleList) {
    //     }
    //     // roleVoList.add(new RoleVo(role));
    //
    //     return roleVoList;
    // }

}
