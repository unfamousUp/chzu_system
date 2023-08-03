// package com.chzu.utils.redis;
//
// import redis.clients.jedis.Jedis;
// import redis.clients.jedis.JedisPool;
// import redis.clients.jedis.JedisPoolConfig;
//
// public class JedisConnectionFactory {
//
//     private static final JedisPool jedisPool;
//
//     static {
//         // 配置redis连接池
//         JedisPoolConfig poolConfig = new JedisPoolConfig();
//         poolConfig.setMaxTotal(8); // 最大连接数
//         poolConfig.setMaxIdle(8); // 最大空闲连接
//         poolConfig.setMinIdle(0); // 最小空闲连接
//         poolConfig.setMaxWaitMillis(1000); // 等待1000ms，看是否有空闲连接
//         // 创建连接池对象
//         jedisPool = new JedisPool(poolConfig,"192.168.244.128",6379,1000,"123456");
//     }
//
//     public static Jedis getJedis(){
//         return jedisPool.getResource();
//     }
//
// }
