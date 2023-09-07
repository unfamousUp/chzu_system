package com.chzu;

import com.alibaba.fastjson.JSON;
import com.chzu.entity.User;
import com.chzu.service.impl.RedisService;
import com.chzu.utils.R;
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
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisService redisService;

    @Test
    void testString(){
        // stringRedisTemplate.opsForValue().set("name","Chenjiujia");
        //
        // Object name = stringRedisTemplate.opsForValue().get("name");
        // log.info("{}",name);
        // redisService.saveUserContent(2,"aaaa");
        // R<String> userContent = redisService.getUserContent(2);
        // redisTemplate.opsForValue().set("A:B:test:key3","value3");
        redisService.saveUserContent(101,"textareaaa");
        R<String> userContent = redisService.getUserContent(101);
        log.info("{}",userContent);
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
