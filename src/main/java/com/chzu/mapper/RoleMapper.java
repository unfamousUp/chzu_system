package com.chzu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chzu.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过用户名查询角色信息
     * @param principal
     * @return
     */
    List<Role> getRoleByUsername(@Param("principal") String principal);

    /**
     * 通过用户名查询角色名称
     * @param principal
     * @return
     */
    List<String> getRolenameByUsername(@Param("principal") String principal);

}
