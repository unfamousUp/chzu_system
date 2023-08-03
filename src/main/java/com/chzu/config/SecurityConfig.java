// package com.chzu.config;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.builders.WebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
//
// @Configuration
// public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//     /**
//      * 创建BCryptPasswordEncoder注入容器
//      * @return
//      */
//     @Bean
//     public PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();
//     }
//
//     /**
//      * 认证处理
//      * @return
//      * @throws Exception
//      */
//     @Override
//     @Bean
//     public AuthenticationManager authenticationManagerBean() throws Exception {
//         return super.authenticationManagerBean();
//     }
//
//     /**
//      * 相关配置
//      * @param http
//      * @throws Exception
//      */
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.csrf()
//                 .disable() // 关闭csrf
//                 // .ignoringAntMatchers("/doc.html/**") // 允许访问接口文档
//                 .sessionManagement() // 不通过Session获取SecurityContext
//                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                 .and()
//                 .authorizeRequests() // 放行接口配置
//                 .antMatchers("/user/login","/doc.html/**")
//                 .anonymous() // 登录接口可以匿名访问
//                 .anyRequest()
//                 .authenticated(); // 除上面以外的所有请求需要鉴权认证
//     }
//
//     @Override
//     public void configure(WebSecurity web) throws Exception {
//         web.ignoring().antMatchers(
//                 "/doc.html",
//                 "/v2/api-docs",
//                 "/swagger-resources/**",
//                 "/webjars/**");
//     }
// }
