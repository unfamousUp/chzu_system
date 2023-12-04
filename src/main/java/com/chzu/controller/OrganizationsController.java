package com.chzu.controller;

import com.chzu.entity.Organizations;
import com.chzu.service.OrganizationsService;
import com.chzu.utils.JwtUser;
import com.chzu.utils.R;
import com.chzu.vo.OrgVo;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/org")
public class OrganizationsController {

    @Autowired
    private OrganizationsService organizationsService;

    @GetMapping("/getOrgInfo")
    @ApiOperation("获取机构信息")
    public R<List<OrgVo>> getOrgInfo(){
        return organizationsService.getOrgInfo();
    }
}
