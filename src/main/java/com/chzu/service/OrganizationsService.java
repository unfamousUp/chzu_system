package com.chzu.service;

import com.chzu.utils.R;
import com.chzu.vo.OrgVo;

import java.util.List;

public interface OrganizationsService {
    R<List<OrgVo>> getOrgInfo();
}
