package com.chzu.config;

import com.chzu.filter.CORSUserFilter;
import com.chzu.filter.JwtFilter;
import com.chzu.shiro.TokenValidateAndAuthorizingRealm;
import com.chzu.shiro.UsernamePasswordRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import com.chzu.shiro.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // @Autowired
    // private MyRealm myRealm;


    /**
     * 配置安全管理器:通过配置类替代原有的ini配置
     *
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("tokenValidateAndAuthorizingRealm") TokenValidateAndAuthorizingRealm realm, @Qualifier("usernamePasswordRealm") UsernamePasswordRealm usernamePasswordRealm) {
        // 1.创建defaultWebSecurityManager对象
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // // 2.创建加密对象，设置相关属性
        // HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // // 2.1.采用md5加密
        // matcher.setHashAlgorithmName("MD5");
        // // 2.2.迭代加密次数
        // matcher.setHashIterations(1024);
        // // 3.将加密对象封装到到myRealm中
        // realm.setCredentialsMatcher(matcher);
        // // 4.将myReals对象存入defaultWebSecurityManager中
        // securityManager.setRealm(realm);
        securityManager.setRealms(Arrays.asList(realm,usernamePasswordRealm));
        // // 4.5.设置remenberMe
        // securityManager.setRememberMeManager(rememberMeManager());
        // 5.返回初始化好的对象
        //关闭shiro的session（无状态的方式使用shiro）
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    //cookie 属性设置
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //设置跨域
        // cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        return cookie;
    }

    //创建 Shiro 的 cookie 管理对象
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new
                CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());

        cookieRememberMeManager.setCipherKey("1234567890987654".getBytes());
        return cookieRememberMeManager;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 自定义过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("cors", new CORSUserFilter());
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        // 设置登录 URL
        // shiroFilterFactoryBean.setLoginUrl("/loginPage");
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, String> filters = new LinkedHashMap<>();
        // 放行路径
        filters.put("/testTokenLogin", "anon");
        filters.put("/testUserPasswordTokenLogin","anon");
        filters.put("/testLogin", "anon");
        // 放行swagger
        filters.put("/doc.html", "anon");
        filters.put("/swagger-ui/*", "anon");
        filters.put("/v2/api-docs", "anon");
        filters.put("/swagger-resources/**", "anon");
        filters.put("/webjars/**", "anon");
        // 设置剩余的所有路径都需要进行JWT验证
        filters.put("/**", "jwt");
        // filters.put("/**", "cors");
        // 设置全局过滤器，包括CORS过滤器和noSessionCreation过滤器
        shiroFilterFactoryBean.setGlobalFilters(Arrays.asList("cors","noSessionCreation"));
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filters);
        return shiroFilterFactoryBean;
    }


    /**
     * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 由Spring管理 Shiro的生命周期
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    //创建自定义Realm，注入到spring容器中
    @Bean
    public MyRealm getMyRealm(){
        MyRealm realm = new MyRealm();
        //修改凭证校验匹配器（处理加密），只有使用了UsernamePasswordToken并且有对password进行加密的才需要
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
//        hashedCredentialsMatcher.setHashIterations(1024);
//
//        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        return realm;
    }

    //创建自定义Realm，注入到spring容器中
    @Bean
    public UsernamePasswordRealm getUsernamePasswordRealm(){
        UsernamePasswordRealm realm = new UsernamePasswordRealm();
        //修改凭证校验匹配器（处理加密），只有使用了UsernamePasswordToken并且有对password进行加密的才需要
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
//        hashedCredentialsMatcher.setHashIterations(1024);
//
//        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        return realm;
    }

    //创建自定义Realm，注入到spring容器中
    @Bean
    public TokenValidateAndAuthorizingRealm getTokenValidateAndAuthorizingRealm(){
        TokenValidateAndAuthorizingRealm realm = new TokenValidateAndAuthorizingRealm();
        return realm;
    }
}
