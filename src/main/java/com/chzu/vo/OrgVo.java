package com.chzu.vo;

import com.chzu.entity.Events;
import com.chzu.entity.Organizations;

import java.util.ArrayList;
import java.util.List;

public class OrgVo extends Organizations {

    public OrgVo(Integer orgId, String orgName, String orgDomainName, String orgType) {
        super(orgId, orgName, orgDomainName, orgType);
    }

    public OrgVo(Organizations org) {
        super(org.getOrgId(), org.getOrgName(), org.getOrgDomainName(), org.getOrgType());
    }


    // 将 List<Organizations> 转换为 List<OrgVo> 的静态方法
    public static List<OrgVo> convertToOrgVoList(List<Organizations> orgList) {
        List<OrgVo> orgVoList  = new ArrayList<>();
        for (Organizations org : orgList) {
            orgVoList.add(new OrgVo(org));
        }
        return orgVoList;
    }
}
