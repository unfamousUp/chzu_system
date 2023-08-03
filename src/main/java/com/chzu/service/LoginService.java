package com.chzu.service;

import com.chzu.dto.UserLoginDTO;
import com.chzu.utils.Result;
import com.chzu.vo.UserLoginVo;

public interface LoginService {
    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    public Result<UserLoginVo> login(UserLoginDTO userLoginDTO);
}
