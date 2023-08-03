package com.chzu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chzu.mapper.UserMapper;
import com.chzu.entity.User;
import com.chzu.service.UserService;
import com.chzu.utils.R;
import com.chzu.utils.Status;
import com.chzu.vo.SelectOptionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public R<SelectOptionVo> getUserByUserId(Integer userId) {
        User user = userMapper.getUserByUserId(userId);
        if(user!=null){
            return R.buildR(Status.OK, "success", new SelectOptionVo(user));
        }
        return R.buildR(Status.SYSTEM_ERROR);
    }

    @Override
    public R<List<SelectOptionVo>> getUserByOrgId(Integer orgId) {
        List<User> userList = userMapper.getUserByOrgId(orgId);
        if(userList.size()!=0){
            List<SelectOptionVo> voList = SelectOptionVo.convertToSelectOptionList(userList);
            return R.buildR(Status.OK, "success", voList);
        }
        return R.buildR(Status.SYSTEM_ERROR);
    }

    @Override
    public User getUserInfoByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public List<String> getUserPermissionInfo(List<String> roles) {
        return userMapper.getUserPermissionInfo(roles);
    }
}
