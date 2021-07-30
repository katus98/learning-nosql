package com.katus.demo;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Redis简单操作
 *
 * @author SUN Katus
 * @version 1.0, 2021-07-30
 */
public class Demo1 {
    /**
     * Jedis方法名与Redis命令完全一致, 几乎没有学习成本
     */
    @Test
    public void test() {
        Jedis jedis = new Jedis("10.79.231.86", 6379);
        jedis.auth("skrv587");
        jedis.set("jedis_name", "katus");
        System.out.println(jedis.get("jedis_name"));
        jedis.close();
    }
}
