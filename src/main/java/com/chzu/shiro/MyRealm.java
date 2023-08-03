package com.chzu.shiro;

import com.chzu.entity.Role;
import com.chzu.entity.User;
import com.chzu.mapper.RoleMapper;
import com.chzu.service.UserService;
import com.chzu.utils.JwtUser;
import com.chzu.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

// @Component
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleMapper roleMapper;

    /**
     *  自定义登录认证方法，shiro的 login方法底层会调用该类的认证方法进行认证
     *  需要配置自定义的realm生效：在ini文件配置或在SpringBoot中配置
     *  该方法只是获取进行对比的信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    // @Override
    // protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    //     log.info("{},",authenticationToken.getCredentials());
    //     // 1.获取用户登录时携带的身份信息
    //     String username = authenticationToken.getPrincipal().toString();
    //     // 2.调用业务层接口查询数据库获取用户信息
    //     User user = userService.getUserInfoByUsername(username);
    //     // 3.非空判断
    //     if (!Objects.isNull(user)) {
    //         AuthenticationInfo info = new SimpleAuthenticationInfo(
    //                 authenticationToken.getPrincipal(), // 身份信息
    //                 user.getPassword(), // 数据库查询出的密码信息
    //                 ByteSource.Util.bytes("salt"), // 盐相关信息
    //                 authenticationToken.getPrincipal().toString()); // 身份信息.toString()
    //         Subject subject = SecurityUtils.getSubject();
    //         subject.getSession().setAttribute("user", user);
    //         log.info("认证成功");
    //         return info;
    //     }
    //     return null;
    // }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) {
        String token = (String) auth.getCredentials();  //JwtToken中重写了这个方法了
        JwtUser jwtUser = JwtUtil.recreateUserFromToken(token);
        String userName = jwtUser.getUserName();  // 获得username
        //用户不存在（这个在登录时不会进入，只有在token校验时才有可能进入）
        if(userName == null)
            throw new UnknownAccountException();

        //根据用户名，查询数据库获取到正确的用户信息
        User user = userService.getUserInfoByUsername(userName);

        //用户不存在（这个在登录时不会进入，只有在token校验时才有可能进入）
        if(user == null)
            throw new UnknownAccountException();

        //密码错误(这里获取到password，就是3件套处理后的保存到数据库中的凭证，作为密钥)
        // if (! JwtUtil.verifyToken(token, userName,user.getPassword())) {
        //     throw new IncorrectCredentialsException();
        // }
        //toke过期
        if(JwtUtil.isExpire(token)){
            throw new ExpiredCredentialsException();
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, token, getName());
        return info;
    }
    /**
     * 自定义授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("自定义授权方法");
        // 1.获取用户身份信息
        String principal = principalCollection.getPrimaryPrincipal().toString();
        // 2.调用业务层获取用户的角色信息
        List<String> roles = roleMapper.getRolenameByUsername(principal);
        // 3.调用业务层获取用户的权限信息
        List<String> permissions = userService.getUserPermissionInfo(roles);
        // 3.创建对象，封装当前登录用户的角色、权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        // 4.返回Info对象
        log.info("{},",info);
        return info;
    }

    /**
     * 限定这个realm只能处理JwtToken（不加的话会报错）:言外之意，不同ream可以处理不同的token
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
}
