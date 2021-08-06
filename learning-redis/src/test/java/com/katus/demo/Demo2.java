package com.katus.demo;

import com.katus.entity.User;
import org.junit.Test;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;

import java.util.Date;

/**
 * Jedis通过字节数组的方式存取对象
 * 使用spring-context中的序列化和反序列化方法 (实际没必要)
 *
 * @author SUN Katus
 * @version 1.0, 2021-07-30
 */
public class Demo2 {
    /**
     * 存储对象
     */
    @Test
    public void save() {
        Jedis jedis = new Jedis("10.79.231.86", 6379);
        jedis.auth("skrv587");

        String key = "user";
        User value = new User(1, "katus", new Date());

        byte[] keyBytes = SerializationUtils.serialize(key);
        byte[] valueBytes = SerializationUtils.serialize(value);

        jedis.set(keyBytes, valueBytes);
        jedis.close();
    }

    /**
     * 读取对象
     */
    @Test
    public void load() {
        Jedis jedis = new Jedis("10.79.231.86", 6379);
        jedis.auth("skrv587");

        String key = "user";
        byte[] keyBytes = SerializationUtils.serialize(key);

        byte[] valueBytes = jedis.get(keyBytes);
        User value = (User) SerializationUtils.deserialize(valueBytes);
        System.out.println(value);
        jedis.close();
    }
}
