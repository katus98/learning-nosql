package com.katus.demo;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

/**
 * Jedis管道操作
 *
 * @author SUN Katus
 * @version 1.0, 2021-08-06
 */
public class Demo5 {
    /**
     * 无管道操作
     */
    @Test
    public void noPipeline() {
        // 生成连接池
        JedisPool jedisPool = new JedisPool("10.79.231.86", 6379);
        Jedis jedis = jedisPool.getResource();

        jedis.auth("skrv587");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            jedis.incr("pp");
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        jedis.close();
    }

    /**
     * 管道操作
     */
    @Test
    public void pipeline() {
        // 生成连接池
        JedisPool jedisPool = new JedisPool("10.79.231.86", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.auth("skrv587");

        Pipeline pipelined = jedis.pipelined();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            pipelined.incr("pp");
        }
        pipelined.sync();
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        jedis.close();
    }
}
