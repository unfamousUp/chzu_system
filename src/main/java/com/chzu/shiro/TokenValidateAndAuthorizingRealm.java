package com.chzu.shiro;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.chzu.utils.JwtUser;
import com.chzu.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenValidateAndAuthorizingRealm extends AuthorizingRealm {

    //权限管理部分的代码先行略过
    //......

    public TokenValidateAndAuthorizingRealm() {
        //CredentialsMatcher，自定义匹配策略（即验证jwt token的策略）
        // public AuthorizingRealm(CredentialsMatcher matcher){}
        super(new CredentialsMatcher() {
            @Override
            public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
                log.info("doCredentialsMatch token合法性验证");
                JwtToken jwtToken = (JwtToken) authenticationToken;
                String token = (String) jwtToken.getCredentials();
                log.debug(token);
                boolean verified = JwtUtil.verifyToken(token);
                return true;
            }
        });
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    public String getName() {
        return "TokenValidateAndAuthorizingRealm";
    }

    @Override
    public Class getAuthenticationTokenClass() {
        //设置由本realm处理的token类型。BearerToken是在filter里自动装配的。
        // 这里我改为JwtToken
        return JwtToken.class;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        boolean res = super.supports(token);
        log.debug("[TokenValidateRealm is supports]" + res);
        return res;
    }


    @Override//装配用户信息，供Matcher调用
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException, TokenExpiredException {
        log.debug("doGetAuthenticationInfo 将token装载成用户信息");
        log.info("doGetAuthenticationInfo 将token装载成用户信息");
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String token = (String) jwtToken.getPrincipal();
        JwtUser jwtUser = JwtUtil.recreateUserFromToken(token);//只带着用户名和roles
        SimpleAuthenticationInfo res = new SimpleAuthenticationInfo(jwtUser, token, this.getName());
        /*Constructor that takes in an account's identifying principal(s) and its corresponding credentials that verify the principals.*/
        //        这个返回值是造Subject用的，返回值供createSubject使用
        return res;
    }
}
