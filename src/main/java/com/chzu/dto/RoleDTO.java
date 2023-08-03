package com.chzu.dto;

import com.chzu.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
// @AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    // private List<Role> roles;

    private Boolean isAdminUser = false;

    private Boolean isInstitutionUser = false;

    public RoleDTO(Boolean isAdminUser, Boolean isInstitutionUser){
        // this.roles = roles;
        this.isAdminUser = isAdminUser;
        this.isInstitutionUser = isInstitutionUser;
    }

}
