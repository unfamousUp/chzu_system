package com.chzu.controller;

import com.chzu.dto.UserLoginDTO;
import com.chzu.entity.User;
import com.chzu.mapper.UserMapper;
import com.chzu.service.LoginService;
import com.chzu.service.UserService;
import com.chzu.shiro.JwtToken;
import com.chzu.utils.*;
import com.chzu.vo.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = "登录接口")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/user/login")
    // @ApiOperation("登录")
    public Result<UserLoginVo> login(@ApiParam("UserLoginDTO") UserLoginDTO userLoginDTO) {
        log.info("登录信息,{}", userLoginDTO);
        Result<UserLoginVo> userLoginVoResult = loginService.login(userLoginDTO);
        return userLoginVoResult;
    }

    @GetMapping("/user/loginPage")
    // @ApiOperation("登录页面")
    public String indexPage() {
        return "login-page";
    }

    @GetMapping("/user/testPage")
    // @ApiOperation("登录页面")
    public String testPage() {
        return "test-page";
    }

    @RequiresRoles("admin")
    @GetMapping("/user/userLoginRoles")
    public String userLoginRoles() {
        System.out.println("登录认证验证角色");
        return "验证角色成功";
    }

    @PostMapping("/testLogin")
    // @ApiOperation("测试")
    public R<UserLoginVo> test(@ApiParam("UserLoginDTO") UserLoginDTO userLoginDTO,
                               @ApiParam("接收属性name=rememberMe的单选框的value值")
                               @RequestParam(defaultValue = "true") boolean rememberMe) {
        // 1.获取subject对象
        Subject subject = SecurityUtils.getSubject();
        // 2.封装请求数据到token
        AuthenticationToken token = new UsernamePasswordToken(userLoginDTO.getUsername(), userLoginDTO.getPassword(), rememberMe);
        // 3.调用login方法进行登录验证
        try {
            subject.login(token);
            log.info("{}", subject.getSession().getAttribute("user"));
            User user = (User) subject.getSession().getAttribute("user");
            UserLoginVo userLoginVo = new UserLoginVo(user, "token");

            return R.buildR(Status.OK, "登录成功", userLoginVo);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return R.buildR(Status.SYSTEM_ERROR, "登录失败");
        }
    }

    @PostMapping("/testTokenLogin")
    // @ApiOperation("测试testTokenLogin")
    public R<UserLoginVo> testTokenLogin(@ApiParam("UserLoginDTO") UserLoginDTO userLoginDTO,
                                         @ApiParam("接收属性name=rememberMe的单选框的value值")
                                         @RequestParam(defaultValue = "true") boolean rememberMe) {
        R<UserLoginVo> r = null;
        // 1.获取subject对象
        Subject subject = SecurityUtils.getSubject();

        //盐 + 输入的密码(注意不是用户的正确密码) + 1024次散列，作为token生成的密钥
        Md5Hash md5Hash = new Md5Hash(userLoginDTO.getPassword(), "salt", 1024);

        //生成token字符串
        // String token = JwtUtil.getJwtToken(new JwtUser(),md5Hash.toHex());   //toHex转换成16进制，32为字符
        // JwtToken jwtToken = new JwtToken(token); //转换成jwtToken（才可以被shiro识别）
        //进行认证
        try {
            // subject.login(jwtToken);
            // r = R.buildR(Status.SYSTEM_ERROR,"登录成功",new UserLoginVo(new User(),token));
        } catch (UnknownAccountException e) {
            r = R.buildR(Status.SYSTEM_ERROR, "无效用户，用户不存在");
            e.printStackTrace();
        } catch (IncorrectCredentialsException e) {
            r = R.buildR(Status.SYSTEM_ERROR, "密码输入错误");
            e.printStackTrace();
        } catch (ExpiredCredentialsException e) {
            r = R.buildR(Status.SYSTEM_ERROR, "token过期，请重新登录");
            e.printStackTrace();
        } finally {
            return r;
        }
    }

    @PostMapping("/userPasswordTokenLogin")
    @ApiOperation("userPasswordTokenLogin")
    public R<UserLoginVo> testUserPasswordTokenLogin(@ApiParam("UserLoginDTO") UserLoginDTO userLoginDTO,
                                                     @ApiParam("接收属性name=rememberMe的单选框的value值")
                                                     @RequestParam(defaultValue = "true") boolean rememberMe) {
        R<UserLoginVo> r = null;
        // 1.获取subject对象
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        //进行认证
        try {
            subject.login(usernamePasswordToken);
            JwtUser jwtUser = (JwtUser) subject.getPrincipal();
            log.info("jwtUser:" + jwtUser);
            // 生成token
            String token = JwtUtil.getJwtToken(jwtUser);
            r = R.buildR(Status.SYSTEM_ERROR, "登录成功", new UserLoginVo(jwtUser, token));
        } catch (AuthenticationException e) {
            r = R.buildR(Status.SYSTEM_ERROR, "密码输入错误");
            e.printStackTrace();
        }
        // catch (UnknownAccountException e){
        //     r = R.buildR(Status.SYSTEM_ERROR,"无效用户，用户不存在");
        //     e.printStackTrace();
        // } catch (IncorrectCredentialsException e){
        //     r = R.buildR(Status.SYSTEM_ERROR,"密码输入错误");
        //     e.printStackTrace();
        // } catch (ExpiredCredentialsException e){
        //     r = R.buildR(Status.SYSTEM_ERROR,"token过期，请重新登录");
        //     e.printStackTrace();
        // }
        finally {
            return r;
        }
    }


}
