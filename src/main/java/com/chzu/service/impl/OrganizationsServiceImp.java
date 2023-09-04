package com.chzu.service.impl;

import com.chzu.entity.Events;
import com.chzu.entity.Organizations;
import com.chzu.mapper.OrganizationsMapper;
import com.chzu.service.OrganizationsService;
import com.chzu.utils.JwtUser;
import com.chzu.utils.R;
import com.chzu.utils.Status;
import com.chzu.vo.EventsWithOrgVo;
import com.chzu.vo.OrgVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationsServiceImp implements OrganizationsService {

    public JwtUser jwtUser;

    @Autowired
    private OrganizationsMapper organizationsMapper;

    @Override
    public R<List<OrgVo>> getOrgInfo() {
        jwtUser = (JwtUser) SecurityUtils.getSubject().getPrincipal();
        if (jwtUser.getIsAdmin()) {
            List<Organizations> orgList = organizationsMapper.getOrgInfo();
            if (orgList.size() != 0) return R.buildR(Status.OK, "success", OrgVo.convertToOrgVoList(orgList));
        }
        return R.buildR(Status.SYSTEM_ERROR);
    }
}
