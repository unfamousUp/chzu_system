package com.chzu.controller;

import com.chzu.entity.Role;
import com.chzu.entity.User;
import com.chzu.mapper.RoleMapper;
import com.chzu.service.RoleService;
import com.chzu.service.UserService;
import com.chzu.utils.JwtUser;
import com.chzu.utils.JwtUtil;
import com.chzu.utils.R;
import com.chzu.utils.Status;
import com.chzu.vo.RoleVo;
import com.chzu.vo.SelectOptionVo;
import com.chzu.vo.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.function.SupplierUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags="用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/testGetToken")
    // @ApiOperation("测试获取token")
    public String testGetToken(HttpServletRequest httpServlet){
        String token = httpServlet.getHeader("Authorization");
        JwtUser jwtUser = (JwtUser)SecurityUtils.getSubject().getPrincipal();
        log.info("{},",token);
        log.info("{},",jwtUser);
        return "success";
    }


    @GetMapping("/testGetPermissionInfo")
    // @ApiOperation("测试获取用户权限信息")
    public String testGetPermissionInfo(){
        List<String> list = userService.getUserPermissionInfo(roleService.getRolenameByUsername("wxb"));
        list.forEach(System.out::println);
        return "success";
    }

    @GetMapping("/testDeletePermission")
    // @ApiOperation("测试用户delete权限")
    @RequiresPermissions("user:delete")
    public String testDeletePermission(){
        return "delete:成功";
    }

    @GetMapping("/getUserOptionInfo")
    @ApiOperation("获取用户信息：userId|fullName")
    public R<List<SelectOptionVo>> getUserOptionInfo(@RequestParam("orgId") Integer orgId){
        log.info("{}",orgId);
        return userService.getUserByOrgId(orgId);
    }

    /**
     * 根据用户名获取角色信息
     * @param userName
     * @return
     */
    @GetMapping("/getRolesInfo/{userName}")
    @ApiOperation("获取角色信息：roleName")
    public R<RoleVo> getRolesInfo(@PathVariable("userName") String userName){
        return roleService.getRolesInfoByUsernameToVo(userName);
    }

    /**
     * 根据用户名获取角色信息
     * @param orgId
     * @return
     */
    @GetMapping("/getUserInfoByOrgId")
    @ApiOperation("获取用户信息")
    public R<User> getUserInfoByOrgId(@RequestParam("orgId") Integer orgId){
        return userService.getUserInfoByOrgId(orgId);
    }


}
