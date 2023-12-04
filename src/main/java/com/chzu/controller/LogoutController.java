package com.chzu.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags="登出")
public class LogoutController {

    // @GetMapping("/user/logout")
    // public String logout(){
    //     return "logout";
    // }

}
