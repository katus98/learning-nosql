package com.katus.demo;

import com.alibaba.fastjson.JSON;
import com.katus.entity.User;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Date;

/**
 * Jedis通过JSON字符串的方式存取对象
 *
 * @author SUN Katus
 * @version 1.0, 2021-08-06
 */
public class Demo3 {
    /**
     * 存储对象
     */
    @Test
    public void save() {
        Jedis jedis = new Jedis("10.79.231.86", 6379);
        jedis.auth("skrv587");

        String key = "user2";
        User value = new User(2, "pang", new Date());

        String stringValue = JSON.toJSONString(value);

        jedis.set(key, stringValue);
        jedis.close();
    }

    /**
     * 读取对象
     */
    @Test
    public void load() {
        Jedis jedis = new Jedis("10.79.231.86", 6379);
        jedis.auth("skrv587");

        String key = "user2";
        String jsonValue = jedis.get(key);
        User value = JSON.parseObject(jsonValue, User.class);

        System.out.println(value);
        jedis.close();
    }
}
