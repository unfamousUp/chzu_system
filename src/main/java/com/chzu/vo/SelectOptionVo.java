package com.chzu.vo;

import com.chzu.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectOptionVo {

    private Integer userId;

    private String fullName;

    public SelectOptionVo(User user){
        this.userId = user.getUserId();
        this.fullName = user.getFullName();
    }

    // 将 List<User> 转换为 List<SelectOptionVo> 的静态方法
    public static List<SelectOptionVo> convertToSelectOptionList(List<User> userList) {
        List<SelectOptionVo> selectOptionList = new ArrayList<>();

        for (User user : userList) {
            selectOptionList.add(new SelectOptionVo(user));
        }

        return selectOptionList;
    }

}
