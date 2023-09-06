package com.chzu.service.impl;

import com.chzu.utils.R;
import com.chzu.utils.Status;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private static final String USER_TEXTAREA_PREFIX = "com:chzu:user:userTextarea:"; // Redis 中的键前缀

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveUserContent(Integer userId, String content) {
        // 构建用户特定的键名称
        String userKey = USER_TEXTAREA_PREFIX + userId;

        // 将用户内容存储到 Redis，以用户 ID 区分
        redisTemplate.opsForValue().set(userKey, content);
    }

    public R<String> getUserContent(Integer userId) {
        // 构建用户特定的键名称
        String userKey = USER_TEXTAREA_PREFIX + userId;

        // 从 Redis 中根据用户 ID 获取用户内容
        return R.buildR(Status.OK,"success.redis",redisTemplate.opsForValue().get(userKey));
    }

}
