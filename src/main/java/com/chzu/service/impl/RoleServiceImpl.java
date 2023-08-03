package com.chzu.service.impl;

import com.chzu.entity.Role;
import com.chzu.mapper.RoleMapper;
import com.chzu.service.RoleService;
import com.chzu.utils.R;
import com.chzu.utils.Status;
import com.chzu.vo.RoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getRoleByUsername(String principal) {
        return roleMapper.getRoleByUsername(principal);
    }

    public List<String> getRolenameByUsername(String principal) {
        return roleMapper.getRolenameByUsername(principal);
    }

    @Override
    public R<RoleVo> getRolesInfoByUsernameToVo(String principal) {
        Boolean isAdminUser = false;
        Boolean isInstitutionUser = false;
        List<Role> roles = roleMapper.getRoleByUsername(principal);
        if (roles.size() > 0) {
            for (Role role :
                    roles) {
                // 查询为管理员角色
                if (role.getRoleName().equals("admin")) isAdminUser = true;
                // 查询为机构角色
                if (role.getRoleName().equals("normal")) isInstitutionUser = true;
            }
            return R.buildR(Status.OK, "success", new RoleVo(roles,isAdminUser,isInstitutionUser));
        }
        return R.buildR(Status.SYSTEM_ERROR);
    }
}
