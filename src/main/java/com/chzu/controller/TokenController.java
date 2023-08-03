package com.chzu.controller;

import com.chzu.utils.JwtUser;
import com.chzu.utils.R;
import com.chzu.utils.Status;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class TokenController {

    @GetMapping("/getToken")
    @ApiOperation("获取token信息")
    public R<String> getToken(HttpServletRequest httpServlet){
        String token = httpServlet.getHeader("Authorization");
        return R.buildR(Status.OK,"获取token成功",token);
    }

}
