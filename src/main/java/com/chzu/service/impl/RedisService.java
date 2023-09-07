package com.chzu.service.impl;

import com.chzu.utils.R;
import com.chzu.utils.Status;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisService {

    private static final String USER_TEXTAREA_PREFIX = "user:textarea:"; // Redis 中的键前缀
    private static final String USER_SUGGESTION_PREFIX = "user:suggestion:"; // Redis 中的键前缀

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveUserContent(Integer userId, String content) {
        // 构建用户特定的键名称
        String userKey = USER_TEXTAREA_PREFIX + userId;
        log.info("userKey:{}",userKey);
        log.info("content:{}",content);
        // 将用户内容存储到 Redis，以用户 ID 区分
        redisTemplate.opsForValue().set(userKey, content);
    }

    public void saveSuggestion(Integer userId, String suggestion) {
        // 构建用户特定的键名称
        String userKey = USER_SUGGESTION_PREFIX + userId;
        log.info("userKey:{}",userKey);
        log.info("content:{}",suggestion);
        // 将用户内容存储到 Redis，以用户 ID 区分
        redisTemplate.opsForValue().set(userKey, suggestion);
    }


    public R<String> getUserContent(Integer userId) {
        // 构建用户特定的键名称
        String userKey = USER_TEXTAREA_PREFIX + userId;
        log.info("userKey:{}",userKey);
        // 从 Redis 中根据用户 ID 获取用户内容
        return R.buildR(Status.OK,"success.redis",redisTemplate.opsForValue().get(userKey));
    }

    public R<String> getSuggestion(Integer userId) {
        // 构建用户特定的键名称
        String userKey = USER_SUGGESTION_PREFIX + userId;
        log.info("userKey:{}",userKey);
        // 从 Redis 中根据用户 ID 获取用户内容
        return R.buildR(Status.OK,"success.redis",redisTemplate.opsForValue().get(userKey));
    }

}
