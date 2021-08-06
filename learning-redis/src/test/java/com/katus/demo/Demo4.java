package com.katus.demo;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 通过Jedis连接池获取Jedis连接
 *
 * @author SUN Katus
 * @version 1.0, 2021-08-06
 */
public class Demo4 {
    /**
     * 简单连接池
     */
    @Test
    public void simple() {
        // 生成连接池
        JedisPool jedisPool = new JedisPool("10.79.231.86", 6379);
        Jedis jedis = jedisPool.getResource();

        jedis.auth("skrv587");
        String user2 = jedis.get("user2");
        System.out.println("user2: " + user2);

        jedis.close();
    }

    /**
     * 配置连接池
     */
    @Test
    public void complex() {
        // 构建连接池配置
        GenericObjectPoolConfig<?> poolConfig = new GenericObjectPoolConfig<>();
        // 最大活跃数
        poolConfig.setMaxTotal(100);
        // 最大空闲数
        poolConfig.setMaxIdle(10);
        // 最小空闲数
        poolConfig.setMinIdle(5);
        // 当连接池空了之后, 多久没获取到Jedis对象则超时
        poolConfig.setMaxWaitMillis(3000);
        // 构建连接池时直接指定密码
        JedisPool jedisPool = new JedisPool(poolConfig, "10.79.231.86", 6379, 3000, "skrv587");
        Jedis jedis = jedisPool.getResource();

        String user2 = jedis.get("user2");
        System.out.println("user2: " + user2);

        jedis.close();
    }
}
