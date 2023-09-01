package com.chzu.service;

import com.chzu.entity.User;
import com.chzu.utils.R;
import com.chzu.vo.SelectOptionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    User getUserInfoByUsername(String username);

    R<SelectOptionVo> getUserByUserId(Integer userId);

    R<List<SelectOptionVo>> getUserByOrgId(Integer orgId);

    List<String> getUserPermissionInfo(List<String> roles);

    R<User> getUserInfoByOrgId(Integer orgId);
}
