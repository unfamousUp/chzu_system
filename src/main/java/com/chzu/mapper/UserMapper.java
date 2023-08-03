package com.chzu.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chzu.entity.Permissions;
import com.chzu.entity.Role;
import com.chzu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@TableName("user")
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户及其角色
     * @param username
     * @return
     */
    User getUserWithRoles(@Param("username") String username);

    /**
     * 根据角色查询其权限集合
     * @param roles
     * @return
     */
    List<String> getUserPermissionInfo(@Param("roles")List<String> roles);

    /**
     * 根据
     * @param orgId
     * @return
     */
    List<User> getUserByOrgId(@Param("orgId")Integer orgId);

    /**
     * 根据userId查询用户
     * @param userId
     * @return
     */
    User getUserByUserId(@Param("userId")Integer userId);
}
