package com.chzu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chzu.dto.UserLoginDTO;
import com.chzu.mapper.UserMapper;
import com.chzu.entity.User;
import com.chzu.service.LoginService;
import com.chzu.utils.Code;
import com.chzu.utils.Result;
import com.chzu.vo.UserLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<UserLoginVo> login(UserLoginDTO userLoginDTO) {
        Result<UserLoginVo> R = new Result<>();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",userLoginDTO.getUsername());
        User user = userMapper.selectOne(wrapper);
        if(!Objects.isNull(user)){
            // R.setData(new UserLoginVo(user.getUserId(),user.getUsername(),""));
            R.setCode(Code.success);
            return R;
        }
        R.setCode(Code.error);
        return R;
    }

}
