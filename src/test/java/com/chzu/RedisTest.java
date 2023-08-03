package com.chzu;

import com.alibaba.fastjson.JSON;
import com.chzu.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testString(){
        stringRedisTemplate.opsForValue().set("name","Chenjiujia");

        Object name = stringRedisTemplate.opsForValue().get("name");
        log.info("{}",name);
    }

    @Test
    void testSaveUser(){
        // 创建对象
        User user = new User();
        user.setUserId(1);
        String userJson = JSON.toJSONString(user);
        log.info("{},",userJson);
        stringRedisTemplate.opsForValue().set("user:100",userJson);
        String json = stringRedisTemplate.opsForValue().get("user:100");
        User sUser = JSON.parseObject(json, User.class);
        System.out.println("user:"+sUser);
    }

    @Test
    void testHash(){
        stringRedisTemplate.opsForHash().put("user:400","name","chenjiujia");
        stringRedisTemplate.opsForHash().put("user:400","age","20");

        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:400");
        log.info("{},",entries);
    }

}
