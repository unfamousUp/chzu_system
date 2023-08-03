package com.chzu.service;

import com.chzu.entity.Role;
import com.chzu.utils.R;
import com.chzu.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {

    List<Role> getRoleByUsername(String principal);

    R<RoleVo> getRolesInfoByUsernameToVo(String principal);

    List<String> getRolenameByUsername(String principal);

}
