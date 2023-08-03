// package com.chzu.service.impl;
//
// import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
// import com.chzu.mapper.UserMapper;
// import com.chzu.pojo.User;
// import com.chzu.security.LoginUser;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
//
// import java.util.Objects;
//
// @Service
// @Slf4j
// public class UserDetailsServiceImpl implements UserDetailsService {
//
//     @Autowired
//     private UserMapper userMapper;
//
//     @Override
//     /**
//      * 查询用户
//      */
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         // 查询用户信息
//         QueryWrapper<User> wrapper = new QueryWrapper<>();
//         wrapper.eq("username",username);
//         User user = userMapper.selectOne(wrapper); // 查询user_id等信息
//         // 查询失败
//         if (Objects.isNull(user)){
//             throw new RuntimeException("用户名或密码错误");
//         }
//         //TODO 查询对应的权限信息
//         log.info("success");
//         // 封装成UserDetails实现类对象
//         return new LoginUser(user);
//     }
// }
