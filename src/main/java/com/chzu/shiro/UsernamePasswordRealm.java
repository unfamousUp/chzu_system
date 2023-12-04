package com.chzu.shiro;


import com.chzu.entity.User;
import com.chzu.service.UserService;
import com.chzu.utils.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j //Username Password Realm，用户名密码登陆专用Realm
@Component
public class UsernamePasswordRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /*构造器里配置Matcher*/
    public UsernamePasswordRealm() {
        super();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);//密码保存策略一致，1024次md5加密
        this.setCredentialsMatcher(hashedCredentialsMatcher);
    }

    /**
     * 用户名和密码验证，login接口专用。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {

        // UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        log.info("{},", authenticationToken.getCredentials());
        // 1.获取用户登录时携带的身份信息
        String username = authenticationToken.getPrincipal().toString();
        // 2.调用业务层接口查询数据库获取用户信息
        User user = userService.getUserInfoByUsername(username);
        //在使用jwt访问时，shiro中能拿到的用户信息只能是token中携带的jwtUser，所以此处保持统一。
        JwtUser jwtUser = new JwtUser(user);
        // 3.非空判断
        if (!Objects.isNull(user)) {
            AuthenticationInfo info = new SimpleAuthenticationInfo(
                    jwtUser, // 身份信息principal
                    user.getPassword(), // 数据库查询出的密码信息
                    ByteSource.Util.bytes("salt"), // 盐相关信息
                    authenticationToken.getPrincipal().toString()); // 身份信息.toString()
            log.info("UsernamePasswordToken:认证成功");
            return info;
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 通过该方法来判断是否由本realm来处理login请求
     * <p>
     * 调用{@code doGetAuthenticationInfo(AuthenticationToken)}之前会shiro会调用{@code supper.supports(AuthenticationToken)}
     * 来判断该realm是否支持对应类型的AuthenticationToken,如果相匹配则会走此realm
     *
     * @return
     */
    @Override
    public Class getAuthenticationTokenClass() {
        log.info("getAuthenticationTokenClass");
        return UsernamePasswordToken.class;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //继承但啥都不做就为了打印一下info
        boolean res = super.supports(token);//会调用↑getAuthenticationTokenClass来判断
        log.debug("[UsernamePasswordRealm is supports]" + res);
        return res;
    }
}

