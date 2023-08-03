package com.chzu.config;

import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * 用户自定义的配置类和@Bean会被优先加载
 */
@Configuration
public class RedisConfig {

    // @Bean
    // public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
    //     // 创建RedisTemplate对象
    //     RedisTemplate<String, Object> template = new RedisTemplate<>();
    //     // 设置连接工厂
    //     template.setConnectionFactory(redisConnectionFactory);
    //     // 创建JSON序列化工具
    //     GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
    //     // 设置Key的序列化
    //     template.setKeySerializer(RedisSerializer.string());
    //     template.setHashKeySerializer(RedisSerializer.string());
    //     // 设置value的序列化
    //     template.setValueSerializer(jsonRedisSerializer);
    //     template.setHashValueSerializer(jsonRedisSerializer);
    //     // 返回
    //     return template;
    // }

}
